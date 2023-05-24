package package_proiect;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataLoader {
    private static List<Customer> customers; // Store the loaded customers
    private static List<Event> events; // Store the loaded events

    public static void initializeData() {
        Connection connection = null;
        try {

            connection = DatabaseManager.getInstance().getConnection();


            if (!tablesExist(connection)) {
                // Initialising Tables
                DatabaseInitializer.createTables(connection);
            }

            // Load objects from the database
            customers = loadCustomers(connection);

            List<Venue> venues = loadVenues(connection);
            events = loadEvents(connection, venues);
            List<Ticket> tickets = loadTickets(connection, events, customers);

            // Display the loaded objects for debug

        /*    System.out.println("Customers:");
            for (Customer customer : customers) {
                System.out.println(customer);
            }

            System.out.println("Venues:");
            for (Venue venue : venues) {
                System.out.println(venue);
            }

            System.out.println("Events:");
            for (Event event : events) {
                System.out.println(event);
            }

            System.out.println("Tickets:");
            for (Ticket ticket : tickets) {
                System.out.println(ticket);
            } */

        } catch (SQLException e) {
            System.out.println("Error occurred while loading data: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error occurred while closing connection: " + e.getMessage());
                }
            }
        }
    }

    private static boolean tablesExist(Connection connection) throws SQLException {
        boolean tablesExist = false;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE tablename='customer')");
        if (resultSet.next()) {
            tablesExist = resultSet.getBoolean(1);
        }
        resultSet.close();
        statement.close();
        return tablesExist;
    }

    private static List<Customer> loadCustomers(Connection connection) throws SQLException {
        List<Customer> loadedCustomers = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            int phoneNumber = resultSet.getInt("phoneNumber");
            Customer customer = new Customer(name, email, phoneNumber);
            loadedCustomers.add(customer);
        }
        resultSet.close();
        statement.close();

        // Assign the loaded customers to parse the customers
        customers = loadedCustomers;

        return customers;
    }

    public static List<Customer> getCustomers() {
        return customers;
    }

    private static List<Venue> loadVenues(Connection connection) throws SQLException {
        List<Venue> venues = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM venue");
        while (resultSet.next()) {
            int venueId = resultSet.getInt("venueId");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            int capacity = resultSet.getInt("capacity");
            Venue venue = new Venue(venueId, name, address, capacity);
            venues.add(venue);
        }
        resultSet.close();
        statement.close();
        return venues;
    }

    private static List<Event> loadEvents(Connection connection, List<Venue> venues) throws SQLException {
        List<Event> loadedEvents = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM event");
        while (resultSet.next()) {
            int eventId = resultSet.getInt("eventId");
            String name = resultSet.getString("name");
            Date eventDate = resultSet.getDate("eventdate");
            double price = resultSet.getDouble("price");
            int venueId = resultSet.getInt("venueId");

            // Finding venu object
            Venue venue = null;
            for (Venue v : venues) {
                if (v.getVenueId() == venueId) {
                    venue = v;
                    break;
                }
            }

            if (venue != null) {
                Event event = new Event(eventId, name, eventDate, price, venue);
                loadedEvents.add(event);
            }
        }
        resultSet.close();
        statement.close();

        // Assign the loaded events for parsing
        events = loadedEvents;

        return events;
    }

    public static List<Event> getEvents() {
        return events;
    }

    private static List<Ticket> loadTickets(Connection connection, List<Event> events, List<Customer> customers) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ticket");
        while (resultSet.next()) {
            int ticketId = resultSet.getInt("ticketId");
            double price = resultSet.getDouble("price");
            int eventId = resultSet.getInt("eventId");
            String email = resultSet.getString("email");
            Date purchaseDate = resultSet.getDate("purchaseDate");

            // Find the corresponding event and customer objects
            Event event = null;
            for (Event e : events) {
                if (e.getEventId() == eventId) {
                    event = e;
                    break;
                }
            }

            Customer customer = null;
            for (Customer c : customers) {
                if (c.getEmail().equals(email)) {
                    customer = c;
                    break;
                }
            }

            if (event != null && customer != null) {
                Ticket ticket = new Ticket(ticketId, price, event, customer, (java.sql.Date) purchaseDate);
                tickets.add(ticket);
            }
        }
        resultSet.close();
        statement.close();
        return tickets;
    }
}

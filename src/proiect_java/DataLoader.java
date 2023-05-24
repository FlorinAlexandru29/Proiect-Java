package proiect_java;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataLoader {

    public static void initializeData() {
        Connection connection = null;
        try {
            // Get a database connection
            connection = DatabaseManager.getInstance().getConnection();

            // Check if tables exist
            if (!tablesExist(connection)) {
                // Tables don't exist, initialize them
                DatabaseInitializer.createTables(connection);
            }

            // Load objects from the database
            List<Customer> customers = loadCustomers(connection);
            List<Venue> venues = loadVenues(connection);
            List<Event> events = loadEvents(connection);
            List<Ticket> tickets = loadTickets(connection, events);

            // Display the loaded objects or use them as required

            System.out.println("Customers:");
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
            }

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
        List<Customer> customers = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
        while (resultSet.next()) {
            int cnp = resultSet.getInt("CNP");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            int phoneNumber = resultSet.getInt("phoneNumber");
            Customer customer = new Customer(cnp, name, email, phoneNumber);
            customers.add(customer);
        }
        resultSet.close();
        statement.close();
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

    private static List<Event> loadEvents(Connection connection) throws SQLException {
        List<Event> events = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM event");
        while (resultSet.next()) {
            int eventId = resultSet.getInt("eventId");
            String name = resultSet.getString("name");
            Date eventDate = resultSet.getDate("eventdate");
            double price = resultSet.getDouble("price");
            int venueId = resultSet.getInt("venueId");

            // Fetch the venue for the event
            Venue venue = fetchVenue(connection, venueId);

            Event event = new Event(eventId, name, eventDate, price, venue);
            events.add(event);
        }
        resultSet.close();
        statement.close();
        return events;
    }

    private static Venue fetchVenue(Connection connection, int venueId) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM venue WHERE venueId = " + venueId);
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            int capacity = resultSet.getInt("capacity");
            return new Venue(venueId, name, address, capacity);
        }
        resultSet.close();
        statement.close();
        return null;
    }

    private static List<Ticket> loadTickets(Connection connection, List<Event> events) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM ticket";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int ticketId = resultSet.getInt("ticketId");
                int seatNumber = resultSet.getInt("seatNumber");
                int eventId = resultSet.getInt("eventId");
                int customerID = resultSet.getInt("customerID");
                java.sql.Date purchaseDate = resultSet.getDate("purchaseDate");

                // Find the corresponding event for the ticket
                Event event = findEvent(events, eventId);

                // Get the ticket price from the associated event
                double price = event.getTicketPrice();

                // Create the Ticket object and add it to the list
                Ticket ticket = new Ticket(ticketId, seatNumber, event, customerID, purchaseDate);
                tickets.add(ticket);
            }
        }

        return tickets;
    }

    private static Event findEvent(List<Event> events, int eventId) {
        for (Event event : events) {
            if (event.getEventId() == eventId) {
                return event;
            }
        }
        return null;
    }
}

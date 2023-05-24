package package_proiect;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

public class InsertCommand implements EventManager {
    private BufferedReader reader;

    public InsertCommand(BufferedReader reader) {
        this.reader = reader;
    }

    public void execute() {
        try {
            System.out.print("Enter table name: ");
            String tableName = reader.readLine().toLowerCase();

            switch (tableName) {
                case "customer":
                    // Gather customer data
                    System.out.print("Enter customer name: ");
                    String name = reader.readLine();
                    System.out.print("Enter customer email: ");
                    String email = reader.readLine();
                    System.out.print("Enter customer phone number: ");
                    int phoneNumber = Integer.parseInt(reader.readLine());

                    // Insert customer into the database
                    DataInsertion.insertCustomer(DatabaseManager.getInstance().getConnection(), name, email, phoneNumber);

                    System.out.println("Customer inserted successfully.");
                    DataLoader.initializeData();
                    break;

                case "venue":
                    // Gather venue data
                    System.out.print("Enter venue name: ");
                    String venueName = reader.readLine();
                    System.out.print("Enter venue address: ");
                    String address = reader.readLine();
                    System.out.print("Enter venue capacity: ");
                    int capacity = Integer.parseInt(reader.readLine());

                    // Insert venue into the database
                    DataInsertion.insertVenue(DatabaseManager.getInstance().getConnection(), venueName, address, capacity);

                    System.out.println("Venue inserted successfully.");
                    DataLoader.initializeData();
                    break;

                case "event":
                    // Gather event data
                    System.out.print("Enter event name: ");
                    String eventName = reader.readLine();
                    System.out.print("Enter event date (YYYY-MM-DD): ");
                    String eventDateStr = reader.readLine();
                    java.sql.Date eventDate = java.sql.Date.valueOf(eventDateStr);
                    System.out.print("Enter event price: ");
                    double price = Double.parseDouble(reader.readLine());
                    System.out.print("Enter venue ID: ");
                    int venueId = Integer.parseInt(reader.readLine());

                    // Insert event into the database
                    DataInsertion.insertEvent(DatabaseManager.getInstance().getConnection(), eventName, eventDate, price, venueId);

                    System.out.println("Event inserted successfully.");
                    DataLoader.initializeData();
                    break;

                case "ticket":
                    // Gather ticket data
                    System.out.print("Enter ticket ID: ");
                    int ticketId = Integer.parseInt(reader.readLine());
                    System.out.print("Enter ticket price: ");
                    double ticketPrice = Double.parseDouble(reader.readLine());
                    System.out.print("Enter seat number: ");
                    int seatNumber = Integer.parseInt(reader.readLine());
                    System.out.print("Enter event ID: ");
                    int eventId = Integer.parseInt(reader.readLine());
                    System.out.print("Enter customer email: ");
                    String emaill = reader.readLine();
                    System.out.print("Enter purchase date (YYYY-MM-DD): ");
                    String purchaseDateStr = reader.readLine();
                    java.sql.Date purchaseDate = java.sql.Date.valueOf(purchaseDateStr);

                    // Insert ticket into the database
                    DataInsertion.insertTicket(DatabaseManager.getInstance().getConnection(), ticketPrice, eventId, emaill, purchaseDate);

                    System.out.println("Ticket inserted successfully.");
                    DataLoader.initializeData();
                    break;

                default:
                    System.out.println("Invalid table name.");
                    break;
            }
        } catch (IOException | SQLException e) {
            System.out.println("Error occurred during insert: " + e.getMessage());
        }
    }
}

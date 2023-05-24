package proiect_java;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void createTables(Connection connection) {
        try {
            Statement statement = connection.createStatement();

            // Create Customer table
            String createCustomerTableQuery = "CREATE TABLE IF NOT EXISTS Customer (" +
                    "CNP INT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "email VARCHAR(50) NOT NULL, " +
                    "phoneNumber INT NOT NULL" +
                    ")";
            statement.executeUpdate(createCustomerTableQuery);

            // Create Venue table
            String createVenueTableQuery = "CREATE TABLE IF NOT EXISTS Venue (" +
                    "venueId SERIAL PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "address VARCHAR(100) NOT NULL, " +
                    "capacity INT NOT NULL" +
                    ")";
            statement.executeUpdate(createVenueTableQuery);

            // Create Event table
            String createEventTableQuery = "CREATE TABLE IF NOT EXISTS Event (" +
                    "eventId SERIAL PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "eventdate DATE NOT NULL, " +
                    "price DOUBLE PRECISION NOT NULL, " +
                    "venueId INT REFERENCES Venue(venueId)" +
                    ")";
            statement.executeUpdate(createEventTableQuery);

            // Create Ticket table
            String createTicketTableQuery = "CREATE TABLE IF NOT EXISTS Ticket (" +
                    "ticketId SERIAL PRIMARY KEY, " +
                    "price DOUBLE PRECISION NOT NULL, " +
                    "seatNumber INT NOT NULL, " +
                    "eventId INT REFERENCES Event(eventId), " +
                    "customerId INT REFERENCES Customer(CNP), " +
                    "purchaseDate DATE NOT NULL" +
                    ")";
            statement.executeUpdate(createTicketTableQuery);

            System.out.println("Tables created successfully.");

        } catch (SQLException e) {
            System.out.println("Failed to create tables: " + e.getMessage());
        }
    }
}
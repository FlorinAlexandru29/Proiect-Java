package package_proiect;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void createTables(Connection connection) {
        try {
            Statement statement = connection.createStatement();

            // Create Customer table
            String createCustomerTableQuery = "CREATE TABLE IF NOT EXISTS Customer (" +
                    "name VARCHAR(50) NOT NULL, " +
                    "email VARCHAR(50) PRIMARY KEY, " +
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
                    "eventId INT REFERENCES Event(eventId), " +
                    "email VARCHAR(50) REFERENCES Customer(email), " +
                    "purchaseDate DATE NOT NULL" +
                    ")";
            statement.executeUpdate(createTicketTableQuery);

            String createExecutionTableQuery = "CREATE TABLE IF NOT EXISTS Execution (" +
                    "date timestamp without time zone NOT NULL, " +
                    "eveniment VARCHAR(50) NOT NULL " +
                    ")";
            statement.executeUpdate(createExecutionTableQuery);

            System.out.println("Tables created successfully.");

        } catch (SQLException e) {
            System.out.println("Failed to create tables: " + e.getMessage());
        }
    }
}
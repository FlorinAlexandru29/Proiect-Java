package package_proiect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataInsertion {

    public static void insertCustomer(Connection connection,String name, String email, int phoneNumber) throws SQLException {
        String sql = "INSERT INTO customer (name, email, phonenumber) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setInt(3, phoneNumber);
            statement.executeUpdate();
        }
    }

    public static void insertVenue(Connection connection, String venueName, String address, int capacity) throws SQLException {
        String sql = "INSERT INTO venue (name, address, capacity) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, venueName);
            statement.setString(2, address);
            statement.setInt(3, capacity);
            statement.executeUpdate();
        }
    }

    public static void insertEvent(Connection connection, String eventName, java.sql.Date eventDate, double price, int venueId) throws SQLException {
        String sql = "INSERT INTO event (name, eventdate, price, venueid) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, eventName);
            statement.setDate(2, eventDate);
            statement.setDouble(3, price);
            statement.setInt(4, venueId);
            statement.executeUpdate();
        }
    }

    public static void insertTicket(Connection connection, double price, int eventId, String email, java.sql.Date purchaseDate) throws SQLException {
        String sql = "INSERT INTO ticket (price, eventid, email, purchasedate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, price);
            statement.setInt(2, eventId);
            statement.setString(3, email);
            statement.setDate(4, purchaseDate);
            statement.executeUpdate();
        }
    }


}

package proiect_java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataInsertion {

    public static void insertCustomer(Connection connection, int CNP, String name, String email, int phoneNumber) throws SQLException {
        String sql = "INSERT INTO customer (CNP, name, email, phonenumber) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, CNP);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setInt(4, phoneNumber);
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

    public static void insertTicket(Connection connection, double price, int seatNumber, int eventId, int customerID, java.sql.Date purchaseDate) throws SQLException {
        String sql = "INSERT INTO ticket (price, seatnumber, eventid, customerid, purchasedate) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, price);
            statement.setInt(2, seatNumber);
            statement.setInt(3, eventId);
            statement.setInt(4, customerID);
            statement.setDate(5, purchaseDate);
            statement.executeUpdate();
        }
    }


}

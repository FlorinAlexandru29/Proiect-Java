package package_proiect;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public interface EventManager {

    void execute();
}

class Exit implements EventManager {
   public void execute(){
        System.exit(0);
    }
        }
class InitialiseDB implements EventManager {
    private DatabaseManager databaseManager;

    public InitialiseDB(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void execute() {
        try {
            Connection connection = databaseManager.getConnection();
            DatabaseInitializer.createTables(connection);
            DatabaseManager.closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("Failed to execute database initialization: " + e.getMessage());
        }
    }
}

class HelpCommand implements EventManager {
    private Map<String, EventManager> commands;

    public HelpCommand(Map<String, EventManager> commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        // Display all available commands
        System.out.println("Available commands:");
        for (String command : commands.keySet()) {
            System.out.println(command);
        }
    }
}


class CreateAccountCommand implements EventManager{
    private BufferedReader reader;

    public CreateAccountCommand(BufferedReader reader) {
        this.reader = reader;
    }

    public void execute() {
        try {
            System.out.print("Enter customer name: ");
            String name = reader.readLine();
            System.out.print("Enter customer email: ");
            String email = reader.readLine();
            System.out.print("Enter customer phone number: ");
            int phoneNumber = Integer.parseInt(reader.readLine());

            // Insert customer
            DataInsertion.insertCustomer(DatabaseManager.getInstance().getConnection(), name, email, phoneNumber);

            System.out.println("Customer account created successfully.");
            DataInsertion.insertexecution(DatabaseManager.getInstance().getConnection(),"createaccount");
            // Reload the customers from the database
            DataLoader.initializeData();
        } catch (IOException | SQLException e) {
            System.out.println("Error occurred during account creation: " + e.getMessage());
        }
    }
}

class ShowEventsCommand implements EventManager {
    public List<Event> events;

    public ShowEventsCommand(List<Event> events) {
        this.events = events;
    }

    public ShowEventsCommand() {

    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public void execute() {
        if (events.isEmpty()) {
            System.out.println("No events available.");
        } else {
            System.out.println("Events:");
            java.util.Date currentDate = new java.util.Date();
            for (Event event : events) {
                if (event.getDate().compareTo(currentDate) > 0) {
                    System.out.println(event);
                }
            }
        }
    }
}

class BuyTicket extends ShowEventsCommand implements EventManager{
    private BufferedReader reader;
    public BuyTicket (BufferedReader reader) {
        this.reader = reader;
    }
    public void setEvents(List<Event> events) {
        this.events = events;
    }
    @Override
    public void execute() {
        if (LoggedUser.getInstance().getUserEmail()!=null){
        java.util.Date currentDate = new java.util.Date();
        for (Event event : events) {

            if (event.getDate().compareTo(currentDate) > 0) {
                System.out.println(event);
            }
        }
        System.out.println("Select An Event");
        try {
            String selectedeventname = reader.readLine().toLowerCase();
            System.out.println(selectedeventname);
            for (Event event : events) {
                if (event.getName().toLowerCase().equals(selectedeventname)) {
                    try{
                        String dateString = "2023-04-23";
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        try{ java.util.Date utilDate = dateFormat.parse(dateString);
                            Date sqlDate = new Date(utilDate.getTime());


                            DataInsertion.insertTicket(DatabaseManager.getInstance().getConnection(), event.getTicketPrice(),event.getEventId(),LoggedUser.getInstance().getUserEmail(), sqlDate);
                            System.out.println("Cumparat");
                            DataInsertion.insertexecution(DatabaseManager.getInstance().getConnection(),"buyticket");
                            DataLoader.initializeData();
                        }
                        catch (ParseException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    catch (SQLException e) {
                        System.out.println("Error occurred during account creation: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
        else System.out.println("You are not logged. Please connect using the login command");

}}
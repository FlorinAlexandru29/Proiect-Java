package package_proiect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            // Insert customer into the database
            DataInsertion.insertCustomer(DatabaseManager.getInstance().getConnection(), name, email, phoneNumber);

            System.out.println("Customer account created successfully.");

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
            Date currentDate = new Date();
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
    public BuyTicket() {
        super();
    }
    public void setEvents(List<Event> events) {
        this.events = events;
    }
    @Override
    public void execute() {
        Date currentDate = new Date();
        for (Event event : events) {

            if (event.getDate().compareTo(currentDate) > 0) {
                System.out.println(event);
            }
        }
        System.out.println("Select An Event");
        try {
            String selectedeventname = reader.readLine().toLowerCase();
            for (Event event : events) {

                if (event.getName()==selectedeventname) {
                    System.out.println("Cumparat");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
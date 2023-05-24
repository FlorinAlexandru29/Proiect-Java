package package_proiect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {
    private Map<String, EventManager> commands;
    private BufferedReader reader;
    private boolean loggedIn;
    private boolean isAdmin;
    private List<Customer> customers;
    private List<Event> events;

    public Menu() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        commands = new HashMap<>();
        loggedIn = false;
        isAdmin = false;
        customers = new ArrayList<>(); // Initialize customers list
        events=new ArrayList<>();
        setupCommands();
    }

    private void setupCommands() {
        commands.put("exit", new Exit());
        commands.put("help", new HelpCommand(commands));
        commands.put("login", new LoginCommand(reader, customers)); // Pass customers to LoginCommand
        commands.put("createaccount", new CreateAccountCommand(reader)); // Pass customers to CreateAccountCommand

        // Normal user commands

        commands.put("showevents", new ShowEventsCommand(events));
        commands.put("insert", new InsertCommand(reader));
        commands.put("buyticket", new BuyTicket(reader));
       /* commands.put("mytickets", new MyTicketsCommand());


        // Admin commands
        commands.put("admin", new AdminCommand());
        commands.put("initialisedb", new InitialiseDB(DatabaseManager.getInstance()));
        */
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    public void setEvents(List<Event> events) {
        this.events = events;
    }



    public void executeCommand(String commandName) {
        EventManager command = commands.get(commandName);
        if (command instanceof LoginCommand) {
            LoginCommand loginCommand = (LoginCommand) command;
            loginCommand.setCustomers(customers); // Pass customers to LoginCommand
            loggedIn = true;
            isAdmin = false;
        } /* else if (command instanceof AdminCommand) {
            if (loggedIn && isAdmin) {
                isAdmin = true;
            } else {
                System.out.println("Invalid command. You don't have permission to access admin commands.");
                return;
            }
        }*/

        if (command != null) {
            if (command instanceof ShowEventsCommand){
            ShowEventsCommand showevents = (ShowEventsCommand) command;
            showevents.setEvents(events);
            command.execute();}
            else {
                command.execute();}
        } else {
            System.out.println("Invalid command. Enter 'help' to display the full list of commands.");
        }
    }

    public void run() {
        try {
            DataLoader.initializeData(); // Load data using DataLoader
            customers = DataLoader.getCustomers(); // Get the loaded customers
            setCustomers(customers); // Pass customers to Menu class
            events = DataLoader.getEvents(); // Get the loaded customers
            setEvents(events);
            while (true) {
                System.out.print("Enter a command: ");
                String command = reader.readLine();
                executeCommand(command);

                if (loggedIn && command.equals("logout")) {
                    loggedIn = false;
                    isAdmin = false;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading input: " + e.getMessage());
        }
    }
}
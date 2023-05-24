package proiect_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class Menu {
    private Map<String, EventManager> commands;
    private BufferedReader reader;

    public class Menu {
        private Map<String, EventManager> commands;
        private BufferedReader reader;
        private boolean loggedIn;

        public Menu() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            commands = new HashMap<>();
            loggedIn = false;
            setupCommands();
        }

        private void setupCommands() {
            commands.put("exit", new Exit());
            commands.put("help", new HelpCommand(commands));
            commands.put("initialisedb", new InitialiseDB(DatabaseManager.getInstance()));
            commands.put("insert", new InsertCommand(reader));
            commands.put("admin", new AdminCommand());
            commands.put("userlogin", new UserLoginCommand());
            commands.put("createaccount", new CreateAccountCommand());
            commands.put("showevents", new ShowEventsCommand());
            commands.put("buyticket", new BuyTicketCommand());
            // Add other commands
        }

        public void executeCommand(String commandName) {
            EventManager command = commands.get(commandName);
            if (command != null) {
                command.execute();
            } else {
                System.out.println("Invalid command. Enter 'help' to display the full list of commands.");
            }
        }

        public void run() {
            try {
                while (true) {
                    System.out.print("Enter a command: ");
                    String command = reader.readLine();
                    executeCommand(command);
                }
            } catch (IOException e) {
                System.out.println("An error occurred while reading input: " + e.getMessage());
            }
        }
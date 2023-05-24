package proiect_java;

import java.sql.Connection;
import java.sql.SQLException;
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


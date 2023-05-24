package proiect_java;

public class UserManager {
    private static UserManager instance;
    private User loggedUser;

    private UserManager() {
        // Private constructor to prevent instantiation from outside the class
    }

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User user) {
        loggedUser = user;
    }
}
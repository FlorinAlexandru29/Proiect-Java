package package_proiect;

public class LoggedUser {
    private static LoggedUser instance;
    private String userEmail;

    private LoggedUser() {
        // Private constructor to prevent instantiation from outside the class
    }

    public static LoggedUser getInstance() {
        if (instance == null) {
            instance = new LoggedUser();
        }
        return instance;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}

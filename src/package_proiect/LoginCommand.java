package package_proiect;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class LoginCommand implements EventManager {
    private BufferedReader reader;
    private List<Customer> customers;

    public LoginCommand(BufferedReader reader, List<Customer> customers) {
        this.reader = reader;
        this.customers = customers;
    }
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    @Override
    public void execute() {
        System.out.print("Enter email: ");
        try {
            String email = reader.readLine();
            if (isEmailValid(email)) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid email. Login failed.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading input: " + e.getMessage());
        }
    }

    private boolean isEmailValid(String email) {
        if (customers != null) {
            for (Customer customer : customers) {
                System.out.println("Comparing input email: '" + email + "' with customer email: '" + customer.getEmail() + "'");
                if (customer.getEmail().equals(email)) {
                    return true;
                }
            }
        }
        return false;
    }

}

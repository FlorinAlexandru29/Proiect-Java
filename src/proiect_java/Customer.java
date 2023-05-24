package proiect_java;

public class Customer {
    private int CNP;
    private String name;
    private String email;
    private int phoneNumber;

    public Customer(int CNP, String name, String email, int phoneNumber) {
        this.CNP = CNP;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return "Customer [CNP=" + CNP + ", Name=" + name + ", Email=" + email + ", PhoneNumber=" + phoneNumber + "]";
    }

    // Getters and setters
    public int getCNP() {
        return CNP;
    }

    public void setCNP(int CNP) {
        this.CNP = CNP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

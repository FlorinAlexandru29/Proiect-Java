package package_proiect;

public class Venue {
    private int venueId;
    private String name;
    private String address;
    private int capacity;

    public Venue(int venueId, String name, String address, int capacity) {
        this.venueId = venueId;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }
    @Override
    public String toString() {
        return "Venue [VenueId=" + venueId + ", Name=" + name + ", Address=" + address + ", Capacity=" + capacity + "]";
    }
    // Getters and setters
    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}


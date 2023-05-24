package package_proiect;
import java.util.Date;


public class Event {
    private int eventId;
    private String name;
    private Date date;
    private double ticketPrice;

    private Venue venue;

    public Event(int eventId, String name, Date date, double ticketPrice, Venue venue) {
        this.eventId = eventId;
        this.name = name;
        this.date = date;
        this.ticketPrice = ticketPrice;
        this.venue = venue;
    }
    @Override
    public String toString() {
        return "Name=" + name + ", EventDate=" + date + ", Price=" + ticketPrice + ", Venue=" + venue.getName();
    }

    // Getters and setters
    public Venue getVenue() {
        return venue;
    }
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}


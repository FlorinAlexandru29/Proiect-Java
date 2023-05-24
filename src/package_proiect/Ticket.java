package package_proiect;

import java.sql.Date;

public class Ticket {
    private int ticketId;
    private double ticketPrice;
    private int seatNumber;
    private Event event;
    private Customer customer;
    private Date purchaseDate;

    public Ticket(int ticketId, double ticketPrice, int seatNumber, Event event, Customer customer, Date purchaseDate) {
        this.ticketId = ticketId;
        this.ticketPrice = ticketPrice;
        this.seatNumber = seatNumber;
        this.event = event;
        this.customer = customer;
        this.purchaseDate = purchaseDate;
    }
    @Override
    public String toString() {
        return "Ticket [TicketId=" + ticketId + ", SeatNumber=" + seatNumber + ", Event=" + event + ", Customer=" + customer + ", PurchaseDate=" + purchaseDate + "]";
    }

    // Getters and setters

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}

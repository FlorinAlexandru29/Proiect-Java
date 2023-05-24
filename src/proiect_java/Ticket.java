package proiect_java;

import java.sql.Date;

public class Ticket {
    private int ticketId;
    private int seatNumber;
    private Event event;
    private int customerID;
    private Date purchaseDate;

    public Ticket(int ticketId, int seatNumber, Event event, int customerID, Date purchaseDate) {
        this.ticketId = ticketId;
        this.seatNumber = seatNumber;
        this.event = event;
        this.customerID = customerID;
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "Ticket [TicketId=" + ticketId + ", SeatNumber=" + seatNumber + ", Event=" + event + ", CustomerID=" + customerID + ", PurchaseDate=" + purchaseDate + "]";
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

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}

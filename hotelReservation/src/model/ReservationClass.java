package model;

import java.util.Date;
import java.util.Objects;

public class ReservationClass {
    CustomerClass customer;
    Iroom room;
    Date checkInDate;
    Date checkOutDate;

    public ReservationClass(CustomerClass customer, Iroom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    // constructor for reservation class

    public Date getCheckInDate() {
        return this.checkInDate;
    }

    public Date getCheckOutDate() {
        return this.checkOutDate;
    }

    public CustomerClass getCustomer() {
        return this.customer;
    }

    public Iroom getRoom() {
        return this.room;
    }

    public boolean isRoomReserved(Date checkInDate, Date checkOutDate) {
        if (checkInDate.before(this.checkOutDate) && checkOutDate.after(this.checkInDate)) {
            return true;
        }
        return false;
    }
    // can only reserved during 1 period at a time

    @Override
    public String toString() {
        return "Customer: " + this.customer + " Room: " + this.room + " Checkin Date: " + this.checkInDate + " Checkout Date: " + checkOutDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationClass)) return false;
        ReservationClass Reserved = (ReservationClass) o;
        return customer.equals(Reserved.customer) && room.equals(Reserved.room) && checkInDate.equals(Reserved.checkInDate) && checkOutDate.equals(Reserved.checkOutDate);
    }
    // similar to the code in customer class, go to that page to check

    public int hashCode() {
        return Objects.hash(customer, room, checkInDate, checkOutDate);
    }
    // similar to the code in customer class, go to that page to check
}

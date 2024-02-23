package model;

import java.util.Objects;

public class RoomClass implements Iroom {

    protected String roomNumber;
    protected Double price;
    protected RoomTypeEnumeration enumeration;

    public RoomClass(String roomNumber, Double price, RoomTypeEnumeration enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public double getRoomPrice() {
        return this.price;
    }

    @Override
    public RoomTypeEnumeration getRoomType() {

        return this.enumeration;
    }

    @Override
    public boolean isFree() {
        if (this.price == (double) 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Room Number: " + this.roomNumber + " Price: " + this.price + " Room Type: " + this.enumeration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomClass)) return false;
        RoomClass room = (RoomClass) o;
        return roomNumber.equals(room.roomNumber) && price.equals(room.price) && enumeration == room.enumeration;
    }
    // similar to the code in customer class, go to that page to check

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, price, enumeration);
    }
    // similar to the code in customer class, go to that page to check
}

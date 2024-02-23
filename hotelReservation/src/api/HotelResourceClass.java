package api;

import model.CustomerClass;
import model.Iroom;
import model.ReservationClass;
import service.CustomerServiceClass;
import service.ReservationServiceClass;

import java.util.Collection;
import java.util.Date;

public class HotelResourceClass {
    public static CustomerClass getCustomer(String email){
        return CustomerServiceClass.getCustomer(email);
    }

    public static void createACustomer(String email, String firstName, String lastName){
        CustomerServiceClass.addCustomer(email, firstName, lastName);
    }

    public static Iroom getRoom(String roomNumber){
        return ReservationServiceClass.getRoom(roomNumber);
    }

    public static ReservationClass bookARoom(String customerEmail, Iroom room, Date checkInDate, Date checkOutDate){
        CustomerClass customer = CustomerServiceClass.getCustomer(customerEmail);
        return ReservationServiceClass.reserveRoom(customer, room, checkInDate, checkOutDate);
    }
    // ask for the user email and allow them to make a reservation

    public static Collection<ReservationClass> getCustomersReservations(String customerEmail){
        CustomerClass customer = CustomerServiceClass.getCustomer(customerEmail);
        return ReservationServiceClass.getCustomerReservations(customer);
    }

    public static Collection<Iroom> findARoom(Date checkIn, Date checkOut){
        return ReservationServiceClass.findRooms(checkIn, checkOut);
    }
}

package api;

import model.CustomerClass;
import model.Iroom;
import model.ReservationClass;
import service.CustomerServiceClass;
import service.ReservationServiceClass;

import java.util.Collection;
import java.util.List;

public class AdminResourceClass {
    public static CustomerClass getCustomer(String email){
        return CustomerServiceClass.getCustomer(email);
    }

    public static void addRoom(Iroom rooms){
        ReservationServiceClass.addRoom(rooms);
    }
    public static Collection<Iroom> getAllRooms(){
        return ReservationServiceClass.getAllRooms();
    }

    public static Collection<CustomerClass> getAllCustomers(){
        return CustomerServiceClass.getAllCustomer();
    }

    public static Collection<ReservationClass> displayAllReservations(){
       return ReservationServiceClass.getAllReservations();
    }
}

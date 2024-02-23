package service;

import model.*;

import java.util.*;

public class ReservationServiceClass {
    private static final Map<String, Iroom> RoomMap = new HashMap<String, Iroom>();
    private static final Map<String, Collection<ReservationClass>> ReserveMap = new HashMap<String, Collection<ReservationClass>>();

    public static void addRoom(Iroom room){
        RoomMap.put(room.getRoomNumber(), room);
    }
    //use interface Iroom as room to insert roomnumber into room map

    public static Iroom getRoom(String roomID){
        return RoomMap.get(roomID);
    }
    //user interface Iroom to get a roomID in Room map

    public static ReservationClass reserveRoom(CustomerClass customer, Iroom room, Date checkInDate, Date checkOutDate){
        if (isRoomReserved(room, checkInDate, checkOutDate)) {
            return null;
        }
        ReservationClass reservation = new ReservationClass(customer, room, checkInDate, checkOutDate);
        Collection<ReservationClass> customerReservations = getCustomerReservations(customer);
        if (customerReservations == null) {
            customerReservations = new LinkedList<>();
        }
        customerReservations.add(reservation);
        ReserveMap.put(customer.getEmail(), customerReservations);
        return reservation;
    }
    //get the email of the customer and check if the customer have reserve a room before or not, if null then create new linked list then add the reservation detail in to the map which contain the key which is the user email then return the reservation
    public static Collection<Iroom> findRooms(Date checkInDate, Date checkOutDate){
        Collection<Iroom> reservedRooms = getAllReservedRooms(checkInDate, checkOutDate);
        Collection<Iroom> availableRooms = new LinkedList<>();
        for (Iroom room : getAllRooms()) {
            if (!reservedRooms.contains(room)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
    // loop through all the room in the hotel, if the room is not contain in reservedRooms collection, add in available rooms collection which display which room are still available and not already been reserved
    public static Collection<ReservationClass> getCustomerReservations(CustomerClass customer) {
        return ReserveMap.get(customer.getEmail());
    }
    // get the email which is the key of the reserveMap so it return the value of the ReserveMap collection

    public static Collection<ReservationClass> getAllReservations() {
        Collection<ReservationClass> allReservations = new LinkedList<>();
        for (Collection<ReservationClass> customerReservations : ReserveMap.values()) {
            allReservations.addAll(customerReservations);
        }
        return allReservations;
    }
    // loop through all the values of the reserveMap map by assigning the customerReservations with the reserve map value and add all the content in the reserve map into reservation linked list, then return the list
    public static Collection<Iroom> getAllRooms() {
        return RoomMap.values();
    }
    //return all the value(room number, price, roomtype,...) of the Roommap collection

    private static Collection<Iroom> getAllReservedRooms(Date checkInDate, Date checkOutDate) {
        Collection<Iroom> reservedRooms = new LinkedList<>();
        for (ReservationClass reservation : getAllReservations()) {
            if (reservation.isRoomReserved(checkInDate, checkOutDate)) {
                reservedRooms.add(reservation.getRoom());
            }
        }
        return reservedRooms;
    }
    //create reservedRooms linked list and use loop it through all reservation and store all the value one by one in object reservation and check the dates by isRoomReserved(in customer class), if check correct then add into reserve room linked list else return null
    private static boolean isRoomReserved(Iroom room, Date checkInDate, Date checkOutDate) {
        Collection<Iroom> reservedRooms = getAllReservedRooms(checkInDate, checkOutDate);
        if (reservedRooms.contains(room)) {
            return true;
        }
        return false;
    }
    // creating a reserved room collection and
    // check to see if room is reserved, return true if room (which is room number) is contain in reservedRoom collection, return false if room is not reserve (not contain room number in collection)
}

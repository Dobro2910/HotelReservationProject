import api.AdminResourceClass;
import api.HotelResourceClass;
import model.*;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class AdminMenu {
    public static void displayAdminMenu() {
        System.out.println("                AdminMenu                 ");
        System.out.println("------------------------------------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        //System.out.println("5. Add Test Data");
        System.out.println("5. Back to Main Menu");
        System.out.println("------------------------------------------");
        System.out.println("Please select a number for the menu option");
    }
    public static boolean executeOption(Scanner scanner, Integer selection) {
        boolean keepAdminRunning = true;
        switch (selection) {
            case 1:
                getAllCustomers();
                break;
            case 2:
                getAllRooms();
                break;
            case 3:
                getAllReservations();
                break;
            case 4:
                addRooms(scanner);
                break;
           // case 5:
             //   addTestData();
               // break;
            case 5:
                keepAdminRunning = false;
                break;
            default:
                System.out.println("Please enter a number between 1 and 6\n");
        }
        return keepAdminRunning;
    }

    private static void getAllCustomers() {
        Collection<CustomerClass> allCustomers = AdminResourceClass.getAllCustomers();
        if (allCustomers.isEmpty()) {
            System.out.println("There are no customers");
        }
        else {
            for (CustomerClass customer : allCustomers) {
                System.out.println(customer.toString());
            }
        }
        System.out.println();
    }

    private static void getAllRooms() {
        Collection<Iroom> allRooms = AdminResourceClass.getAllRooms();
        if (allRooms.isEmpty()) {
            System.out.println("There are no rooms");
        }
        else {
            for (Iroom room : allRooms) {
                System.out.println(room.toString());
            }
        }
        System.out.println();
    }

    private static void getAllReservations(){
        Collection<ReservationClass> allReservations = AdminResourceClass.displayAllReservations();
        if(allReservations.isEmpty()){
            System.out.println("There are no Reservations");
        }
        else{
            for (ReservationClass reservation : allReservations){
                System.out.println(reservation.toString());
            }
        }
        System.out.println();
    }

    private static void addRooms(Scanner scanner) {
        boolean keepAddingRoom;
        do {
            addRoom(scanner);
            System.out.println("would you like to create another room? y for yes and other character for no");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
                keepAddingRoom = true;
            }
            else{
                keepAddingRoom = false;
            }
        }while (keepAddingRoom);
    }

    private static void addRoom(Scanner scanner){
        String roomNum = null;
        boolean validRoomNum = false;
        while(!validRoomNum) {
            System.out.println("Please enter a room number");
            roomNum = scanner.nextLine();
            Iroom roomExists = HotelResourceClass.getRoom(roomNum);
            if (roomExists == null) { // room doesn't exists, continue
                validRoomNum = true;
            }
            else{
                System.out.println("That room already exist, press y to update it or other character to enter a new room");
                String choice = scanner.next();
                if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
                    validRoomNum = true;
                }
                else{
                    validRoomNum = false;
                }
            }
        }
        double price = 0.0;
        boolean validPrice = false;
        while(!validPrice) {
            try{
                System.out.println("Please enter a price per night");
                price = Double.parseDouble(scanner.nextLine());
                if (price < 0){
                    System.out.println("Please enter a price larger than 0");
                }
                else {
                    validPrice = true;
                }
            }
            catch(Exception e){
                System.out.println("Please enter a valid price");
            }
        }
        RoomTypeEnumeration roomType = null;
        boolean validRoomType = false;
        while(!validRoomType){
            try{
                System.out.println("Please enter a RoomType");
                roomType = RoomTypeEnumeration.valueForNumberOfBeds(Integer.parseInt(scanner.nextLine()));
                if (roomType == null){
                    System.out.println("invalid RoomType");
                }
                else{
                    validRoomType = true;
                }
            }
            catch(Exception e){
                System.out.println("invalid RoomType");
            }
        }
        RoomClass newRoom = new RoomClass(roomNum, price, roomType);
        AdminResourceClass.addRoom(newRoom);
    }
}

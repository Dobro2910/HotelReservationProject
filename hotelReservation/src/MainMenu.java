import api.HotelResourceClass;
import model.CustomerClass;
import model.Iroom;
import model.ReservationClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

        public static void displayMenu() {
            System.out.println("                Main Menu                  ");
            System.out.println("Please create an account first if you dont have one");
            System.out.println("------------------------------------------");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an Account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            System.out.println("------------------------------------------");
            System.out.println("Please select a number for the menu option");
        }

        public static boolean Option(Scanner scanner, Integer option) {
            boolean keepRunning = true;
            switch (option) {
                case 1:
                    findReserveRoom(scanner);
                    break;
                case 2:
                    getCustomerReservations(scanner);
                    break;
                case 3:
                    createAccount(scanner);
                    break;
                case 4:
                    AdminMenu(scanner);
                    break;
                case 5:
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Please select a number between 1 and 5");

            }
            return keepRunning;
        }

        private static void AdminMenu(Scanner scanner) {
            boolean keepAdminRunning = true;
            while (keepAdminRunning) {
                try {
                    AdminMenu.displayAdminMenu();
                    int adminSelection = Integer.parseInt(scanner.nextLine());
                    // parse int turns string into integer
                    keepAdminRunning = AdminMenu.executeOption(scanner, adminSelection);
                }
                catch (Exception ex) {
                    System.out.println("Please enter a number between 1 and 6\n");
                }
            }
        }

    public static void findReserveRoom(Scanner scanner) {
            // ask for the checkin and checkout date and display the available room if the user want to book
            Date checkInDate = getCheckInDate(scanner);
            Date checkOutDate = getCheckOutDate(scanner, checkInDate);

            Collection<Iroom> availableRooms = HotelResourceClass.findARoom(checkInDate, checkOutDate);
            boolean toBook = false;
            if (availableRooms.isEmpty()){
                Date newCheckInDate = getRecommendedDate(checkInDate);
                Date newCheckOutDate = getRecommendedDate(checkOutDate);
                availableRooms = HotelResourceClass.findARoom(newCheckInDate, newCheckOutDate);
                if (!availableRooms.isEmpty()){
                    System.out.println("There are no available room at current date, room will be available at checkin date: " + newCheckInDate + " and check out date: " + newCheckOutDate);
                    toBook = showAvailableRoomsAndAskToBook(scanner, availableRooms);
                }
                // ask user to book if there are room available at a later date
                else{
                    System.out.println("there are no available room");
                }
            }
            else {
                System.out.println("available room at check in: " + checkInDate + " check out: " + checkOutDate);
                toBook = showAvailableRoomsAndAskToBook(scanner, availableRooms);
            }
            if (!toBook) {
                return;
            }
            CustomerClass customer = getCustomerForReservation(scanner);
            if (customer == null) {
                System.out.println("Sorry, no account exists for that email");
                return;
            }
            //validate the email

            Iroom room = getRoomForReservation(scanner, availableRooms);
            ReservationClass reservation = HotelResourceClass.bookARoom(customer.getEmail(), room, checkInDate, checkOutDate);
            if (reservation == null) {
                System.out.println("Couldn't process your booking, the room is not available"); // this shouldn't happen as we validated that the room is available previously
            } else {
                System.out.println("Thank you! Your room was booked successfully!");
                System.out.println(reservation);
            }
        }

    private static Date getCheckInDate(Scanner scanner){
        SimpleDateFormat Date1 = new SimpleDateFormat("MM/dd/yyyy");
        Date checkInDate = null;
        boolean validCheckInDate = false;
        while(!validCheckInDate){
            System.out.println("Please enter Check In date in format MM/dd/yyyy");
           String inputCheckInDate = scanner.nextLine();
           try{
               checkInDate = Date1.parse(inputCheckInDate);
               Date today = new Date();
               if (checkInDate.before(today)){
                   System.out.println("Check In date cannot be before today");
               }
               else{
                   validCheckInDate = true;
               }
           }
           catch(ParseException e){
               System.out.println("Invalid, Please enter in format MM/dd/yyyy");
            }
        }
        return checkInDate;
    }

    private static Date getCheckOutDate(Scanner scanner, Date checkInDate){
        SimpleDateFormat Date2 = new SimpleDateFormat("MM/dd/yyyy");
        Date checkOutDate = null;
        boolean validCheckOutDate = false;
        while (!validCheckOutDate){
            System.out.println("Please enter a Check Out date format MM/dd/yyyy");
            String inputCheckOutDate = scanner.nextLine();
            try{
                checkOutDate = Date2.parse(inputCheckOutDate);
                if(checkOutDate.before(checkInDate)){
                    System.out.println("Check Out dat cant be before Check In Date");
                }
                else{
                    validCheckOutDate = true;
                }
            }
            catch(ParseException e){
                System.out.println("Invalid, Please enter in format MM/dd/yyyy");
            }
        }
        return checkOutDate;
    }

    private static Date getRecommendedDate(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 7);
        return c.getTime();
    }
    // getInstance = to get the instance of calendar according to current time zone set by java Runtime environment.
    // this method is use to add 7 more days to the current booking date

    private static boolean showAvailableRoomsAndAskToBook(Scanner scanner, Collection<Iroom> availableRooms) {
        for (Iroom room : availableRooms){
            System.out.println(room.toString());
        }
        System.out.println(" ");
        System.out.println("Would you like to book? y for yes and any other key for no");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
            return true;
        }
        return false;
    }
    // loop through available room and showing all the rooms, then ask the user if they want to book a room or not

    private static CustomerClass getCustomerForReservation(Scanner scanner){
            String email;
            boolean account = false;
            System.out.println("Do you have an account with us?, press y for yes and any other key for no");
            String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
            account = true;
        }
        if (account = true){
            System.out.println("Please enter email");
            email = scanner.nextLine();
        }
        else{
            email = createAccount(scanner);
        }
        return HotelResourceClass.getCustomer(email);
    }
    // ask if the user has an account or not and let them signup and return the getCustomer (which check if the email is available or not, if not then return null

    private static String createAccount(Scanner scanner) {
        System.out.println("First name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last name: ");
        String lastName = scanner.nextLine();
        String email = null;
        boolean validEmail = false;
        while (!validEmail) {
            try {
                System.out.println("Email (format: name@example.com): ");
                email = scanner.nextLine();
                HotelResourceClass.createACustomer(email, firstName, lastName);
                System.out.println("Account created successfully!\n");
                validEmail = true;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }
        return email;
    }
    // create a new account, return null to later validate the email

    private static void getCustomerReservations(Scanner scanner) {
        System.out.println("Please enter your Email (format: name@example.com): ");
        String email = scanner.nextLine();
        CustomerClass customer = HotelResourceClass.getCustomer(email);
        if (customer == null) {
            System.out.println("Sorry, no account exists for that email");
            return;
        }
        Collection<ReservationClass> reservations = HotelResourceClass.getCustomersReservations(customer.getEmail());
        if (reservations.isEmpty()) {
            System.out.println("You don't have any reservations at the moment");
            return;
        }
        for (ReservationClass reservation : reservations) {
            System.out.println(reservation.toString());
        }
    }

    private static Iroom getRoomForReservation(Scanner scanner, Collection<Iroom> availableRooms) {
        Iroom room = null;
        String roomNumber = null;
        boolean validRoomNumber = false;
        while (!validRoomNumber) {
            System.out.println("What room would you like to reserve? Enter the room number: ");
            roomNumber = scanner.nextLine();
            room = HotelResourceClass.getRoom(roomNumber);
            if (room == null) { // room doesn't exists, ask again check by using the getRoom method
                System.out.println("That room doesn't exists, please enter a valid room number");
            } else { // room exists, validate it's available
                if (!availableRooms.contains(room)) { // room not available, ask again, check if room is available
                    System.out.println("That room is not available, please enter a valid room number");
                } else {
                    validRoomNumber = true;
                }
            }
        }
        return room;
    }

}

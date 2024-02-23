package model;

public class FreeRoomClass extends RoomClass{

    public FreeRoomClass(String roomNumber, RoomTypeEnumeration enumeration) {
        super(roomNumber, (double) 0, enumeration);
    }
    
    @Override
    public String toString() {
        return "Room Number: " + this.roomNumber + " Room Type: " + this.enumeration + " Room Price = 0$";
    }
}

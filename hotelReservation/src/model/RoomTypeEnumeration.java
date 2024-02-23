package model;

import java.util.HashMap;
import java.util.Map;

public enum RoomTypeEnumeration {
    SINGLE (1), DOUBLE(2);
// assigning roomtype enumeration with value 1 = single bed and 2 = double bed

    private int numberOfBeds;

    private static final Map<Integer, RoomTypeEnumeration> NUMBER_OF_BEDS = new HashMap<Integer, RoomTypeEnumeration>();
// hash map doesnt allow duplicate keys but allows duplicates value
    static {
        for (RoomTypeEnumeration roomType : values()) {
            NUMBER_OF_BEDS.put(roomType.numberOfBeds, roomType);
        }
    }
// assigning the roomType the value(single or double) and put into the either key 1 or 2 in the map collection

    RoomTypeEnumeration(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }
//constructor

    public static RoomTypeEnumeration valueForNumberOfBeds(int numberOfBeds) {
        return NUMBER_OF_BEDS.get(numberOfBeds);
    }
// return single of double bed when getting the ket of the map collection number of beds
}

// Room.java
import java.io.Serializable;

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    private int roomNumber;
    private RoomCategory category;
    private double pricePerNight;
    private boolean isBooked;

    public Room(int roomNumber, RoomCategory category, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.pricePerNight = pricePerNight;
        this.isBooked = false; // Initially, all rooms are available
    }

    // Getters
    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomCategory getCategory() {
        return category;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isBooked() {
        return isBooked;
    }

    // Setters
    public void bookRoom() {
        this.isBooked = true;
    }

    public void releaseRoom() {
        this.isBooked = false;
    }

    @Override
    public String toString() {
        return "Room #" + roomNumber +
                " [" + category +
                ", $" + String.format("%.2f", pricePerNight) + "/night, " +
                (isBooked ? "Booked" : "Available") + "]";
    }
}
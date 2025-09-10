// Reservation.java
import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    private int reservationId;
    private String userName;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Reservation(int reservationId, String userName, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.reservationId = reservationId;
        this.userName = userName;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    // Getters
    public int getReservationId() {
        return reservationId;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "--- Reservation Details ---\n" +
                "Reservation ID: " + reservationId + "\n" +
                "Guest Name: " + userName + "\n" +
                "Room: " + room.getRoomNumber() + " (" + room.getCategory() + ")\n" +
                "Check-in: " + checkInDate + "\n" +
                "Check-out: " + checkOutDate + "\n" +
                "--------------------------";
    }
}
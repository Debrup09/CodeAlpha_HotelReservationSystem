// Hotel.java
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Hotel implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Room> rooms;
    private List<Reservation> reservations;
    private int nextReservationId = 1;

    public Hotel() {
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        // Initialize with some default rooms if the list is empty
        initializeRooms();
    }

    private void initializeRooms() {
        // Add rooms only if the list is empty to avoid duplication on load
        if (rooms.isEmpty()) {
            rooms.add(new Room(101, RoomCategory.STANDARD, 100.0));
            rooms.add(new Room(102, RoomCategory.STANDARD, 110.0));
            rooms.add(new Room(201, RoomCategory.DELUXE, 200.0));
            rooms.add(new Room(202, RoomCategory.DELUXE, 210.0));
            rooms.add(new Room(301, RoomCategory.SUITE, 350.0));
        }
    }

    /**
     * Searches for available rooms of a specific category.
     * Note: This is a simplified search that only checks the current booking status.
     */
    public List<Room> searchAvailableRooms(RoomCategory category) {
        return rooms.stream()
                .filter(room -> room.getCategory() == category && !room.isBooked())
                .collect(Collectors.toList());
    }

    /**
     * Makes a reservation for a specific room.
     */
    public Reservation makeReservation(String userName, int roomNumber, LocalDate checkIn, LocalDate checkOut) {
        Optional<Room> roomToBook = rooms.stream()
                .filter(room -> room.getRoomNumber() == roomNumber && !room.isBooked())
                .findFirst();

        if (roomToBook.isPresent()) {
            Room room = roomToBook.get();
            room.bookRoom();
            Reservation reservation = new Reservation(nextReservationId++, userName, room, checkIn, checkOut);
            reservations.add(reservation);
            // Simulate Payment
            simulatePayment(reservation);
            return reservation;
        }
        return null; // Indicates booking failed
    }

    /**
     * Cancels a reservation by its ID.
     */
    public boolean cancelReservation(int reservationId) {
        Optional<Reservation> reservationToCancel = reservations.stream()
                .filter(res -> res.getReservationId() == reservationId)
                .findFirst();

        if (reservationToCancel.isPresent()) {
            Reservation reservation = reservationToCancel.get();
            reservation.getRoom().releaseRoom(); // Make the room available again
            reservations.remove(reservation);
            System.out.println("Reservation ID " + reservationId + " has been successfully canceled.");
            return true;
        }
        System.out.println("Error: Reservation ID " + reservationId + " not found.");
        return false;
    }


    /**
     * Finds and returns details of a booking by its ID.
     */
    public Optional<Reservation> findReservationById(int reservationId) {
        return reservations.stream()
                .filter(res -> res.getReservationId() == reservationId)
                .findFirst();
    }

    private void simulatePayment(Reservation reservation) {
        // In a real system, this would integrate with a payment gateway.
        System.out.println("\nSimulating payment for Reservation ID: " + reservation.getReservationId());
        System.out.println("Payment successful! Your booking is confirmed.");
    }


    /**
     * Saves the current state of the hotel (rooms and reservations) to a file.
     */
    public static void saveData(Hotel hotel, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(hotel);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    /**
     * Loads the hotel state from a file.
     */
    public static Hotel loadData(String filename) {
        File dataFile = new File(filename);
        if (dataFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
                Hotel hotel = (Hotel) ois.readObject();
                System.out.println("Data loaded successfully.");
                return hotel;
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading data: " + e.getMessage());
                return new Hotel(); // Return a new instance on error
            }
        }
        System.out.println("No existing data file found. Starting with a new system.");
        return new Hotel(); // Return a new instance if file doesn't exist
    }
}
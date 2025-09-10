// Main.java
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final String DATA_FILE = "hotel_data.ser";
    private static Hotel hotel = Hotel.loadData(DATA_FILE);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    searchRooms();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewBookingDetails();
                    break;
                case 4:
                    cancelReservation();
                    break;
                case 5:
                    Hotel.saveData(hotel, DATA_FILE);
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n===== Hotel Reservation System =====");
        System.out.println("1. Search for Available Rooms");
        System.out.println("2. Make a Reservation");
        System.out.println("3. View Booking Details");
        System.out.println("4. Cancel Reservation");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next(); // Consume the invalid input
        }
        return scanner.nextInt();
    }

    private static void searchRooms() {
        System.out.print("Enter room category (STANDARD, DELUXE, SUITE): ");
        String categoryStr = scanner.next().toUpperCase();
        try {
            RoomCategory category = RoomCategory.valueOf(categoryStr);
            List<Room> availableRooms = hotel.searchAvailableRooms(category);
            if (availableRooms.isEmpty()) {
                System.out.println("No available rooms in the " + category + " category.");
            } else {
                System.out.println("\n--- Available " + category + " Rooms ---");
                availableRooms.forEach(System.out::println);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category. Please enter STANDARD, DELUXE, or SUITE.");
        }
    }

    private static void makeReservation() {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter your full name: ");
        String userName = scanner.nextLine();

        System.out.print("Enter room number to book: ");
        int roomNumber = scanner.nextInt();

        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        String checkInStr = scanner.next();
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        String checkOutStr = scanner.next();

        try {
            LocalDate checkIn = LocalDate.parse(checkInStr);
            LocalDate checkOut = LocalDate.parse(checkOutStr);

            Reservation reservation = hotel.makeReservation(userName, roomNumber, checkIn, checkOut);
            if (reservation != null) {
                System.out.println("\nReservation Successful!");
                System.out.println(reservation);
            } else {
                System.out.println("\nReservation failed. The room is either booked or does not exist.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    private static void viewBookingDetails() {
        System.out.print("Enter your reservation ID to view details: ");
        int resId = scanner.nextInt();
        Optional<Reservation> reservation = hotel.findReservationById(resId);
        if (reservation.isPresent()) {
            System.out.println(reservation.get());
        } else {
            System.out.println("No reservation found with ID: " + resId);
        }
    }

    private static void cancelReservation() {
        System.out.print("Enter reservation ID to cancel: ");
        int resId = scanner.nextInt();
        hotel.cancelReservation(resId);
    }
}

# 🏨 Hotel Reservation System

A simple, **console-based Hotel Reservation System** built in Java.
This project demonstrates **core Object-Oriented Programming (OOP) principles** and handles **data persistence** through file I/O, making it a robust command-line application.

---

## ✨ Features

* 🔍 **Search Rooms**: Find available rooms by category (`STANDARD`, `DELUXE`, `SUITE`).
* 🏷️ **Make Reservations**: Book a room for a guest with their name and check-in/check-out dates.
* 📖 **View Booking Details**: Retrieve and display reservation details using a unique booking ID.
* ❌ **Cancel Reservations**: Cancel an existing booking (frees up the room automatically).
* 💳 **Simulated Payments**: Includes a confirmation step to simulate a payment process.
* 💾 **Data Persistence**: Room and reservation data is automatically saved (`hotel_data.ser`) and reloaded upon startup.

---

## ⚙️ How It Works

The system is designed with a **clear separation of concerns**, using multiple classes to manage different aspects of hotel operations.

### 🏗️ Core Architecture (OOP)

* **`Main.java`** → Entry point & CLI interface (handles user input/output).
* **`Hotel.java`** → Central engine managing rooms & reservations (business logic).
* **`Room.java`** → Model class for rooms (number, category, price, booking status).
* **`Reservation.java`** → Model class linking guest to a booked room (name, dates, ID).
* **`RoomCategory.java`** → Enum for room categories (`STANDARD`, `DELUXE`, `SUITE`).

---

## 💾 Data Persistence

The application uses **Java Serialization** to save its state.

* **On Startup**

  * If `hotel_data.ser` exists → Deserialize & load saved data.
  * If not → Create new hotel object with default rooms.
* **On Exit**

  * Serialize and save all data to `hotel_data.ser`.

---

## 🛠️ Tech Stack

* **Language**: Java
* **Concepts**:

  * Object-Oriented Programming (Encapsulation, Abstraction)
  * Java Collections Framework
  * File I/O (Object Serialization)

---

## 🚀 Getting Started

Follow these steps to run the project locally.

### ✅ Prerequisites

* Install **Java JDK 8+** on your system.

### 📥 Installation & Execution

```bash
# Clone the repository
git clone <repository-url>

# Navigate to the project directory
cd path/to/project

# Compile all Java files
javac *.java

# Run the application
java Main
```

The **main menu** will appear in your console, and you can start interacting with the system.

---

## 📂 Project Structure

```
.
├── Main.java             # User Interface & Entry Point
├── Hotel.java            # Core Business Logic
├── Room.java             # Room Data Model
├── Reservation.java      # Reservation Data Model
├── RoomCategory.java     # Enum for Room Types
└── hotel_data.ser        # (Generated at runtime) Data file for persistence
```


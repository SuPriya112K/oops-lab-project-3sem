package RideShare;

import java.util.List;
import java.util.Scanner;

/**
 * Interactive CLI for the Ride-Sharing platform.
 *
 * Enhancements: interactive menu, input validation, and basic state inspection.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the RideShare Platform (Interactive CLI)!");

        RideSharePlatform platform = new RideSharePlatform();
        seedData(platform);

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Select an option: ");
            switch (choice) {
                case 1:
                    listVehicles(platform);
                    break;
                case 2:
                    listPassengers(platform);
                    break;
                case 3:
                    addPassengerInteractive(platform);
                    break;
                case 4:
                    addVehicleInteractive(platform);
                    break;
                case 5:
                    bookRideInteractive(platform);
                    break;
                case 6:
                    listRides(platform);
                    break;
                case 7:
                    advanceRideStatusInteractive(platform);
                    break;
                case 8:
                    System.out.println("Exiting. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 8.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== RideShare Menu ===");
        System.out.println("1) List vehicles");
        System.out.println("2) List passengers");
        System.out.println("3) Add passenger");
        System.out.println("4) Add vehicle");
        System.out.println("5) Book ride");
        System.out.println("6) Show ride history");
        System.out.println("7) Advance ride status (arrive/start/complete)");
        System.out.println("8) Exit");
    }

    private static void seedData(RideSharePlatform platform) {
        platform.addVehicle(new Mini("Raju", "KA-01-M-1234", new Location(2, 2)));
        platform.addVehicle(new Sedan("Suresh", "KA-01-S-5678", new Location(5, 5)));
        platform.addVehicle(new AutoRickshaw("Babu", "KA-01-A-9999", new Location(1, 1)));
        platform.addVehicle(new Mini("Priya", "KA-01-M-4321", new Location(10, 10)));

        platform.addPassenger(new Passenger("Amit", "P1001", new Location(0, 0)));
        platform.addPassenger(new Passenger("Sunita", "P1002", new Location(4, 4)));
    }

    private static void listVehicles(RideSharePlatform platform) {
        List<Vehicle> vehicles = platform.getVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles registered.");
            return;
        }
        System.out.println("\n-- Vehicles --");
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            System.out.printf("%d) %s - %s (%s) | Available: %b | Loc: %s\n", i, v.getVehicleType(), v.getDriverName(), v.getLicensePlate(), v.isAvailable(), v.getCurrentLocation());
        }
    }

    private static void listPassengers(RideSharePlatform platform) {
        List<Passenger> passengers = platform.getPassengers();
        if (passengers.isEmpty()) {
            System.out.println("No passengers registered.");
            return;
        }
        System.out.println("\n-- Passengers --");
        for (int i = 0; i < passengers.size(); i++) {
            Passenger p = passengers.get(i);
            System.out.printf("%d) %s (ID: %s) | Loc: %s\n", i, p.getName(), p.getPassengerId(), p.getCurrentLocation());
        }
    }

    private static void addPassengerInteractive(RideSharePlatform platform) {
        System.out.println("\nAdd Passenger:");
        String name = readString("Name: ");
        String id = readString("Passenger ID: ");
        int x = readInt("Location X: ");
        int y = readInt("Location Y: ");
        Passenger p = new Passenger(name, id, new Location(x, y));
        platform.addPassenger(p);
    }

    private static void addVehicleInteractive(RideSharePlatform platform) {
        System.out.println("\nAdd Vehicle:");
        String type = readString("Type (Mini/Sedan/AutoRickshaw): ");
        String driver = readString("Driver name: ");
        String plate = readString("License plate: ");
        int x = readInt("Location X: ");
        int y = readInt("Location Y: ");

        Vehicle v = null;
        Location loc = new Location(x, y);
        if (type.equalsIgnoreCase("Mini")) v = new Mini(driver, plate, loc);
        else if (type.equalsIgnoreCase("Sedan")) v = new Sedan(driver, plate, loc);
        else if (type.equalsIgnoreCase("AutoRickshaw") || type.equalsIgnoreCase("Auto")) v = new AutoRickshaw(driver, plate, loc);
        else System.out.println("Unknown type; vehicle not added.");

        if (v != null) platform.addVehicle(v);
    }

    private static void bookRideInteractive(RideSharePlatform platform) {
        List<Passenger> passengers = platform.getPassengers();
        if (passengers.isEmpty()) {
            System.out.println("No passengers available. Add a passenger first.");
            return;
        }
        listPassengers(platform);
        int pIndex = readInt("Select passenger index: ");
        if (pIndex < 0 || pIndex >= passengers.size()) {
            System.out.println("Invalid passenger index.");
            return;
        }
        Passenger passenger = passengers.get(pIndex);
        int x = readInt("Destination X: ");
        int y = readInt("Destination Y: ");
        String type = readString("Vehicle type (Mini/Sedan/AutoRickshaw): ");
        Ride ride = platform.bookRide(passenger, new Location(x, y), type);
        if (ride != null) {
            System.out.println("Ride booked. You can advance its status from the menu.");
        }
    }

    private static void listRides(RideSharePlatform platform) {
        List<Ride> rides = platform.getRideHistory();
        if (rides.isEmpty()) {
            System.out.println("No rides in history.");
            return;
        }
        System.out.println("\n-- Ride History --");
        for (int i = 0; i < rides.size(); i++) {
            Ride r = rides.get(i);
            System.out.printf("%d) %s -> %s | Driver: %s | Status: %s | Fare: Rs. %.2f\n", i, r.getStartLocation(), r.getEndLocation(), r.getVehicle().getDriverName(), r.getStatus(), r.getFare());
        }
    }

    private static void advanceRideStatusInteractive(RideSharePlatform platform) {
        List<Ride> rides = platform.getRideHistory();
        if (rides.isEmpty()) {
            System.out.println("No rides to advance.");
            return;
        }
        listRides(platform);
        int idx = readInt("Select ride index to advance: ");
        if (idx < 0 || idx >= rides.size()) {
            System.out.println("Invalid ride index.");
            return;
        }
        Ride ride = rides.get(idx);
        switch (ride.getStatus()) {
            case BOOKED:
            case REQUESTED:
                platform.vehicleArrives(ride);
                break;
            case ARRIVED:
                platform.startTrip(ride);
                break;
            case IN_PROGRESS:
                platform.completeRide(ride);
                break;
            case COMPLETED:
                System.out.println("Ride already completed.");
                break;
            default:
                System.out.println("Unknown status.");
        }
    }

    // --- simple input helpers ---
    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine();
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

}

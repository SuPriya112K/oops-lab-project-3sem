package RideShare;

/**
 * Main application class to simulate the Ride-Sharing platform.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the RideShare Platform!");

        // 1. Create the platform
        RideSharePlatform platform = new RideSharePlatform();

        // 2. Add some vehicles (Drivers) to the platform
        platform.addVehicle(new Mini("Raju", "KA-01-M-1234", new Location(2, 2)));
        platform.addVehicle(new Sedan("Suresh", "KA-01-S-5678", new Location(5, 5)));
        platform.addVehicle(new AutoRickshaw("Babu", "KA-01-A-9999", new Location(1, 1)));
        platform.addVehicle(new Mini("Priya", "KA-01-M-4321", new Location(10, 10)));


        // 3. Add a passenger
        Passenger passengerA = new Passenger("Amit", "P1001", new Location(0, 0));
        platform.addPassenger(passengerA);

        // 4. Passenger Amit books a ride
        // He wants to go from (0,0) to (10, 12) and wants a Mini.
        Location destination = new Location(10, 12);
        Ride ride1 = platform.bookRide(passengerA, destination, "Mini");

        if (ride1 != null) {
            // 5. Simulate the ride progression
            
            // Adding a small delay for realism
            try { Thread.sleep(1000); } catch (InterruptedException e) {}

            platform.vehicleArrives(ride1);
            
            try { Thread.sleep(1000); } catch (InterruptedException e) {}

            platform.startTrip(ride1);

            try { Thread.sleep(2000); } catch (InterruptedException e) {}

            platform.completeRide(ride1);
        }

        // 6. Passenger Amit tries to book another ride (Auto)
        // His location is now at his previous destination (10, 12)
        Location newDestination = new Location(20, 15);
        Ride ride2 = platform.bookRide(passengerA, newDestination, "AutoRickshaw");
        
        if (ride2 != null) {
            // Simulate progression for the second ride
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            platform.vehicleArrives(ride2);
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            platform.startTrip(ride2);
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
            platform.completeRide(ride2);
        }

        // 7. Try to book a ride that has no available vehicles
        // Let's assume all Sedans are busy (we only have one, and it's available,
        // but let's book it to make it unavailable)
        
        // We need a new passenger to book the Sedan
        Passenger passengerB = new Passenger("Sunita", "P1002", new Location(4, 4));
        platform.addPassenger(passengerB);
        
        Ride ride3 = platform.bookRide(passengerB, new Location(8, 8), "Sedan");
        
        // Now, if Amit tries to book a Sedan, it should fail
        System.out.println("\n" + passengerA.getName() + " is trying to book a Sedan...");
        Ride ride4 = platform.bookRide(passengerA, new Location(0, 0), "Sedan");
        // This will print the "Booking FAILED" message.

    }
}

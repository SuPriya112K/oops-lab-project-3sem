package RideShare;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The main "controller" class.
 * It manages all the vehicles, passengers, and ride bookings.
 */
public class RideSharePlatform {
    private List<Vehicle> vehicles;
    private List<Passenger> passengers;
    private List<Ride> rideHistory;

    public RideSharePlatform() {
        this.vehicles = new ArrayList<>();
        this.passengers = new ArrayList<>();
        this.rideHistory = new ArrayList<>();
    }

    // Getters to allow UI/CLI to inspect current state
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public List<Ride> getRideHistory() {
        return rideHistory;
    }

    // Methods to add vehicles and passengers to the platform
    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
        System.out.println("Vehicle " + vehicle.getLicensePlate() + " (" + vehicle.getVehicleType() + ") added.");
    }

    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
        System.out.println("Passenger " + passenger.getName() + " added.");
    }

    /**
     * Finds the first available vehicle of the requested type.
     * In a real app, this would also check for the *closest* vehicle.
     * @param vehicleType The type of vehicle (e.g., "Mini", "Sedan").
     * @return An available Vehicle, or null if none are found.
     */
    private Vehicle findAvailableVehicle(String vehicleType) {
        for (Vehicle v : vehicles) {
            if (v.getVehicleType().equalsIgnoreCase(vehicleType) && v.isAvailable()) {
                return v;
            }
        }
        return null; // No available vehicle of this type
    }

    /**
     * The main method for a passenger to book a ride.
     * @param passenger The passenger booking the ride.
     * @param destination The desired destination.
     * @param vehicleType The type of vehicle requested.
     * @return A Ride object if booking is successful, otherwise null.
     */
    public Ride bookRide(Passenger passenger, Location destination, String vehicleType) {
        System.out.println("\nBooking request for " + passenger.getName() + " to " + destination + " in a " + vehicleType);
        
        Vehicle assignedVehicle = findAvailableVehicle(vehicleType);

        if (assignedVehicle == null) {
            System.out.println("Booking FAILED: No available " + vehicleType + " vehicles found.");
            return null;
        }

        // Create the ride
        Ride newRide = new Ride(passenger, assignedVehicle, passenger.getCurrentLocation(), destination);
        
        // Update statuses
        assignedVehicle.setAvailable(false);
        newRide.setStatus(RideStatus.BOOKED);
        
        // Log the ride
        this.rideHistory.add(newRide);

        System.out.println("Booking SUCCESSFUL!");
        newRide.printRideDetails();
        return newRide;
    }

    /**
     * Simulates the vehicle arriving for pickup.
     * @param ride The ride in progress.
     */
    public void vehicleArrives(Ride ride) {
        if (ride != null && ride.getStatus() == RideStatus.BOOKED) {
            ride.setStatus(RideStatus.ARRIVED);
            ride.getVehicle().setCurrentLocation(ride.getStartLocation());
            System.out.println("\nDRIVER: " + ride.getVehicle().getDriverName() + " has arrived at " + ride.getStartLocation());
        }
    }

    /**
     * Simulates the start of the trip.
     * @param ride The ride in progress.
     */
    public void startTrip(Ride ride) {
        if (ride != null && ride.getStatus() == RideStatus.ARRIVED) {
            ride.setStatus(RideStatus.IN_PROGRESS);
            System.out.println("TRIP: Ride for " + ride.getPassenger().getName() + " is now in progress.");
        }
    }

    /**
     * Completes the ride, updates locations, and makes the vehicle available again.
     * @param ride The ride to complete.
     */
    public void completeRide(Ride ride) {
        if (ride != null && ride.getStatus() == RideStatus.IN_PROGRESS) {
            ride.setStatus(RideStatus.COMPLETED);
            
            // Update vehicle and passenger locations
            ride.getVehicle().setCurrentLocation(ride.getEndLocation());
            ride.getPassenger().setCurrentLocation(ride.getEndLocation());

            // Make vehicle available again
            ride.getVehicle().setAvailable(true);

            System.out.println("\nTRIP: Ride for " + ride.getPassenger().getName() + " has COMPLETED.");
            System.out.println("Final Details:");
            ride.printRideDetails();
        }
    }
}
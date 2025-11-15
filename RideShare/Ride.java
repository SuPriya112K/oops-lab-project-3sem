package RideShare;

/**
 * Represents a single ride transaction, linking a Passenger, a Vehicle,
 * and the ride details.
 */
public class Ride {
    private Passenger passenger;
    private Vehicle vehicle;
    private Location startLocation;
    private Location endLocation;
    private double distance;
    private double fare;
    private RideStatus status;

    public Ride(Passenger passenger, Vehicle vehicle, Location startLocation, Location endLocation) {
        this.passenger = passenger;
        this.vehicle = vehicle;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.status = RideStatus.REQUESTED; // Initial status

        // Calculate distance and fare upon creation
        this.distance = Location.calculateDistance(startLocation, endLocation);
        this.fare = vehicle.calculateFare(this.distance);
    }

    // Getters
    public Passenger getPassenger() {
        return passenger;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public double getDistance() {
        return distance;
    }

    public double getFare() {
        return fare;
    }

    public RideStatus getStatus() {
        return status;
    }

    // Setters
    public void setStatus(RideStatus status) {
        this.status = status;
    }

    /**
     * Helper method to print a summary of the ride.
     */
    public void printRideDetails() {
        System.out.println("-------------------------");
        System.out.println("      RIDE DETAILS       ");
        System.out.println("-------------------------");
        System.out.println("Passenger: " + passenger.getName());
        System.out.println("Driver: " + vehicle.getDriverName() + " (" + vehicle.getVehicleType() + ")");
        System.out.println("Vehicle: " + vehicle.getLicensePlate());
        System.out.println("From: " + startLocation);
        System.out.println("To: " + endLocation);
        System.out.printf("Distance: %.2f km\n", distance);
        System.out.printf("Total Fare: Rs. %.2f\n", fare);
        System.out.println("Status: " + status);
        System.out.println("-------------------------");
    }
}
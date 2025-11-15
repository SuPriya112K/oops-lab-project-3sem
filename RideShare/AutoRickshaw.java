package RideShare;

/**
 * Concrete implementation of an "AutoRickshaw" vehicle.
 */
public class AutoRickshaw extends Vehicle {

    private static final double AUTO_RATE = 8.0; // Rs. 8 per km
    private static final double MINIMUM_FARE = 30.0; // Minimum charge

    public AutoRickshaw(String driverName, String licensePlate, Location initialLocation) {
        super(driverName, licensePlate, AUTO_RATE, initialLocation);
    }

    @Override
    public String getVehicleType() {
        return "AutoRickshaw";
    }

    /**
     * Autos also have a minimum fare.
     */
    @Override
    public double calculateFare(double distance) {
        double fare = super.calculateFare(distance);
        return Math.max(fare, MINIMUM_FARE);
    }
}

package RideShare;

/**
 * Concrete implementation of a "Mini" vehicle.
 */
public class Mini extends Vehicle {

    private static final double MINI_RATE = 10.0; // Rs. 10 per km
    private static final double MINIMUM_FARE = 40.0; // Minimum charge

    public Mini(String driverName, String licensePlate, Location initialLocation) {
        super(driverName, licensePlate, MINI_RATE, initialLocation);
    }

    @Override
    public String getVehicleType() {
        return "Mini";
    }

    /**
     * Minis have a special fare calculation with a minimum charge.
     */
    @Override
    public double calculateFare(double distance) {
        double fare = super.calculateFare(distance);
        return Math.max(fare, MINIMUM_FARE);
    }
}

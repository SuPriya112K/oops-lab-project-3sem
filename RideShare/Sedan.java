package RideShare;

/**
 * Concrete implementation of a "Sedan" vehicle.
 */
public class Sedan extends Vehicle {

    private static final double SEDAN_RATE = 14.0; // Rs. 14 per km
    private static final double MINIMUM_FARE = 60.0; // Minimum charge

    public Sedan(String driverName, String licensePlate, Location initialLocation) {
        super(driverName, licensePlate, SEDAN_RATE, initialLocation);
    }

    @Override
    public String getVehicleType() {
        return "Sedan";
    }

    /**
     * Sedans also have a minimum fare.
     */
    @Override
    public double calculateFare(double distance) {
        double fare = super.calculateFare(distance);
        return Math.max(fare, MINIMUM_FARE);
    }
}

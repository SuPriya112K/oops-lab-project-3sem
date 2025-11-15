package RideShare;

/**
 * Abstract base class for all vehicles.
 * It defines the common properties and behaviors that all vehicles must have.
 */
public abstract class Vehicle {
    private String driverName;
    private String licensePlate;
    private boolean isAvailable;
    private Location currentLocation;
    private double baseRatePerKm; // Cost per kilometer

    public Vehicle(String driverName, String licensePlate, double baseRatePerKm, Location initialLocation) {
        this.driverName = driverName;
        this.licensePlate = licensePlate;
        this.baseRatePerKm = baseRatePerKm;
        this.currentLocation = initialLocation;
        this.isAvailable = true; // All vehicles start as available
    }

    // Abstract method to be implemented by all subclasses
    /**
     * Returns the specific type of vehicle (e.g., "Mini", "Sedan").
     * @return Vehicle type as a String.
     */
    public abstract String getVehicleType();

    /**
     * Calculates the fare for a ride of a given distance.
     * Subclasses can override this to add type-specific charges.
     * @param distance The distance of the ride in km.
     * @return The total fare.
     */
    public double calculateFare(double distance) {
        // Base fare calculation
        return baseRatePerKm * distance;
    }

    // Getters
    public String getDriverName() {
        return driverName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public double getBaseRatePerKm() {
        return baseRatePerKm;
    }

    // Setters
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
}

package RideShare;

/**
 * Represents a passenger in the ride-sharing system.
 */
public class Passenger {
    private String name;
    private String passengerId;
    private Location currentLocation;

    public Passenger(String name, String passengerId, Location currentLocation) {
        this.name = name;
        this.passengerId = passengerId;
        this.currentLocation = currentLocation;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    // Setters
    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
}

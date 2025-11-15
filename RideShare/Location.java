package RideShare;

/**
 * A simple helper class to represent a location with x and y coordinates.
 * In a real-world app, this would be latitude and longitude.
 */
public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Calculates the simple Euclidean distance between two locations.
     * @param start The starting location.
     * @param end The ending location.
     * @return The distance.
     */
    public static double calculateDistance(Location start, Location end) {
        int deltaX = end.getX() - start.getX();
        int deltaY = end.getY() - start.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
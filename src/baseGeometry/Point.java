package baseGeometry;//Alon Livne (ID: 208688762)

import myUtill.MyUtilities;

/**
 * Represents a point in a 2D coordinate system with x and y coordinates.
 */
public class Point {
    private double x; // x-coordinate of the point
    private double y; // y-coordinate of the point

    /**
     * Constructs a Point object with the specified x and y coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates and returns the distance between this point and another point.
     *
     * @param other The other point to calculate the distance to.
     * @return The distance between this point and the other point.
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other The other point to compare with.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (MyUtilities.isEqual(this.x, other.x) && MyUtilities.isEqual(this.y, other.y)) {
            return true;
        }
        return false;
    }

    /**
     * Gets the x-coordinate of this point.
     *
     * @return The x-coordinate of this point.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of this point.
     *
     * @return The y-coordinate of this point.
     */
    public double getY() {
        return y;
    }
}

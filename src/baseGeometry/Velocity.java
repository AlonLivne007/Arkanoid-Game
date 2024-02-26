//Alon Livne (ID: 208688762)
package baseGeometry;

/**
 * The Velocity class specifies the change in position on the `x` and `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    // constructor

    /**
     * Constructs a Velocity with the specified changes in x and y.
     *
     * @param dx The change in x-coordinate.
     * @param dy The change in y-coordinate.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates a new Velocity based on an angle and speed.
     *
     * @param angle The angle in degrees.
     * @param speed The speed of the Velocity.
     * @return A new Velocity calculated from the given angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // correct up to be 0 angle and convert to radians
        double angleRadian = Math.toRadians(angle + 270);
        double dx = Math.cos(angleRadian) * speed;
        double dy = Math.sin(angleRadian) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Sets the change in x-coordinate.
     *
     * @param dx The new change in x-coordinate.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets the change in y-coordinate.
     *
     * @param dy The new change in y-coordinate.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Gets the change in x-coordinate.
     *
     * @return The change in x-coordinate.
     */
    public double getDx() {
        return dx;
    }

    /**
     * Gets the change in y-coordinate.
     *
     * @return The change in y-coordinate.
     */
    public double getDy() {
        return dy;
    }

    /**
     * Get the speed of the paddle.
     *
     * @return The speed of the paddle, calculated as the magnitude of the velocity vector.
     */
    public double getSpeed() {
        // Calculate the speed
        return Math.sqrt(this.getDx() * this.getDx() + this.getDy() * this.getDy());
    }

    /**
     * Takes a point with position (x, y) and returns a new point
     * with position (x + dx, y + dy).
     *
     * @param p The Point to which the Velocity is applied.
     * @return A new Point after applying the Velocity to the given Point.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}


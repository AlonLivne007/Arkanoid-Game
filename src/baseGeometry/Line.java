//Alon Livne (ID: 208688762)
package baseGeometry;

import myUtill.MyUtilities;

import java.util.List;

/**
 * Represents a line segment in a 2D coordinate system defined by two points.
 */
public class Line {
    private Point start; // Starting point of the line
    private Point end;   // Ending point of the line

    /**
     * Constructs a Line object with the specified starting and ending points.
     *
     * @param start The starting point of the line.
     * @param end   The ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a Line object with specified coordinates for the starting and ending points.
     *
     * @param x1 The x-coordinate of the starting point.
     * @param y1 The y-coordinate of the starting point.
     * @param x2 The x-coordinate of the ending point.
     * @param y2 The y-coordinate of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        this.start = p1;
        this.end = p2;
    }

    /**
     * Calculates and returns the length of the line.
     *
     * @return The length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return The middle point of the line.
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * Returns the starting point of the line.
     *
     * @return The starting point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the ending point of the line.
     *
     * @return The ending point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Calculates and returns the gradient (slope) of the line.
     *
     * @return The gradient of the line, or null if the line is vertical.
     */
    public Double gradient() {
        if (MyUtilities.isEqual(this.start.getX(), this.end.getX())) {
            return null;
        }
        return ((this.start.getY() - this.end().getY()) / (this.start.getX() - this.end.getX()));
    }

    /**
     * Calculates and returns the free number (y-intercept) of the line.
     *
     * @return The free number of the line, or null if the line is vertical.
     */
    public Double freeNumber() {
        Double a = this.gradient();
        if (a == null) {
            return null;
        }
        return this.start.getY() - this.start.getX() * a;
    }

    /**
     * Checks if this line intersects with another line.
     *
     * @param other The other line to check for intersection.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
//        check if the lines match's and in the same section
        if (this.gradient().equals(this.end.getX()) && this.freeNumber().equals(other.freeNumber())) {
            if (MyUtilities.greaterThanOrEqual(Math.max(this.end.getX(), this.start.getX()),
                    Math.min(other.start.getX(), other.end.getX()))) {
                return true;
            }
            return false;
        }
//        check if there is a valid intersection
        if (intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    /**
     * Checks if this line intersects with two other lines.
     *
     * @param other1 The first line to check for intersection.
     * @param other2 The second line to check for intersection.
     * @return true if this line intersects with both other lines, false otherwise.
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return isIntersecting(other1) && isIntersecting(other2);
    }

    /**
     * Checks if a given point is on the line segment defined by the start and end points.
     *
     * @param p The point to check.
     * @return True if the point is on the line segment, false otherwise.
     */
    public boolean isPointOnline(Point p) {
        // Calculate the distance from the start point to the given point.
        double distance1 = start.distance(p);
        // Calculate the distance from the end point to the given point.
        double distance2 = end.distance(p);
        double length = length();
        // Check if the sum of distances from the start and end points is  equal to the length.
        return MyUtilities.isEqual(distance1 + distance2, length);
    }

    /**
     * Calculates and returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other The other line to find the intersection point with.
     * @return The intersection point if the lines intersect, or null otherwise.
     */
    public Point intersectionWith(Line other) {
        Point intersection;
        Double a1 = this.gradient();
        Double b1 = this.freeNumber();
        Double a2 = other.gradient();
        Double b2 = other.freeNumber();
        double interX;
        double interY;
//        check if both the lines are vertical
        if (a1 == null && a2 == null) {
            return null;
//            check if one line is vertical
        } else if (a1 == null) {
            interX = this.start.getX();
            interY = a2 * interX + b2;
        } else if (a2 == null) {
            interX = other.start.getX();
            interY = a1 * interX + b1;
//            check if the lines are parallels
        } else if (a1.equals(a2)) {
            return null;
        } else {
//            the lines are valid and non verticals
            interX = (b2 - b1) / (a1 - a2);
            interY = a1 * interX + b1;
        }
        intersection = new Point(interX, interY);
//        check if the intersection is both segments
        if (this.isPointOnline(intersection) && other.isPointOnline(intersection)) {
            return intersection;
        }
        return null;
    }

    /**
     * Checks if this line is equal to another line.
     *
     * @param other The other line to compare with.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        } else if (this.start.equals(other.end) && this.end.equals(other.start)) {
            return true;
        }
        return false;
    }


    /**
     * Finds the closest intersection point between the line and a given rectangle.
     * If the line does not intersect with the rectangle, the method returns null.
     * Otherwise, it returns the closest intersection point to the start of the line.
     *
     * @param rect The rectangle with which the line is checked for intersections.
     * @return The closest intersection point to the start of the line, or null if there are no intersections.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // Find intersection points between the line and the rectangle.
        List<Point> intersections = rect.intersectionPoints(this);

        // If no intersections, return null.
        if (intersections.isEmpty()) {
            return null;
        }

        // Variables to track the closest intersection point and its distance.
        Point closest = null;
        double minDistance = -1;

        // Iterate through each intersection point.
        for (Point inter : intersections) {
            // Calculate the distance between the intersection point and the start of the line.
            double distance = inter.distance(this.start);

            // If it's the first iteration or the distance is smaller than the current minimum distance.
            if (minDistance == -1 || MyUtilities.lessThan(distance, minDistance)) {
                // Update the closest intersection point and the minimum distance.
                minDistance = distance;
                closest = inter;
            }
        }

        // Return the closest intersection point.
        return closest;
    }

}

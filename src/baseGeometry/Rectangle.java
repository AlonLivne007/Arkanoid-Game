package baseGeometry;//Alon Livne (ID: 208688762)

import biuoop.DrawSurface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Rectangle class represents a rectangle defined by its width, height, and two corner points (start and end).
 */
public class Rectangle {
    private double width;
    private double height;
    private Point upperLeft;

    private Line leftLine;
    private Line rightLine;
    private Line topLine;
    private Line bottomLine;

    /**
     * Constructs a rectangle with a specified width, height and upperLeft point.
     *
     * @param upperLeft The width of the rectangle.
     * @param width     The width of the rectangle.
     * @param height    The height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
        this.leftLine = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + height);
        this.rightLine = new Line(upperLeft.getX() + width, upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY() + height);
        this.topLine = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY());
        this.bottomLine = new Line(upperLeft.getX(), upperLeft.getY() + height,
                upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**
     * Gets the upperLeft point of the rectangle.
     *
     * @return The upperLeft point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets the width of the rectangle.
     *
     * @return The width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets the height of the rectangle.
     *
     * @return The height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets the left line of the rectangle.
     *
     * @return The left line of the rectangle.
     */
    public Line getLeftLine() {
        return leftLine;
    }

    /**
     * Gets the right line of the rectangle.
     *
     * @return The right line of the rectangle.
     */
    public Line getRightLine() {
        return rightLine;
    }

    /**
     * Gets the top line of the rectangle.
     *
     * @return The top line of the rectangle.
     */
    public Line getTopLine() {
        return topLine;
    }

    /**
     * Gets the bottom line of the rectangle.
     *
     * @return The bottom line of the rectangle.
     */
    public Line getBottomLine() {
        return bottomLine;
    }


    /**
     * Draws a filled rectangle on the given DrawSurface with the specified color.
     *
     * @param surface The DrawSurface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.fillRectangle((int) Math.round(upperLeft.getX()), (int) Math.round(upperLeft.getY()),
                (int) Math.round(getWidth()), (int) Math.round(getHeight()));
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) Math.round(upperLeft.getX()), (int) Math.round(upperLeft.getY()),
                (int) Math.round(getWidth()), (int) Math.round(getHeight()));

    }


    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line .
     * @return The list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<Point>();
        // Check for intersections with each side of the rectangle
        Point inter = rightLine.intersectionWith(line);
        if (inter != null) {
            intersections.add(inter);
        }
        inter = leftLine.intersectionWith(line);
        if (inter != null) {
            intersections.add(inter);
        }
        inter = topLine.intersectionWith(line);
        if (inter != null) {
            intersections.add(inter);
        }
        inter = bottomLine.intersectionWith(line);
        if (inter != null) {
            intersections.add(inter);
        }
        return intersections;
    }

}


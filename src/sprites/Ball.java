//Alon Livne (ID: 208688762)
package sprites;

import baseGeometry.Line;
import baseGeometry.Point;
import baseGeometry.Rectangle;
import baseGeometry.Velocity;
import biuoop.DrawSurface;
import gameFlow.Game;
import gameFlow.GameEnvironment;
import gameFlow.HitListener;
import myUtill.MyUtilities;
import sprites.collidables.CollisionInfo;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Ball class represents a ball in a 2D space.
 */
public class Ball implements Sprite {
    private static final int DEGREE = 360;
    private double size;
    private Point center;
    private GameEnvironment environment;
    private java.awt.Color color;
    private Velocity velocity;
    private List<HitListener> hitListeners;


    // constructors

    /**
     * Constructs a new Ball with a specified center, size, and color.
     *
     * @param center      The center point of the ball.
     * @param size        The size of the ball.
     * @param color       The color of the ball.
     * @param environment The environment of collidables in which ball can collide.
     */
    public Ball(Point center, double size, Color color, GameEnvironment environment) {
        this.size = size;
        this.center = center;
        this.color = color;
        this.setVelocity(0, 0);
        this.environment = environment;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructs a new Ball with specified coordinates, size, and color.
     *
     * @param x           The x-coordinate of the ball's center.
     * @param y           The y-coordinate of the ball's center.
     * @param size        The size of the ball.
     * @param color       The color of the ball.
     * @param environment The environment of collidables in which ball can collide.
     */
    public Ball(double x, double y, double size, Color color, GameEnvironment environment) {
        this.size = size;
        this.color = color;
        this.center = new Point(x, y);
        this.setVelocity(0, 0);
        this.environment = environment;
    }

    // Accessors

    /**
     * Gets the x-coordinate of the ball's center.
     *
     * @return The x-coordinate of the ball's center.
     */
    public int getX() {
        return (int) Math.round(this.center.getX());
    }

    /**
     * Gets the y-coordinate of the ball's center.
     *
     * @return The y-coordinate of the ball's center.
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }

    /**
     * Gets the size of the ball.
     *
     * @return The size of the ball.
     */
    public int getSize() {
        return (int) Math.round(size);
    }

    /**
     * Gets the center point of the ball.
     *
     * @return The center point of the ball.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Gets the color of the ball.
     *
     * @return The color of the ball.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the ball.
     *
     * @param color The new color of the ball.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v The new velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball based on the given components.
     *
     * @param dx The change in x-coordinate per step.
     * @param dy The change in y-coordinate per step.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets the velocity of the ball.
     *
     * @return The velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Sets the velocity of the ball based on its size, a speed-size ratio, and a threshold size.
     *
     * @param speedSizeRatio The speed-size ratio.
     * @param largeBall      The threshold size for a large ball.
     */
    public void setVelocityBySize(double speedSizeRatio, double largeBall) {
        Random rand = new Random();
        double angel = rand.nextDouble(DEGREE);
        double speed;
        if (MyUtilities.greaterThanOrEqual(this.getSize(), largeBall)) {
            speed = speedSizeRatio / largeBall;
        } else {
            speed = speedSizeRatio / this.getSize();
        }

        this.setVelocity(Velocity.fromAngleAndSpeed(angel, speed));
    }

    /**
     * Calculates the trajectory of the ball, represented as a line segment.
     *
     * @return Line representing the trajectory of the ball.
     */
    public Line ballTrajectory() {
        return new Line(center, velocity.applyToPoint(center));
    }


    // Draw the ball on the given DrawSurface

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface The DrawSurface on which to draw the ball.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }


    /**
     * Moves the ball one step based on its current velocity.
     */
    public void moveOneStep() {
        // Check for the closest collision using the ball's trajectory
        CollisionInfo collisionInfo = environment.getClosestCollision(ballTrajectory());

        // If no collision, move the ball to the end of the trajectory
        if (collisionInfo == null) {
            center = getVelocity().applyToPoint(this.center);
        } else {
            // If there is a collision, move the ball just before the collision point
            moveToJustBeforeCollision(collisionInfo.getCollisionObject().getCollisionRectangle(),
                    collisionInfo.getCollisionPoint());

            // Update the ball's velocity based on the collision
            this.setVelocity(collisionInfo.getCollisionObject().hit(this, collisionInfo.getCollisionPoint(), velocity));
        }
    }


    /**
     * Moves the ball just before a collision.
     *
     * @param rect The rectangle with which the ball is colliding.
     * @param p    The collision point on the rectangle's edge.
     */
    private void moveToJustBeforeCollision(Rectangle rect, Point p) {
        // Check if the collision point is on the left line of the rectangle.
        if (rect.getLeftLine().isPointOnline(p)) {
            // Move the ball to a position just before the collision point on the left side.
            this.center = new Point(p.getX() - size, p.getY());
        } else if (rect.getRightLine().isPointOnline(p)) {
            // Check if the collision point is on the right line of the rectangle.
            // Move the ball to a position just before the collision point on the right side.
            this.center = new Point(p.getX() + size, p.getY());
        }

        // Check if the collision point is on the bottom line of the rectangle.
        if (rect.getBottomLine().isPointOnline(p)) {
            // Move the ball to a position just before the collision point on the bottom side.
            this.center = new Point(p.getX(), p.getY() + size);
        } else if (rect.getTopLine().isPointOnline(p)) {
            // Check if the collision point is on the top line of the rectangle.
            // Move the ball to a position just before the collision point on the top side.
            this.center = new Point(p.getX(), p.getY() - size);
        }
    }

    /**
     * Move the ball by one step in the game. This method is called in each frame
     * of the game to update the ball's position based on its velocity.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Adds the ball to the specified game.
     *
     * @param game The Game object to which the sprite should be added.
     */
    @Override
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    /**
     * Removes the ball from the specified game.
     *
     * @param game The Game object from which the ball should be removed.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }
}
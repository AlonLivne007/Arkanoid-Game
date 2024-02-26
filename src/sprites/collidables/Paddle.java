//Alon Livne (ID: 208688762)
package sprites.collidables;

import baseGeometry.Point;
import baseGeometry.Velocity;
import baseGeometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gameFlow.Game;
import myUtill.MyUtilities;
import sprites.Ball;
import sprites.Sprite;

import java.awt.Color;

/**
 * The Paddle class represents the paddle in the game. It implements both the Sprite and Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private Color color;
    private GUI gui;
    private double speed;
    private final double borderWidth = 20;

    /**
     * Constructor for the Paddle class.
     *
     * @param rect  The rectangle representing the paddle.
     * @param gui   The GUI object of the game.
     * @param speed The speed of the paddle's movement.
     * @param color The color of the paddle.
     */
    public Paddle(Rectangle rect, GUI gui, double speed, Color color) {
        this.color = color;
        this.rect = rect;
        this.gui = gui;
        keyboard = gui.getKeyboardSensor();
        this.speed = speed;
    }

    /**
     * Move the paddle to the left within the game screen.
     */
    public void moveLeft() {

        Point newUpperLeft = new Point(rect.getUpperLeft().getX() - speed, rect.getUpperLeft().getY());
        double borderLeft = borderWidth;
        // Move the paddle left, considering screen borders.
        // If reaching the left border, move to the rightmost position.
        if (MyUtilities.lessThan(newUpperLeft.getX(), borderLeft)) {
            double borderRight = gui.getDrawSurface().getWidth() - borderWidth;
            newUpperLeft = new Point(borderRight - rect.getWidth(), rect.getUpperLeft().getY());
        }
        this.rect = new Rectangle(newUpperLeft, rect.getWidth(), rect.getHeight());
    }

    /**
     * Move the paddle to the right within the game screen.
     */
    public void moveRight() {
        Point newUpperLeft = new Point(rect.getUpperLeft().getX() + speed, rect.getUpperLeft().getY());
        double borderRight = gui.getDrawSurface().getWidth() - borderWidth;
        // Move the paddle right, considering screen borders.
        // If reaching the right border, move to the leftmost position.
        if (MyUtilities.greaterThan(newUpperLeft.getX() + rect.getWidth(), borderRight)) {
            double borderLeft = borderWidth;
            newUpperLeft = new Point(borderLeft, rect.getUpperLeft().getY());
        }
        this.rect = new Rectangle(newUpperLeft, rect.getWidth(), rect.getHeight());
    }

    // Sprite

    /**
     * Check for keyboard input and move the paddle accordingly.
     */
    @Override
    public void timePassed() {
        // Check for left and right key presses and move the paddle.
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * Draw the paddle on the given DrawSurface.
     *
     * @param surface The DrawSurface to draw the paddle on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        this.rect.drawOn(surface);

    }

    // Collidable

    /**
     * Return the rectangle representing the paddle's collision shape.
     *
     * @return The collision rectangle of the paddle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Handle the hit event and return the new velocity of the object.
     *
     * @param collisionPoint  The point where the collision occurred.
     * @param currentVelocity The current velocity of the object.
     * @return The new velocity after the hit event.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // Create a new velocity object with the same values as the current velocity.
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        // If the collision occurred on the left or right side, reverse the horizontal direction.
        if (rect.getLeftLine().isPointOnline(collisionPoint) || rect.getRightLine().isPointOnline(collisionPoint)) {
            newVelocity.setDx(-currentVelocity.getDx());
        } else if (rect.getTopLine().isPointOnline(collisionPoint)) {
            // If the collision occurred on the top side of the paddle, determine the region.
            double regionWidth = rect.getWidth() / 5.0;
            double collisionX = collisionPoint.getX();
            double paddleStartX = rect.getUpperLeft().getX();
            if (MyUtilities.lessThanOrEqual(collisionX, 1 * regionWidth + paddleStartX)) {
                // Region 1: Bounce back with an angle of 300 degrees.
                newVelocity = Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
            } else if (MyUtilities.lessThanOrEqual(collisionX, 2 * regionWidth + paddleStartX)) {
                // Region 2: Bounce back with an angle of 330 degrees.
                newVelocity = Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
            } else if (MyUtilities.lessThanOrEqual(collisionX, 3 * regionWidth + paddleStartX)) {
                // Region 3: Keep horizontal direction, change vertical direction
                newVelocity.setDy(-currentVelocity.getDy());
            } else if (MyUtilities.lessThanOrEqual(collisionX, 4 * regionWidth + paddleStartX)) {
                // Region 4: Bounce back with an angle of 30 degrees
                newVelocity = Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
            } else if (MyUtilities.lessThanOrEqual(collisionX, 5 * regionWidth + paddleStartX)) {
                // Region 5: Bounce back with an angle of 60 degrees
                newVelocity = Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
            }
        }
        // Return the new velocity after the hit event.
        return newVelocity;
    }


    /**
     * Add this paddle to the given game by adding it to both sprites and collidables lists.
     *
     * @param g The game to add the paddle to.
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}

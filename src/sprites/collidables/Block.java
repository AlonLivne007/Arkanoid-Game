package sprites.collidables;
//Alon Livne (ID: 208688762)

import baseGeometry.Velocity;
import baseGeometry.Rectangle;
import baseGeometry.Point;


import biuoop.DrawSurface;
import gameFlow.Game;
import gameFlow.HitListener;
import gameFlow.HitNotifier;
import sprites.Ball;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The Block class represents a rectangular block that implements the Collidable interface.
 * It is used to define obstacles in the game environment.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructs a new Block with the specified rectangle and color.
     *
     * @param color the color of the block.
     * @param rect  The rectangle defining the boundaries of the block.
     */
    public Block(Rectangle rect, Color color) {
        this.color = color;
        this.rect = rect;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Returns the collision rectangle.
     *
     * @return The collision rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Returns the color of the block.
     *
     * @return The color of the block.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Notifies the object that a collision occurred with it at the specified collision point
     * and with a given velocity.
     *
     * @param hitter          The ball that is doing the hitting.
     * @param collisionPoint  The point at which the collision occurred.
     * @param currentVelocity The current velocity of the colliding object.
     * @return The new velocity expected after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        if (rect.getLeftLine().isPointOnline(collisionPoint) || rect.getRightLine().isPointOnline(collisionPoint)) {
            newVelocity.setDx(-currentVelocity.getDx());
        }
        if (rect.getBottomLine().isPointOnline(collisionPoint) || rect.getTopLine().isPointOnline(collisionPoint)) {
            newVelocity.setDy(-currentVelocity.getDy());
        }
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        return newVelocity;
    }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param surface The DrawSurface on which the block will be drawn.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        rect.drawOn(surface);
    }

    /**
     * This method is called in each frame of the game.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Adds the block to the specified game, integrating it as both a sprite and a collidable object.
     *
     * @param game The Game object to which the sprite should be added.
     */
    @Override
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * Checks if the color of the block matches the color of the ball.
     *
     * @param ball The ball to check against.
     * @return True if colors match, false otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color.equals(ball.getColor());
    }

    /**
     * Removes the block from the specified game, both as a sprite and a collidable object.
     *
     * @param game The Game object from which the sprite and collidable should be removed.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies all registered HitListeners about a hit event.
     * Makes a copy of the HitListeners list before iterating over them to avoid concurrent modification.
     *
     * @param hitter The Ball object that caused the hit event.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}

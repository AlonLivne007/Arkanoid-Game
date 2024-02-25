package sprites.collidables;//Alon Livne (ID: 208688762)

import baseGeometry.Velocity;
import baseGeometry.Rectangle;
import baseGeometry.Point;


import biuoop.DrawSurface;
import gameFlow.Game;
import sprites.Sprite;

import java.awt.*;


/**
 * The Block class represents a rectangular block that implements the Collidable interface.
 * It is used to define obstacles in the game environment.
 */
public class Block implements Collidable, Sprite {
    private Rectangle rect;
    private Color color;

    /**
     * Constructs a new Block with the specified rectangle and color.
     *
     * @param color the color of the block.
     * @param rect  The rectangle defining the boundaries of the block.
     */
    public Block(Rectangle rect, Color color) {
        this.color = color;
        this.rect = rect;
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
     * Notifies the object that a collision occurred with it at the specified collision point
     * and with a given velocity.
     *
     * @param collisionPoint  The point at which the collision occurred.
     * @param currentVelocity The current velocity of the colliding object.
     * @return The new velocity expected after the hit.
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        if (rect.getLeftLine().isPointOnline(collisionPoint) || rect.getRightLine().isPointOnline(collisionPoint)) {
            newVelocity.setDx(-currentVelocity.getDx());
        }
        if (rect.getBottomLine().isPointOnline(collisionPoint) || rect.getTopLine().isPointOnline(collisionPoint)) {
            newVelocity.setDy(-currentVelocity.getDy());
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

}

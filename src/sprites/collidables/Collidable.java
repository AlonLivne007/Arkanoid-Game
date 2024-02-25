package sprites.collidables;//Alon Livne (ID: 208688762)

import baseGeometry.*;

/**
 * The Collidable interface represents objects that can participate in collisions.
 */
public interface Collidable {
    /**
     * Returns the collision rectangle.
     *
     * @return The collision rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred with it at the specified collision point
     * and with a given velocity.
     *
     * @param collisionPoint  The point at which the collision occurred.
     * @param currentVelocity The current velocity of the colliding object.
     * @return The new velocity expected after the hit.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}


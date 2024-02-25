package sprites.collidables;//Alon Livne (ID: 208688762)

import baseGeometry.Point;

/**
 * The CollisionInfo class represents information about a collision between a trajectory and a collidable object.
 */
public class CollisionInfo {

    // The point at which the collision occurs.
    private Point collisionPoint;

    // The collidable object involved in the collision.
    private Collidable collisionObject;

    /**
     * Constructs a new CollisionInfo with the specified collision point and collidable object.
     *
     * @param collisionPoint  The point at which the collision occurs.
     * @param collisionObject The collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return The collidable object involved in the collision.
     */
    public Collidable getCollisionObject() {
        return collisionObject;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return The point at which the collision occurs.
     */
    public Point getCollisionPoint() {
        return collisionPoint;
    }
}

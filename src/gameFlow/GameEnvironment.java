//Alon Livne (ID: 208688762)
package gameFlow;

import baseGeometry.Line;
import baseGeometry.Point;
import myUtill.MyUtilities;
import sprites.collidables.Collidable;
import sprites.collidables.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameEnvironment class represents the environment of collidables in which ball can collide.
 */
public class GameEnvironment {
    // A collection to store collidables in the environment.
    private List<Collidable> collidableList;

    /**
     * Constructs a new GameEnvironment.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
    }

    /**
     * Adds the given collidable to the environment.
     *
     * @param c The collidable object to be added.
     */
    public void addCollidable(Collidable c) {
        collidableList.add(c);
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c The collidable object to be removed.
     */
    public void removeCollidable(Collidable c) {
        collidableList.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory The trajectory of the moving object.
     * @return CollisionInfo representing the information about the closest collision,
     * or null if no collision is detected.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // Initialize variables to store information about the closest collision
        CollisionInfo collisionInfo = null;
        double minDistance = -1;
        double currentDistance;
        Point closestCollidePoint;
        Point currentCollidePoint;
        List<Collidable> collidables = new ArrayList<>(collidableList);
        // Iterate over each collidable object
        for (Collidable collidable : collidables) {
            // Find the closest intersection point between the trajectory and the collidable rectangle
            currentCollidePoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());

            // If no intersection point, continue to the next collidable
            if (currentCollidePoint == null) {
                continue;
            }
            // Calculate the distance from the start of the trajectory to the current intersection point
            currentDistance = trajectory.start().distance(currentCollidePoint);
            // If  this is the first collision initialise information about the closest collision
            if (minDistance == -1) {
                closestCollidePoint = currentCollidePoint;
                minDistance = currentDistance;
                collisionInfo = new CollisionInfo(closestCollidePoint, collidable);

            } else if (MyUtilities.lessThan(currentDistance, minDistance)) {
                // If this collision closer than the previous closest collision
                //Update the information about the closest collision
                closestCollidePoint = currentCollidePoint;
                minDistance = currentDistance;
                collisionInfo = new CollisionInfo(closestCollidePoint, collidable);
            }
        }
        return collisionInfo;
    }
}

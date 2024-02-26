// Alon Livne (ID: 208688762)
package gameFlow;

import sprites.Ball;
import sprites.collidables.Block;

/**
 * The HitListener interface represents an object that listens for hit events between a Ball and a Block.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit The Block that was hit.
     * @param hitter   The Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
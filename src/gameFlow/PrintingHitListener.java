package gameFlow;

import sprites.Ball;
import sprites.collidables.Block;
/**
 * A simple implementation of HitListener that prints a message when a Block is hit.
 */
public class PrintingHitListener implements HitListener {
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

        System.out.println("A Block was hit.");
    }
}

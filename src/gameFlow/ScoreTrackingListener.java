// Alon Livne (ID: 208688762)
package gameFlow;

import sprites.Ball;
import sprites.collidables.Block;

/**
 * The ScoreTrackingListener class is responsible for tracking and updating the player's score
 * whenever a block is hit by a ball in the game.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a new ScoreTrackingListener with the specified score counter.
     *
     * @param scoreCounter The Counter object representing the player's current score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit,and Increase the player's score by a fixed amount when
     * a block is hit.
     *
     * @param beingHit The Block that was hit.
     * @param hitter   The Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}

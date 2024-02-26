// Alon Livne (ID: 208688762)
package gameFlow;

import sprites.Ball;
import sprites.collidables.Block;

/**
 * The BallRemover class is responsible for removing balls from the game when they hit a designated death region.
 * It implements the HitListener interface to handle hit events.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructs a new BallRemover.
     *
     * @param game           The game to which the BallRemover belongs.
     * @param remainingBalls The counter tracking the remaining balls in the game.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.remainingBalls = remainingBalls;
        this.game = game;
    }

    /**
     * Handles a hit event when a ball hits a designated region.
     * Removes the ball from the game and updates the count of remaining balls.
     *
     * @param deathRegion The Block representing the designated region.
     * @param hitter      The Ball that hit the designated region.
     */
    @Override
    public void hitEvent(Block deathRegion, Ball hitter) {
        hitter.removeFromGame(this.game);
        remainingBalls.decrease(1);
    }
}

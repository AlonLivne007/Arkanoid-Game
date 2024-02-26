//Alon Livne (ID: 208688762)
package gameFlow;

import sprites.Ball;
import sprites.collidables.Block;

/**
 * The BlockRemover class is in charge of removing blocks from the game and keeping track of the remaining blocks.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a new BlockRemover instance.
     *
     * @param game            The game in which the blocks exist.
     * @param remainingBlocks The counter representing the remaining blocks.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.remainingBlocks = remainingBlocks;
        this.game = game;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.setColor(beingHit.getColor());
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        remainingBlocks.decrease(1);
    }
}
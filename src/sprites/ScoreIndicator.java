// Alon Livne (ID: 208688762)
package sprites;

import biuoop.DrawSurface;
import gameFlow.Counter;
import gameFlow.Game;

/**
 * The ScoreIndicator class represents a graphical display of the player's score in the game.
 * It implements the Sprite interface and is responsible for drawing the current score on the game screen.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructs a new ScoreIndicator with the specified score counter.
     *
     * @param score The Counter object representing the player's current score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Draw the sprite on the given DrawSurface.
     *
     * @param d The DrawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        // Draw the player's score on the specified DrawSurface
        d.drawText(360, 16, "Score: " + score.getValue(), 18);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Adds the sprite to the specified game, integrating it into the list of sprites to be drawn.
     *
     * @param game The Game object to which the sprite should be added.
     */
    @Override
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}

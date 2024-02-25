package sprites;//Alon Livne (ID: 208688762)

import biuoop.DrawSurface;
import gameFlow.Game;

/**
 * The Sprite interface represents a game object that can be drawn on the screen and reacts to the passage of time.
 */
public interface Sprite {
    /**
     * Draw the sprite on the given DrawSurface.
     *
     * @param d The DrawSurface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Adds the sprite to the specified game.
     *
     * @param game The Game object to which the sprite should be added.
     */
    void addToGame(Game game);
}
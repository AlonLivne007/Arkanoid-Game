//Alon Livne (ID: 208688762)

import gameFlow.Game;

/**
 * The main class for running the Ass3Game application.
 * Initializes and runs the game.
 */
public class Ass3Game {
    /**
     * The main method that creates a Game instance, initializes it, and runs the game.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}



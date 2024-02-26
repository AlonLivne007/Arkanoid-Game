// Alon Livne (ID: 208688762)
package gameFlow;

import baseGeometry.Point;
import baseGeometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import sprites.Ball;
import sprites.Sprite;
import sprites.collidables.Block;
import sprites.collidables.Collidable;
import sprites.collidables.Paddle;


import java.awt.Color;

/**
 * The Game class represents the main logic of the game, managing sprites, the game environment, and the GUI.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private Counter blockCounter;
    private Counter ballCounter;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;

    /**
     * Constructs a new Game instance.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.sleeper = new Sleeper();
        this.gui = new GUI("Game", 800, 600);
        this.blockCounter = new Counter();
        this.blockRemover = new BlockRemover(this, blockCounter);
        this.ballCounter = new Counter();
        this.ballRemover = new BallRemover(this, ballCounter);
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c The collidable object to be added.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the game.
     *
     * @param s The sprite to be added.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initializes a new game, creating borders, paddle, and adding them to the game environment.
     */
    public void initialize() {

        // Create borders and add them to the game .
        Block borderLeft = new Block(new Rectangle(new Point(0, 0), 20, 600), Color.GRAY);
        Block borderRight = new Block(new Rectangle(new Point(780, 0), 20, 600), Color.GRAY);
        Block borderTop = new Block(new Rectangle(new Point(0, 0), 800, 20), Color.GRAY);
        borderTop.addToGame(this);
        borderLeft.addToGame(this);
        borderRight.addToGame(this);

//        Create death block, and add it to the game.
        Block deathRegion = new Block(new Rectangle(new Point(0, 580), 800, 20), Color.GRAY);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);

        // Create  paddle and add it to the game.
        Paddle paddle = new Paddle(new Rectangle(new Point(385, 560), 100, 20), gui, 5, Color.PINK);
        paddle.addToGame(this);

        // Create balls and add them to the game
        int numOfBalls = 3;
        for (int i = 0; i < numOfBalls; i++) {
            Color ballColor;
            ballColor = switch (i) {
                case 0 -> Color.RED;
                case 1 -> Color.ORANGE;
                case 2 -> Color.GREEN;
                case 3 -> Color.YELLOW;
                case 4 -> Color.BLUE;
                case 5 -> Color.MAGENTA;
                default -> Color.BLACK;
            };
            Ball ball = new Ball(400, 300, 4, ballColor, this.environment);
            ball.setVelocity(2 + 0.5 * i, 2 + 0.5 * i);
            ball.addToGame(this);
        }
        ballCounter.increase(numOfBalls);

        // Add 6 lines of blocks with decreasing block count and different colors
        for (int i = 0; i < 6; i++) {
            int numOfBlocks = 12 - i * 2;
            double blockWidth = 60;
            double blockHeight = 20;
            Color blockColor;
            Point start = new Point(40 + blockWidth * i, 20 + blockHeight * i);

            for (int j = 0; j < numOfBlocks; j++) {
                blockColor = switch (i) {
                    case 0 -> Color.RED;
                    case 1 -> Color.ORANGE;
                    case 2 -> Color.YELLOW;
                    case 3 -> Color.GREEN;
                    case 4 -> Color.BLUE;
                    case 5 -> Color.MAGENTA;
                    default -> Color.BLACK;
                };
                Block block = new Block(new Rectangle(new Point(start.getX() + j * blockWidth, start.getY()),
                        blockWidth, blockHeight), blockColor);
                block.addToGame(this);
                block.addHitListener(blockRemover);
            }
            blockCounter.increase(numOfBlocks);
        }
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c The collidable object to be removed.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Removes a sprite from the game.
     *
     * @param s The sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }


    /**
     * Runs the game loop, managing the animation and timing of each frame.
     * The game loop continues as long as there are remaining blocks and balls in the game.
     */
    public void run() {
        // Set the desired frames per second and calculate milliseconds per frame
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        // Continue the game loop as long as there are blocks remaining and balls in play
        while (blockCounter.getValue() > 0 && ballCounter.getValue() > 0) {
            long startTime = System.currentTimeMillis(); // Timing the frame start

            DrawSurface d = gui.getDrawSurface();

            // Draw all sprites on the DrawSurface
            this.sprites.drawAllOn(d);
            gui.show(d);

            // Notify all sprites that a frame has passed
            this.sprites.notifyAllTimePassed();

            // Timing the frame end
            long usedTime = System.currentTimeMillis() - startTime;

            // Calculate the time left to sleep to maintain desired frame rate
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }

        // Close the GUI after the game loop ends
        gui.close();
    }
}

package gameFlow;// Alon Livne (ID: 208688762)

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import sprites.*;
import sprites.collidables.*;
import baseGeometry.*;


import java.awt.Color;

/**
 * The Game class represents the main logic of the game, managing sprites, the game environment, and the GUI.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;

    /**
     * Constructs a new Game instance.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
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
        this.sleeper = new Sleeper();
        this.gui = new GUI("Game", 800, 600);

        // Create borders, paddle, and add them to the game environment
        Block borderLeft = new Block(new Rectangle(new Point(0, 0), 20, 600), Color.GRAY);
        Block borderRight = new Block(new Rectangle(new Point(780, 0), 20, 600), Color.GRAY);
        Block borderTop = new Block(new Rectangle(new Point(0, 0), 800, 20), Color.GRAY);
        Block borderBottom = new Block(new Rectangle(new Point(0, 580), 800, 20), Color.GRAY);
        Paddle paddle = new Paddle(new Rectangle(new Point(385, 560), 100, 20), gui, 4, Color.PINK);
        // add the borders, paddle to the game
        borderTop.addToGame(this);
        borderLeft.addToGame(this);
        borderRight.addToGame(this);
        borderBottom.addToGame(this);
        paddle.addToGame(this);

        // Create balls and add them to the game
        Ball ball1 = new Ball(400, 300, 4, java.awt.Color.BLUE, this.environment);
        ball1.setVelocity(4, 4);
        ball1.addToGame(this);
        Ball ball2 = new Ball(400, 300, 4, Color.RED, this.environment);
        ball2.setVelocity(3, 3);
        ball2.addToGame(this);

        // Add 6 lines of blocks with decreasing block count and different colors
        for (int i = 0; i < 6; i++) {
            int numOfBlocks = 12 - i * 2;
            double blockWidth = 60;
            double blockHeight = 20;
            Color blockColor;
            Point start = new Point(40 + blockWidth * i, 20 + blockHeight * i);

            for (int j = 0; j < numOfBlocks; j++) {
                switch (i) {
                    case 0:
                        blockColor = Color.RED;
                        break;
                    case 1:
                        blockColor = Color.ORANGE;
                        break;
                    case 2:
                        blockColor = Color.YELLOW;
                        break;
                    case 3:
                        blockColor = Color.GREEN;
                        break;
                    case 4:
                        blockColor = Color.BLUE;
                        break;
                    case 5:
                        blockColor = Color.MAGENTA;
                        break;
                    default:
                        blockColor = Color.BLACK;
                }
                Block block = new Block(new Rectangle(new Point(start.getX() + j * blockWidth, start.getY()),
                        blockWidth, blockHeight), blockColor);

                block.addToGame(this);
            }
        }
    }

    /**
     * Runs the game - starts the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}

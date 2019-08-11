package runners;

import biuoop.KeyboardSensor;
import items.Ball;
import items.Block;
import items.LivesIndicator;
import items.NameOfLevel;
import items.Paddle;
import items.ScoreIndicator;
import geometry.Point;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.LevelInformation;
import interfaces.Sprite;
import setting.BallRemover;
import setting.BlockRemover;
import setting.Counter;
import setting.GameEnvironment;
import setting.ScoreTrackingListener;
import setting.SpriteCollection;
import biuoop.DrawSurface;

/**
 * this class is the game's class. Holds all of the information
 * about the game's setting and the runnable version.
 * @author Inbar Avital.
 */
public class GameLevel implements Animation {
    //members
    private SpriteCollection sprites;
    private GameEnvironment environment;
    // number of lines of blocks.
    private static final int LINE_OF_BLOCKS = 6;
    // life
    private int life;
    // Screen Size
    private static final int WIDTH = 800;
    private static final int HEIGTH = 600;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter scoreCounter;
    private Counter lifeCounter;
    private Paddle paddle;
    // keyboard
    private biuoop.KeyboardSensor keyboard;
    // running
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation level;
    //constructor
    /**
     * Constructor.
     * @param li - the levels of the game.
     * @param kb - the keyboard.
     * @param ar - the animation runner.
     * @param lf - the life counter.
     * @param sc - the score counter.
     */
    public GameLevel(LevelInformation li, KeyboardSensor kb,
            AnimationRunner ar, Counter lf, Counter sc) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.scoreCounter = sc;
        this.lifeCounter = lf;
        this.keyboard = kb;
        this.runner = ar;
        this.level = li;
        this.life = lf.getValue();
    }
    /**
     * Adds the collidable "c" to the environment.
     * @param c - the collidable item.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    /**
     * Adds the Sprite "s" to the sprites.
     * @param s - the sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }
    /**
     * removes the collidable "c" from the environment.
     * @param c - the collidable item.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }
    /**
     * removes the Sprite "s" from the sprites.
     * @param s - the sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        // Sets the point of the borders and background blocks.
        Point borders1 = new Point(0, 0);
        Point borders2 = new Point(0, 25);
        Point borders3 = new Point(0, 50);
        Point borders4 = new Point(25, HEIGTH);
        Point borders5 = new Point(WIDTH - 25, 50);
        Point borders6 = new Point(WIDTH / 3, 0);
        Point borders7 = new Point((WIDTH / 3) * 2, 0);
        sprites.addSprite(this.level.getBackground());
        HitListener blockRemoval = new BlockRemover(this, this.blocksCounter);
        HitListener deathBlock = new BallRemover(this, this.ballsCounter);
        HitListener score = new ScoreTrackingListener(this.scoreCounter);
        // setting the colors for the blocks (different each line)
        java.awt.Color[] colors = new java.awt.Color[LINE_OF_BLOCKS];
        colors[0] = java.awt.Color.GRAY;
        colors[1] = java.awt.Color.RED;
        colors[2] = java.awt.Color.YELLOW;
        colors[3] = java.awt.Color.BLUE;
        colors[4] = java.awt.Color.PINK;
        colors[5] = java.awt.Color.GREEN;
        // setting the borders and add them to game.
        Sprite scoreBlock = new ScoreIndicator(borders6, WIDTH / 3, 25,
                java.awt.Color.WHITE, this.scoreCounter);
        Sprite lifeBlock = new LivesIndicator(borders1, WIDTH / 3, 25,
                java.awt.Color.WHITE, this.lifeCounter);
        Sprite nameBlock = new NameOfLevel(borders7, WIDTH / 3, 25,
                java.awt.Color.WHITE, this.level.levelName());
        Block r1 = new Block(borders2, WIDTH, 25, java.awt.Color.GRAY);
        Block r2 = new Block(borders3, 25, HEIGTH - 25, java.awt.Color.GRAY);
        Block r3 = new Block(borders4, WIDTH, 25, java.awt.Color.GRAY);
        Block r4 = new Block(borders5, 25, HEIGTH - 25, java.awt.Color.GRAY);
        // sets the border's hit number to -1 so they won't be effected.
        r1.setHitNumber(-1);
        r2.setHitNumber(-1);
        r3.setHitNumber(-1);
        r4.setHitNumber(-1);
        // sets the death block to the down one.
        r3.addHitListener(deathBlock);
        scoreBlock.addToGame(this);
        lifeBlock.addToGame(this);
        nameBlock.addToGame(this);
        // adds the borders to the game.
        r1.addToGame(this);
        r2.addToGame(this);
        r3.addToGame(this);
        r4.addToGame(this);
        for (Block block: this.level.blocks()) {
            this.blocksCounter.increase(1);
            block.addHitListener(blockRemoval);
            block.addHitListener(score);
            block.addToGame(this);
        }
    }
    /**
     * sets balls and paddle.
     */
    public void initializeTurn() {
        switch(this.level.numberOfBalls()) {
        case 10:
            Ball bal1 = new Ball(WIDTH / 2 + 40, HEIGTH / 2 + 70, 5, java.awt.Color.WHITE);
            bal1.setVelocity(this.level.initialBallVelocities().get(0));
            bal1.addToGame(this);
            bal1.setGame(this.environment);
            Ball bal2 = new Ball(WIDTH / 2 - 40, HEIGTH / 2 + 70, 5, java.awt.Color.WHITE);
            bal2.setVelocity(this.level.initialBallVelocities().get(1));
            bal2.addToGame(this);
            bal2.setGame(this.environment);
            this.ballsCounter.increase(2);
        case 8:
            Ball bal3 = new Ball(WIDTH / 2 + 80, HEIGTH / 2 + 80 , 5, java.awt.Color.WHITE);
            bal3.setVelocity(this.level.initialBallVelocities().get(2));
            bal3.addToGame(this);
            bal3.setGame(this.environment);
            Ball bal4 = new Ball(WIDTH / 2 - 80, HEIGTH / 2 + 80, 5, java.awt.Color.WHITE);
            bal4.setVelocity(this.level.initialBallVelocities().get(3));
            bal4.addToGame(this);
            bal4.setGame(this.environment);
            Ball bal5 = new Ball(WIDTH / 2 + 120, HEIGTH / 2 + 100 , 5, java.awt.Color.WHITE);
            bal5.setVelocity(this.level.initialBallVelocities().get(4));
            bal5.addToGame(this);
            bal5.setGame(this.environment);
            Ball bal6 = new Ball(WIDTH / 2 - 120, HEIGTH / 2 + 100, 5, java.awt.Color.WHITE);
            bal6.setVelocity(this.level.initialBallVelocities().get(5));
            bal6.addToGame(this);
            bal6.setGame(this.environment);
            Ball bal7 = new Ball(WIDTH / 2 + 160, HEIGTH / 2 + 130, 5, java.awt.Color.WHITE);
            bal7.setVelocity(this.level.initialBallVelocities().get(6));
            bal7.addToGame(this);
            bal7.setGame(this.environment);
            Ball bal8 = new Ball(WIDTH / 2 - 160, HEIGTH / 2 + 130 , 5, java.awt.Color.WHITE);
            bal8.setVelocity(this.level.initialBallVelocities().get(7));
            bal8.addToGame(this);
            bal8.setGame(this.environment);
            Ball bal9 = new Ball(WIDTH / 2 + 200, HEIGTH / 2 + 170, 5, java.awt.Color.WHITE);
            bal9.setVelocity(this.level.initialBallVelocities().get(8));
            bal9.addToGame(this);
            bal9.setGame(this.environment);
            Ball bal10 = new Ball(WIDTH / 2 - 200, HEIGTH / 2 + 170, 5, java.awt.Color.WHITE);
            bal10.setVelocity(this.level.initialBallVelocities().get(9));
            bal10.addToGame(this);
            bal10.setGame(this.environment);
            this.ballsCounter.increase(8);
            break;
        case 4:
            Ball b1 = new Ball(WIDTH / 2 - 102, HEIGTH / 2 + 177, 5, java.awt.Color.WHITE);
            b1.setVelocity(this.level.initialBallVelocities().get(0));
            b1.addToGame(this);
            b1.setGame(this.environment);
            Ball b2 = new Ball(WIDTH / 2 + 102, HEIGTH / 2 + 177, 5, java.awt.Color.WHITE);
            b2.setVelocity(this.level.initialBallVelocities().get(1));
            b2.addToGame(this);
            b2.setGame(this.environment);
            Ball b3 = new Ball(WIDTH / 2 - 150, HEIGTH / 2 + 250, 5, java.awt.Color.WHITE);
            b3.setVelocity(this.level.initialBallVelocities().get(0));
            b3.addToGame(this);
            b3.setGame(this.environment);
            Ball b4 = new Ball(WIDTH / 2 + 150, HEIGTH / 2 + 250, 5, java.awt.Color.WHITE);
            b4.setVelocity(this.level.initialBallVelocities().get(1));
            b4.addToGame(this);
            b4.setGame(this.environment);
            this.ballsCounter.increase(4);
            break;
        case 3:
            Ball ball4 = new Ball(WIDTH / 2, HEIGTH / 2 + 100, 5, java.awt.Color.WHITE);
            ball4.setVelocity(this.level.initialBallVelocities().get(2));
            ball4.addToGame(this);
            ball4.setGame(this.environment);
            this.ballsCounter.increase(1);
        case 2:
            Ball ball2 = new Ball(WIDTH / 2 - 102, HEIGTH / 2 + 177, 5, java.awt.Color.WHITE);
            ball2.setVelocity(this.level.initialBallVelocities().get(0));
            ball2.addToGame(this);
            ball2.setGame(this.environment);
            Ball ball3 = new Ball(WIDTH / 2 + 102, HEIGTH / 2 + 177, 5, java.awt.Color.WHITE);
            ball3.setVelocity(this.level.initialBallVelocities().get(1));
            ball3.addToGame(this);
            ball3.setGame(this.environment);
            this.ballsCounter.increase(2);
            break;
        case 1:
            Ball ball = new Ball(WIDTH / 2, HEIGTH / 2 + 200 , 5, java.awt.Color.WHITE);
            ball.setVelocity(this.level.initialBallVelocities().get(0));
            ball.addToGame(this);
            ball.setGame(this.environment);
            this.ballsCounter.increase(1);
            break;
        default:
            // there are no other settings as for that moment.
            break;
        }
        // creates the paddle and adds to game.
        if (this.lifeCounter.getValue() != life) {
            this.paddle.removeFromGame(this);
        }
        this.paddle = new Paddle(this.keyboard,
                this.level.paddleWidth(),
                this.level.paddleSpeed());
        this.paddle.addToGame(this);
    }
    /**
     *  Plays one turn - one life.
     */
    public void playOneTurn() {
        this.initializeTurn();
        this.running = true;
        // countdown
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        // use our runner to run the current animation - which is one turn of
        // the game.
        this.runner.run(this);
    }
    /**
     * Run the games.
     */
    public void run() {
        // as long as we are alive, continue
        while (lifeCounter.getValue() != 0) {
            // play one turn.
            playOneTurn();
            // if we broke all blocks:
            if (this.blocksCounter.getValue() == 0) {
                // add 100 points to the score.
                scoreCounter.increase(100);
                // end the game.
                runner.getGUI().close();
            }
            // if there are no more balls:
            if (this.ballsCounter.getValue() == 0) {
                // decrease life and reset blocks counter.
                lifeCounter.decrease(1);
            }
        }
        runner.getGUI().close();
    }
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        // sprites
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        // stopping condition
        if (this.ballsCounter.getValue() == 0) {
            this.running = false;
        }
        if (this.blocksCounter.getValue() == 0) {
            this.running = false;
        }
        // pause menu
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
    }
    /**
     * @return the amount of blocks left in the level.
     */
    public int blocksLeft() {
        return this.blocksCounter.getValue();
    }
    /**
     * @return the number of balls left in the level.
     */
    public int ballsLeft() {
        return this.ballsCounter.getValue();
    }
}
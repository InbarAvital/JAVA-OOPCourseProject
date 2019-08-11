package interfaces;

import java.util.List;

import items.Block;
import setting.Velocity;

/**
 * @author Inbar Avital
 * The interface of the level information.
 */
public interface LevelInformation {
    /**
     * @return the number of balls.
     */
    int numberOfBalls();
    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return the velocities.
     */
    List<Velocity> initialBallVelocities();
    /**
     * @return the paddle's speed.
     */
    int paddleSpeed();
    /**
     * @return the paddle's width.
     */
    int paddleWidth();
    /**
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();
    /**
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();
    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return the blocks described above.
     */
    List<Block> blocks();
    /**
     * @return Number of levels that should be removed
     *  before the level is considered to be "cleared".
     *  This number should be <= blocks.size();
     */
    int numberOfBlocksToRemove();
 }

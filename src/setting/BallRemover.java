package setting;

import interfaces.HitListener;
import items.Ball;
import items.Block;
import runners.GameLevel;

/**
 * a BallRemover is in charge of removing balls
 * from the game, as well as keeping count
 * of the number of balls that remain.
 * @author Inbar Avital.
 *
 */
public class BallRemover implements HitListener {
    // members
    private GameLevel game;
    private Counter remainingBalls;
    /**
     * Constructor.
     * @param game - the game of the ball.
     * @param remainedBalls - the number of balls left.
     */
    public BallRemover(GameLevel game, Counter remainedBalls) {
        this.game = game;
        this.remainingBalls = remainedBalls;
    }
    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     * @param deathRegion - the death region block.
     * @param hitter - the hitter ball.
     */
    public void hitEvent(Block deathRegion, Ball hitter) {
        remainingBalls.decrease(1);
        hitter.removeFromGame(this.game);
    }
}
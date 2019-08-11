package setting;

import interfaces.HitListener;
import items.Ball;
import items.Block;
import runners.GameLevel;
/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 * @author Inbar Avital.
 */
public class BlockRemover implements HitListener {
    // members
    private GameLevel game;
    private Counter remainingBlocks;
    /**
     * Constructor.
     * @param game - the game of the block
     * @param remainedBlocks - the blocks remained in game.
     */
    public BlockRemover(GameLevel game, Counter remainedBlocks) {
        this.game = game;
        this.remainingBlocks = remainedBlocks;
    }
    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     * @param beingHit - the block that is being hit.
     * @param hitter - the ball that hits the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // if the block has no more hits
        if (beingHit.getHitPoints() == 0) {
            remainingBlocks.decrease(1);
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.game);
        }
    }
}
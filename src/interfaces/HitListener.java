package interfaces;

import items.Ball;
import items.Block;

/**
 * A listener to whenever the ball is being hit.
 * @author Inbar Avital.
 *
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit - the block that is being hit.
     * @param hitter - the ball that hitted.
     */
    void hitEvent(Block beingHit, Ball hitter);
 }
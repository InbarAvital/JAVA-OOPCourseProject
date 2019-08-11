package setting;

import interfaces.HitListener;
import items.Ball;
import items.Block;
/**
 * This class is a class of score tracking. It keeps the player's score.
 * @author Inbar Avital.
 */
public class ScoreTrackingListener implements HitListener {
    // members
    private Counter currentScore;
    /**
     * Constructor.
     * @param scoreCounter - the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
       this.currentScore = scoreCounter;
    }
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit - the block that is being hit.
     * @param hitter - the hitter ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // there was a hit, increase the score by 5.
        currentScore.increase(5);
        // in case the block is now broken, give extra 10 points.
        if (beingHit.getHitPoints() == 0) {
            currentScore.increase(10);
        }
    }
 }

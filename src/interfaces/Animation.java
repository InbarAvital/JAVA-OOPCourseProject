package interfaces;

import biuoop.DrawSurface;
/**
 *
 * @author Inbar.
 * The animation interface.
 */
public interface Animation {
    /**
     * Performs one frame of the game.
     * @param d - the draw surface to draw the one frame with.
     */
    void doOneFrame(DrawSurface d);
    /**
     * Is in charge of stoping the animation.
     * @return true if should stop and false otherwise.
     */
    boolean shouldStop();
}

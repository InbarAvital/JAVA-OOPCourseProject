package runners;

import biuoop.DrawSurface;
import interfaces.Animation;

/**
 *
 * @author Inbar.
 * The pause screen.
 */
public class PauseScreen implements Animation {
    /**
     * Empty Constructor.
     */
    public PauseScreen() {
    }
    @Override
    public void doOneFrame(DrawSurface d) {
       d.drawText(25, d.getHeight() / 2, "paused -- press space to continue", 50);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
 }
package runners;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import interfaces.Animation;
import setting.SpriteCollection;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 * @author Inbar
 *
 */
public class CountdownAnimation implements Animation {
    // members
    private int countFrom;
    private SpriteCollection gameScreen;
    private double count;
    /**
     * Constructor.
     * @param numOfSeconds - the number of seconds to run the countdown.
     * @param countFrom - from what number to start counting.
     * @param gameScreen - the sprite collection to display during countdown.
     */
    public CountdownAnimation(double numOfSeconds,
            int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.count = (double) (numOfSeconds / countFrom);
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        //first draw the screen
        gameScreen.drawAllOn(d);
        // counting:
        Sleeper sleeper = new Sleeper();
        // setting the string that will display
        String display = ((Integer) countFrom).toString();
        if (countFrom == 0) {
            d.setColor(java.awt.Color.BLACK);
            d.drawText(320, 300, "GO!", 110);
            d.setColor(java.awt.Color.GREEN);
            d.drawText(320, 300, "GO!", 100);
        } else {
            if (countFrom >= 0) {
                d.setColor(java.awt.Color.BLACK);
                d.drawText(385, 305, display, 100);
                d.setColor(new java.awt.Color(255, 204, 51));
                d.drawText(380, 300, display, 100);
            }
        }
        sleeper.sleepFor((long) (this.count * 1000));
        countFrom--;
    }
    @Override
    public boolean shouldStop() {
        if (this.countFrom == -2) {
            return true;
        }
        return false;
    }
}

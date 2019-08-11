package runners;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 *
 * @author Inbar
 * Animator runner.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    /**
     * Constructor.
     * @param gui - the gui of the animation.
     */
    public AnimationRunner(GUI gui) {
        this.framesPerSecond = 60;
        this.gui = gui;
        this.sleeper = new Sleeper();
    }
    /**
     * Runs the animation.
     * @param animation - the animation to run.
     */
    public void run(Animation animation) {
       int millisecondsPerFrame = 1000 / this.framesPerSecond;
       while (!animation.shouldStop()) {
          long startTime = System.currentTimeMillis(); // timing
          DrawSurface d = this.gui.getDrawSurface();
          animation.doOneFrame(d);
          gui.show(d);
          long usedTime = System.currentTimeMillis() - startTime;
          long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
          if (milliSecondLeftToSleep > 0) {
              this.sleeper.sleepFor(milliSecondLeftToSleep);
          }
       }
    }
    /**
     * @return the gui of the runner.
     */
    public GUI getGUI() {
        return this.gui;
    }
 }

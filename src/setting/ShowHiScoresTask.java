package setting;

import interfaces.Animation;
import interfaces.Task;
import runners.AnimationRunner;

/**
 *
 * @author Inbar
 *
 */
public class ShowHiScoresTask implements Task<Void> {
    // members
    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * Constructor.
     *
     * @param runner              - the runner
     * @param highScoresAnimation - the animation.
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    /**
     * Runs.
     * @return void object.
     */
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }
}

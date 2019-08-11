package runners;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 *
 * @author Inbar
 * A decorator of the keys pressed.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean flag = false;
    private boolean isAlreadyPressed = true;
    /**
     * Constructor.
     * @param sensor - the keyboard to detect from.
     * @param key - the key to detect.
     * @param animation - the animation to stop when pressing.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.sensor.isPressed(this.key)) {
            if (!isAlreadyPressed) {
                isAlreadyPressed = true;
                flag = true;
            }
        } else {
            isAlreadyPressed = false;
        }
        this.animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return flag;
    }
}
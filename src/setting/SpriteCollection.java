package setting;
import java.util.ArrayList;
import biuoop.DrawSurface;
import interfaces.Sprite;
/**
 * This is a collection of sprite objects.
 * @author Inbar Avital.
 */
public class SpriteCollection {
    //members
    private java.util.List<Sprite> sprites;
    //constructor
    /**
     * Default constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }
    /**
     * gets sprite s and add it to the sprite collections.
     * @param s - the sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**
     * gets sprite s and remove it from the sprite collections.
     * @param s - the sprite needed removal.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
    /**
     * calls all timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        java.util.List<Sprite> copy = new ArrayList<>(sprites);
        for (Sprite s : copy) {
            s.timePassed();
        }
    }
    /**
     * call drawOn(d) on all sprites.
     * @param d - this is the draw surface to use for drawing the sprites.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite i: sprites) {
            i.drawOn(d);
        }
    }
    /**
     * returns this sprite collection's array.
     * @return java.util.List<Sprite>
     */
    public java.util.List<Sprite> getSprites() {
        return this.sprites;
    }
}
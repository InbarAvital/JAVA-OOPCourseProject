package interfaces;
import biuoop.DrawSurface;
import runners.GameLevel;
/**
 * This is the interface for all of the moving objects - sprites.
 * @author Inbar Avital.
 */
public interface Sprite {
    /**
     * Draw the sprite to the screen.
     * @param d - the draw surface to draw with.
     */
   void drawOn(DrawSurface d);
   /**
    * notify the sprite that time has passed to moves one steps.
    */
   void timePassed();
   /**
    * adds the Sprite to the game.
    * @param g - the game to add the sprite to.
    */
   void addToGame(GameLevel g);
}
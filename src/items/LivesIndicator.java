package items;

import biuoop.DrawSurface;
import geometry.Point;
import interfaces.Sprite;
import runners.GameLevel;
import setting.Counter;
/**
 * @author Inbar Avital.
 * This class is in charge of the life functions.
 */
public class LivesIndicator implements Sprite {
    // members
    private Counter lifes;
    private Block block;
    /**
     * Constructor #1.
     * @param lifes - the counter of the amount of lifes this game has.
     * @param block - the block that will print the life counter.
     */
    public LivesIndicator(Counter lifes, Block block) {
        this.lifes = lifes;
    }
    /**
     * Constroctor #2.
     * @param upperLeft - the left point of the block to present the life.
     * @param width - the width of it.
     * @param height - the height of it.
     * @param color - the color of it.
     * @param lifes - the counter of the amount of lifes this game has.
     */
    public LivesIndicator(Point upperLeft, double width, double height,
            java.awt.Color color, Counter lifes) {
        this.lifes = lifes;
        this.block = new Block(upperLeft, width, height, color);
    }
    @Override
    public void drawOn(DrawSurface d) {
        block.drawOn(d);
        d.drawText((int) (this.block.getCollisionRectangle().getUpperLeft().getX()
                + this.block.getCollisionRectangle().getWidth() / 3), 20
                , String.valueOf("Life:" + lifes.getValue()), 16);
    }
    /**
     * This do something when time passes, but time does
     * not effect life currently so we will ignore this
     * function for now.
     */
    public void timePassed() {
    }
    /**
     * adds the indicator to the chosen game.
     * @param g - the game to add the indicator to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}

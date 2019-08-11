package items;

import biuoop.DrawSurface;
import geometry.Point;
import interfaces.Sprite;
import runners.GameLevel;
/**
 * @author Inbar Avital.
 * This class is in charge of the life functions.
 */
public class NameOfLevel implements Sprite {
    // members
    private String name;
    private Block block;
    /**
     * Constructor #1.
     * @param name - the name of the level.
     * @param block - the block that will print the life counter.
     */
    public NameOfLevel(String name, Block block) {
        this.name = name;
        this.block = block;
    }
    /**
     * Constructor #2.
     * @param upperLeft - the left point of the block to present the life.
     * @param width - the width of it.
     * @param height - the height of it.
     * @param color - the color of it.
     * @param name - the name of the level.
     */
    public NameOfLevel(Point upperLeft, double width, double height,
            java.awt.Color color, String name) {
        this.name = name;
        this.block = new Block(upperLeft, width, height, color);
    }
    @Override
    public void drawOn(DrawSurface d) {
        block.drawOn(d);
        d.drawText((int) (this.block.getCollisionRectangle().getUpperLeft().getX()
                + 10), 20
                , String.valueOf("Level: " + this.name), 16);
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

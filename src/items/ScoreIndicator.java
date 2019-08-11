package items;

import biuoop.DrawSurface;
import geometry.Point;
import interfaces.Sprite;
import runners.GameLevel;
import setting.Counter;

/**
 * This class is in charge of keeping the score and all
 * of the functions this score indicator can do.
 * @author Inbar Avital.
 */
public class ScoreIndicator implements Sprite {
    // members
    private Counter score;
    private Block block;
    /**
     * Constructor #1.
     * @param score - the score counter.
     * @param block - the block to present the score in.
     */
    public ScoreIndicator(Counter score, Block block) {
        this.score = score;
        this.block = block;
    }
    /**
     * Constructor #2.
     * @param upperLeft - the left upper corner of the block to present the score in.
     * @param width - the width of the block.
     * @param height - the height of the block.
     * @param color - the color of the block.
     * @param score - the score counter.
     */
    public ScoreIndicator(Point upperLeft, double width, double height,
            java.awt.Color color, Counter score) {
        this.score = score;
        this.block = new Block(upperLeft, width, height, color);
    }
    /**
     * gets a draw surface and draw the indicator according to
     * the block, the score and the draw surface it gets.
     * @param d - the draw surface.
     */
    public void drawOn(DrawSurface d) {
        block.drawOn(d);
        d.setColor(java.awt.Color.BLACK);
        d.drawText((int) (this.block.getCollisionRectangle().getUpperLeft().getX()
                + this.block.getCollisionRectangle().getWidth() / 3), 20
                , String.valueOf("Score:" + score.getValue()), 16);
    }

    /**
     * This do something when time passes, but time does
     * not effect the score currently so we will ignore this
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

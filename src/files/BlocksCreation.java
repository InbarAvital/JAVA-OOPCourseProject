package files;

import java.awt.Color;
import java.util.HashMap;

import geometry.Point;
import geometry.Rectangle;
import interfaces.BlockCreator;
import items.Block;

/**
 * Creating the blocks.
 * @author Inbar
 *
 */
public class BlocksCreation implements BlockCreator {
    //members
    private int height;
    private int width;
    private int hitPoints;
    private String fillColor;
    private Color color;
    private HashMap<Integer, String> hash;
    /**
     * Constructor.
     * @param height - the height of the block.
     * @param width - the width of the block.
     * @param hitPoints - the amount of hit points.
     * @param fillColor - the fill color.
     * @param color - the stroke color.
     * @param hash - the hash map.
     */
    public BlocksCreation(int height, int width, int hitPoints, String fillColor,
            Color color, HashMap<Integer, String> hash) {
        this.height = height;
        this.width = width;
        this.hitPoints = hitPoints;
        this.fillColor = fillColor;
        this.color = color;
        this.hash = hash;
    }
    /**
     * creates the block.
     * @param xpos - x position.
     * @param ypos - y position.
     * @return the block.
     */
    public Block create(int xpos, int ypos) {
        return new Block(new Rectangle(new Point(xpos, ypos),
                this.width, this.height), this.fillColor,
                this.hitPoints, this.color, this.hash);
    }
}

package interfaces;

import items.Block;

/**
 *
 * @author Inbar
 *
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     *
     * @param xpos - the X position
     * @param ypos - the Y position.
     * @return Block with these positions.
     */
    Block create(int xpos, int ypos);
}

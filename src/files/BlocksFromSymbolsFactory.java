package files;

import java.util.HashMap;
import java.util.Map;

import interfaces.BlockCreator;
import items.Block;
/**
 * blocks as symbols.
 * @author Inbar
 *
 */
public class BlocksFromSymbolsFactory {
    //members
    private Map<String, Integer> spacerWidths = new HashMap<String, Integer>();
    private Map<String, BlockCreator> blockCreators = new HashMap<String, BlockCreator>();
    /**
     * Empty Constructor.
     */
    public BlocksFromSymbolsFactory() {
    }
    /**
     * returns true if 's' is a valid space symbol.
     * @param s - the symbol
     * @return boolean - true if it is a valid symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }
    /**
     * returns true if 's' is a valid block symbol.
     * @param s - the symbol.
     * @return boolean - true if it is a valid symbol.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }
    /**
     * Return a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos).
     * @param s - the block's symbol.
     * @param x - x position.
     * @param y - y position.
     * @return block.
     */
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
     }
    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     * @param s - the symbol
     * @return the width in pixels.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
    /**
     * Adds a block creator.
     * @param s - the string.
     * @param creator - the Block creator.
     */
    public void addBlockCreator(String s, BlockCreator creator) {
        blockCreators.put(s, creator);
    }

    /**
     * Adds a spacer.
     * @param s - the string.
     * @param size - integer, size of spaces.
     */
    public void addSpacer(String s, Integer size) {
        spacerWidths.put(s, size);
    }
 }

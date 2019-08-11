package files;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import interfaces.LevelInformation;
import interfaces.Sprite;
import items.Block;
import setting.Velocity;

/**
 * creates the levels by hash.
 *
 * @author Inbar
 *
 */
public class LevelsByHash implements LevelInformation {
    // members
    private List<Velocity> velocities = new ArrayList<Velocity>();
    // getting in constructor
    private HashMap<String, String> hash;
    private List<String> lineBlocks;

    /**
     * Constructor.
     *
     * @param hash       - the HashMap.
     * @param lineBlocks - the blocks separated into lines.
     */
    public LevelsByHash(HashMap<String, String> hash, List<String> lineBlocks) {
        this.hash = hash;
        this.lineBlocks = lineBlocks;
    }

    /**
     * The amount of balls in the level. by returning the amount of velocities we
     * can know the amount of balls.
     *
     * @return int - the amount.
     */
    public int numberOfBalls() {
        String allVelocities = this.hash.get("ball_velocities");
        int numOfBalls = allVelocities.split(" ").length;
        return numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        String[] currentVelocities = this.hash.get("ball_velocities").split(" ");
        for (int i = 0; i < currentVelocities.length; i++) {
            this.velocities.add(Velocity.fromAngleAndSpeed(Integer.parseInt(currentVelocities[i].split(",")[0]),
                    Integer.parseInt(currentVelocities[i].split(",")[1])));
        }
        return this.velocities;
    }

    @Override
    public int paddleSpeed() {
        return Integer.parseInt(hash.get("paddle_speed"));
    }

    @Override
    public int paddleWidth() {
        return Integer.parseInt(hash.get("paddle_width"));
    }

    @Override
    public String levelName() {
        return hash.get("level_name");
    }

    @Override
    public Sprite getBackground() {
        return new Background(hash.get("background"));
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocksArray = new ArrayList<Block>();
        BlocksFromSymbolsFactory factory = null;
        // tries getting blocks definition.
        try {
            factory = BlocksDefinitionReader.fromReader(new BufferedReader(new InputStreamReader(
                    ClassLoader.getSystemClassLoader().getResourceAsStream(hash.get("block_definitions")))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // x and y beginning locations
        int x = Integer.parseInt(hash.get("blocks_start_x"));
        int y = Integer.parseInt(hash.get("blocks_start_y"));
        // all blocks. goes down each line.
        for (int i = 0; i < this.lineBlocks.size(); i++) {
            char[] currentLineOfBlocks = this.lineBlocks.get(i).toCharArray();
            // current line, goes to each block.
            for (int r = 0; r < currentLineOfBlocks.length; r++) {
                if (factory.isBlockSymbol(Character.toString(currentLineOfBlocks[r]))) {
                    Block tempBlock = factory.getBlock(Character.toString(currentLineOfBlocks[r]), x, y);
                    blocksArray.add(tempBlock);
                    x += tempBlock.getCollisionRectangle().getWidth();
                } else if (factory.isSpaceSymbol(Character.toString(currentLineOfBlocks[r]))) {
                    x += factory.getSpaceWidth(Character.toString(currentLineOfBlocks[r]));
                }
            }
            // move down to place the new block. and resets x again.
            x = Integer.parseInt(hash.get("blocks_start_x"));
            y += Integer.parseInt(hash.get("row_height"));
        }
        return blocksArray;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return Integer.parseInt(hash.get("num_blocks"));
    }
}

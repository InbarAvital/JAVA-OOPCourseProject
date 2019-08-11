package files;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Reads block's symbols.
 * @author Inbar
 *
 */
public class BlocksDefinitionReader {
    /**
     * reads from file.
     * @param reader - the reader.
     * @return the blocks as symbols.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BlocksFromSymbolsFactory blocks = new BlocksFromSymbolsFactory();
        // so that we can read each line.
        BufferedReader line = (BufferedReader) reader;
        String currentLine = null;
        // keeps level info.
        HashMap<String, String> forAllBlocks = new HashMap<String, String>();
        try {
            while ((currentLine = line.readLine()) != null) {
                String[] parts = currentLine.split(" ");
                if (parts[0].equals("default")) {
                    for (int i = 1; i < parts.length; i++) {
                        forAllBlocks.put(parts[i].split(":")[0], parts[i].split(":")[1]);
                    }
                } else if (parts[0].equals("bdef")) {
                    HashMap<String, String> lineBlocks = new HashMap<String, String>(forAllBlocks);
                    HashMap<Integer, String> fill = new HashMap<Integer, String>();
                    Color stroke = null;
                    for (int i = 1; i < parts.length; i++) {
                        lineBlocks.put(parts[i].split(":")[0], parts[i].split(":")[1]);
                    }
                    for (int r = 1; r < 10; r++) {
                        if (lineBlocks.containsKey("fill-" + r)) {
                            fill.put(r, lineBlocks.get("fill-" + r));
                        }
                    }
                    if (lineBlocks.containsKey("stroke")) {
                        stroke = ColorsParser.colorFromString(lineBlocks.get("stroke"));
                    }
                    blocks.addBlockCreator(lineBlocks.get("symbol"),
                            new BlocksCreation(Integer.parseInt(lineBlocks.get("height")),
                                    Integer.parseInt(lineBlocks.get("width")),
                                    Integer.parseInt(lineBlocks.get("hit_points")),
                                    lineBlocks.get("fill"), stroke, fill));
                } else if (parts[0].equals("sdef")) {
                    HashMap<String, String> spaceMap = new HashMap<String, String>(forAllBlocks);
                    for (int i = 1; i < parts.length; i++) {
                        spaceMap.put(parts[i].split(":")[0], parts[i].split(":")[1]);
                    }
                    blocks.addSpacer(spaceMap.get("symbol"), Integer.parseInt(spaceMap.get("width")));
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return blocks;
    }
}

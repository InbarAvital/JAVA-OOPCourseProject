package files;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import interfaces.LevelInformation;

/**
 * reads level.
 *
 * @author Inbar
 *
 */
public class LevelSpecificationReader {
    /**
     * reading levels from files.
     *
     * @param reader - the reader.
     * @return the level information list that we read from the file.
     */
    public List<LevelInformation> fromReader(BufferedReader reader) {
        List<LevelInformation> levels = new ArrayList<>();
        // so that we can read each line.
        String currentLine = null;
        try {
            currentLine = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (currentLine != null) {
            // for the level info
            List<String> levelString = new ArrayList<String>();
            List<String> lineBlocks = new ArrayList<String>();
            HashMap<String, String> hash = new HashMap<String, String>();
            // if info on the level starts
            if (currentLine.equals("START_LEVEL")) {
                // as a counter for the array
                int i = 0;
                while (!currentLine.equals("END_LEVEL")) {
                    try {
                        currentLine = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    levelString.add(currentLine);
                }
                // all the information not including the blocks
                while (!levelString.get(i++).equals("START_BLOCKS")) {
                    String[] parts = levelString.get(i - 1).split(":");
                    hash.put(parts[0], parts[1]);
                }
                String current = levelString.get(i);
                // the blocks info
                while (!current.equals("END_BLOCKS")) {
                    lineBlocks.add(levelString.get(i));
                    i++;
                    current = levelString.get(i);
                }
                LevelInformation levelInfo = new LevelsByHash(hash, lineBlocks);
                levels.add(levelInfo);
            }
            // if we did not reach the beginning yet.
            try {
                currentLine = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return levels;
    }
}

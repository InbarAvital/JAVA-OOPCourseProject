package files;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Inbar
 *
 */
public class LevelSetsFromFile {
    /**
     * reads level sets from reader.
     * @param reader - the reader.
     * @return list of level sets.
     */
    public static List<LevelSet> fromReader(Reader reader) {
        List<LevelSet> sets = new ArrayList<LevelSet>();
        LineNumberReader line = null;
        String current = "";
        try {
            line = new LineNumberReader(reader);
            String key = "a";
            String name = "a";
            String path = "a";
            while ((current = line.readLine()) != null) {
                if (line.getLineNumber() % 2 != 0) {
                    key = current.split(":")[0];
                    name = current.split(":")[1];
                } else {
                    path = current;
                    sets.add(new LevelSet(key, name, path));
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sets;
    }
}

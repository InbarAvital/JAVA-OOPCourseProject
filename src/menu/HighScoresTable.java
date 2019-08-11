package menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Inbar
 *
 */
public class HighScoresTable implements Serializable {
    // Members
    private int size;
    private List<ScoreInfo> scores;

    /**
     * Create an empty high-scores table with the specified size. The size means
     * that the table holds up to size top scores.
     *
     * @param size - the size of the scores table;
     */
    public HighScoresTable(int size) {
        this.size = size;
        // starts a new list of scores.
        this.scores = new ArrayList<ScoreInfo>();
    }

    /**
     * Adds a new score.
     *
     * @param score - the score.
     */
    public void add(ScoreInfo score) {
        boolean flag = false;
        List<ScoreInfo> highScores = new ArrayList<ScoreInfo>();
        for (ScoreInfo current : this.scores) {
            if (current.getScore() < score.getScore() && !flag) {
                highScores.add(score);
                flag = true;
            }
            highScores.add(current);
        }
        if (!flag) {
            highScores.add(score);
        }
        if (highScores.size() == 0) {
            highScores.add(score);
        }
        this.scores = highScores;
        if (this.size < this.scores.size()) {
            this.scores.remove(size);
        }
    }

    /**
     * @return the table size.
     */
    public int size() {
        return this.size;
    }

    /**
     * Return the current high scores. The list is sorted such that the highest
     * scores come first.
     *
     * @return the list of high scores
     */
    public List<ScoreInfo> getHighScores() {
        // notice that the list is already sorted!.
        return this.scores;
    }

    /**
     * return the rank of the current score: where will it be on the list if added?
     * Rank 1 means the score will be highest on the list. Rank `size` means the
     * score will be lowest. Rank > `size` means the score is too low and will not
     * be added to the list.
     *
     * @param score - the score to place.
     * @return the rank of the current score.
     */
    public int getRank(int score) {
        int spot = 1;
        for (ScoreInfo current : this.scores) {
            // if this score is bigger than the current score
            // we are looking at.
            if (current.getScore() <= score) {
                return spot;
            }
            spot++;
        }
        // last
        return spot;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.scores.clear();
    }

    /**
     * Load table data from file.
     * Current table data is cleared.
     * @param tableFile - the file table.
     * @throws IOException -ex.
     */
    public void load(File tableFile) throws IOException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(tableFile));
        try {
            HighScoresTable highScores = (HighScoresTable) objectInputStream.readObject();
            this.size = highScores.size();
            this.scores = highScores.getHighScores();
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + tableFile.getName());
        } finally {
            objectInputStream.close();
        }
    }

    /**
     * Save table data to the specified file.
     *
     * @param tableFile - the file.
     * @throws IOException - ex.
     */
    public void save(File tableFile) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(tableFile));
        try {
            objectOutputStream.writeObject(this);
        } finally {
            objectOutputStream.close();
        }
    }

    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     * @param tableFile - the table file.
     * @return the rable.
     */
    public static HighScoresTable loadFromFile(File tableFile) {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(tableFile));
            HighScoresTable highScores = (HighScoresTable) objectInputStream.readObject();
            return highScores;
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + tableFile.getName());
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + tableFile.getName());
        } catch (IOException e) { // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + tableFile.getName());
            }
        }
        return new HighScoresTable(0);
    }
}

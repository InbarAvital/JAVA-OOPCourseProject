package menu;

import java.io.Serializable;

/**
 * @author Inbar Avital
 * keeps name and score of a player - the info of the
 * player's score.
 */
public class ScoreInfo implements Serializable {
    // Members
    private String name;
    private int score;

    /**
     * Constructor.
     *
     * @param name  - the name of the player.
     * @param score - the score of the player.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * @return the name of the player.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the score of the player.
     */
    public int getScore() {
        return this.score;
    }
}
package menu;

import biuoop.DrawSurface;
import interfaces.Animation;

/**
 *
 * @author Inbar
 *
 */
public class HighScoresAnimation implements Animation {
    // members
    private HighScoresTable scores;

    /**
     * Constructor.
     *
     * @param scores - the scores table
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        d.drawText(15, 30, "<To return press space>", 30);
        d.setColor(java.awt.Color.RED);
        d.drawText(15, 50, " ____________________________________", 41);
        d.drawText(20, 90, "         Rank                 Player                 Score         ", 40);
        d.drawText(15, 95, " ____________________________________", 41);
        d.drawText(20, 90, "|", 60);
        d.drawText(260, 90, "|", 60);
        d.drawText(520, 90, "|", 60);
        d.drawText(775, 90, "|", 60);
        for (int i = 0; i < this.scores.size(); i++) {
            d.drawText(20, 145 + i * 45, "|", 60);
            d.drawText(260, 145 + i * 45, "|", 60);
            d.drawText(520, 145 + i * 45, "|", 60);
            d.drawText(775, 145 + i * 45, "|", 60);
            d.drawText(20, 115 + i * 45, "|", 60);
            d.drawText(260, 115 + i * 45, "|", 60);
            d.drawText(520, 115 + i * 45, "|", 60);
            d.drawText(775, 115 + i * 45, "|", 60);
            d.drawText(15, 140 + i * 45, " ____________________________________", 41);
        }
        int j = 0;
        d.setColor(java.awt.Color.BLACK);
        for (ScoreInfo i : this.scores.getHighScores()) {
            d.drawText(140, 135 + j * 45, ((Integer) this.scores.getRank(i.getScore())).toString(), 35);
            d.drawText(280, 135 + j * 45, i.getName(), 30);
            d.drawText(550, 135 + j * 45, ((Integer) i.getScore()).toString(), 35);
            j++;
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
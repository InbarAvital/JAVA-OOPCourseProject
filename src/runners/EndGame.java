package runners;

import biuoop.DrawSurface;
import interfaces.Animation;

/**
 * @author Inbar
 * The end of the game - either a win or a lose.
 */
public class EndGame implements Animation {
    private boolean win;
    private int score;
    private boolean stop;
    private static final int X = 335;
    private static final int Y = 100;
    /**
     * Constructor.
     * @param win - tells if wins or loses.
     * @param score - the total score in the end of the game.
     */
    public EndGame(boolean win, int score) {
        this.win = win;
        this.score = score;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(java.awt.Color.WHITE);
        d.setColor(java.awt.Color.YELLOW);
        d.fillRectangle(X + 40, Y, 50, 130);
        d.fillRectangle(X + 20, Y + 10, 20, 110);
        d.fillRectangle(X + 10, Y + 20, 10, 90);
        d.fillRectangle(X + 90, Y + 10, 20, 110);
        d.fillRectangle(X + 110, Y + 20, 10, 90);
        d.setColor(java.awt.Color.ORANGE);
        d.fillRectangle(X + 40, Y, 50, 10);
        d.fillRectangle(X + 20, Y + 10, 20, 10);
        d.fillRectangle(X + 10, Y + 20, 10, 20);
        d.fillRectangle(X, Y + 40, 10, 50);
        d.fillRectangle(X + 10, Y + 90, 10, 20);
        d.fillRectangle(X + 20, Y + 110, 20, 10);
        d.fillRectangle(X + 40, Y + 120, 50, 10);
        d.fillRectangle(X + 90, Y + 10, 20, 10);
        d.fillRectangle(X + 110, Y + 20, 10, 20);
        d.fillRectangle(X + 120, Y + 40, 10, 50);
        d.fillRectangle(X + 110, Y + 90, 10, 20);
        d.fillRectangle(X + 90, Y + 110, 20, 10);
        if (win) {
            d.setColor(java.awt.Color.BLACK);
            // eyes
            d.fillRectangle(X + 30, Y + 40, 10, 20);
            d.fillRectangle(X + 40, Y + 30, 10, 20);
            d.fillRectangle(X + 50, Y + 40, 10, 20);
            d.fillRectangle(X + 70, Y + 40, 10, 20);
            d.fillRectangle(X + 80, Y + 30, 10, 20);
            d.fillRectangle(X + 90, Y + 40, 10, 20);
            // mouth
            d.fillRectangle(X + 30, Y + 80, 10, 10);
            d.fillRectangle(X + 90, Y + 80, 10, 10);
            d.fillRectangle(X + 40, Y + 90, 50, 10);
            d.fillRectangle(X + 50, Y + 100, 30, 10);
            d.setColor(java.awt.Color.WHITE);
            d.fillRectangle(X + 40, Y + 80, 50, 10);
            // text
            d.drawText(100, d.getHeight() / 2, "You Win! Your score is " + this.score, 50);
        } else {
            d.setColor(java.awt.Color.BLUE);
            // crying
            d.fillRectangle(X + 30, Y + 60, 20, 50);
            d.fillRectangle(X + 80, Y + 60, 20, 50);
            d.setColor(java.awt.Color.BLACK);
            // eyes
            d.fillRectangle(X + 30, Y + 30, 10, 10);
            d.fillRectangle(X + 20, Y + 40, 10, 10);
            d.fillRectangle(X + 30, Y + 50, 20, 10);
            d.fillRectangle(X + 90, Y + 30, 10, 10);
            d.fillRectangle(X + 100, Y + 40, 10, 10);
            d.fillRectangle(X + 80, Y + 50, 20, 10);
            // mouth
            d.fillRectangle(X + 60, Y + 90, 10, 10);
            d.setColor(java.awt.Color.WHITE);
            // text
            d.drawText(100, d.getHeight() / 2, "Game Over. Your score is " + this.score, 50);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}

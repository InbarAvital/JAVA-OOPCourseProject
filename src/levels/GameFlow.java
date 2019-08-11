package levels;

import java.util.List;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;
import menu.HighScoresAnimation;
import menu.HighScoresTable;
import menu.ScoreInfo;
import runners.AnimationRunner;
import runners.EndGame;
import runners.GameLevel;
import runners.KeyPressStoppableAnimation;
import setting.Counter;

/**
 *
 * @author Inbar Avital
 *
 */
public class GameFlow {
    // memebers
    // keyboard
    private biuoop.KeyboardSensor keyboard;
    // running
    private AnimationRunner runner;
    // score counter
    private Counter score;
    // life counter
    private Counter life;
    // score table in the end of the game
    private HighScoresTable table;

    /**
     * Constructor.
     *
     * @param ar    - the animation runner to run.
     * @param ks    - the keyboard to use.
     * @param table - the high score table.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, HighScoresTable table) {
        this.keyboard = ks;
        this.runner = ar;
        this.score = new Counter();
        this.life = new Counter();
        this.life.increase(7);
        this.table = table;
    }

    /**
     * Run the levels.
     *
     * @param levels - the levels to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean win = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboard, this.runner, this.life, this.score);
            level.initialize();
            while (this.life.getValue() != 0 && level.blocksLeft() != 0) {
                level.playOneTurn();
                // if we broke all blocks:
                if (level.blocksLeft() == 0) {
                    // add 100 points to the score.
                    this.score.increase(100);
                    // end the game.
                }
                // if there are no more balls:
                if (level.ballsLeft() == 0) {
                    // decrease life and reset blocks counter.
                    life.decrease(1);
                }
            }
            if (this.life.getValue() == 0) {
                win = false;
                break;
            }
        }
        runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                new EndGame(win, this.score.getValue())));
        if (this.table.getRank(this.score.getValue()) <= this.table.size()) {
            DialogManager dialog = runner.getGUI().getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo score1 = new ScoreInfo(name, this.score.getValue());
            this.table.add(score1);
        }
        runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(this.table)));
    }
}
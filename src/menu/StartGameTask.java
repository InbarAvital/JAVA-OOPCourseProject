package menu;

import java.util.List;
import interfaces.LevelInformation;
import interfaces.Task;
import levels.GameFlow;

/**
 *
 * @author Inbar
 *
 */
public class StartGameTask implements Task<Void> {
    // members
    private GameFlow game;
    private List<LevelInformation> levels;

    /**
     * Constructor.
     *
     * @param game   - the game to run.
     * @param levels - the levels.
     */
    public StartGameTask(GameFlow game, List<LevelInformation> levels) {
        this.game = game;
        this.levels = levels;
    }

    /**
     * Runs.
     *
     * @return void object.
     */
    public Void run() {
        this.game.runLevels(this.levels);
        return null;
    }
}

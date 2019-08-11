package menu;

import biuoop.GUI;
import interfaces.Task;

/**
 *
 * @author Inbar Quit task.
 */
public class QuitTask implements Task<Void> {
    // members
    private GUI gui;

    /**
     * Constructor.
     *
     * @param gui - the game's gui.
     */
    public QuitTask(GUI gui) {
        this.gui = gui;
    }

    /**
     * Runs.
     *
     * @return void object.
     */
    public Void run() {
        System.exit(0);
        return null;
    }
}

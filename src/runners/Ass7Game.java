package runners;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import files.LevelSet;
import files.LevelSetsFromFile;
import files.LevelSpecificationReader;
import interfaces.LevelInformation;
import interfaces.Menu;
import interfaces.Task;
import levels.GameFlow;
import levels.MenuAnimation;
import menu.HighScoresAnimation;
import menu.HighScoresTable;
import menu.QuitTask;
import menu.StartGameTask;
import setting.ShowHiScoresTask;

/**
 * This class is all about running the game.
 *
 * @author Inbar Avital
 */
public class Ass7Game {
    // Screen Size
    private static final int WIDTH = 800;
    private static final int HEIGTH = 600;

    /**
     * This main function runs the game.
     *
     * @param args - (Probably empty) input from user.
     */
    public static void main(String[] args) {
        java.io.File file = new java.io.File("highscores");
        HighScoresTable table;
        if (file.exists() && !file.isDirectory()) {
            table = HighScoresTable.loadFromFile(file);
        } else {
            table = new HighScoresTable(8);
            try {
                table.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        AnimationRunner runner = new AnimationRunner(new GUI("Arkanoid", WIDTH, HEIGTH));
        while (true) {
            GameFlow game = new GameFlow(runner, runner.getGUI().getKeyboardSensor(), table);
            Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid", runner.getGUI().getKeyboardSensor());
            Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>("Arkanoid", runner.getGUI().getKeyboardSensor());;
            setSubMenu(subMenu, game, args);
            menu.addSubMenu("s", "Start Game", subMenu);
            menu.addSelection("h", "High Scores",
                    new ShowHiScoresTask(runner, new KeyPressStoppableAnimation(runner.getGUI().getKeyboardSensor(),
                            KeyboardSensor.SPACE_KEY, new HighScoresAnimation(table))));
            menu.addSelection("q", "Quit", new QuitTask(runner.getGUI()));
            runner.run(menu);
            // wait for user selection
            Task<Void> task = null;
            if (menu.getStatus() == null) {
                runner.run(subMenu);
                task = (Task<Void>) subMenu.getStatus();
            } else {
                task = (Task<Void>) menu.getStatus();
            }
            try {
                table.save(file);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            task.run();
        }
    }
    /**
     * sets the sub menu.
     * @param sub - the sub menu.
     * @param game - the game.
     * @param args - the args getting from main.
     * @return the sub menu.
     */
    public static Menu<Task<Void>> setSubMenu(Menu<Task<Void>> sub, GameFlow game, String[] args) {
        List<LevelSet> sets = new ArrayList<LevelSet>();
        if (args.length != 0) {
            sets = LevelSetsFromFile.fromReader(new BufferedReader(
                    new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]))));
        } else {
            sets = LevelSetsFromFile.fromReader(new BufferedReader(
                    new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt"))));
        }
        for (LevelSet i : sets) {
            sub.addSelection(i.getKey(), i.getMessage(), new StartGameTask(game, setupLevels(i.getPath())));
        }
        return sub;
    }
    /**
     * @param s - string path.
     * @return the levels.
     */
    public static List<LevelInformation> setupLevels(String s) {
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        LevelSpecificationReader reader = new LevelSpecificationReader();
        levels = reader.fromReader(
                new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(s))));
        return levels;
    }
}
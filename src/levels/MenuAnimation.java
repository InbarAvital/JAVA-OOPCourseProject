package levels;

import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import interfaces.Menu;
import setting.Selections;
/**
 *
 * @author Inbar
 *
 * @param <T>
 */
public class MenuAnimation<T> implements Menu<T> {
    // members
    private String name;
    private List<Selections<T>> selections = new ArrayList<Selections<T>>();
    private T status = null;
    private Menu<T> statusSub = null;
    private biuoop.KeyboardSensor key;
    private List<Selections<Menu<T>>> subSelections = new ArrayList<Selections<Menu<T>>>();

    /**
     * Constructor.
     * @param name - the name.
     * @param key - the key.
     */
    public MenuAnimation(String name, biuoop.KeyboardSensor key) {
        this.name = name;
        this.key = key;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int j = 0;
        d.setColor(java.awt.Color.RED);
        d.drawText(25, 75, this.name, 50);
        d.drawText(15, 95, " ____________________________________", 41);
        d.setColor(java.awt.Color.BLACK);
        d.drawText(23, 73, this.name, 52);
        d.drawText(15, 92, " ____________________________________", 41);
        for (Selections<Menu<T>> i : subSelections) {
            d.drawText(15, 140 + j * 45, "(" + i.getKey() + ") " + i.getMessage(), 30);
            j++;
        }
        for (Selections<T> i : selections) {
            d.drawText(15, 140 + j * 45, "(" + i.getKey() + ") " + i.getMessage(), 30);
            j++;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean shouldStop() {
        for (Selections<Menu<T>> i : subSelections) {
            if (this.key.isPressed(i.getKey())) {
                this.statusSub = i.getVal();
                return true;
            }
        }
        for (Selections<T> i : selections) {
            if (this.key.isPressed(i.getKey())) {
                status = i.getVal();
                return true;
            }
        }
        return false;
    }

    @Override
    public void addSelection(String key1, String message, T returnVal) {
        selections.add(new Selections<T>(key1, message, returnVal));
    }

    @Override
    public T getStatus() {
        return this.status;
    }

    /**
     * @return sub status
     */
    public Menu<T> getStatusSub() {
        return this.statusSub;
    }

    @Override
    public void addSubMenu(String key1, String message, Menu<T> subMenu) {
        subSelections.add(new Selections<Menu<T>>(key1, message, subMenu));
    }
}

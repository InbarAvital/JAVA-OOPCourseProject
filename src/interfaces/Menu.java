package interfaces;

/**
 *
 * @author Inbar
 *
 * @param <T> - menu object
 */
public interface Menu<T> extends Animation {
    /**
     * adds selection.
     * @param key - the key.
     * @param message - the message.
     * @param returnVal - the value.
     */
    void addSelection(String key, String message, T returnVal);
    /**
     * @return T object.
     */
    T getStatus();
    /**
     * @param key - the key.
     * @param message - the message.
     * @param subMenu - the sub menu.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
 }
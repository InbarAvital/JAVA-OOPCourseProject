package setting;

/**
 *
 * @author Inbar
 *
 * @param <T>
 */
public class Selections<T> {
    // members
    private String key;
    private String message;
    private T returnVal;

    /**
     * Constuctor.
     *
     * @param <T>
     * @param key       - the key to press for the selection.
     * @param message   - the message on screen.
     * @param returnVal - the val returned when selecting.
     */
    public Selections(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }

    /**
     * @return the key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return the message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @return the val.
     */
    public T getVal() {
        return this.returnVal;
    }
}

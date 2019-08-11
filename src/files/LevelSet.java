package files;

/**
 *
 * @author Inbar
 *
 */
public class LevelSet {
    // members
    private String key;
    private String message;
    private String path;

    /**
     * Constructor.
     *
     * @param key     - the key.
     * @param message - the message;
     * @param path    - the level info path.
     */
    public LevelSet(String key, String message, String path) {
        this.key = key;
        this.message = message;
        this.path = path;
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
     * @return the path.
     */
    public String getPath() {
        return this.path;
    }
}

package interfaces;
/**
 *
 * @author Inbar
 *
 * @param <T>
 */
public interface Task<T> {
    /**
     * run.
     * @return T object.
     */
    T run();
}
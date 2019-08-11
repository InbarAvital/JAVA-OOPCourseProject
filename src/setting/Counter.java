package setting;

/**
 * this class in charge of the counter functions.
 * @author Inbar Avital.
 *
 */
public class Counter {
    // members.
    private int count;
    /**
     * default constructor.
     */
    public Counter() {
        this.count = 0;
    }
    /**
     *  add number to current count.
     *  @param number - the number to increase by.
     */
    public void increase(int number) {
        this.count += number;
    }
    /**
     *  subtract number from current count.
     *  @param number - the number to decrease by.
     */
    public void decrease(int number) {
        this.count -= number;
    }
    /**
     * get current count.
     * @return int - the value of the counter.
     */
    public int getValue() {
        return this.count;
    }
 }

// Alon Livne (ID: 208688762)
package gameFlow;

/**
 * The Counter class represents a simple counter that can be incremented and decremented.
 */
public class Counter {
    private int count;

    /**
     * Constructs a new Counter instance with an initial count of 0.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Increases the counter by the given number.
     *
     * @param number The number to increase the counter by.
     */
    void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * Decreases the counter by the given number.
     *
     * @param number The number to decrease the counter by.
     */
    void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * Gets the current value of the counter.
     *
     * @return The current count.
     */
    int getValue() {
        return this.count;
    }
}

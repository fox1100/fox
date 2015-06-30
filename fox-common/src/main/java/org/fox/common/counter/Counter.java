package org.fox.common.counter;

/**
 * @author jie.huang
 *         Date: 2015/6/30
 *         Time: 15:22
 */
public class Counter {
    private final LongAdder count;

    public Counter() {
        this.count = new LongAdder();
    }

    /**
     * Increment the counter by one.
     */
    public void inc() {
        inc(1);
    }

    /**
     * Increment the counter by {@code n}.
     *
     * @param n the amount by which the counter will be increased
     */
    public void inc(long n) {
        count.add(n);
    }

    /**
     * Decrement the counter by one.
     */
    public void dec() {
        dec(1);
    }

    /**
     * Decrement the counter by {@code n}.
     *
     * @param n the amount by which the counter will be decreased
     */
    public void dec(long n) {
        count.add(-n);
    }

    /**
     * Returns the counter's current value.
     *
     * @return the counter's current value
     */
    public long getCount() {
        return count.sum();
    }
}

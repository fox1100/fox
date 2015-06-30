package org.fox.server.report;

/**
 * @author jie.huang
 *         Date: 2015/6/30
 *         Time: 16:48
 */
public enum PeriodType {
    HOUR(60);
    private int period;

    PeriodType(int period) {
        this.period = period;
    }

    public int period() {
        return this.period;
    }
}

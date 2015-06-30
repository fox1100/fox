package org.fox.server.report;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.fox.common.counter.Counter;

/**
 * @author jie.huang
 *         Date: 2015/6/30
 *         Time: 16:45
 */
public class Period {
    private String name;
    private Counter[] counters;
    private PeriodType periodType;

    public Period(String name, PeriodType periodType) {
        this.name = name;
        this.periodType = periodType;
        int period = this.periodType.period();
        this.counters = new Counter[period];
        for (int i = 0; i < period; i++) {
            this.counters[i] = new Counter();
        }
    }

    public void inc(int period, long value) {
        counters[period].inc(value);
    }

    public String getName() {
        return name;
    }

    public Counter[] getCounters() {
        return counters;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public JSONArray toJSON() {
        JSONArray counter = new JSONArray();
        for (Counter counter1 : counters) {
            counter.add(counter1.getCount());
        }
        return counter;
    }
}

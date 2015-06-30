package org.fox.server.report.os;

import com.alibaba.fastjson.JSONObject;
import org.fox.server.report.Period;
import org.fox.server.report.PeriodType;
import org.fox.server.report.Report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jie.huang
 *         Date: 2015/6/29
 *         Time: 14:28
 */
public class OSReport extends Report {
    private Map<String,Period> periodMap;

    public OSReport() {
        this.periodMap = new HashMap<>();
    }

    public void inc(String name, int period, long value) {
        Period periodObj = periodMap.get(name);
        if (periodObj == null) {
            periodObj = new Period(name, PeriodType.HOUR);
            periodMap.put(name, periodObj);
        }
        periodObj.inc(period,value);
    }


    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        for (Period period : periodMap.values()) {
            jsonObject.put(period.getName(), period.toJSON());
        }
        return jsonObject.toString();
    }
}

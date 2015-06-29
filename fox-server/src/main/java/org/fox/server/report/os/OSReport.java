package org.fox.server.report.os;

import org.fox.common.message.monitor.os.OSStats;
import org.fox.server.report.Report;

/**
 * @author jie.huang
 *         Date: 2015/6/29
 *         Time: 14:28
 */
public class OSReport extends Report{
    private OSStats osStats;

    public OSStats getOsStats() {
        return osStats;
    }

    public void setOsStats(OSStats osStats) {
        this.osStats = osStats;
    }
}

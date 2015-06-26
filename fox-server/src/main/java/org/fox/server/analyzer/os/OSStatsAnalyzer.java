package org.fox.server.analyzer.os;

import org.fox.common.message.Message;
import org.fox.common.message.monitor.os.OSStats;
import org.fox.server.analyzer.Analyzer;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 17:18
 */
public class OSStatsAnalyzer implements Analyzer{
    @Override
    public void process(Message message) {
        if (message instanceof OSStats) {
            System.out.println(message);
        }
    }
}

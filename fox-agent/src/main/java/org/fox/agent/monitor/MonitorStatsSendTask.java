package org.fox.agent.monitor;

import org.fox.agent.message.io.TcpSocketSender;
import org.fox.agent.monitor.os.OSStatsCollector;
import org.fox.common.message.monitor.os.OSStats;
import org.fox.common.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 14:23
 */
public class MonitorStatsSendTask implements Runnable {
    private final static Logger LOGGER = LoggerFactory.getLogger(MonitorStatsSendTask.class);
    private long interval = 60 * 1000;// 1 minute
    private OSStatsCollector osStatsCollector;
    private Calendar calendar;
    private TcpSocketSender sender;

    public MonitorStatsSendTask() {
        osStatsCollector = new OSStatsCollector();
        calendar = Calendar.getInstance();
        sender = new TcpSocketSender();
    }

    @Override
    public void run() {
        while (true) {
            int second = calendar.get(Calendar.SECOND);
            if (second < 2 || second > 58) {
                ThreadUtil.sleepSecond(1);
            } else {
                break;
            }
        }
        while (true) {
            long start = System.currentTimeMillis();
            try {
                OSStats osStats = osStatsCollector.getOSStats();
                System.out.println(osStats);
                sender.send(osStats);
            } catch (Exception e) {
                LOGGER.warn("Collect os stats error:", e);
            }
            long elapsed = System.currentTimeMillis() - start;

            if (elapsed < interval) {
                ThreadUtil.sleep(interval - elapsed);
            }
        }
    }

    public static void main(String[] args) {
        MonitorStatsSendTask monitorStatsSendTask = new MonitorStatsSendTask();
        Thread t = new Thread(monitorStatsSendTask);
        t.start();
    }
}

package org.fox.server.analyzer.os;

import org.fox.common.message.Message;
import org.fox.common.message.monitor.os.OSStats;
import org.fox.server.analyzer.Analyzer;
import org.fox.server.report.Report;
import org.fox.server.report.os.OSReport;
import org.fox.server.storage.Storage;
import org.fox.server.storage.StorageFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 17:18
 */
public class OSStatsAnalyzer implements Analyzer {
    private Storage<String, Report> memStore;

    public OSStatsAnalyzer() {
        memStore = StorageFactory.getMemoryStorage();
    }

    @Override
    public void process(Message message) {
        if (message instanceof OSStats) {
            OSStats osStats = (OSStats) message;
            System.out.println(osStats);
            OSReport report = new OSReport();
            report.setOsStats(osStats);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(osStats.getLogTime());
            String dateStr= format.format(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            String key  = dateStr+"/"+hour+"/"+osStats.getApplicationName();
            memStore.store(key, report);
            System.out.println(message);
        }
    }
}

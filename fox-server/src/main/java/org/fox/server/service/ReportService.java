package org.fox.server.service;

import org.fox.server.report.Report;

/**
 * @author jie.huang
 *         Date: 2015/6/30
 *         Time: 10:44
 */
public interface ReportService {
    Report queryReport(String date, String hour, String app);
}

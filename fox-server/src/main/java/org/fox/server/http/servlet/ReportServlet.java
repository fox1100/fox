package org.fox.server.http.servlet;

import org.fox.server.report.Report;
import org.fox.server.storage.Storage;
import org.fox.server.storage.StorageFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jie.huang
 *         Date: 2015/6/29
 *         Time: 18:27
 */
@WebServlet(urlPatterns = "/r", asyncSupported = true)
public class ReportServlet extends AbstractServlet {
    @Override
    public Object query(HttpServletRequest req, HttpServletResponse resp) {
        String date = req.getParameter("d");
        String hour = req.getParameter("h");
        String appName = req.getParameter("a");
        Storage<String, Report> storage = StorageFactory.getMemoryStorage();
        return storage.get(date + "/" + hour + "/" + appName);
    }

}

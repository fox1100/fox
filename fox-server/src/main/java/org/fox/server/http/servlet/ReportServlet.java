package org.fox.server.http.servlet;

import com.alibaba.fastjson.JSON;
import org.fox.server.report.Report;
import org.fox.server.storage.Storage;
import org.fox.server.storage.StorageFactory;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author jie.huang
 *         Date: 2015/6/29
 *         Time: 18:27
 */
@WebServlet(urlPatterns = "/r", asyncSupported = true)
public class ReportServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final String date = req.getParameter("date");
        final String hour = req.getParameter("hour");
        final String appName = req.getParameter("appName");
        final AsyncContext asyncCtx = req.startAsync();
        asyncCtx.setTimeout(9000);

        getExecutor(req).execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Storage<String, Report> storage = StorageFactory.getMemoryStorage();
                    Report report = storage.get(date + "/" + hour + "/" + appName);
                    PrintWriter out = null;
                    out = resp.getWriter();
                    out.println(JSON.toJSON(report));
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    asyncCtx.complete();
                }
            }
        });
    }
}

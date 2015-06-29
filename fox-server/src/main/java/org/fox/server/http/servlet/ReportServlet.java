package org.fox.server.http.servlet;

import com.alibaba.fastjson.JSON;
import org.fox.server.report.Report;
import org.fox.server.storage.Storage;
import org.fox.server.storage.StorageFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author jie.huang
 *         Date: 2015/6/29
 *         Time: 18:27
 */
@WebServlet(urlPatterns = "/r")
public class ReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = req.getParameter("date");
        String hour = req.getParameter("hour");
        String appName = req.getParameter("appName");
        Storage<String, Report> storage = StorageFactory.getMemoryStorage();
        Report report = storage.get(date + "/" + hour + "/" + appName);
        PrintWriter out = resp.getWriter();
        out.println(JSON.toJSON(report));
    }
}

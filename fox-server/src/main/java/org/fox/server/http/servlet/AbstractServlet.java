package org.fox.server.http.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author jie.huang
 *         Date: 2015/6/30
 *         Time: 9:59
 */
public class AbstractServlet extends HttpServlet {
    public ThreadPoolExecutor getExecutor(HttpServletRequest req) {
        return (ThreadPoolExecutor) req.getServletContext().getAttribute("executor");
    }
}

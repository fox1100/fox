package org.fox.server.http.servlet;

import com.alibaba.fastjson.JSON;
import org.fox.server.http.listener.ProcessorListener;
import org.fox.server.report.Report;
import org.fox.server.storage.Storage;
import org.fox.server.storage.StorageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author jie.huang
 *         Date: 2015/6/30
 *         Time: 9:59
 */
public abstract class AbstractServlet extends HttpServlet {
    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractServlet.class);

    protected ThreadPoolExecutor getExecutor(HttpServletRequest req) {
        return (ThreadPoolExecutor) req.getServletContext().getAttribute("executor");
    }

    public Object query(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final AsyncContext asyncCtx = req.startAsync();
        asyncCtx.setTimeout(60 * 1000);
        asyncCtx.addListener(new ProcessorListener());
        getExecutor(req).execute(new Runnable() {
            @Override
            public void run() {
                ServletOutputStream stream = null;
                try {
                    stream = resp.getOutputStream();
                    resp.addHeader("content-type", "application/json");
                    Object result = query(req, resp);
                    if (result != null) {
                        resp.setStatus(HttpServletResponse.SC_OK);
                        stream.print(result.toString());
                    } else {
                        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    }
                } catch (Throwable e) {
                    LOGGER.error("Do query error:", e);
                } finally {
                    try {
                        if (stream != null) {
                            stream.close();
                        }
                    } catch (IOException e) {
                        LOGGER.error("Close http output stream error:", e);
                    }
                    asyncCtx.complete();
                }
            }
        });
    }
}

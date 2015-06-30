package org.fox.server.http.listener;

import org.fox.common.util.WorkThreadFactory;
import org.fox.server.message.io.TcpSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 15:34
 */
@WebListener
public class ServerListener implements ServletContextListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        TcpSocketServer.getInstance().start(63100);
        LOGGER.info("Create work processor thread pool.");
        ThreadPoolExecutor executor = new ThreadPoolExecutor(128, 512, 1, TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(1024), new WorkThreadFactory("fox-work-thread"));
        servletContextEvent.getServletContext().setAttribute("executor", executor);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        TcpSocketServer.getInstance().shutdown();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) servletContextEvent.getServletContext().getAttribute("executor");
        if (executor != null) {
            LOGGER.info("Start shutdown work processor thread pool.");
            executor.shutdown();
        }
    }
}

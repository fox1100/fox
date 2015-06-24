package org.fox.server.http.listener;

import org.fox.server.message.io.TcpSocketServer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 15:34
 */
public class ServerListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        TcpSocketServer.getInstance().start(63100);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        TcpSocketServer.getInstance().shutdown();
    }
}

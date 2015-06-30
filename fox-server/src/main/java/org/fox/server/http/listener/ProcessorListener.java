package org.fox.server.http.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import java.io.IOException;

/**
 * @author jie.huang
 *         Date: 2015/6/30
 *         Time: 11:13
 */
public class ProcessorListener implements AsyncListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProcessorListener.class);

    public void onComplete(AsyncEvent asyncEvent) throws IOException {
        System.out.println(asyncEvent.getSuppliedResponse());
    }

    @Override
    public void onTimeout(AsyncEvent asyncEvent) throws IOException {
        System.out.println("timeout");
    }

    @Override
    public void onError(AsyncEvent asyncEvent) throws IOException {
        LOGGER.error("Process async error: ", asyncEvent.getThrowable());
    }

    @Override
    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {

    }
}

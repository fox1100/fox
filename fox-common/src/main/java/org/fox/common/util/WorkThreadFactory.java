package org.fox.common.util;

import java.util.concurrent.ThreadFactory;

/**
 * @author jie.huang
 *         Date: 2015/6/30
 *         Time: 9:56
 */
public class WorkThreadFactory implements ThreadFactory {
    private String baseName;
    private int threadNo = 0;
    private boolean isDaemon = false;

    public WorkThreadFactory(String baseName, int threadNo, boolean isDaemon) {
        this.baseName = baseName;
        this.threadNo = threadNo;
        this.isDaemon = isDaemon;
    }

    public WorkThreadFactory(String baseName, int threadNo) {
        this(baseName, threadNo, false);
    }

    public WorkThreadFactory(String baseName) {
        this(baseName, 0);
    }

    @Override
    public Thread newThread(Runnable run) {
        final Thread thread = new Thread(run);
        thread.setName(baseName + "-worker-" + (threadNo++));
        thread.setDaemon(isDaemon);
        return thread;
    }
}

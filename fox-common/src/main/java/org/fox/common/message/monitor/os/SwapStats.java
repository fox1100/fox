package org.fox.common.message.monitor.os;

import org.fox.common.message.Message;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 14:06
 */
public class SwapStats extends Message {
    private long used;
    private long free;
    private long pageIn;
    private long pageOut;

    public long getUsed() {
        return used;
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public long getFree() {
        return free;
    }

    public void setFree(long free) {
        this.free = free;
    }

    public long getPageIn() {
        return pageIn;
    }

    public void setPageIn(long pageIn) {
        this.pageIn = pageIn;
    }

    public long getPageOut() {
        return pageOut;
    }

    public void setPageOut(long pageOut) {
        this.pageOut = pageOut;
    }

    @Override
    public String toString() {
        return "SwapStats{" +
                "used=" + used +
                ", free=" + free +
                ", pageIn=" + pageIn +
                ", pageOut=" + pageOut +
                '}';
    }
}

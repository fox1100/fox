package org.fox.common.message.monitor.os;

import org.fox.common.message.Message;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 14:00
 */
public class FileSystemStats extends Message {
    private long free;
    private long used;
    private long diskReads;
    private long diskWrites;
    private long diskReadBytes;
    private long diskWriteBytes;
    private double diskQueue;
    private double usePercent;

    public long getFree() {
        return free;
    }

    public void setFree(long free) {
        this.free = free;
    }

    public long getUsed() {
        return used;
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public long getDiskReads() {
        return diskReads;
    }

    public void setDiskReads(long diskReads) {
        this.diskReads = diskReads;
    }

    public long getDiskWrites() {
        return diskWrites;
    }

    public void setDiskWrites(long diskWrites) {
        this.diskWrites = diskWrites;
    }

    public long getDiskReadBytes() {
        return diskReadBytes;
    }

    public void setDiskReadBytes(long diskReadBytes) {
        this.diskReadBytes = diskReadBytes;
    }

    public long getDiskWriteBytes() {
        return diskWriteBytes;
    }

    public void setDiskWriteBytes(long diskWriteBytes) {
        this.diskWriteBytes = diskWriteBytes;
    }

    public double getDiskQueue() {
        return diskQueue;
    }

    public void setDiskQueue(double diskQueue) {
        this.diskQueue = diskQueue;
    }

    public double getUsePercent() {
        return usePercent;
    }

    public void setUsePercent(double usePercent) {
        this.usePercent = usePercent;
    }

    @Override
    public String toString() {
        return "FileSystemStats{" +
                "free=" + free +
                ", used=" + used +
                ", diskReads=" + diskReads +
                ", diskWrites=" + diskWrites +
                ", diskReadBytes=" + diskReadBytes +
                ", diskWriteBytes=" + diskWriteBytes +
                ", diskQueue=" + diskQueue +
                ", usePercent=" + usePercent +
                '}';
    }
}

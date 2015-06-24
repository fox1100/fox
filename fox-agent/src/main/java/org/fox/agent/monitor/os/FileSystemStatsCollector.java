package org.fox.agent.monitor.os;

import org.fox.common.message.FileSystemStats;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 13:59
 */
public class FileSystemStatsCollector extends AbstractSigarStatsCollector<FileSystemStats> {
    private String fs;

    protected FileSystemStatsCollector(String fs, Sigar sigar) {
        super(sigar);
        this.fs = fs;
    }

    @Override
    public FileSystemStats getStatsValue() throws Exception {
        FileSystemStats fileSystemStats = new FileSystemStats();
        FileSystemUsage fileSystemUsage = sigar.getFileSystemUsage(fs);
        fileSystemStats.setFree(fileSystemUsage.getFree());
        fileSystemStats.setUsed(fileSystemUsage.getUsed());
        fileSystemStats.setUsePercent(fileSystemUsage.getUsePercent());
        fileSystemStats.setDiskQueue(fileSystemUsage.getDiskQueue());
        fileSystemStats.setDiskReadBytes(fileSystemUsage.getDiskReadBytes());
        fileSystemStats.setDiskReads(fileSystemUsage.getDiskReads());
        fileSystemStats.setDiskWriteBytes(fileSystemUsage.getDiskWriteBytes());
        fileSystemStats.setDiskWrites(fileSystemUsage.getDiskWrites());
        return fileSystemStats;
    }
}

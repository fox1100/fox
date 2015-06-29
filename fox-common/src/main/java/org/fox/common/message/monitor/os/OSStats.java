package org.fox.common.message.monitor.os;

import org.fox.common.message.Message;

import java.util.List;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 12:53
 */
public class OSStats extends Message {
    private int applicationId;
    private String applicationName;
    private String hostName;
    private String hostIp;
    private CpuStats cpuStats;
    private MemoryStats memoryStats;
    private SwapStats swapStats;
    private NetStats netStats;
    private List<FileSystemStats> fileSystemStatsList;
    private List<NetInterfaceStats> netInterfaceStatsList;

    public CpuStats getCpuStats() {
        return cpuStats;
    }

    public void setCpuStats(CpuStats cpuStats) {
        this.cpuStats = cpuStats;
    }

    public MemoryStats getMemoryStats() {
        return memoryStats;
    }

    public void setMemoryStats(MemoryStats memoryStats) {
        this.memoryStats = memoryStats;
    }

    public SwapStats getSwapStats() {
        return swapStats;
    }

    public void setSwapStats(SwapStats swapStats) {
        this.swapStats = swapStats;
    }

    public NetStats getNetStats() {
        return netStats;
    }

    public void setNetStats(NetStats netStats) {
        this.netStats = netStats;
    }

    public List<FileSystemStats> getFileSystemStatsList() {
        return fileSystemStatsList;
    }

    public void setFileSystemStatsList(List<FileSystemStats> fileSystemStatsList) {
        this.fileSystemStatsList = fileSystemStatsList;
    }

    public List<NetInterfaceStats> getNetInterfaceStatsList() {
        return netInterfaceStatsList;
    }

    public void setNetInterfaceStatsList(List<NetInterfaceStats> netInterfaceStatsList) {
        this.netInterfaceStatsList = netInterfaceStatsList;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    @Override
    public String toString() {
        return "OSStats{" +
                "cpuStats=" + cpuStats +
                ", memoryStats=" + memoryStats +
                ", swapStats=" + swapStats +
                ", netStats=" + netStats +
                ", fileSystemStatsList=" + fileSystemStatsList +
                ", netInterfaceStatsList=" + netInterfaceStatsList +
                '}';
    }
}

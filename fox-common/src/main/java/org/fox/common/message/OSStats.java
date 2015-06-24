package org.fox.common.message;

import java.util.List;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 12:53
 */
public class OSStats extends Message {
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

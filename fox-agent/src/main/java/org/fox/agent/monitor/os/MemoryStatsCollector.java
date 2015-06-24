package org.fox.agent.monitor.os;

import org.fox.common.message.MemoryStats;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 12:43
 */
public class MemoryStatsCollector extends AbstractSigarStatsCollector<MemoryStats> {
    protected MemoryStatsCollector(Sigar sigar) {
        super(sigar);
    }

    @Override
    public MemoryStats getStatsValue() throws Exception {
        MemoryStats memoryStats = new MemoryStats();
        Mem mem = sigar.getMem();
        memoryStats.setFree(mem.getFree());
        memoryStats.setActualFree(mem.getActualFree());
        memoryStats.setUsed(mem.getUsed());
        memoryStats.setActualUsed(mem.getActualUsed());
        memoryStats.setRam(mem.getRam());
        memoryStats.setUsedPercent(mem.getUsedPercent());
        memoryStats.setFreePercent(mem.getFreePercent());
        return memoryStats;
    }

}

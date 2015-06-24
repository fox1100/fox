package org.fox.agent.monitor.os;

import org.fox.common.message.SwapStats;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.Swap;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 14:09
 */
public class SwapStatsCollector extends AbstractSigarStatsCollector<SwapStats> {
    protected SwapStatsCollector(Sigar sigar) {
        super(sigar);
    }

    @Override
    public SwapStats getStatsValue() throws Exception {
        SwapStats swapStats = new SwapStats();
        Swap swap = sigar.getSwap();
        swapStats.setFree(swap.getFree());
        swapStats.setUsed(swap.getUsed());
        swapStats.setPageIn(swap.getPageIn());
        swapStats.setPageOut(swap.getPageOut());
        return swapStats;
    }
}

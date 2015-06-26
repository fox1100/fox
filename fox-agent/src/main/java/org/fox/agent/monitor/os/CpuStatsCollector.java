package org.fox.agent.monitor.os;

import org.fox.common.message.monitor.os.CpuStats;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 11:53
 */
public class CpuStatsCollector extends AbstractSigarStatsCollector<CpuStats> {
    private final static Logger LOGGER = LoggerFactory.getLogger(CpuStatsCollector.class);
    private CpuInfo cpuInfo;

    protected CpuStatsCollector(Sigar sigar) {
        super(sigar);
        try {
            CpuInfo[] cpuInfos = sigar.getCpuInfoList();
            if (cpuInfos != null && cpuInfos.length > 0) {
                cpuInfo = cpuInfos[0];
            }
        } catch (SigarException e) {
            LOGGER.warn("Get cpu info error:", e);
            cpuInfo = null;
        }
    }

    @Override
    public CpuStats getStatsValue() throws Exception {
        CpuStats cpuStats = new CpuStats();
        CpuPerc cpuPerc = sigar.getCpuPerc();
        cpuStats.setSys(cpuPerc.getSys());
        cpuStats.setUser(cpuPerc.getUser());
        cpuStats.setWait(cpuPerc.getWait());
        cpuStats.setIdle(cpuPerc.getIdle());
        cpuStats.setUsagePercent(cpuPerc.getCombined());

        if (cpuInfo != null) {
            cpuStats.setMhz(cpuInfo.getMhz());
            cpuStats.setModel(cpuInfo.getModel());
            cpuStats.setVendor(cpuInfo.getVendor());
            cpuStats.setTotalCores(cpuInfo.getTotalCores());
        }
        return cpuStats;
    }
}

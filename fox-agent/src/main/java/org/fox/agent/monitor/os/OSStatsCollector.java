package org.fox.agent.monitor.os;

import org.fox.common.message.FileSystemStats;
import org.fox.common.message.NetInterfaceStats;
import org.fox.common.message.OSStats;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.NetRoute;
import org.hyperic.sigar.Sigar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 14:11
 */
public class OSStatsCollector {
    private final static Logger LOGGER = LoggerFactory.getLogger(OSStatsCollector.class);
    private CpuStatsCollector cpuStatsCollector;
    private List<FileSystemStatsCollector> fileSystemStatsCollectorList;
    private MemoryStatsCollector memoryStatsCollector;
    private List<NetInterfaceStatsCollector> netInterfaceStatsCollectorList;
    private NetStatsCollector netStatsCollector;
    private SwapStatsCollector swapStatsCollector;
    private Sigar sigar;

    public OSStatsCollector() {
        try {
            String sigarPath = SigarNativeBindingLoader.loadNativeSigarBindings();
            LOGGER.info("Load sigar native lib from:" + sigarPath);
            sigar = new Sigar();
            cpuStatsCollector = new CpuStatsCollector(sigar);
            memoryStatsCollector = new MemoryStatsCollector(sigar);
            netStatsCollector = new NetStatsCollector(sigar);
            swapStatsCollector = new SwapStatsCollector(sigar);
            fileSystemStatsCollectorList = new ArrayList<>();
            netInterfaceStatsCollectorList = new ArrayList<>();

            Set<String> routedNetworkInterfaces = new HashSet<>();
            for (NetRoute netRoute : sigar.getNetRouteList()) {
                routedNetworkInterfaces.add(netRoute.getIfname());
            }
            for (String ifname : routedNetworkInterfaces) {
                netInterfaceStatsCollectorList.add(new NetInterfaceStatsCollector(ifname, sigar));
            }

            @SuppressWarnings("unchecked")
            Set<Map.Entry<String, FileSystem>> entries = (Set<Map.Entry<String, FileSystem>>) sigar.getFileSystemMap().entrySet();
            for (Map.Entry<String, FileSystem> e : entries) {
                final FileSystem fs = e.getValue();
                if (fs.getType() == FileSystem.TYPE_LOCAL_DISK || fs.getType() == FileSystem.TYPE_NETWORK) {
                    fileSystemStatsCollectorList.add(new FileSystemStatsCollector(e.getKey(), sigar));
                }
            }
        } catch (Exception e) {
            LOGGER.warn("Init sigar collectors error: ", e);
        }
    }

    public OSStats getOSStats() throws Exception {
        OSStats osStats = new OSStats();
        osStats.setCpuStats(cpuStatsCollector.getStatsValue());
        osStats.setMemoryStats(memoryStatsCollector.getStatsValue());
        osStats.setNetStats(netStatsCollector.getStatsValue());
        osStats.setSwapStats(swapStatsCollector.getStatsValue());
        List<NetInterfaceStats> netInterfaceStatsList = new ArrayList<>();
        for (NetInterfaceStatsCollector netInterfaceStatsCollector : netInterfaceStatsCollectorList) {
            netInterfaceStatsList.add(netInterfaceStatsCollector.getStatsValue());
        }
        osStats.setNetInterfaceStatsList(netInterfaceStatsList);
        List<FileSystemStats> fileSystemStatsList = new ArrayList<>();
        for (FileSystemStatsCollector fileSystemStatsCollector : fileSystemStatsCollectorList) {
            fileSystemStatsList.add(fileSystemStatsCollector.getStatsValue());
        }
        osStats.setFileSystemStatsList(fileSystemStatsList);
        return osStats;
    }
}

package org.fox.agent.monitor.os;

import org.fox.common.message.NetInterfaceStats;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 13:26
 */
public class NetInterfaceStatsCollector extends AbstractSigarStatsCollector<NetInterfaceStats> {
    private String ifname;

    public NetInterfaceStatsCollector(String ifname, Sigar sigar) {
        super(sigar);
        this.ifname = ifname;
    }

    @Override
    public NetInterfaceStats getStatsValue() throws Exception {
        NetInterfaceStats netInterfaceStats = new NetInterfaceStats();
        NetInterfaceStat netInterfaceStat = sigar.getNetInterfaceStat(ifname);
        netInterfaceStats.setReadBytes(netInterfaceStat.getRxBytes());
        netInterfaceStats.setReadPackets(netInterfaceStat.getRxPackets());
        netInterfaceStats.setReadDropped(netInterfaceStat.getRxDropped());
        netInterfaceStats.setReadErrors(netInterfaceStat.getRxErrors());

        netInterfaceStats.setWriteBytes(netInterfaceStat.getTxBytes());
        netInterfaceStats.setWritePackets(netInterfaceStat.getTxPackets());
        netInterfaceStats.setWriteDropped(netInterfaceStat.getTxDropped());
        netInterfaceStats.setWriteErrors(netInterfaceStat.getTxErrors());
        return netInterfaceStats;
    }
}

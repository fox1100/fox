package org.fox.agent.monitor.os;

import org.fox.common.message.NetStats;
import org.hyperic.sigar.NetStat;
import org.hyperic.sigar.Sigar;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 13:50
 */
public class NetStatsCollector extends AbstractSigarStatsCollector<NetStats> {
    public NetStatsCollector(Sigar sigar) {
        super(sigar);
    }

    @Override
    public NetStats getStatsValue() throws Exception {
        NetStats netStats = new NetStats();
        NetStat netStat = sigar.getNetStat();
        netStats.setAllInboundTotal(netStat.getAllInboundTotal());
        netStats.setAllOutboundTotal(netStat.getAllOutboundTotal());
        netStats.setTcpInboundTotal(netStat.getTcpInboundTotal());
        netStats.setTcpOutboundTotal(netStat.getTcpOutboundTotal());
        netStats.setTcpClose(netStat.getTcpClose());
        netStats.setTcpEstablished(netStat.getTcpEstablished());
        netStats.setTcpIdle(netStat.getTcpIdle());
        netStats.setTcpSynRecv(netStat.getTcpSynRecv());
        netStats.setTcpSynSent(netStat.getTcpSynSent());
        return netStats;
    }
}

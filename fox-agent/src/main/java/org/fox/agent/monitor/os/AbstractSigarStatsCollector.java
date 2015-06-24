package org.fox.agent.monitor.os;

import org.hyperic.sigar.Sigar;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 11:54
 */
public abstract class AbstractSigarStatsCollector<V> {
    protected final Sigar sigar;

    protected AbstractSigarStatsCollector(Sigar sigar) {
        this.sigar = sigar;
    }

    public abstract V getStatsValue() throws Exception;
}

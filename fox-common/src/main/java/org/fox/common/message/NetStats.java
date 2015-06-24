package org.fox.common.message;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 13:46
 */
public class NetStats extends Message {
    private int tcpInboundTotal;
    private int tcpOutboundTotal;
    private int allInboundTotal;
    private int allOutboundTotal;
    private int tcpEstablished;
    private int tcpSynSent;
    private int tcpSynRecv;
    private int tcpClose;
    private int tcpIdle;

    public int getTcpInboundTotal() {
        return tcpInboundTotal;
    }

    public void setTcpInboundTotal(int tcpInboundTotal) {
        this.tcpInboundTotal = tcpInboundTotal;
    }

    public int getTcpOutboundTotal() {
        return tcpOutboundTotal;
    }

    public void setTcpOutboundTotal(int tcpOutboundTotal) {
        this.tcpOutboundTotal = tcpOutboundTotal;
    }

    public int getAllInboundTotal() {
        return allInboundTotal;
    }

    public void setAllInboundTotal(int allInboundTotal) {
        this.allInboundTotal = allInboundTotal;
    }

    public int getAllOutboundTotal() {
        return allOutboundTotal;
    }

    public void setAllOutboundTotal(int allOutboundTotal) {
        this.allOutboundTotal = allOutboundTotal;
    }

    public int getTcpEstablished() {
        return tcpEstablished;
    }

    public void setTcpEstablished(int tcpEstablished) {
        this.tcpEstablished = tcpEstablished;
    }

    public int getTcpSynSent() {
        return tcpSynSent;
    }

    public void setTcpSynSent(int tcpSynSent) {
        this.tcpSynSent = tcpSynSent;
    }

    public int getTcpSynRecv() {
        return tcpSynRecv;
    }

    public void setTcpSynRecv(int tcpSynRecv) {
        this.tcpSynRecv = tcpSynRecv;
    }

    public int getTcpClose() {
        return tcpClose;
    }

    public void setTcpClose(int tcpClose) {
        this.tcpClose = tcpClose;
    }

    public int getTcpIdle() {
        return tcpIdle;
    }

    public void setTcpIdle(int tcpIdle) {
        this.tcpIdle = tcpIdle;
    }

    @Override
    public String toString() {
        return "NetStats{" +
                "tcpInboundTotal=" + tcpInboundTotal +
                ", tcpOutboundTotal=" + tcpOutboundTotal +
                ", allInboundTotal=" + allInboundTotal +
                ", allOutboundTotal=" + allOutboundTotal +
                ", tcpEstablished=" + tcpEstablished +
                ", tcpSynSent=" + tcpSynSent +
                ", tcpSynRecv=" + tcpSynRecv +
                ", tcpClose=" + tcpClose +
                ", tcpIdle=" + tcpIdle +
                '}';
    }


}

package org.fox.common.message.monitor.os;

import org.fox.common.message.Message;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 13:27
 */
public class NetInterfaceStats extends Message {
    private long readBytes = 0L;
    private long readPackets = 0L;
    private long readErrors = 0L;
    private long readDropped = 0L;
    private long writeBytes = 0L;
    private long writePackets = 0L;
    private long writeErrors = 0L;
    private long writeDropped = 0L;
    private int tcpInboundTotal;
    private int tcpOutboundTotal;
    private int allInboundTotal;
    private int allOutboundTotal;

    public long getReadBytes() {
        return readBytes;
    }

    public void setReadBytes(long readBytes) {
        this.readBytes = readBytes;
    }

    public long getReadPackets() {
        return readPackets;
    }

    public void setReadPackets(long readPackets) {
        this.readPackets = readPackets;
    }

    public long getReadErrors() {
        return readErrors;
    }

    public void setReadErrors(long readErrors) {
        this.readErrors = readErrors;
    }

    public long getReadDropped() {
        return readDropped;
    }

    public void setReadDropped(long readDropped) {
        this.readDropped = readDropped;
    }

    public long getWriteBytes() {
        return writeBytes;
    }

    public void setWriteBytes(long writeBytes) {
        this.writeBytes = writeBytes;
    }

    public long getWritePackets() {
        return writePackets;
    }

    public void setWritePackets(long writePackets) {
        this.writePackets = writePackets;
    }

    public long getWriteErrors() {
        return writeErrors;
    }

    public void setWriteErrors(long writeErrors) {
        this.writeErrors = writeErrors;
    }

    public long getWriteDropped() {
        return writeDropped;
    }

    public void setWriteDropped(long writeDropped) {
        this.writeDropped = writeDropped;
    }

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

    @Override
    public String toString() {
        return "NetInterfaceStats{" +
                "readBytes=" + readBytes +
                ", readPackets=" + readPackets +
                ", readErrors=" + readErrors +
                ", readDropped=" + readDropped +
                ", writeBytes=" + writeBytes +
                ", writePackets=" + writePackets +
                ", writeErrors=" + writeErrors +
                ", writeDropped=" + writeDropped +
                ", tcpInboundTotal=" + tcpInboundTotal +
                ", tcpOutboundTotal=" + tcpOutboundTotal +
                ", allInboundTotal=" + allInboundTotal +
                ", allOutboundTotal=" + allOutboundTotal +
                '}';
    }
}

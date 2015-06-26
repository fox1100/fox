package org.fox.common.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.fox.common.util.KryoUtil;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 12:58
 */
public abstract class Message {
    private long id;
    private long logTime;
    private long queueTime;
    private long sendTime;
    private long receiveTime;
    private long processTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLogTime() {
        return logTime;
    }

    public void setLogTime(long logTime) {
        this.logTime = logTime;
    }

    public long getQueueTime() {
        return queueTime;
    }

    public void setQueueTime(long queueTime) {
        this.queueTime = queueTime;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public long getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(long receiveTime) {
        this.receiveTime = receiveTime;
    }

    public long getProcessTime() {
        return processTime;
    }

    public void setProcessTime(long processTime) {
        this.processTime = processTime;
    }

    public static ByteBuf toByteBuf(Message message) {
        byte[] data = KryoUtil.serialize(message);
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        byteBuf.writeInt(data.length);
        byteBuf.writeBytes(data);
        return byteBuf;
    }

    public static Message toObject(byte[] in) {
        return (Message) KryoUtil.deserialize(in);
    }
}

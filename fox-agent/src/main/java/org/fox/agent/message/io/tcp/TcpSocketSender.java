package org.fox.agent.message.io.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import org.fox.agent.message.io.Sender;
import org.fox.common.message.Message;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 16:31
 */
public class TcpSocketSender implements Sender{
    private ChannelManager channelManager;

    public TcpSocketSender() {
        channelManager = new ChannelManager();
    }

    @Override
    public void send(Message message) {
        ChannelFuture channelFuture = channelManager.channel();
        ByteBuf buf = Message.toByteBuf(message);
        Channel channel = channelFuture.channel();
        channel.writeAndFlush(buf);
        System.out.println("sender.....");
    }
}

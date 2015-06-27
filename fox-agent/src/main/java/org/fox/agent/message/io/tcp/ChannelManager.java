package org.fox.agent.message.io.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 16:19
 */
public class ChannelManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(ChannelManager.class);
    private Bootstrap bootstrap;
    private ChannelFuture channelFuture;

    public ChannelManager() {
        EventLoopGroup group = new NioEventLoopGroup(1);
        bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
            }
        });
        List<InetSocketAddress> addresses = new ArrayList<>();
        addresses.add(new InetSocketAddress("localhost", 63100));
        initChannel(addresses);
    }

    public ChannelFuture channel() {
        return channelFuture;
    }

    private void initChannel(List<InetSocketAddress> addresses) {
        try {
            int len = addresses.size();
            for (int i = 0; i < len; i++) {
                InetSocketAddress address = addresses.get(i);
                channelFuture = createChannel(address);
            }
        } catch (Exception e) {
            LOGGER.error("init channel error:", e);
        }
    }

    private ChannelFuture createChannel(InetSocketAddress address) {
        ChannelFuture future = null;

        try {
            future = bootstrap.connect(address);
            future.awaitUninterruptibly(100, TimeUnit.MILLISECONDS); // 100 ms
            if (!future.isSuccess()) {
                LOGGER.error("Error when try connecting to " + address);
                closeChannel(future);
            } else {
                LOGGER.info("Connected to fox server at " + address);
                return future;
            }
        } catch (Throwable e) {
            LOGGER.error("Error when connect server " + address.getAddress(), e);
            closeChannel(future);
        }
        return null;
    }

    private void closeChannel(ChannelFuture channel) {
        try {
            if (channel != null) {
                LOGGER.info("close channel " + channel.channel().remoteAddress());
                channel.channel().close();
            }
        } catch (Exception ignore) {
        }
    }
}

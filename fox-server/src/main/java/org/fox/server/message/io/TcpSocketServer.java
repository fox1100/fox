package org.fox.server.message.io;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 15:02
 */
public class TcpSocketServer {
    private final static Logger LOGGER = LoggerFactory.getLogger(TcpSocketServer.class);
    private boolean isRunning = false;
    private int port;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ChannelFuture channelFuture;

    private TcpSocketServer() {
    }

    private static class TcpSocketServerHolder {
        private static TcpSocketServer INSTANCE = new TcpSocketServer();
    }

    public static TcpSocketServer getInstance() {
        return TcpSocketServerHolder.INSTANCE;
    }

    public synchronized void start(int port) {
        checkStatus();
        boolean isLinux = isLinux();
        int boosThreadNum = 1;
        bossGroup = isLinux ? new EpollEventLoopGroup(boosThreadNum) : new NioEventLoopGroup(boosThreadNum);
        workerGroup = isLinux ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup);
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        serverBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        serverBootstrap.childOption(ChannelOption.SO_REUSEADDR, true);
        serverBootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        serverBootstrap.channel(isLinux ? EpollServerSocketChannel.class : NioServerSocketChannel.class);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addFirst(new MessageDecoder());
            }
        });

        try {
            channelFuture = serverBootstrap.bind(port).sync();
            isRunning = true;
            this.port = port;
            LOGGER.info("Tcp socket server started on " + port);
        } catch (Exception e) {
            LOGGER.error("Start tcp socket server error:", e);
        }
    }

    public synchronized void shutdown() {
        LOGGER.info("Start shutdown tcp socket server on " + port);
        channelFuture.channel().closeFuture();
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    private boolean isLinux() {
        String os = System.getProperty("os.name");
        return !(os == null || os.length() == 0) && os.toLowerCase().startsWith("linux");
    }

    private void checkStatus() {
        if (isRunning) {
            throw new IllegalStateException("Tcp socket server already start, please check!");
        }
    }

}

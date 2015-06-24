package org.fox.server.message.io;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.fox.common.message.Message;
import org.fox.common.util.KryoUtil;
import org.fox.common.util.StringUtil;
import org.fox.server.analyzer.os.OSStatsAnalyzer;

import java.util.List;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 15:38
 */
public class MessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        int length = in.readInt();
        if (in.readableBytes() < length) {
            return;
        }
        byte[] data = new byte[length];
        in.readBytes(data);
        Message message = Message.toObject(data);
        OSStatsAnalyzer analyzer = new OSStatsAnalyzer();
        analyzer.process(message);
    }
}

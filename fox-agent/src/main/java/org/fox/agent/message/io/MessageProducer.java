package org.fox.agent.message.io;

import org.fox.common.message.Message;

import java.util.concurrent.BlockingQueue;

/**
 * @author jie.huang
 *         Date: 2015/6/27
 *         Time: 18:36
 */
public class MessageProducer {
    private BlockingQueue<Message> queue;

    public MessageProducer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    public void offer(Message message) {
        queue.offer(message);
    }
}

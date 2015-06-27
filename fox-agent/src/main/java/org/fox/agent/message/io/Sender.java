package org.fox.agent.message.io;

import org.fox.common.message.Message;

/**
 * @author jie.huang
 *         Date: 2015/6/27
 *         Time: 20:49
 */
public interface Sender {
    void send(Message message);
}

package org.fox.server.analyzer;

import org.fox.common.message.Message;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 17:24
 */
public interface Analyzer {
    void process(Message message);
}

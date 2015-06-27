package org.fox.server.analyzer.message;

import org.fox.common.message.Message;
import org.fox.server.analyzer.Analyzer;

/**
 * @author jie.huang
 *         Date: 2015/6/27
 *         Time: 18:09
 */
public class MessageStoreAnalyzer implements Analyzer {
    @Override
    public void process(Message message) {
        System.out.println(message);
    }
}

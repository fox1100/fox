package org.fox.common.message.trace;

import org.fox.common.message.Message;

/**
 * @author jie.huang
 *         Date: 2015/6/27
 *         Time: 18:32
 */
public class Event extends Message {
    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

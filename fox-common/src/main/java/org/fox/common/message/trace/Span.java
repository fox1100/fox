package org.fox.common.message.trace;

import org.fox.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jie.huang
 *         Date: 2015/6/26
 *         Time: 12:48
 */
public class Span extends Message {
    private String traceId;
    private long parentSpanId;
    private long childSpanId;
    private long startTime;
    private long stopTime;
    private String type;
    private String name;
    private int depth;
    private long durationTime;
    private long selfTime;
    private long childSpanCost;
    private List<Span> children;

    public Span() {
        this(null, null);
    }

    public Span(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public void addChild(Span span) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(span);
    }

    public List<Span> getChildren() {
        return children;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public long getParentSpanId() {
        return parentSpanId;
    }

    public void setParentSpanId(long parentSpanId) {
        this.parentSpanId = parentSpanId;
    }

    public long getChildSpanId() {
        return childSpanId;
    }

    public void setChildSpanId(long childSpanId) {
        this.childSpanId = childSpanId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStopTime() {
        return stopTime;
    }

    public void setStopTime(long stopTime) {
        this.stopTime = stopTime;
    }

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

    public long getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(long durationTime) {
        this.durationTime = durationTime;
    }

    public long getSelfTime() {
        return selfTime;
    }

    public void setSelfTime(long selfTime) {
        this.selfTime = selfTime;
    }

    public long getChildSpanCost() {
        return childSpanCost;
    }

    public void setChildSpanCost(long childSpanCost) {
        this.childSpanCost = childSpanCost;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}

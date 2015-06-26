package org.fox.common.message.trace;

import org.fox.common.util.HostUtil;
import org.fox.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

/**
 * @author jie.huang
 *         Date: 2015/6/26
 *         Time: 12:46
 */
public class CallStack {
    private final static Logger LOGGER = LoggerFactory.getLogger(CallStack.class);
    private int applicationId;
    private String applicationName;
    private String hostName;
    private String hostIp;
    private String threadName;
    private Stack<Span> stack;
    private Span currentSpan;

    public CallStack() {
        stack = new Stack<>();
        threadName = Thread.currentThread().getName();
        hostName = HostUtil.getHostName();
        hostIp = HostUtil.getHostIp();
        //TODO set application name
    }

    public Span getCurrentSpan() {
        return currentSpan;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void startSpan(Span span) {
        if (currentSpan == null) {
            if (StringUtil.isEmpty(span.getTraceId())) {
                span.setTraceId(TraceIdGenerator.nextTraceId(applicationId, span.getStartTime()));
            }
            span.setId(SpanIdGenerator.nextSpanId(0l));
        } else {
            span.setTraceId(currentSpan.getTraceId());
            span.setId(SpanIdGenerator.nextSpanId(span.getParentSpanId()));
            span.setParentSpanId(currentSpan.getId());
            span.setDepth(currentSpan.getDepth() + 1);
            //set parent span's child span id
            currentSpan.setChildSpanId(span.getId());//TODO too many children span id?
            currentSpan.addChild(span);
        }
        currentSpan = span;
        stack.push(span);
    }

    public Span stopSpan() {
        if (stack.isEmpty()) {
            //TODO wrong stop span, do something?
            return null;
        }
        Span span = stack.pop();
        long currentSpanCost = 0l;
        if (span != null) {
            long stopTime = System.currentTimeMillis();
            span.setStopTime(stopTime);
            currentSpanCost = stopTime - span.getStartTime();
            span.setDurationTime(currentSpanCost);
            span.setSelfTime(currentSpanCost - span.getChildSpanCost());
        }
        if (stack.isEmpty()) {
            currentSpan = span;
        } else {
            Span currSpan = stack.peek();
            if (currSpan != null) {
                currentSpan = currSpan;
                currentSpan.setChildSpanCost(currentSpan.getChildSpanCost() + currentSpanCost);
            }
        }
        return span;
    }
}

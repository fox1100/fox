package org.fox.agent.tarce;

import org.fox.common.message.trace.CallStack;
import org.fox.common.message.trace.Span;

/**
 * @author jie.huang
 *         Date: 2015/6/26
 *         Time: 13:52
 */
public class Tracer {
    public static void startTrace(String type, String name) {
        //TODO if type or name is null?
        CallStack callStack = TraceContext.getCallStack();
        Span span = new Span(type, name);
        span.setStartTime(System.currentTimeMillis());
        callStack.startSpan(span);
    }

    public static CallStack endTrace() {
        CallStack callStack = TraceContext.getCallStack();
        callStack.stopSpan();
        return TraceContext.clearContext();
    }

    public static Span getSpan() {
        CallStack callStack = TraceContext.getCallStack();
        return callStack.getCurrentSpan();
    }

    public static long getSpanId() {
        Span span = getSpan();
        if (span != null) {
            return span.getId();
        }
        return 0l;
    }

    public static String getTraceId() {
        Span span = getSpan();
        if (span != null) {
            return span.getTraceId();
        }
        return null;
    }
}

package org.fox.agent.tarce;

import org.fox.common.message.trace.CallStack;

/**
 * @author jie.huang
 *         Date: 2015/6/26
 *         Time: 13:56
 */
public class TraceContext {
    private final static ThreadLocal<CallStack> localCallStack = new ThreadLocal<>();

    protected static CallStack getCallStack() {
       CallStack callStack = localCallStack.get();
        if (callStack == null) {
            localCallStack.set(new CallStack());
        }
        return localCallStack.get();
    }

    protected static CallStack clearContext() {
        CallStack callStack = getCallStack();
        if (callStack.isEmpty()) {
            //TODO send call stack
            localCallStack.set(null);
            return callStack;
        }
        return null;
    }
}

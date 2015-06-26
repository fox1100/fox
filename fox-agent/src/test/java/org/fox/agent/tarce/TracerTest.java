package org.fox.agent.tarce;

import org.fox.common.message.trace.Span;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TracerTest {
    @Test
    public void testRootSpan() {
        Tracer.startTrace("method", "test-1");
        Span span = Tracer.getSpan();
        Tracer.endTrace();
        Assert.assertEquals("method", span.getType());
        Assert.assertEquals("test-1", span.getName());
        Assert.assertTrue(span.getParentSpanId() == 0l);
        Assert.assertTrue(span.getDepth()==0);
    }

    @Test
    public void testSpanWithChild() throws InterruptedException {
        Tracer.startTrace("method", "test-1");
        Span  rootSpan = Tracer.getSpan();
        Tracer.startTrace("method", "test-2");
        Tracer.startTrace("method", "test-3");
        Thread.sleep(3l);
        Tracer.endTrace();
        Tracer.startTrace("method", "test-4");
        Thread.sleep(4l);
        Tracer.endTrace();
        Tracer.endTrace();
        Tracer.endTrace();
        Assert.assertEquals("method", rootSpan.getType());
        Assert.assertEquals("test-1", rootSpan.getName());
        Assert.assertTrue(rootSpan.getParentSpanId() == 0l);
        Assert.assertTrue(rootSpan.getDurationTime() >= 7);
        Assert.assertTrue(rootSpan.getDurationTime()-rootSpan.getSelfTime() >= 7);
        Assert.assertTrue(rootSpan.getDepth()==0);

        List<Span> parentSpanList = rootSpan.getChildren();
        Assert.assertTrue(parentSpanList.size() == 1);
        Span parentSpan = parentSpanList.get(0);
        Assert.assertEquals("method", parentSpan.getType());
        Assert.assertEquals("test-2", parentSpan.getName());
        Assert.assertTrue(parentSpan.getParentSpanId() == rootSpan.getId());
        Assert.assertTrue(parentSpan.getDurationTime() >= 7);
        Assert.assertTrue(parentSpan.getDurationTime()-parentSpan.getSelfTime() >= 7);
        Assert.assertTrue(parentSpan.getDepth()==1);

        List<Span> childrenSpanList = parentSpan.getChildren();
        Assert.assertTrue(childrenSpanList.size() == 2);
        Span childrenSpan1 = childrenSpanList.get(0);
        Assert.assertEquals("method", childrenSpan1.getType());
        Assert.assertEquals("test-3", childrenSpan1.getName());
        Assert.assertTrue(childrenSpan1.getParentSpanId() == parentSpan.getId());
        Assert.assertTrue(childrenSpan1.getDurationTime() >= 3);
        Assert.assertTrue(childrenSpan1.getSelfTime() >= 3);
        Assert.assertTrue(childrenSpan1.getDepth()==2);
        Span childrenSpan2 = childrenSpanList.get(1);
        Assert.assertEquals("method", childrenSpan2.getType());
        Assert.assertEquals("test-4", childrenSpan2.getName());
        Assert.assertTrue(childrenSpan2.getParentSpanId() == parentSpan.getId());
        Assert.assertTrue(childrenSpan2.getDurationTime() >= 4);
        Assert.assertTrue(childrenSpan2.getSelfTime() >= 4);
        Assert.assertTrue(childrenSpan2.getDepth()==2);
    }

    @Test
    public void testSpanWithChild_MultiThread() throws InterruptedException {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i < 10; i++) {
                            testSpanWithChild();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.join();
            threads[i] = t;
        }
        for (Thread thread : threads) {
            thread.start();
        }
        Thread.sleep(2000l);
    }
}
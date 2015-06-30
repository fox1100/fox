package org.fox.common.counter;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertTrue;

public class CounterTest {
    @Test
    public void testInc() throws Exception {
        final Counter counter = new Counter();
        long start = System.currentTimeMillis();
        final CountDownLatch c = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000000; i++) {
                        counter.inc();
                    }
                    c.countDown();
                }
            };
            t.start();
        }
        c.await();
        System.out.println(System.currentTimeMillis() - start);
        assertTrue(counter.getCount() == 200000000);
    }

    @Test
    public void testInc1() throws Exception {
        final Counter counter = new Counter();
        long start = System.currentTimeMillis();
        final CountDownLatch c = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000000; i++) {
                        counter.inc(2);
                    }
                    c.countDown();
                }
            };
            t.start();
        }
        c.await();
        System.out.println(System.currentTimeMillis() - start);
        assertTrue(counter.getCount() == 400000000);
    }

    @Test
    public void testDec() throws Exception {
        final Counter counter = new Counter();
        long start = System.currentTimeMillis();
        final CountDownLatch c = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000000; i++) {
                        counter.inc(2);
                        counter.dec(2);
                    }
                    c.countDown();
                }
            };
            t.start();
        }
        c.await();
        System.out.println(System.currentTimeMillis() - start);
        assertTrue(counter.getCount() == 0);
    }
}
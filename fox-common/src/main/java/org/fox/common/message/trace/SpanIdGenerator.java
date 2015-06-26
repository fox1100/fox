package org.fox.common.message.trace;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author jie.huang
 *         Date: 2015/6/26
 *         Time: 14:44
 */
public class SpanIdGenerator {
    public static final long NULL = -1;

    public static long nextSpanId(long parentSpanId) {
        final Random seed = getRandom();
        long newId = createSpanId(seed);
        while (newId == parentSpanId) {
            newId = createSpanId(seed);
        }
        return newId;
    }

    private static Random getRandom() {
        return ThreadLocalRandom.current();
    }

    private static long createSpanId(Random seed) {
        long id = seed.nextLong();
        while (id == NULL) {
            id = seed.nextLong();
        }
        return id;
    }
}

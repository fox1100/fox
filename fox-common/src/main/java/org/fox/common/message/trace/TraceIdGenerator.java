package org.fox.common.message.trace;

import org.fox.common.util.ByteUtil;
import org.fox.common.util.HostUtil;
import org.fox.common.util.StringUtil;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author jie.huang
 *         Date: 2015/6/26
 *         Time: 14:44
 */
public class TraceIdGenerator {
    public static final long NULL = -1;

    public static String nextTraceId(int applicationId, long timestamp) {
        return nextTraceId(applicationId, HostUtil.getHostIpIntValue(), timestamp);
    }

    public static String nextTraceId(int applicationId, int hostIp) {
        return nextTraceId(applicationId, hostIp, System.currentTimeMillis());
    }

    public static String nextTraceId(int applicationId, int hostIp, long timestamp) {
        final Random seed = getRandom();
        long nextId = createTraceId(seed);
        StringBuilder traceId = new StringBuilder();
        traceId.append(applicationId);
        traceId.append(timestamp);
        traceId.append(hostIp);
        traceId.append(nextId);
        return traceId.toString();
    }

    private static Random getRandom() {
        return ThreadLocalRandom.current();
    }

    private static long createTraceId(Random seed) {
        long id = seed.nextLong();
        while (id == NULL) {
            id = seed.nextLong();
        }
        return id;
    }
}

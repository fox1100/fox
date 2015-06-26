package org.fox.common.message.trace;

import org.fox.common.util.HostUtil;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TraceIdGeneratorTest {

    @Test
    public void testNextTraceId() throws Exception {
        int applicationId = "test".hashCode();
        int hostIp = HostUtil.getHostIpIntValue();
        long timestamp = System.currentTimeMillis();
        String traceId = TraceIdGenerator.nextTraceId(applicationId, hostIp, timestamp);
        String prefix = applicationId + "" + timestamp + hostIp;
        assertTrue(traceId.startsWith(prefix));
    }
}
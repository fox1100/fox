package org.fox.common.util;


import org.junit.Assert;
import org.junit.Test;

public class HostUtilTest {

    @Test
    public void testIntToIp() throws Exception {
        int i = HostUtil.getHostIpIntValue();
        Assert.assertEquals(HostUtil.getHostIp(), HostUtil.intToIp(i));
    }
}
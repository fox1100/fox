package org.fox.common.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void testFixLength() throws Exception {
        assertEquals("0009", StringUtil.fixLength("9", 4, "0"));
        assertEquals("0099", StringUtil.fixLength("99", 4, "0"));
        assertEquals("00000009", StringUtil.fixLength("9", 8, "0"));
        assertEquals("00000099", StringUtil.fixLength("99", 8, "0"));
    }
}
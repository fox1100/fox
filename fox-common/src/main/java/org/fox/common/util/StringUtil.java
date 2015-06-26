package org.fox.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * @author jie.huang
 *         Date: 2015/6/8
 *         Time: 11:03
 */
public class StringUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);
    public final static String UTF_8 = "utf-8";

    public static byte[] toBytes(String data) {
        try {
            return data.getBytes(UTF_8);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("toBytes() error:", e);
            return null;
        }
    }

    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

    public static String toString(byte[] bytes) {
        return toString(bytes, UTF_8);
    }

    public static String toString(byte[] bytes, String charset) {
        try {
            if (bytes == null || bytes.length <= 0) {
                return null;
            }
            return new String(bytes, charset);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("toString() error:", e);
            return null;
        }
    }

    public static String fixLength(long value, int length, String fixValue) {
        return fixLength(String.valueOf(value), length, fixValue);
    }

    public static String fixLength(int value, int length, String fixValue) {
        return fixLength(String.valueOf(value), length, fixValue);
    }

    public static String fixLength(String value, int length, String fixValue) {
        while (value.length() < length) {
            value = fixValue + value;
        }
        return value;
    }
}

package org.fox.common.util;

/**
 * @author jie.huang
 *         Date: 2015/6/26
 *         Time: 16:58
 */
public class ByteUtil {
    public static int toInt(byte[] bytes) {
        int n = 0;
        for (int i = 0; i < bytes.length; i++) {
            n <<= 8;
            n ^= bytes[i] & 0xFF;
        }
        return n;
    }

    public static byte[] toBytes(int val) {
        byte[] b = new byte[4];
        for (int i = 3; i > 0; i--) {
            b[i] = (byte) val;
            val >>>= 8;
        }
        b[0] = (byte) val;
        return b;
    }

    public static byte[] toBytes(long val) {
        byte[] b = new byte[8];
        for (int i = 7; i > 0; i--) {
            b[i] = (byte) val;
            val >>>= 8;
        }
        b[0] = (byte) val;
        return b;
    }

    public static String toString(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append((char)b);
        }
        return result.toString();
    }
}

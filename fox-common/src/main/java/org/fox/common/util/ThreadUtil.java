package org.fox.common.util;

/**
 * @author jie.huang
 *         Date: 2015/6/24
 *         Time: 14:53
 */
public class ThreadUtil {
    public static void sleepSecond(long second) {
        sleep(second * 1000);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignore) {
        }
    }
}

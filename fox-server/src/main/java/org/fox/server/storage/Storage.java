package org.fox.server.storage;

/**
 * @author jie.huang
 *         Date: 2015/6/27
 *         Time: 18:01
 */
public interface Storage {

    void store(byte[] key, byte[] value);

    byte[] get(byte[] key);
}

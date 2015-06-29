package org.fox.server.storage;

/**
 * @author jie.huang
 *         Date: 2015/6/27
 *         Time: 18:01
 */
public interface Storage<K, V> {

    void store(K key, V value);

    V get(K key);
}

package org.fox.server.storage.local;

import org.fox.server.storage.Storage;

/**
 * Local storage using level db
 *
 * @author jie.huang
 *         Date: 2015/6/27
 *         Time: 18:05
 */
public class LocalStorage implements Storage<byte[], byte[]> {
    @Override
    public void store(byte[] key, byte[] value) {

    }

    @Override
    public byte[] get(byte[] key) {
        return new byte[0];
    }
}

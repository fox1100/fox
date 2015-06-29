package org.fox.server.storage;

import org.fox.server.storage.memory.MemoryStorage;

/**
 * @author jie.huang
 *         Date: 2015/6/29
 *         Time: 14:23
 */
public class StorageFactory {
    private static class MemoryStorageHolder {
        private static MemoryStorage instance = new MemoryStorage();
    }

    public static MemoryStorage getMemoryStorage() {
        return MemoryStorageHolder.instance;
    }
}

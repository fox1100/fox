package org.fox.server.storage.memory;

import org.fox.server.report.Report;
import org.fox.server.storage.Storage;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jie.huang
 *         Date: 2015/6/29
 *         Time: 14:00
 */
public class MemoryStorage implements Storage<String, Report> {
    private ConcurrentHashMap<String, Report> memStore = new ConcurrentHashMap<>();

    @Override
    public Report store(String key, Report value) {
        return memStore.putIfAbsent(key, value);
    }

    @Override
    public Report get(String key) {
        return memStore.get(key);
    }
}

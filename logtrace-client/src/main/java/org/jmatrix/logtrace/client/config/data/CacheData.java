package org.jmatrix.logtrace.client.config.data;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: jmatrix
 * @date: 16/1/19
 */
public final class CacheData<T> {

    private T data;

    private ReentrantLock swapLock = new ReentrantLock();

    private long lastUpdateTime;

    public void load(T dataBak) {
        this.lastUpdateTime = System.currentTimeMillis() / 1000L;
        try {
            swapLock.lock();
            if (dataBak != null) {
                this.data = dataBak;
                dataBak = null;
            }

        } finally {
            swapLock.unlock();
        }
    }

    public T getData() {
        return data;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }
}

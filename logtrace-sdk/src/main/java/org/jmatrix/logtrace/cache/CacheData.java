package org.jmatrix.logtrace.cache;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: jmatrix
 * @date: 16/1/19
 */
public final class CacheData<T> {

    private T data;

    private ReentrantLock swapLock = new ReentrantLock();

    private long lastUpdateTime;

    public void load(final T dataBak) {
        this.lastUpdateTime = System.currentTimeMillis() / 1000L;
        try {
            swapLock.lock();
            if (dataBak != null) {
                this.data = dataBak;
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

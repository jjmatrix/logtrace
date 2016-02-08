package org.jmatrix.logtrace.client.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: jmatrix
 * @date: 16/1/19
 */
public class NamedThreadFactory implements ThreadFactory {

    private String threadName;

    private AtomicInteger threadCount = new AtomicInteger(1);

    public NamedThreadFactory() {
        threadName = "defaultNamed-";
    }

    public NamedThreadFactory(String threadName) {
        this.threadName = threadName;
    }

    public Thread newThread(Runnable r) {
        Thread thread = new Thread();
        thread.setName(threadName + threadCount.incrementAndGet());
        thread.setDaemon(true);
        return thread;
    }
}

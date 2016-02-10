package org.jmatrix.logtrace.client.config;

import org.jmatrix.logtrace.client.config.data.CacheData;

/**
 * @author: jmatrix
 * @date: 16/1/19
 */
public interface Subscriber {
    public void start();

    public CacheData getCacheData();
}

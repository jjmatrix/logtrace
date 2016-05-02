package org.jmatrix.logtrace.client.manager.impl;

import org.jmatrix.logtrace.client.config.LogTraceConfiguration;
import org.jmatrix.logtrace.client.config.Subscriber;
import org.jmatrix.logtrace.client.config.data.CacheData;
import org.jmatrix.logtrace.client.config.impl.ZookeeperSubscriber;
import org.jmatrix.logtrace.client.manager.TraceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: jmatrix
 * @date: 16/1/19
 */
public class DefaultTraceManager implements TraceManager {

    private Logger logger = LoggerFactory.getLogger(DefaultTraceManager.class);

    private String systemId;

    private LogTraceConfiguration logTraceConfiguration;

    private Subscriber subscriber;

    public DefaultTraceManager(String systemId) {
        this.systemId = systemId;
        logTraceConfiguration = new LogTraceConfiguration();
        logTraceConfiguration.start();
        if (!logTraceConfiguration.isRun()) {
            logger.error("start failed. there is some problem for configuration.");
            return;
        }
        subscriber = new ZookeeperSubscriber(this.systemId, logTraceConfiguration);
        subscriber.start();
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public CacheData getWhiteCache() {
        return subscriber.getCacheData();
    }

}

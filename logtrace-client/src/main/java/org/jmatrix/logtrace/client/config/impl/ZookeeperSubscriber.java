package org.jmatrix.logtrace.client.config.impl;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;
import org.jmatrix.logtrace.client.config.AbstractSubscriber;
import org.jmatrix.logtrace.client.config.LogTraceConfiguration;
import org.jmatrix.logtrace.client.config.data.WhiteUidCacheData;
import org.jmatrix.logtrace.client.util.StringPushThroughSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: jmatrix
 * @date: 16/1/19
 */
public class ZookeeperSubscriber extends AbstractSubscriber implements IZkDataListener {

    private Logger logger = LoggerFactory.getLogger(ZookeeperSubscriber.class);

    private ZkClient zkClient;

    private String zkPath;

    private LogTraceConfiguration logTraceConfiguration;

    private volatile boolean start = false;

    private WhiteUidCacheData whiteUidCacheData;

    public ZookeeperSubscriber(String zkPath) {
        this.zkPath = zkPath;
    }

    public ZookeeperSubscriber(String systemId, LogTraceConfiguration logTraceConfiguration) {
        this.zkPath = logTraceConfiguration.getZkDataRoot() + "/" + systemId;
        this.logTraceConfiguration = logTraceConfiguration;
    }

    public void init() {
        if (start) {
            return;
        }

        whiteUidCacheData = new WhiteUidCacheData();
        try {
            whiteUidCacheData.initWhiteUid((String) zkClient.readData(zkPath));
        } catch (Exception e) {
            logger.error("can't read white uid.", e);
            return;
        }
        start = true;
    }


    public void start() {
        zkClient = new ZkClient(logTraceConfiguration.getZkServerList(), 2000, 2000, new StringPushThroughSerializer());
        zkClient.subscribeDataChanges(zkPath, this);

        //初始化
        init();
    }

    public void handleDataChange(String dataPath, Object object) throws Exception {
        logger.info("zookeeper data change, data:{}", object);
        whiteUidCacheData.loadAndSwap((String) object);
    }

    public void handleDataDeleted(String dataPath) throws Exception {
        logger.warn("logtrace zookeeper path:{} have been deleted!", dataPath);
    }

    public WhiteUidCacheData getWhiteUidCacheData() {
        return whiteUidCacheData;
    }
}

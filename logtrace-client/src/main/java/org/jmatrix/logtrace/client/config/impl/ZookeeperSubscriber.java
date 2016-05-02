package org.jmatrix.logtrace.client.config.impl;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;
import org.jmatrix.logtrace.client.config.AbstractSubscriber;
import org.jmatrix.logtrace.client.config.LogTraceConfiguration;
import org.jmatrix.logtrace.client.config.data.CacheData;
import org.jmatrix.logtrace.client.config.data.WhiteConfig;
import org.jmatrix.logtrace.client.util.JsonUtils;
import org.jmatrix.logtrace.client.util.WhiteConfigSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

    private CacheData<WhiteConfig> cacheData;

    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

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

        readInitCacheData();

        start = true;
    }


    public void start() {
        zkClient = new ZkClient(logTraceConfiguration.getZkServerList(), 2000, 2000, new WhiteConfigSerializer());
        zkClient.subscribeDataChanges(zkPath, this);

        //初始化
        init();
    }

    /**
     * init, first read local cache data. if failed, read from remote server after random 1-10 second
     */
    private void readInitCacheData() {
        if (cacheData == null) {
            cacheData = new CacheData<WhiteConfig>();
        }
        WhiteConfig whiteConfig = loadLocalData();
        if (whiteConfig == null) {
            executorService.schedule(new Runnable() {
                public void run() {
                    WhiteConfig whiteConfigTmp = zkClient.readData(zkPath);
                    if (whiteConfigTmp != null) {
                        writeLocalData(whiteConfigTmp);
                        cacheData.load(whiteConfigTmp);
                    }
                }
            }, new Random().nextInt(5) + 1, TimeUnit.SECONDS);
        } else {
            updateCacheData(whiteConfig);
        }
    }

    /**
     * update white config data
     *
     * @param whiteConfig
     */
    private void updateCacheData(WhiteConfig whiteConfig) {
        writeLocalData(whiteConfig);
        cacheData.load(whiteConfig);
    }

    private WhiteConfig loadLocalData() {
        File bakFile = getBakFile();
        WhiteConfig whiteConfig = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(bakFile));
            String jsonStr = "";
            for (String line = reader.readLine(); line != null; jsonStr += line) ;
            if (!StringUtils.isEmpty(jsonStr)) {
                whiteConfig = JsonUtils.fromJson(jsonStr, WhiteConfig.class);
            }
        } catch (Exception e) {

        }
        return whiteConfig;
    }

    private void writeLocalData(WhiteConfig whiteConfig) {
        if (whiteConfig == null) {
            logger.debug("WhiteConfig is empty.");
            return;
        }
        File bakFile = getBakFile();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(bakFile));
            String jsonStr = JsonUtils.toJsonString(whiteConfig, WhiteConfig.class);
            writer.write(jsonStr);
        } catch (Exception e) {
            logger.error("write white data failed.", e);
        }
    }

    private File getBakFile() {
        File bakFile = new File(logTraceConfiguration.getCacheFilePath());
        if (!bakFile.exists()) {
            bakFile.mkdirs();
        }
        return bakFile;
    }

    public void handleDataChange(String dataPath, Object object) throws Exception {
        logger.debug("zookeeper data change, data:{}", object);
        cacheData.load((WhiteConfig) object);
    }

    public void handleDataDeleted(String dataPath) throws Exception {
        logger.debug("logtrace zookeeper path:{} have been deleted!", dataPath);
    }

    public CacheData<WhiteConfig> getCacheData() {
        return cacheData;
    }

    public static void main(String[] args) {
        System.out.println(new Random().nextInt());
    }
}

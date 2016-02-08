package org.jmatrix.logtrace.client.config;

import org.jmatrix.logtrace.client.util.NamedThreadFactory;
import org.jmatrix.logtrace.sdk.Env;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: jmatrix
 * @date: 16/1/19
 */
public class LogTraceConfiguration {
    private Logger logger = LoggerFactory.getLogger(LogTraceConfiguration.class);

    private final static LogTraceConfiguration INSTANCE = new LogTraceConfiguration();

    public static LogTraceConfiguration getInstance() {
        return INSTANCE;
    }

    private String zkServerList;

    private String zkDataRoot = Constants.ZK_ROOT;

    private String homePath = System.getProperty(Constants.LOGTRACE_HOME);

    private volatile boolean isRun = false;

    private ScheduledExecutorService scheduledExecutor;

    public LogTraceConfiguration() {
        this.scheduledExecutor = Executors.newScheduledThreadPool(1, new NamedThreadFactory("LogtraceConfigUpd-"));
    }

    public LogTraceConfiguration(ScheduledExecutorService scheduledExecutor) {
        this.scheduledExecutor = scheduledExecutor;
    }

    public void start() {
        if (isRun) {
            return;
        }
        loadConfig();
        asyncLoadConfig();
    }

    public void loadConfig() {
        File file = null;
        if (homePath == null || homePath.length() == 0) {
            file = new File(this.getClass().getResource("/").getFile() + "logtrace.properties");
        } else
            file = new File(homePath + "logtrace.properties");
        if (!file.exists()) {
            logger.error("config file[logtrace.properties] don't existed. please check!");
        }

        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(file));
            synchronized (this) {
                this.zkDataRoot = properties.getProperty("logtrace.zkroot");
                if (Env.isDebug()) {
                    this.zkServerList = "127.0.0.1:2181";
                } else
                    this.zkServerList = properties.getProperty("logtrace.servers");
            }
            isRun = true;
        } catch (Exception e) {
            logger.error("load config file fail.", e);
        }
    }

    public void asyncLoadConfig() {
        scheduledExecutor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                loadConfig();
            }
        }, 300, 300, TimeUnit.SECONDS);
    }


    public String getZkServerList() {
        return zkServerList;
    }

    public void setZkServerList(String zkServerList) {
        this.zkServerList = zkServerList;
    }

    public String getZkDataRoot() {
        return zkDataRoot;
    }

    public void setZkDataRoot(String zkDataRoot) {
        this.zkDataRoot = zkDataRoot;
    }

    public boolean isRun() {
        return isRun;
    }
}

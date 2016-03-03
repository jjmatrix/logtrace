package org.jmatrix.logtrace.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MongoClient Factory
 *
 * @author jmatrix
 * @date 16/3/1
 */
public class MongoClientFactory extends AbstractFactoryBean<MongoClient> {

    private Logger logger = LoggerFactory.getLogger(MongoClientFactory.class);

    /**
     * server address, example:(host1:port1,host1:port1,host2:port2)
     */
    private String host;

    private String userName;

    private String password;

    /**
     * collection name
     */
    private String source;

    private int minConnectionsPerHost;

    private int maxConnectionsPerHost;

    private int threadsAllowedToBlockForConnectionMultiplier;

    private int maxWaitTime;

    private int maxConnectionIdleTime;

    private int maxConnectionLifeTime;

    private int connectTimeout;

    private int socketTimeout;

    private boolean socketKeepAlive;

    @Override
    public Class<?> getObjectType() {
        return MongoClient.class;
    }

    @Override
    protected MongoClient createInstance() throws Exception {
        List<ServerAddress> serverAddresses = getServerList();
        if (serverAddresses.isEmpty()) {
            logger.warn("server address is not specified.");
            return null;
        }

        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.minConnectionsPerHost(this.minConnectionsPerHost)
                .threadsAllowedToBlockForConnectionMultiplier(this.threadsAllowedToBlockForConnectionMultiplier)
                .maxWaitTime(this.maxWaitTime)
                .maxConnectionIdleTime(this.maxConnectionIdleTime)
                .maxConnectionLifeTime(this.maxConnectionLifeTime)
                .connectTimeout(this.connectTimeout)
                .socketTimeout(this.socketTimeout)
                .socketKeepAlive(this.socketKeepAlive);
        MongoClientOptions clientOptions = builder.build();

        MongoCredential credential = null;
        if (!StringUtils.isEmpty(this.userName) && !StringUtils.isEmpty(this.password)) {
            credential = MongoCredential.createCredential(this.userName, this.source, this.password.toCharArray());
        }

        if (credential != null) {
            List<MongoCredential> credentials = new ArrayList<MongoCredential>(1);
            credentials.add(credential);
            return new MongoClient(serverAddresses, credentials, clientOptions);
        }
        return new MongoClient(serverAddresses, clientOptions);
    }

    private List<ServerAddress> getServerList() {
        if (host == null || host.length() == 0) {
            return Collections.emptyList();
        }
        String[] hosts = StringUtils.split(host, ",");
        List<ServerAddress> addresses = new ArrayList<ServerAddress>(hosts.length);
        for (String host : hosts) {
            String[] address = StringUtils.split(host, ":");
            if (address.length == 2) {
                addresses.add(new ServerAddress(address[0], Integer.parseInt(address[1])));
            }
        }
        return addresses;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setMinConnectionsPerHost(int minConnectionsPerHost) {
        this.minConnectionsPerHost = minConnectionsPerHost;
    }

    public void setMaxConnectionsPerHost(int maxConnectionsPerHost) {
        this.maxConnectionsPerHost = maxConnectionsPerHost;
    }

    public void setThreadsAllowedToBlockForConnectionMultiplier(int threadsAllowedToBlockForConnectionMultiplier) {
        this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
    }

    public void setMaxWaitTime(int maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public void setMaxConnectionIdleTime(int maxConnectionIdleTime) {
        this.maxConnectionIdleTime = maxConnectionIdleTime;
    }

    public void setMaxConnectionLifeTime(int maxConnectionLifeTime) {
        this.maxConnectionLifeTime = maxConnectionLifeTime;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public void setSocketKeepAlive(boolean socketKeepAlive) {
        this.socketKeepAlive = socketKeepAlive;
    }
}

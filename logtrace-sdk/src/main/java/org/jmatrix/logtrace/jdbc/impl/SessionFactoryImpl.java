package org.jmatrix.logtrace.jdbc.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jmatrix.logtrace.jdbc.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.Reader;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jmatrix
 * @date 16/2/14
 */
public class SessionFactoryImpl {

    private Logger logger = LoggerFactory.getLogger(SessionFactoryImpl.class);

    /**
     * divide by group
     */
    private Map<String, RouteSessionFactory> sessionFactoryGroupMap = new ConcurrentHashMap<String, RouteSessionFactory>();

    /**
     * reserve for future
     */
    private Config config;

    public void init() {
        RouteSessionFactory defaultRouteSessionFactory = new RouteSessionFactory();
        Properties properties = getProperties();
        defaultRouteSessionFactory.setMasterSessionFactory(createSessionFactory(properties, properties.getProperty("log.env")));
        defaultRouteSessionFactory.setSlaveSessionFactory(createSessionFactory(properties, properties.getProperty("log.env") + ".slave"));
        sessionFactoryGroupMap.put("default", defaultRouteSessionFactory);
    }

    /**
     * Create SessionFactory according to environment
     *
     * @param properties
     * @param env
     * @return
     */
    private SqlSessionFactory createSessionFactory(Properties properties, String env) {
        SqlSessionFactory sqlSessionFactory = null;
        try {
            Reader reader = Resources.getResourceAsReader("ibatis.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, env, properties);
            if (sqlSessionFactory == null) {
                logger.info("create SessionFactory failed.");
            }
        } catch (Exception e) {
            logger.error("create SessionFactory error", e);
            sqlSessionFactory = null;
        }
        return sqlSessionFactory;
    }

    private Properties getProperties() {
        Properties prop = System.getProperties();

        String env = prop.getProperty("log.env");
        String configName = "config";
        if (StringUtils.isEmpty(env)) {
            configName += ".properties";
        } else
            configName += "-" + env + ".properties";

        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/config-db/" + configName);
            prop.load(inputStream);
        } catch (Exception e) {
            logger.error("load db config info error.", e);
        }
        return prop;
    }

}

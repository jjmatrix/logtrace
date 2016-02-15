package org.jmatrix.logtrace.core;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: jmatrix
 * @date: 16/1/19
 */
public class Env {

    private static final Logger logger = LoggerFactory.getLogger(Env.class);

    public static boolean isDebug() {
        String isDebug = System.getProperty("debug", "");
        if (!StringUtils.isEmpty(isDebug)) {
            try {
                return Boolean.parseBoolean(isDebug);
            } catch (Exception e) {
                logger.error("parse debug parameter error.", e);
            }
        }
        return false;
    }
}

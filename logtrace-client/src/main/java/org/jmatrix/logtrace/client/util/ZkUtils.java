package org.jmatrix.logtrace.client.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.IllegalFormatException;

/**
 * @author: jmatrix
 * @date: 16/2/10
 */
public class ZkUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(ZkUtils.class);

    public static String getZkPath(String zkPath) {
        String path = "";
        if (!StringUtils.isEmpty(zkPath)) {
            if (zkPath.startsWith("/"))
                path = zkPath.substring(1);
        } else
            throw new IllegalArgumentException("zkPath can not empty.");
        return path;
    }

}

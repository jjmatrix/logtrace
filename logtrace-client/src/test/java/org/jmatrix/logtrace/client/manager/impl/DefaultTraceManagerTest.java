package org.jmatrix.logtrace.client.manager.impl;

import org.jmatrix.logtrace.client.config.data.CacheData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: jmatrix
 * @date: 16/1/19
 */
public class DefaultTraceManagerTest {

    private DefaultTraceManager defaultTraceManager;

    @Before
    public void setUp() throws Exception {
        defaultTraceManager = new DefaultTraceManager("matrix");
    }

    @Test
    public void testGetWhiteUidCache() throws Exception {
        CacheData cacheData = defaultTraceManager.getWhiteCache();
        Assert.assertNotNull(cacheData);
    }
}
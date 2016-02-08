package org.jmatrix.logtrace.client.manager.impl;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.jmatrix.logtrace.client.config.data.WhiteUidCacheData;

/**
 * @author: jmatrix
 * @date: 16/1/19
 */
public class DefaultTraceManagerTest extends TestCase {

    private DefaultTraceManager defaultTraceManager;

    public void setUp() throws Exception {
        super.setUp();
        defaultTraceManager = new DefaultTraceManager("matrix");
    }

    public void testGetWhiteUidCache() throws Exception {
        WhiteUidCacheData cacheData = defaultTraceManager.getWhiteUidCache();
        Assert.assertNotNull(cacheData);
    }
}
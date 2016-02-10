package org.jmatrix.logtrace.client.util;

import junit.framework.TestCase;

/**
 * @author: jmatrix
 * @date: 16/1/19
 */
public class ZkUtilsTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void testGetZkPath() throws Exception {
        String zkPath1 = null;
        assertNotNull(ZkUtils.getZkPath(zkPath1));

    }
}
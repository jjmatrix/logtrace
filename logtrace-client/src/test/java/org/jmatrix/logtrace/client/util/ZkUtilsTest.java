package org.jmatrix.logtrace.client.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: jmatrix
 * @date: 16/1/19
 */
public class ZkUtilsTest{

    @Before
    public void setUp() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetZkPathNullThrowException() throws Exception {
        String zkPath = null;
        ZkUtils.getZkPath(zkPath);
    }

    @Test
    public void testGetZkPath() throws Exception {
        String zkPath = "/logtrace";
        Assert.assertEquals("logtrace", ZkUtils.getZkPath(zkPath));
    }
}
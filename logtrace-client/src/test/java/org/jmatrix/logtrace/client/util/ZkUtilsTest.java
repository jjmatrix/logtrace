package org.jmatrix.logtrace.client.util;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author: jmatrix
 * @date: 16/1/19
 */
public class ZkUtilsTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetZkPathNullThrowException() throws Exception {
        String zkPath = null;
        ZkUtils.getZkPath(zkPath);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetZkPathThrowExceptionMsg() throws Exception {
        String zkPath = "";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("empty");
        thrown.expectMessage(CoreMatchers.containsString("zkPath"));
        ZkUtils.getZkPath(zkPath);
    }

    @Test
    public void testGetZkPath() throws Exception {
        String zkPath = "/logtrace";
        Assert.assertEquals("logtrace", ZkUtils.getZkPath(zkPath));
    }
}
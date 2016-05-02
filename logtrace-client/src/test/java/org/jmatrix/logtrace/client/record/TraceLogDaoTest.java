package org.jmatrix.logtrace.client.record;

import org.bson.types.ObjectId;
import org.jmatrix.logtrace.client.record.mapper.TraceLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by coral on 16/3/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class TraceLogDaoTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private TraceLogDao traceLogDao;

    @Test
    public void testSave() throws Exception {
        TraceLog traceLog = new TraceLog();
        traceLog.setSystemId("default");
        traceLog.setIdentify("111_0001_0001");
        ObjectId id = (ObjectId) traceLogDao.save(traceLog);
    }

    public void testDelete() throws Exception {

    }

    @Test
    public void testQuery() throws Exception {

    }

    @Test
    public void testQueryAll() throws Exception {
        List<TraceLog> traceLogs = traceLogDao.queryAll();
    }

    @Test
    public void testExecuteQueryCallback() throws Exception {
        List<TraceLog> traceLogs = (List<TraceLog>) traceLogDao.executeQuery(query -> {
            query.retrievedFields(true, "systemId");
            return query.asList();
        });
    }

    public void testExecuteQueryContextCallback() throws Exception {

    }
}
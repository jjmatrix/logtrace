package org.jmatrix.logtrace.client.record;

import org.jmatrix.logtrace.client.record.mapper.TraceLog;
import org.jmatrix.logtrace.mongodb.impl.BaseMongoDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * @author jmatrix
 * @date 16/3/3
 */
@Repository
public class TraceLogDao extends BaseMongoDaoImpl<TraceLog> {
}

package org.jmatrix.logtrace.mongodb;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.aggregation.Sort;

import java.util.List;
import java.util.Map;

/**
 * Base Operation
 *
 * @author jmatrix
 * @date 16/3/1
 */
public interface BaseMongoDao<T> {

    Object save(T entity);

    boolean delete(T entity);

    boolean delete(MapBeanContext context);

    int update(T entity, Map<String, String> fieldValues);

    List<T> queryAll();

    List<T> query(MapBeanContext context, int offset, int limit);

    List<T> query(MapBeanContext context, int offset, int limit, Sort sort);

    List<T> query(MapBeanContext context);

    List<T> executeQuery(MongoQueryCallback<T> callback);

    List<T> executeQuery(MapBeanContext context, MongoQueryCallback<T> callback);
}

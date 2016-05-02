package org.jmatrix.logtrace.mongodb.impl;

import org.jmatrix.logtrace.mongodb.BaseMongoDao;
import org.jmatrix.logtrace.mongodb.MapBeanContext;
import org.jmatrix.logtrace.mongodb.MongoQueryCallback;
import org.jmatrix.logtrace.utils.ReflectionUtils;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.aggregation.Sort;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;
import java.util.Map;

/**
 * Base Operation Abstract
 *
 * @author jmatrix
 * @date 16/3/1
 */
public class BaseMongoDaoImpl<T> implements BaseMongoDao<T> {

    private Class<T> entityCls;

    public BaseMongoDaoImpl() {
        this.entityCls = (Class<T>) ReflectionUtils.getParameterizedType(getClass().getGenericSuperclass());
    }

    public Class getEntityClass() {
        return super.getClass();
    }

    private Datastore datastore;

    @Override
    public Object save(T entity) {
        return datastore.save(entity).getId();
    }

    @Override
    public boolean delete(T entity) {
        return datastore.delete(entity).wasAcknowledged();
    }

    @Override
    public boolean delete(MapBeanContext context) {
        Query<T> query = datastore.createQuery(this.entityCls);
        context.getConditions().forEach((condition, value) -> query.filter(condition, value));
        return datastore.delete(query).wasAcknowledged();
    }

    @Override
    public int update(T entity, Map<String, String> fieldValues) {
        UpdateOperations<T> operations = datastore.createUpdateOperations(this.entityCls);
        if (fieldValues != null) {
            fieldValues.forEach((field, value) -> operations.set(field, value));
        }
        return datastore.update(entity, operations).getUpdatedCount();
    }

    @Override
    public List<T> queryAll() {
        return datastore.createQuery(entityCls).asList();
    }

    @Override
    public List<T> query(MapBeanContext context, int offset, int limit) {
        return query(context, offset, limit, null);
    }

    @Override
    public List<T> query(MapBeanContext context, int offset, int limit, Sort sort) {
        final Query<T> query = datastore.createQuery(this.entityCls);
        if (context != null) {
            context.getConditions().forEach((field, value) -> query.filter(field, value));
        }
        if (sort != null) {

        }
        return query.offset(offset).limit(limit).asList();
    }

    @Override
    public List<T> query(MapBeanContext context) {
        final Query<T> query = datastore.createQuery(this.entityCls);
        if (context != null) {
            context.getConditions().forEach((field, value) -> query.filter(field, value));
        }
        return query.asList();
    }

    @Override
    public List<T> executeQuery(MongoQueryCallback<T> callback) {
        Query<T> query = datastore.createQuery(this.entityCls);
        return callback.execute(query);
    }

    @Override
    public List<T> executeQuery(MapBeanContext context, MongoQueryCallback<T> callback) {
        final Query<T> query = datastore.createQuery(this.entityCls);
        if (context != null) {
            context.getConditions().forEach((field, value) -> query.filter(field, value));
        }
        return callback.execute(query);
    }

    public void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }

    protected Datastore getDatastore() {
        return this.datastore;
    }

}

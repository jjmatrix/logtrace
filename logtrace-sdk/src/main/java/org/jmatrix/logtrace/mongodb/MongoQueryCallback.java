package org.jmatrix.logtrace.mongodb;

import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * @author jmatrix
 * @date 16/3/3
 */
@FunctionalInterface
public interface MongoQueryCallback<T> {
    public List<T> execute(Query<T> query);
}

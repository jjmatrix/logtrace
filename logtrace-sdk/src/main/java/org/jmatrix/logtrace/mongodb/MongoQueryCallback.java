package org.jmatrix.logtrace.mongodb;

import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * Callback
 *
 * @author jmatrix
 * @date 16/3/3
 */
@FunctionalInterface
public interface MongoQueryCallback<T> {
    List<T> execute(Query<T> query);
}

package org.jmatrix.logtrace.mongodb;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * @author jmatrix
 * @date 16/3/1
 */
public class MongoDatastoreFactory extends AbstractFactoryBean<Datastore> {

    private MongoClient mongoClient;

    private Morphia morphia;

    private String dbName;

    @Override
    public Class<?> getObjectType() {
        return Datastore.class;
    }

    @Override
    protected Datastore createInstance() throws Exception {
        return morphia.createDatastore(mongoClient, dbName);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        if (mongoClient == null) {
            throw new IllegalStateException("MongoClient not set.");
        }
        if (morphia == null) {
            throw new IllegalStateException("Morphia not set.");
        }
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public void setMorphia(Morphia morphia) {
        this.morphia = morphia;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}

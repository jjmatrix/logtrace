package org.jmatrix.logtrace.jdbc.impl;

import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author jmatrix
 * @date 16/2/14
 */
public class RouteSessionFactory {

    private SqlSessionFactory masterSessionFactory;

    private SqlSessionFactory slaveSessionFactory;

    public SqlSessionFactory getSessionFactory() {
        return masterSessionFactory;
    }

    public SqlSessionFactory getMasterSessionFactory() {
        return masterSessionFactory;
    }

    public SqlSessionFactory getSlaveSessionFactory() {
        return slaveSessionFactory;
    }

    public void setSlaveSessionFactory(SqlSessionFactory slaveSessionFactory) {
        this.slaveSessionFactory = slaveSessionFactory;
    }

    public void setMasterSessionFactory(SqlSessionFactory masterSessionFactory) {
        this.masterSessionFactory = masterSessionFactory;
    }
}

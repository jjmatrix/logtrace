package org.jmatrix.logtrace.jdbc.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jmatrix.logtrace.jdbc.BaseDao;
import org.jmatrix.logtrace.jdbc.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jmatrix
 * @date 16/2/15
 */
public class BaseDaoImpl implements BaseDao {

    private Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

    private SessionFactory sessionFactory;

    protected SqlSession getSession(boolean isAutoCommit) {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        if (sessionFactory == null || sqlSessionFactory == null) {
            return null;
        }
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession(isAutoCommit);
        } catch (Exception e) {

        }
        return sqlSession;
    }


    private SqlSessionFactory getSqlSessionFactory() {
        return getRouteSessionFactory().getMasterSessionFactory();
    }

    /**
     * Get @{RouteSessionFactory}, use default group if generate don't overriding it
     *
     * @return
     */
    protected RouteSessionFactory getRouteSessionFactory() {
        return this.sessionFactory.getRouteSessionFactory(DEFAULT_GROUP);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

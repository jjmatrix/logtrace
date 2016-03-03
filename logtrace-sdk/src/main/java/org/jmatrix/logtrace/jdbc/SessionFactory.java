package org.jmatrix.logtrace.jdbc;

import org.jmatrix.logtrace.jdbc.impl.RouteSessionFactory;

/**
 * @author jmatrix
 * @date 16/2/14
 */
public interface SessionFactory {
    RouteSessionFactory getRouteSessionFactory(String group);
}

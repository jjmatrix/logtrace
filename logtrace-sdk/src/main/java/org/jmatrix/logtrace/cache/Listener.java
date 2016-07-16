package org.jmatrix.logtrace.cache;

/**
 * @author: jmatrix
 * @date: 16/1/19
 */
public interface Listener<T> {
    T onEvent(String receiveData);
}

package org.jmatrix.logtrace.client.config;

import org.jmatrix.logtrace.client.config.data.Event;

/**
 * @author: jmatrix
 * @date: 16/1/19
 */
public interface Listener {
    public void dataReceive(Event event);
}

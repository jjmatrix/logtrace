package org.jmatrix.logtrace.cache;

/**
 * @author jmatrix
 * @date 16/7/16
 */
public class CacheService {

    private Listener listener;

    public CacheService(Listener listener) {
        this.listener = listener;
    }

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}

package org.jmatrix.logtrace.client.config.data;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: jmatrix
 * @date: 16/1/19
 */
public final class WhiteUidCacheData {

    private Set<String> whiteUidSet;

    private Set<String> whiteUidSetBak;

    private ReentrantLock swapLock = new ReentrantLock();

    private long lastUpdateTime;


    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void swap() {
        try {
            swapLock.lock();
            if (this.whiteUidSetBak != null) {
                this.whiteUidSet = this.whiteUidSetBak;
                this.whiteUidSetBak = null;
            }

        } finally {
            swapLock.unlock();
        }
    }

    public void loadAndSwap(String uids) {
        this.whiteUidSetBak = parseUid(uids);
        swap();
    }

    public void initWhiteUid(String uids) {
        this.whiteUidSet = parseUid(uids);
    }

    public boolean isContainUid(String uid) {
        return this.whiteUidSet.contains(uid);
    }

    private Set<String> parseUid(String uids) {
        StringTokenizer tokenizer = new StringTokenizer(uids, ",");
        Set<String> whiteUids = new HashSet<String>(tokenizer.countTokens());
        while (tokenizer.hasMoreTokens()) {
            whiteUids.add(tokenizer.nextToken());
        }
        return whiteUids;
    }

    public Set<String> getWhiteUidSet() {
        return Collections.unmodifiableSet(whiteUidSet);
    }
}

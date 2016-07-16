package org.jmatrix.logtrace.core;

/**
 * @author jmatrix
 * @date 16/7/16
 */
public class ZkConfig {

    private long version;

    private String content;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

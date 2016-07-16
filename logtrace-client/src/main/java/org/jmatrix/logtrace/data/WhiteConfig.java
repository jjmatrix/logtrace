package org.jmatrix.logtrace.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

/**
 * @author jmatrix
 * @date 16/2/10
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WhiteConfig {

    /**
     * 0:关闭,1:开启,other:待定
     */
    @JsonProperty(value = "status")
    private int status;

    @JsonProperty(value = "whiteUids")
    private Set<String> whiteUids;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<String> getWhiteUids() {
        return whiteUids;
    }

    public void setWhiteUids(Set<String> whiteUids) {
        this.whiteUids = whiteUids;
    }

    @Override
    public String toString() {
        return "WhiteConfig{" +
                "status=" + status +
                ", whiteUids=" + whiteUids +
                '}';
    }
}

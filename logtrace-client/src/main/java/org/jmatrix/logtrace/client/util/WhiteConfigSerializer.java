package org.jmatrix.logtrace.client.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.jmatrix.logtrace.client.config.data.WhiteConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author: jmatrix
 * @date: 16/2/8
 */
public class WhiteConfigSerializer implements ZkSerializer {

    private Logger logger = LoggerFactory.getLogger(WhiteConfigSerializer.class);

    public byte[] serialize(Object data) throws ZkMarshallingError {
        if (!(data instanceof WhiteConfig)) {
            throw new ZkMarshallingError("datatype must be WhiteConfig.");
        }
        try {
            return JsonUtils.toJson((WhiteConfig) data, WhiteConfig.class);
        } catch (JsonProcessingException e) {
            throw new ZkMarshallingError("WhiteConfig to json failed.", e);
        }
    }

    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        try {
            return JsonUtils.fromJson(bytes, WhiteConfig.class);
        } catch (IOException e) {
            throw new ZkMarshallingError("from json to WhiteConfig failed.", e);
        }
    }
}

package org.jmatrix.logtrace.client.util;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * @author: jmatrix
 * @date: 16/2/8
 */
public class StringPushThroughSerializer implements ZkSerializer {
    private Logger logger = LoggerFactory.getLogger(StringPushThroughSerializer.class);

    private String encoding;

    public StringPushThroughSerializer() {
    }

    public StringPushThroughSerializer(String encoding) {
        this.encoding = encoding;
    }

    public byte[] serialize(Object data) throws ZkMarshallingError {
        try {
            if (encoding != null) {
                return ((String) data).getBytes(encoding);
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("encode zk data failed.", e);
        }
        return ((String) data).getBytes();
    }

    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        try {
            if (encoding != null) {
                return new String(bytes, encoding);
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("decode zk data failed.", e);
        }
        return new String(bytes);
    }
}

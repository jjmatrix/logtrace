package org.jmatrix.logtrace.zk;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.jmatrix.logtrace.utils.JsonUtils;
import org.mongodb.morphia.utils.ReflectionUtils;

import java.io.IOException;

/**
 * @author: jmatrix
 * @date: 16/2/8
 */
public class ZkObjectSerializer<T> implements ZkSerializer {

    private Class<T> serializeCls;

    public ZkObjectSerializer() {
        this.serializeCls = ReflectionUtils.getParameterizedClass(getClass());
    }

    public ZkObjectSerializer(Class<T> serializeCls) {
        this.serializeCls = serializeCls;
    }

    public byte[] serialize(Object data) throws ZkMarshallingError {
        if (!data.getClass().isAssignableFrom(serializeCls)) {
            throw new ZkMarshallingError("datatype must be " + this.serializeCls);
        }
        try {
            return JsonUtils.toJson((T) data, serializeCls);
        } catch (JsonProcessingException e) {
            throw new ZkMarshallingError("WhiteConfig to json failed.", e);
        }
    }

    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        try {
            return JsonUtils.fromJson(bytes, serializeCls);
        } catch (IOException e) {
            throw new ZkMarshallingError("from json to WhiteConfig failed.", e);
        }
    }
}

package org.jmatrix.logtrace.client.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author: jmatrix
 * @date: 16/1/19
 */
public class JsonUtils {
    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private final static ObjectMapper mapper = new ObjectMapper();

    public static <T> T fromJson(String jsonStr, Class<T> cls) {
        T obj = null;
        try {
            obj = mapper.readValue(jsonStr, cls);
        } catch (IOException e) {

        }
        return obj;
    }
}

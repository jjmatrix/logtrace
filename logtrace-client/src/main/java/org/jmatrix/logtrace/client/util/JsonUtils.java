package org.jmatrix.logtrace.client.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Json Utils
 *
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

    public static <T> T fromJson(byte[] jsonBytes, Class<T> cls) throws IOException {
        try {
            return mapper.readValue(jsonBytes, cls);
        } catch (IOException e) {
            throw e;
        }
    }

    public static <T> byte[] toJson(T jsonObject, Class<T> cls) throws JsonProcessingException {
        try {
            return mapper.writeValueAsBytes(jsonObject);
        } catch (JsonProcessingException e) {
            logger.error("toJson error. jsonObject:{}", jsonObject);
            throw e;
        }
    }

    public static <T> String toJsonString(T jsonObject, Class<T> cls) throws JsonProcessingException {
        try {
            return mapper.writeValueAsString(jsonObject);
        } catch (JsonProcessingException e) {
            logger.error("toJson error. jsonObject:{}", jsonObject);
            throw e;
        }
    }
}

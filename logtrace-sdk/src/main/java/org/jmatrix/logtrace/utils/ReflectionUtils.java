package org.jmatrix.logtrace.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Reflect Tools
 *
 * @author jmatrix
 * @date 16/3/3
 */
public class ReflectionUtils {

    /**
     * Get parameterized type
     *
     * @param genericType
     * @return
     */
    public static Class<?> getParameterizedType(final Type genericType) {
        if (genericType instanceof ParameterizedType) {
            Type[] param = ((ParameterizedType) genericType).getActualTypeArguments();
            return (Class) param[0];
        }
        return null;
    }

}

package com.malaone.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;

/**
 * @Author: xulifei
 * @Date: 19:27 2019/1/3
 * @Description: json util base on Jackson
 */
public class JacksonUtil {

    // json ---> bean
    //-------------------------------------------------------------------------

    /**
     * json to bean
     *
     * @param json json
     * @param clazz class of bean
     * @param isSnakeCase lower case with underscores to camel case
     * @param <T> class of bean
     * @return bean
     */
    public static <T> T jsonToBean(String json, Class<T> clazz, boolean isSnakeCase) {
        ObjectMapper mapper = new ObjectMapper();
        // mapper的configure方法可以设置多种配置（例如：多字段 少字段的处理）
        //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if (isSnakeCase) {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        }

        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    // bean ---> json
    //-------------------------------------------------------------------------

    /**
     * bean to json，驼峰转下划线
     *
     * @param obj bean
     * @param <T> T
     * @return json
     */
    public static <T> String bean2JsonSnakeCase(T obj) {
        return bean2Json(obj, true);
    }

    /**
     * bean to json
     *
     * @param obj bean
     * @param <T> T
     * @return json
     */
    public static <T> String bean2Json(T obj) {
        return bean2Json(obj, false);
    }


    /**
     * bean to json
     * 无法将String类型驼峰转下划线
     *
     * @param obj         bean
     * @param isSnakeCase camel case to lower case with underscores
     * @param <T>         T
     * @return json
     */
    public static <T> String bean2Json(T obj, boolean isSnakeCase) {
        if (obj == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        if (isSnakeCase) {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        }

        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

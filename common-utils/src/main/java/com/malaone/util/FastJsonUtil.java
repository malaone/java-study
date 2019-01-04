package com.malaone.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.List;
import java.util.Map;

/**
 * @Author: xulifei
 * @Date: 19:27 2019/1/3
 * @Description: json util base on Fastjson
 */
public class FastJsonUtil {

    private FastJsonUtil(){throw new AssertionError();}

    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer());
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
//        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase; //下划线转驼峰
    }
    private static final SerializerFeature[] features = {
            SerializerFeature.WriteMapNullValue,    //输出空置字段
            SerializerFeature.WriteNullListAsEmpty, //list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero,//数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,// Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty  // 字符类型字段如果为null，输出为""，而不是null
    };

    /**
     * 按照特性和序列化配置将对象转换为字符串
     *
     * @param obj obj
     * @return json
     */
    public static String toJSONString(Object obj) {
        return JSON.toJSONString(obj, config, features);
    }

    /**
     * 按照序列化配置将对象装换为字符串
     *
     * @param obj obj
     * @return json
     */
    public static String toJSONNoFeatures(Object obj) {
        return JSON.toJSONString(obj, config);
    }


    /**
     * 按照默认特性进行转为对象
     * @param str
     * @return object
     */
    public static Object toBean(String str) {
        return JSON.parse(str);
    }

    /**
     * 按照指定clazz转化为对象
     *
     * @param json json
     * @param clazz class of bean
     * @param <T> class of bean
     * @return bean
     */
    public static <T> T toBean(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * 字符串转换为数组
     *
     * @param str
     * @return object[]
     */
    public static Object[] toArray(String str) {
        return toArray(str, null);
    }

    /**
     * 字符串转换为对象数组
     *
     * @param str
     * @param clazz
     * @param <T>
     * @return object[]
     */
    public static <T> Object[] toArray(String str, Class<T> clazz) {
        return JSON.parseArray(str, clazz).toArray();
    }

    /**
     * 字符串转化List
     *
     * @param str
     * @param clazz
     * @param <T>
     * @return List
     */
    public static <T> List<T> toList(String str, Class<T> clazz) {
        return JSON.parseArray(str, clazz);
    }

    /**
     * 将javabean转化为序列化的json字符串
     *
     * @param keyvalue
     * @return object
     */
    public static Object beanToJson(KeyValue keyvalue) {
        String textJson = JSON.toJSONString(keyvalue);
        return JSON.parse(textJson);
    }

    /**
     * 将string转化为序列化的json字符串
     *
     * @param str
     * @return object
     */
    public static Object strToJson(String str) {
        return JSON.parse(str);
    }

    /**
     * json字符串转化为map
     * @param str
     * @return map
     */
    public static Map jsonToMap(String str) {
        return JSONObject.parseObject(str);
    }

    /**
     * 将map转化为string
     * @param map
     * @return string
     */
    public static String mapToJson(Map map) {
        return JSONObject.toJSONString(map);
    }
}

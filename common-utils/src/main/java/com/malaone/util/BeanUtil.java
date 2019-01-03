package com.malaone.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xulifei
 * @Date: 10:03 2019/1/3
 * @Description:
 */
public class BeanUtil {


    // json <---> bean
    //-------------------------------------------------------------------------

    /**
     * bean to json，驼峰转下划线
     *
     * @param obj bean
     * @param <T> T
     * @return json
     */
    public static <T> String bean2JacksonSnakeCase(T obj) {
        return bean2Jackson(obj, true);
    }

    /**
     * bean to json
     *
     * @param obj bean
     * @param <T> T
     * @return json
     */
    public static <T> String bean2Jackson(T obj) {
        return bean2Jackson(obj, false);
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
    public static <T> String bean2Jackson(T obj, boolean isSnakeCase) {
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

    // map <---> bean
    //-------------------------------------------------------------------------

    /**
     * map转bean，根据内省和反射进行转换
     * 当map.key=bean.field名称且map.value的类型与bean.field类型一致才会对该属性赋值
     *
     * @param map map
     * @param bean bean
     */
    public static void mapToBean(Map<String, Object> map, Object bean) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                String fieldName = propertyDescriptor.getName();

                if (map.containsKey(fieldName)) {
                    Object value = map.get(fieldName);
                    Method setter = propertyDescriptor.getWriteMethod();

                    //setter方法参数检查：有且只有一个参数，校验类型与map中是否一致
                    Class<?>[] type = setter.getParameterTypes();
                    if (type[0].isInstance(value)) {
                        setter.invoke(bean, value);
                    }
                }
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     * map转bean，根据BeanUtils进行转换
     * 会进行类型检查，String类型时可以传入任何对象，调用.toString()方法，
     * Integer等基本数据类型包装类默认值为不是null，为基本数据类型默认值
     *
     * @param map map
     * @param bean bean
     */
    public static void mapToBean2(Map<String, Object> map, Object bean) {
        try {
            BeanUtils.populate(bean, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * bean转map，map值为Object
     *
     * @param bean bean
     * @return Map<String, Object>
     */
    public static Map<String, Object> beanToMap(Object bean) {
        if (bean == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                String fieldName = propertyDescriptor.getName();

                if (!"class".equals(fieldName)) {
                    Method getter = propertyDescriptor.getReadMethod();
                    Object value = getter.invoke(bean);
                    map.put(fieldName, value);
                }
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * bean转map，map值为String
     * bean中属性为对象时，调用toString()转为String，
     * map中会多一对键值对：class=class + " " + 类的全限定名
     *
     * @param bean bean
     * @return Map<String, String>
     */
    public static Map<String, String> beanToMap2(Object bean) {
        try {
            return BeanUtils.describe(bean);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * bean转map，map值为Object
     * map中会多一对键值对：class=class + " " + 类的全限定名
     *
     * @param bean bean
     * @return Map<String, String>
     */
    public static Map beanToMap3(Object bean) {
        return new BeanMap(bean);
    }

}

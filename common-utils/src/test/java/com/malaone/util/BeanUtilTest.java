package com.malaone.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Author: xulifei
 * @Date: 14:10 2019/1/3
 * @Description:
 */
public class BeanUtilTest {


    @Test
    public void beanToJson() {
        String a = "{\"a\":\"bbCc\"}";
    }


    @Test
    public void beanToMap() {
        ExampleBean exampleBean = new ExampleBean();
        ExampleBean exampleBean2 = new ExampleBean();
        exampleBean2.setAB("AB2");
        exampleBean.setAB("AB");
        exampleBean.setAge(22);
        exampleBean.setHeight(180);
        exampleBean.setmN("mN");
        exampleBean.setName("Jack");
        exampleBean.setExampleBean(exampleBean2);

        Map map = BeanUtil.beanToMap3(exampleBean);
        System.out.println(map.get("exampleBean") instanceof ExampleBean);
        map.forEach((k,v)-> System.out.println(k));
    }

    @Test
    public void mapToBean() {
        ExampleBean exampleBean = new ExampleBean();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Jack");
        map.put("age", "aa");
        map.put("height", exampleBean);
        map.put("mN", "male");
        map.put("AB", "ab");

        BeanUtil.mapToBean(map, exampleBean);
        System.out.println(exampleBean);
        System.out.println(map);
    }
}
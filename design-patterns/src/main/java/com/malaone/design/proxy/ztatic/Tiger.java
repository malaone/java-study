package com.malaone.design.proxy.ztatic;

/**
 * @Author: xulifei
 * @Date: 16:26 2019/1/7
 * @Description:
 */
public class Tiger implements Animal {

    private String name;

    public Tiger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void eat(String foodName) {
        System.out.println(name + " is eating " + foodName);
    }
}

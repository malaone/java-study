package com.malaone.base;

/**
 * @Author: xulifei
 * @Date: 15:34 2019/3/4
 * @Description:
 */
public class Child extends Parent {
    private String c;

    @Override
    public String get() {
        return "c:" + c;
    }

    @Override
    public void set(String c) {
        this.c = c;
    }
}

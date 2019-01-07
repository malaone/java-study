package com.malaone.design.proxy.ztatic;

/**
 * @Author: xulifei
 * @Date: 16:59 2019/1/7
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        Tiger tiger = new Tiger("东北虎");
        Animal staticProxy = new StaticProxy(tiger);
        staticProxy.eat("pig");
    }
}

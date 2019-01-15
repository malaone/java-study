package com.malaone.design.proxy.cglib;

/**
 * @Author: xulifei
 * @Date: 14:53 2019/1/15
 * @Description: 被代理类
 */
public class Target {

    public Target() {
        System.out.println("Target构造器");
    }


    /**
     * 该方法不能被子类覆盖,Cglib是无法代理final修饰的方法的
     */
    final public String finalMethod(String name) {
        System.out.println("Target:finalMethod>>"+name);
        return null;
    }

    public void sayHello() {
        System.out.println("Target:sayHello");
    }
}

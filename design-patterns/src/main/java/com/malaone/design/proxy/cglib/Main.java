package com.malaone.design.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @Author: xulifei
 * @Date: 15:02 2019/1/15
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        // 代理类class文件存入本地磁盘方便我们反编译查看源码
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\tmp\\cglib");
        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(Target.class);
        // 设置enhancer的回调对象
        enhancer.setCallback(new CglibMethodInterceptor());
        // 创建代理对象
        Target proxy = (Target) enhancer.create();
        // 通过代理对象调用目标方法
        proxy.sayHello();
        proxy.finalMethod("aaa");
    }
}

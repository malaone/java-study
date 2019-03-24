package com.malaone.design.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: xulifei
 * @Date: 14:57 2019/1/15
 * @Description:
 */
public class CglibMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before method: " + method.getName());
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("after method: " + method.getName());
        return object;
    }
}

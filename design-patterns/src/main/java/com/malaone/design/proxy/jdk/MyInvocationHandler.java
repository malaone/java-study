package com.malaone.design.proxy.jdk;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author: xulifei
 * @Date: 14:34 2019/1/8
 * @Description:
 */

@Slf4j
public class MyInvocationHandler<T> implements InvocationHandler {

    private T target;

    public MyInvocationHandler(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("开始======================");
        log.info("代理类：{}", proxy.getClass());
        log.info("方法名：{}, 方法返回类型：{}", method.getName(), method.getReturnType());
        log.info("接口方法入参数组：{}", (args ==null ? "null" : Arrays.toString(args)));

        Object result = method.invoke(target, args);
        log.info("结束======================");
        return result;
    }
}

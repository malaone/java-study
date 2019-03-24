package com.malaone.base;

/**
 * @Author: xulifei
 * @Date: 16:19 2019/3/4
 * @Description:
 */
public class A<T> {
    private T t;
    public T get(){
        return t;
    }
    public void set(T t){
        this.t=t;
    }
}

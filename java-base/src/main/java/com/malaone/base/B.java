package com.malaone.base;

/**
 * @Author: xulifei
 * @Date: 16:19 2019/3/4
 * @Description:
 */
public class B extends A<Number> {
    private Number n;

    @Override
    public Number get() {//返回类型是Number，与A中返回Object(原型)不一样，不能算重写
        return n;
    }

    @Override
    public void set(Number n) {//入参类型与A不一样
        this.n=n;
    }
}

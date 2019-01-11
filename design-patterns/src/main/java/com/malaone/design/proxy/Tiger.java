package com.malaone.design.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: xulifei
 * @Date: 16:26 2019/1/7
 * @Description:
 */
@Slf4j
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
        log.info("{} is eating {}", name, foodName);
    }
}

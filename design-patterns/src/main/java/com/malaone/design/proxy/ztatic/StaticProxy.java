package com.malaone.design.proxy.ztatic;

import com.malaone.design.proxy.Animal;
import com.malaone.design.proxy.Tiger;

/**
 * @Author: xulifei
 * @Date: 16:53 2019/1/7
 * @Description: 静态代理类
 */
public class StaticProxy implements Animal {

    private Animal animal;

    public StaticProxy(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void eat(String foodName) {
        if (animal instanceof Tiger) {
            Tiger tiger = (Tiger) animal;
            System.out.println("tiger " + tiger.getName() + " is comming");
        } else {
            System.out.println("not tiger");
        }

        animal.eat(foodName);
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}

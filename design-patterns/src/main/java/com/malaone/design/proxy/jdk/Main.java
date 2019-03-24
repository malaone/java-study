package com.malaone.design.proxy.jdk;

import com.malaone.design.proxy.Animal;
import com.malaone.design.proxy.Tiger;

import java.lang.reflect.Proxy;

/**
 * @Author: xulifei
 * @Date: 14:46 2019/1/8
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        Animal animal = new Tiger("Tidy");
        MyInvocationHandler<Animal> handler = new MyInvocationHandler<>(animal);
        Animal animalProxy1 = (Animal) Proxy.newProxyInstance(
                animal.getClass().getClassLoader(), animal.getClass().getInterfaces(), handler);

        //除了该方法，还有equals, toString, hashCode三个方法做了增强
        animalProxy1.eat("bread");
        System.out.println(animalProxy1 instanceof Proxy); //true
    }
}

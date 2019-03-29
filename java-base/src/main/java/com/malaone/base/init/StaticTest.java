package com.malaone.base.init;

/**
 * @Author: xulifei
 * @Date: 15:46 2019/3/24
 * @Description:
 */
public class StaticTest {
    public static void main(String[] args)
    {
        staticFunction();
    }

    static StaticTest st = new StaticTest();

    {
        g = 1;
        System.out.println("g" + g);
    }
    private static int g;
    static
    {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    StaticTest()
    {
        System.out.println("3");
        System.out.println("a="+a+",b="+b);
    }

    public static void staticFunction(){
        System.out.println("4");
    }

    int a=110;
    static int b =112;
}

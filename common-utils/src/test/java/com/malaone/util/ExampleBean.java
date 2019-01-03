package com.malaone.util;

/**
 * @Author: xulifei
 * @Date: 14:07 2019/1/3
 * @Description: 样例bean
 */
public class ExampleBean {

    private String  name;
    private Integer age;
    private int height;
    private String mN;
    private String AB;
    private ExampleBean exampleBean;

    public ExampleBean getExampleBean() {
        return exampleBean;
    }

    public void setExampleBean(ExampleBean exampleBean) {
        this.exampleBean = exampleBean;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getAB() {
        return AB;
    }

    public void setAB(String AB) {
        this.AB = AB;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getmN() {
        return mN;
    }

    public void setmN(String mN) {
        this.mN = mN;
    }

    @Override
    public String toString() {
        return "ExampleBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", mN='" + mN + '\'' +
                ", AB='" + AB + '\'' +
                ", exampleBean=" + exampleBean +
                '}';
    }
}

package com.malaone.design.chain;

/**
 * @Auther: xulifei
 * @Date: 2019-01-08 22:26
 * @Description: 模拟mvc框架的控制器
 */
public class Controller {

    public static void process(Request request, Response response) {
        response.setCode("0");
        response.setMsg("成功");
        response.setData("欢迎" + request.getUsername());
    }
}

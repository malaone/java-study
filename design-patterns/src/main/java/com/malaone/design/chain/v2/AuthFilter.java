package com.malaone.design.chain.v2;

import com.malaone.design.chain.Controller;
import com.malaone.design.chain.Request;
import com.malaone.design.chain.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: xulifei
 * @Date: 2019-01-08 22:20
 * @Description:
 */
@Slf4j
public class AuthFilter extends Filter {
    @Override
    public void doFilter(Request request, Response response) {
        String role = request.getRole();

        if ("admin".equals(role)) {
            log.info("访问权限验证通过");
            Controller.process(request, response);

        } else {
            response.setCode("3");
            response.setMsg("没有访问权限");
            log.info("访问权限验证：没有权限");
        }
    }
}

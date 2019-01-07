package com.malaone.design.chain.v1;

import com.malaone.design.chain.Request;
import com.malaone.design.chain.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: xulifei
 * @Date: 11:24 2019/1/7
 * @Description: 安全认证
 */
@Slf4j
public class SecurityFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        String username = request.getUsername();
        String password = request.getPassword();

        if ("malaone".equals(username) && "123456".equals(password)) {
            log.info("用户名密码正确");
            chain.doFilter(request, response);
        } else {
            response.setCode("2");
            response.setMsg("用户名或密码不正确");
            log.info("用户名或密码不正确");
        }

    }
}

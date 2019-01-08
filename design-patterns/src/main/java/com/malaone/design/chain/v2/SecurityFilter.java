package com.malaone.design.chain.v2;

import com.malaone.design.chain.Request;
import com.malaone.design.chain.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: xulifei
 * @Date: 2019-01-08 22:15
 * @Description:
 */
@Slf4j
public class SecurityFilter extends Filter {
    @Override
    public void doFilter(Request request, Response response) {
        String username = request.getUsername();
        String password = request.getPassword();

        if ("malaone".equals(username) && "123456".equals(password)) {
            log.info("用户名密码正确");
            doNext(request, response);

        } else {
            response.setCode("2");
            response.setMsg("用户名或密码不正确");
            log.info("用户名或密码不正确");
        }
    }
}

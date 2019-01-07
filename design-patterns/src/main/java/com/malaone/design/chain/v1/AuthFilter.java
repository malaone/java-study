package com.malaone.design.chain.v1;

import com.malaone.design.chain.Request;
import com.malaone.design.chain.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: xulifei
 * @Date: 11:23 2019/1/7
 * @Description: 访问权限认证
 */
@Slf4j
public class AuthFilter implements Filter{
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        String role = request.getRole();

        if ("admin".equals(role)) {
            log.info("访问权限验证通过");
            chain.doFilter(request, response);
        } else {
            response.setCode("3");
            response.setMsg("没有访问权限");
            log.info("访问权限验证：没有权限");
        }
    }
}

package com.malaone.design.chain.v1;

import com.malaone.design.chain.Request;
import com.malaone.design.chain.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: xulifei
 * @Date: 11:37 2019/1/7
 * @Description:
 */
@Slf4j
public class Main {

    public static void main(String[] args) {
        Request request = new Request();
        request.setIp("192.168.1.1");
        request.setUsername("malaone2");
        request.setPassword("123456");
        request.setRole("admin");

        Filter whiteListFilter = new WhiteListFilter();
        Filter securityFilter = new SecurityFilter();
        Filter authFilter = new AuthFilter();

        FilterChain filterChain = new FilterChain();
        filterChain.addFilters(whiteListFilter);
        filterChain.addFilters(securityFilter);
        filterChain.addFilters(authFilter);

        Response response = new Response();
        filterChain.doFilter(request, response);
        log.info(response.toString());
    }
}

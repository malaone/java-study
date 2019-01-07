package com.malaone.design.chain.v1;

import com.malaone.design.chain.Request;
import com.malaone.design.chain.Response;

/**
 * @Author: xulifei
 * @Date: 10:01 2019/1/3
 * @Description: 过滤器接口
 */
public interface Filter {

    void doFilter(Request request, Response response, FilterChain chain);
}

package com.malaone.design.chain.v1;

import com.malaone.design.chain.Request;
import com.malaone.design.chain.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xulifei
 * @Date: 10:01 2019/1/3
 * @Description: 过滤器链接口
 */
public class FilterChain {

    private List<Filter> filters = new ArrayList<>();
    private int index = 0;

    public void addFilters(Filter filter) {
        filters.add(filter);
    }

    public void doFilter(Request request, Response response) {
        if (index == filters.size()) {
            response.setCode("0");
            response.setMsg("成功");
            response.setData("欢迎。。。");
            return;
        }
        Filter filter = filters.get(index++);
        filter.doFilter(request, response, this);
    }
}

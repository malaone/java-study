package com.malaone.design.chain.v2;

import com.malaone.design.chain.Controller;
import com.malaone.design.chain.Request;
import com.malaone.design.chain.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: xulifei
 * @Date: 10:14 2019/1/7
 * @Description: 版本2过滤器
 */
@Slf4j
public abstract class Filter {

    protected Filter successorFilter;

    public void setSuccessorFilter(Filter successorFilter) {
        this.successorFilter = successorFilter;
    }

    public abstract void doFilter(Request request, Response response);

    protected void doNext(Request request, Response response) {
        if (successorFilter != null) {
            successorFilter.doFilter(request, response);
        } else {
            log.info("通过所有过滤器进入Controller");
            Controller.process(request, response);
        }
    }

}

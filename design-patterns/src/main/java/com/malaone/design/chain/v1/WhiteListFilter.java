package com.malaone.design.chain.v1;

import com.malaone.design.chain.Request;
import com.malaone.design.chain.Response;
import com.malaone.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xulifei
 * @Date: 10:57 2019/1/7
 * @Description: 白名单过滤器
 */
@Slf4j
public class WhiteListFilter implements Filter {

    private static List<String> whiteList = new ArrayList<>();

    static {
        whiteList.add("192.168.1.1");
    }

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        String ip = request.getIp();
        if (StringUtil.isNotBlank(ip) && whiteList.contains(ip)) {
            log.info("通过白名单校验");
            chain.doFilter(request, response);
        } else {
            response.setCode("1");
            response.setMsg("白名单限制");
            log.info("不在白名单");
        }
    }
}

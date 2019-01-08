package com.malaone.design.chain.v2;

import com.malaone.design.chain.Request;
import com.malaone.design.chain.Response;
import com.malaone.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xulifei
 * @Date: 2019-01-08 22:08
 * @Description: 白名单过滤器
 */
@Slf4j
public class WhiteListFilter extends Filter {

    private static List<String> whiteList = new ArrayList<>();

    static {
        whiteList.add("192.168.1.1");
    }

    @Override
    public void doFilter(Request request, Response response) {
        String ip = request.getIp();
        if (StringUtil.isNotBlank(ip) && whiteList.contains(ip)) {
            log.info("通过白名单校验");
            doNext(request, response);

        } else {
            response.setCode("1");
            response.setMsg("白名单限制");
            log.info("不在白名单");
        }
    }
}

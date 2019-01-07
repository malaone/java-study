package com.malaone.design.chain;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: xulifei
 * @Date: 10:14 2019/1/7
 * @Description:
 */
@Data
@ToString
public class Response {
    private String code;
    private String msg;
    private String data;
}

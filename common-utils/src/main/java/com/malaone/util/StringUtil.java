package com.malaone.util;

/**
 * @Author: xulifei
 * @Date: 10:31 2019/1/3
 * @Description:
 */
public class StringUtil {

    public static boolean isNotBlank(String str) {
        return str == null || "".equals(str);
    }
}

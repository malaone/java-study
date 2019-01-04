package com.malaone.util;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * @Author: xulifei
 * @Date: 14:25 2019/1/3
 * @Description:
 */
public class DateUtil {

    //常见的时间格式
    private static final String[] possibleFormats = {
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd",
            "yyyy-MM",
            "yyyyMMdd",
            "yyyyMM",
            "yyyy/MM/dd",
            "yyyy/MM",
            "yyyy MM dd",
            "yyyy MM",
            "yyyy.MM.dd",
            "yyyy.MM",
            "dd MMMM yyyy",
            "MMMM yyyy",
            "MMMM dd, yyyy",
            "yyyy",
            "yyyy年MM月dd日",
            "yyyy年MM月",
            "yyyy年"
    };

    /**
     * 时间解析：将格式化的时间解析成java.util.Date对象
     *
     * @param inputDate 待解析时间
     * @return java.util.Date
     */
    public static Date parseDate(String inputDate) {
        inputDate = inputDate.trim();
        try {
            return DateUtils.parseDate(inputDate, Locale.CHINESE, possibleFormats);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            return DateUtils.parseDate(inputDate, Locale.ENGLISH, possibleFormats);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

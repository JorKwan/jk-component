package com.persagy.fm.mybatis.utils;

/**
 * 字符串工具类
 *
 * @author lixing
 * @version V1.0 2021/4/23 4:23 下午
 **/
public class MyBatisStringUtils {
    /**
     * 首字母大写
     *
     * @param s 字符串
     * @return 首字母大写后的字符串
     * @author lixing
     * @version V1.0 2021/4/23 4:24 下午
     */
    public static String firstCharUpperCase(String s) {
        char[] cs = s.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);

    }
}

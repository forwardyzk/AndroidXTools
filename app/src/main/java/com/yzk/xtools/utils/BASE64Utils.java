package com.yzk.xtools.utils;

import android.util.Base64;

/**
 * Author:yang
 * Time:2019/4/19 10:30
 * Description:Base64编码工具类
 */
public class BASE64Utils {

    public static final String UTIL_DESC = "BASE64编码工具类";
    public static final String UTIL_NAME = BASE64Utils.class.getSimpleName();


    /**
     * base64编码
     *
     * @param input
     * @return
     */
    public static byte[] encodeBase64(byte[] input) {
        return Base64.encode(input, Base64.DEFAULT);
    }

    /**
     * base64编码
     *
     * @param s
     * @return
     */
    public static String encodeBase64(String s) {
        return Base64.encodeToString(s.getBytes(), Base64.DEFAULT);
    }

    /**
     * base64解码
     *
     * @param input
     * @return
     */
    public static byte[] decodeBase64(byte[] input) {
        return Base64.decode(input, Base64.DEFAULT);
    }

    /**
     * base64解码
     *
     * @param s
     * @return
     */
    public static String decodeBase64(String s) {
        return new String(Base64.decode(s, Base64.DEFAULT));
    }
}

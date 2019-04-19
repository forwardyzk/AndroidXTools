package com.yzk.xtools.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Author:yang
 * Time:2019/4/19 10:40
 * Description:
 */
public class SHA1Utils {

    public static final String UTIL_DESC = "SHA1工具类";
    public static final String UTIL_NAME = SHA1Utils.class.getSimpleName();

    /**
     * Encode SHA1 for a string
     *
     * @param s
     * @return
     */
    public static String SHA1(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            return toHexString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String toHexString(byte[] keyData) {
        if (keyData == null) {
            return null;
        }
        int expectedStringLen = keyData.length * 2;
        StringBuilder sb = new StringBuilder(expectedStringLen);
        for (int i = 0; i < keyData.length; i++) {
            String hexStr = Integer.toString(keyData[i] & 0x00FF, 16);
            if (hexStr.length() == 1) {
                hexStr = "0" + hexStr;
            }
            sb.append(hexStr);
        }
        return sb.toString();
    }

}

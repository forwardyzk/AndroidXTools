package com.yzk.xtools.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @Description: 关闭工具类
 * @Author: yang
 * @Time: 2019/4/18 11:38
 */


public final class IOCloseUtil {

    public static final String UTIL_DESC = "IO流关闭工具类";
    public static final String UTIL_NAME = IOCloseUtil.class.getSimpleName();


    private IOCloseUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 关闭IO
     *
     * @param closeables closeables
     */
    public static void closeIO(Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 安静关闭IO
     *
     * @param closeables closeables
     */
    public static void closeIOQuietly(Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
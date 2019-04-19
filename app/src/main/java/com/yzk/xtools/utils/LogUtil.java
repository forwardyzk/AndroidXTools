package com.yzk.xtools.utils;

import android.util.Log;

import java.util.Formatter;

/**
 * @Description: 日志管理器
 * @Author: yang
 * @Time: 2019/4/18 14:00
 */
public final class LogUtil {
    public static final String UTIL_DESC = "日志管理器工具类";
    public static final String UTIL_NAME = LogUtil.class.getSimpleName();

    public static void init(boolean debug, String tag) {
        sDebug = debug;
        sTag = tag;
    }

    private static final int V = Log.VERBOSE;
    private static final int D = Log.DEBUG;
    private static final int I = Log.INFO;
    private static final int W = Log.WARN;
    private static final int E = Log.ERROR;
    private static final int A = Log.ASSERT;

    private static String sTag = "Log";
    private static boolean sDebug = false;

    private LogUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void v(String msg) {
        log(V, sTag, msg);
    }

    public static void v(String tag, String msg) {
        log(V, tag, msg);
    }

    public static void d(String msg) {
        log(D, sTag, msg);
    }

    public static void d(String tag, String msg) {
        log(D, tag, msg);
    }

    public static void i(String msg) {
        log(I, sTag, msg);
    }

    public static void i(String tag, String msg) {
        log(I, tag, msg);
    }

    public static void w(String msg) {
        log(W, sTag, msg);
    }

    public static void w(String tag, String msg) {
        log(W, tag, msg);
    }

    public static void e(String msg) {
        log(E, sTag, msg);
    }

    public static void e(String tag, String msg) {
        log(E, tag, msg);
    }

    public static void e(String msg, Throwable e) {
        e(sTag, msg, e);
    }

    public static void e(String tag, String msg, Throwable e) {
        if (!sDebug) return;
        Log.e(tag, msg + processTagAndHead(), e);
    }

    public static void a(String msg) {
        log(A, sTag, msg);
    }

    public static void a(String tag, String msg) {
        log(A, tag, msg);
    }


    private static void log(final int type, String tag, String msg) {
        if (!sDebug) return;
        Log.println(type, tag, msg + processTagAndHead());
    }

    private static String processTagAndHead() {
        StackTraceElement targetElement = null;

        final StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        for (int i = elements.length - 2; i >= 0; i--) {
            StackTraceElement caller = elements[i];
            if (LogUtil.class.getName().equals(caller.getClassName())) {
                targetElement = elements[i + 1];
                break;
            }
        }
        if (targetElement == null) {
            return null;
        } else {
            String head = new Formatter()
                    .format("\t--> .%s(%s:%d)  Thread -> %s",
                            targetElement.getMethodName(),
                            targetElement.getFileName(),
                            targetElement.getLineNumber(),
                            Thread.currentThread().getName())
                    .toString();
            return head;
        }
    }
}
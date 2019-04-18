package com.yzk.xtools.utils;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Description: 内存常量
 * @Author: yang
 * @Time: 2019/4/18 11:37
 */
public final class MemoryConstant {

    /**
     * Byte与Byte的倍数
     */
    public static final int BYTE = 1;
    /**
     * KB与Byte的倍数
     */
    public static final int KB = 1024;
    /**
     * MB与Byte的倍数
     */
    public static final int MB = 1024 * 1024;
    /**
     * GB与Byte的倍数
     */
    public static final int GB = 1024 * 1024 * 1024;

    @IntDef({BYTE, KB, MB, GB})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Unit {
    }
}
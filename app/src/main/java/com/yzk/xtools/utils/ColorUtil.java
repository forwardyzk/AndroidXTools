package com.yzk.xtools.utils;

import android.graphics.Color;

/**
 * @Description: 日期工具类
 * @Author: yang
 * @Time: 2019/4/18 16:44
 */
public class ColorUtil {

    public static final String UTIL_DESC = "颜色工具类";
    public static final String UTIL_NAME = ColorUtil.class.getSimpleName();


    private ColorUtil() {
    }

    /**
     * 计算从startColor过度到endColor过程中比例为scale时的颜色值
     *
     * @param startColor 起始颜色
     * @param endColor   结束颜色
     * @param scale      百分比
     * @return 颜色
     */
    public static int calculateColor(String startColor, String endColor, float scale) {
        return calculateColor(Color.parseColor(startColor), Color.parseColor(endColor), scale);
    }

    /**
     * 计算从startColor过度到endColor过程中比例为scale时的颜色值
     *
     * @param startColor 起始颜色
     * @param endColor   结束颜色
     * @param scale      百分比
     * @return 颜色
     */
    public static int calculateColor(int startColor, int endColor, float scale) {
        int startAlpha = startColor >> 24 & 0xFF;
        int startRed = startColor >> 16 & 0xFF;
        int startGreen = startColor >> 8 & 0xFF;
        int startBlue = startColor & 0xFF;

        int endAlpha = endColor >> 24 & 0xFF;
        int endRed = endColor >> 16 & 0xFF;
        int endGreen = endColor >> 8 & 0xFF;
        int endBlue = endColor & 0xFF;

        int currentAlpha = (int) ((endAlpha - startAlpha) * scale + startAlpha);
        int currentRed = (int) ((endRed - startRed) * scale + startRed);
        int currentGreen = (int) ((endGreen - startGreen) * scale + startGreen);
        int currentBlue = (int) ((endBlue - startBlue) * scale + startBlue);

        int currentColor = currentAlpha << 24
                | currentRed << 16
                | currentGreen << 8
                | currentBlue;
        return currentColor;
    }


    /**
     * 根据百分比改变颜色透明度
     */
    public static int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }
}
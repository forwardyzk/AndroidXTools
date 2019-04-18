package com.yzk.xtools.utils;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.DisplayMetrics;

import com.yzk.xtools.app.ToolsApplication;

/**
 * @Description: 资源管理器
 * @Author: yang
 * @Time: 2019/4/18 14:12
 */
public class ResourceUtil {
    public static final String UTIL_DESC = "Resource工具类";
    public static final String UTIL_NAME = ResourceUtil.class.getName();


    private ResourceUtil() {
    }

    public static Resources getResources() {
        return ToolsApplication.getInstance().getResources();
    }

    public static DisplayMetrics getDisplayMetrics() {
        return ToolsApplication.getInstance().getResources().getDisplayMetrics();
    }

    public static int getDimensionPixelSize(@DimenRes int id) {
        return ToolsApplication.getInstance().getResources().getDimensionPixelSize(id);
    }

    @ColorInt
    public static int getColor(@ColorRes int id) {
        return ToolsApplication.getInstance().getResources().getColor(id);
    }

    public Drawable getDrawable(@DrawableRes int id) {
        return ToolsApplication.getInstance().getResources().getDrawable(id);
    }

    public String getString(@StringRes int id) {
        return ToolsApplication.getInstance().getResources().getString(id);
    }

    public String getString(@StringRes int id, Object... formatArgs) {
        return ToolsApplication.getInstance().getResources().getString(id, formatArgs);
    }

    public CharSequence getText(@StringRes int id) {
        return ToolsApplication.getInstance().getResources().getText(id);
    }

    /**
     * 获取颜色选择器
     *
     * @param resId
     * @return
     */
    public static ColorStateList getColorStateList(int resId) {
        ColorStateList colorStateList = ToolsApplication.getInstance().getResources().getColorStateList(resId);
        return colorStateList;
    }
}

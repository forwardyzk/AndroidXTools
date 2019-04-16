package com.yzk.xtools.utils;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.yzk.xtools.app.ToolsApplication;

import java.lang.ref.WeakReference;

/**
 * @Description: Toast工具类
 * @Author: yang
 * @Time: 2019/4/16 16:53
 */
public final class ToastUtil {

    private static WeakReference<Toast> sToastRef;

    private ToastUtil() {
    }

    public static void showToast(final CharSequence text) {
        showToast(Toast.LENGTH_SHORT, text);
    }

    /**
     * 显示短时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    public static void showToast(final String format, final Object... args) {
        showToast(Toast.LENGTH_SHORT, format, args);
    }

    /**
     * 显示短时吐司
     *
     * @param resId 资源Id
     * @param args  参数
     */
    public static void showToast(@StringRes final int resId, final Object... args) {
        showToast(Toast.LENGTH_SHORT, resId, args);
    }

    /**
     * 显示长时吐司
     *
     * @param text 文本
     */
    public static void showLongToast(@NonNull final CharSequence text) {
        showToast(Toast.LENGTH_LONG, text);
    }

    /**
     * 显示长时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    public static void showLongToast(final String format, final Object... args) {
        showToast(Toast.LENGTH_LONG, format, args);
    }

    /**
     * 显示长时吐司
     *
     * @param resId 资源Id
     * @param args  参数
     */
    public static void showLongToast(@StringRes final int resId, final Object... args) {
        showToast(Toast.LENGTH_LONG, resId, args);
    }

    /**
     * 显示吐司
     *
     * @param resId    资源Id
     * @param duration 显示时长
     * @param args     参数
     */
    private static void showToast(final int duration, @StringRes final int resId, final Object... args) {
        final CharSequence text;
        if (args == null || args.length == 0) {
            text = ToolsApplication.getInstance().getText(resId);
        } else {
            text = String.format(ToolsApplication.getInstance().getString(resId), args);
        }
        showToast(duration, text);
    }

    /**
     * 显示吐司
     *
     * @param duration 显示时长
     * @param format   格式
     * @param args     参数
     */
    private static void showToast(final int duration, final String format, final Object... args) {
        showToast(duration, String.format(format, args));
    }

    /**
     * 显示吐司
     *
     * @param duration 显示时长
     * @param text     文本
     */
    private static void showToast(final int duration, final CharSequence text) {
        cancel();
        final Toast toast = Toast.makeText(ToolsApplication.getInstance(), "", duration);

//        View view = LayoutInflater.from(Cube.app()).inflate(R.layout.core_view_custom_toast, null);
//        TextView tv = view.findViewById(R.id.toast_tv);
//        tv.setText(text);
//        toast.setView(view);
//        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(text);
        sToastRef = new WeakReference<Toast>(toast);
        toast.show();
    }

    /**
     * 取消吐司显示
     */
    public static void cancel() {
        if (sToastRef != null) {
            final Toast toast = sToastRef.get();
            if (toast != null) {
                toast.cancel();
                sToastRef = null;
            }
        }
    }
}
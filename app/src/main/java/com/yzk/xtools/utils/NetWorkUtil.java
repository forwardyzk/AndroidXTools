package com.yzk.xtools.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.yzk.xtools.app.ToolsApplication;

/**
 * @Description: 网络管理器
 * @Author: yang
 * @Time: 2019/4/18 14:07
 */
public class NetWorkUtil {

    public static final String UTIL_DESC = "网络管理器工具类";
    public static final String UTIL_NAME = NetWorkUtil.class.getName();

    /**
     * 检测网络是否连接
     */
    public static boolean isNetConnected() {
        ConnectivityManager cm = (ConnectivityManager) ToolsApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测wifi是否连接
     */
    public static boolean isWifiConnected() {
        ConnectivityManager cm = (ConnectivityManager) ToolsApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null
                    && networkInfo.isConnected()
                    && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测3G是否连接
     */
    public static boolean isMobileConnected() {
        ConnectivityManager cm = (ConnectivityManager) ToolsApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null
                    && networkInfo.isConnected()
                    && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }
}

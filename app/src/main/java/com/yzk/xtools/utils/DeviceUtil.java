package com.yzk.xtools.utils;

import android.Manifest;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Description: 设备管理器
 * @Author: yang
 * @Time: 2019/4/18 16:41
 */
public class DeviceUtil {

    public static final String UTIL_DESC = "设备管理器工具类";
    public static final String UTIL_NAME = DeviceUtil.class.getSimpleName();


    /**
     * 获取手机设备id   首先获取手机imei,如果为空,再获取WLANMAC地址,如果还为空,就直接用Build.SERIAL手机序列号
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        String device;
        device = getDeviceIMEI(context);
        if (TextUtils.isEmpty(device)) {
            device = getWifiMac();
            if (TextUtils.isEmpty(device)) {
                device = Build.SERIAL;//设备序列号
            }
        }
        return TextUtils.isEmpty(device) ? "" : device;
    }

    /**
     * 获取手机imei
     *
     * @param context
     * @return
     */
    public static String getDeviceIMEI(Context context) {
        String device_id = null;
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (checkPermission(context, Manifest.permission.READ_PHONE_STATE)) {
                device_id = tm.getDeviceId();
                if (!TextUtils.isEmpty(device_id)) {
                    if (device_id.replace("0", "").length() == 0) {
                        device_id = null;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TextUtils.isEmpty(device_id) ? "" : device_id;
    }


    /**
     * 获取手机WLAN MAC地址
     *
     * @return
     */
    public static String getWifiMac() {
        String mac = null;
        try {
            FileReader reader = null;
            try {
                reader = new FileReader("/sys/class/net/wlan0/address");
            } catch (FileNotFoundException e) {
                reader = new FileReader("/sys/class/net/eth0/address");
            }
            BufferedReader in = null;
            if (reader != null) {
                try {
                    in = new BufferedReader(reader, 1024);
                    mac = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TextUtils.isEmpty(mac) ? "" : mac;
    }

    /**
     * 检查权限
     *
     * @param context
     * @param permission
     * @return
     */
    private static boolean checkPermission(Context context, String permission) {
        boolean result = false;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Class<?> clazz = Class.forName("android.content.Context");
                Method method = clazz.getMethod("checkSelfPermission", String.class);
                int rest = (Integer) method.invoke(context, permission);
                if (rest == PackageManager.PERMISSION_GRANTED) {
                    result = true;
                } else {
                    result = false;
                }
            } catch (Exception e) {
                result = false;
            }
        } else {
            PackageManager pm = context.getPackageManager();
            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
                result = true;
            }
        }
        return result;
    }


    /**
     * 判断应用是否处于后台
     *
     * @param context
     * @return
     */
    public static boolean isAppOnBackground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否锁屏
     *
     * @param context
     * @return
     */
    public static boolean isLockScreeen(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = pm.isScreenOn();//如果为true，则表示屏幕“亮”了，否则屏幕“暗”了。
        if (isScreenOn) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * 获取 ANDROID_ID
     *
     * @param context
     * @return
     */
    public static String getAndroidId(Context context) {
        String ANDROID_ID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return TextUtils.isEmpty(ANDROID_ID) ? "" : ANDROID_ID;
    }


}

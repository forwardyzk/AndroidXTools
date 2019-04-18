package com.yzk.xtools.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 启动地图工具类
 * 高德、腾讯地图使用GCJ-02 "火星坐标"
 * 百度地图使用  BD-09坐标
 * @Author: yang
 * @Time: 2019/4/18 16:36
 */
public class MapAppOpenUtil {
    public static final String UTIL_DESC = "启动地图工具类";
    public static final String UTIL_NAME = MapAppOpenUtil.class.getName();

    public static String[] mapPacks = new String[]{
            "com.autonavi.minimap",//高德地图包名
            "com.baidu.BaiduMap" //百度地图包名
    };


    /**
     * 是否安装了百度地图
     *
     * @param context
     * @return
     */
    public static boolean isInstallBaidu(Context context) {
        List<String> mapApps = getMapApps(context);
        if (mapApps != null && !mapApps.isEmpty()) {
            if (mapApps.contains(mapPacks[1])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否安装了高德地图
     *
     * @param context
     * @return
     */
    public static boolean isInstallGaode(Context context) {
        List<String> mapApps = getMapApps(context);
        if (mapApps != null && !mapApps.isEmpty()) {
            if (mapApps.contains(mapPacks[0])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回当前设备上的地图应用集合
     *
     * @param context
     * @return 所有地图的包名集合
     */
    public static List<String> getMapApps(Context context) {
        LinkedList<String> apps = new LinkedList<>();
        for (String pak : mapPacks) {
            String appinfo = getAppInfoByPak(context, pak);
            if (appinfo != null) {
                apps.add(appinfo);
            }
        }
        return apps;
    }

    /**
     * 通过包名获取应用信息
     *
     * @param context
     * @param packageName
     * @return
     */
    public static String getAppInfoByPak(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        for (PackageInfo packageInfo : packageInfos) {
            if (packageName.equals(packageInfo.packageName)) {
                return packageName;
            }
        }
        return null;
    }


    /**
     * *************************    百度地图专区    *******************************
     */

    /**
     * 调起百度地图
     * http://lbsyun.baidu.com/index.php?title=uri/api/android     -2.2.3展示地图图区
     *
     * @param activity
     */
    public static void openBaiduMap(Context activity) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("baidumap://map?"));
        activity.startActivity(intent);
    }


    /**
     * 调起百度地图app 显示对应位置打点  调用该接口可以在调起百度地图时，在图区显示地址对应的坐标点。
     * http://lbsyun.baidu.com/index.php?title=uri/api/android        -2.2.4地址解析
     *
     * @param activity 上下文
     * @param address  地址名称
     */
    public static void openBaiduMarkerMap(Context activity, String address) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("baidumap://map/geocoder?src=openApiDemo&address=" + address + ""));
        activity.startActivity(intent);
    }

    /**
     * 调起百度地图app   调用该接口可调起百度地图，经过逆地理编码后，以标注形式显示位置和地址信息
     * http://lbsyun.baidu.com/index.php?title=uri/api/android        -2.2.5 反向地址解析
     *
     * @param activity  上下文
     * @param longitude 经度
     * @param latitude  纬度
     */
    public static void openBaiduMarkerMap(Context activity, double longitude, double latitude) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("baidumap://map/geocoder?location=" + latitude + "," + longitude + ""));
        activity.startActivity(intent);
    }


    /**
     * 没有安装百度地图 打开网页版本
     * http://lbsyun.baidu.com/index.php?title=uri/api/android
     * 打开网页版 反向地址解析
     *
     * @param activity  上下文
     * @param longitude 经度
     * @param latitude  纬度
     * @param title     地理位置名称
     * @param content   地理说明
     * @param appName   应用名字
     */
    public static void openBrosserMarkerMap(Context activity,
                                            double longitude,
                                            double latitude,
                                            String title,
                                            String content,
                                            String appName) {
        Uri mapUri = Uri.parse("http://api.map.baidu.com/marker?location=" + latitude + "," + longitude +
                "&title=" + title + "&content=" + content + "&output=html&src=" + appName);
        Intent loction = new Intent(Intent.ACTION_VIEW, mapUri);
        activity.startActivity(loction);
    }

    /**
     * 调起百度地图app 显示对应位置打点    调用该接口可调起Android百度地图，且在指定坐标点上显示点的名称和内容信息。
     * http://lbsyun.baidu.com/index.php?title=uri/api/android    -2.2.2自定义打点
     *
     * @param activity  上下文
     * @param longitude 经度
     * @param latitude  纬度
     * @param title     地理位置名称  打点标题
     * @param content   地理说明     打点内容
     */
    public static void openBaiduMarkerMap(Context activity,
                                          double longitude,
                                          double latitude,
                                          String title,
                                          String content) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(
                "baidumap://map/marker?location=" + latitude + "," + longitude + "&title=" +
                        (TextUtils.isEmpty(title) ? (TextUtils.isEmpty(content) ? "地理位置名称" : content) : title)
                        + "&content=" + content + "&traffic=on"));
        activity.startActivity(intent);
    }


    /**
     * *************************    高德地图专区   *******************************
     */


    /**
     * 调起高德客户端 展示标注点
     * http://lbs.amap.com/api/amap-mobile/guide/android/regeocoding
     *
     * @param activity  上下文
     * @param longitude 经度
     * @param latitude  纬度
     * @param appName   应用名称
     */
    public static void openGaodeMarkerMap(Context activity,
                                          double longitude,
                                          double latitude,
                                          String appName) {
        Intent intent = new Intent("android.intent.action.VIEW",
                Uri.parse("androidamap://viewReGeo?sourceApplication=" +
                        appName + "&lat=" + latitude + "&lon=" + longitude + "&dev=0"));
        intent.setPackage("com.autonavi.minimap");
        activity.startActivity(intent);


    }


    /**
     * 调起高德客户端 展示标注点
     * 根据名称或经纬度，启动高德地图产品展示一个标注点
     * http://lbs.amap.com/api/amap-mobile/guide/android/marker
     *
     * @param activity
     * @param longitude
     * @param latitude
     * @param poiname
     * @param appName
     */
    public static void openGaodeMarkerMap(Context activity,
                                          double longitude,
                                          double latitude,
                                          String poiname,
                                          String appName) {
        Intent intent = new Intent("android.intent.action.VIEW",
                Uri.parse("androidamap://viewMap?sourceApplication=" +
                        appName + "&poiname=" + poiname + "&lat=" + latitude + "&lon=" + longitude + "&dev=0"));
        intent.setPackage("com.autonavi.minimap");
        activity.startActivity(intent);
    }

    /**
     * 高德地图--网页版(http://lbs.amap.com/api/uri-api/gettingstarted)
     * <p>
     * 地址:
     * http://uri.amap.com/marker?position=116.473195,39.993253&name=首开广场&src=mypage&coordinate=gaode&callnative=0
     *
     * @param activity
     * @param longitude
     * @param latitude
     * @param title
     * @param appName
     */
    public static void openGaoDeMarkerMapNet(Context activity,
                                             double longitude,
                                             double latitude,
                                             String title,
                                             String appName) {
        Uri mapUri = Uri.parse("http://uri.amap.com/marker?position=" + longitude + "," + latitude +
                "&name=" + title + "&coordinate=gaode" + "&callnative=0&src=" + appName);
        Intent loction = new Intent(Intent.ACTION_VIEW, mapUri);
        activity.startActivity(loction);
    }

    /**
     * *************************    经纬度坐标转换专区   *******************************
     * 说明：高德 谷歌 腾讯地图使用 GCJ-02 火星左边  百度使用自家的  BD-09 坐标  需要转换
     */


    private static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

    /**
     * 将 GCJ-02 坐标转换成 BD-09 坐标
     * GoogleMap和高德map用的是同一个坐标系GCJ-02
     */
    public static double[] bd_encrypt(double gg_lat, double gg_lon) {
        double bd_lat = 0.0;
        double bd_lon = 0.0;
        double location[] = new double[2];
        double x = gg_lon, y = gg_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        bd_lon = z * Math.cos(theta) + 0.0065;
        bd_lat = z * Math.sin(theta) + 0.006;
        location[0] = bd_lat;
        location[1] = bd_lon;
        return location;
    }

    /**
     * 将 BD-09 坐标转换成 GCJ-02 坐标
     * GoogleMap和高德map用的是同一个坐标系GCJ-02
     */
    public static double[] bd_decrypt(double bd_lat, double bd_lon) {
        double gg_lat = 0.0;
        double gg_lon = 0.0;
        double location[] = new double[2];
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        gg_lon = z * Math.cos(theta);
        gg_lat = z * Math.sin(theta);
        location[0] = gg_lat;
        location[1] = gg_lon;
        return location;
    }


}

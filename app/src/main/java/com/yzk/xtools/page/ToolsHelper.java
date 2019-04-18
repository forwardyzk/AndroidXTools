package com.yzk.xtools.page;

import com.yzk.xtools.page.data.UtilBean;
import com.yzk.xtools.utils.AppManager;
import com.yzk.xtools.utils.BitmapUtil;
import com.yzk.xtools.utils.ColorUtil;
import com.yzk.xtools.utils.DateUtil;
import com.yzk.xtools.utils.DeviceUtil;
import com.yzk.xtools.utils.ExitActivityUtil;
import com.yzk.xtools.utils.FileUtil;
import com.yzk.xtools.utils.GsonUtil;
import com.yzk.xtools.utils.IOCloseUtil;
import com.yzk.xtools.utils.IOFileUtil;
import com.yzk.xtools.utils.ImageUtil;
import com.yzk.xtools.utils.KeyboardUtil;
import com.yzk.xtools.utils.LogUtil;
import com.yzk.xtools.utils.MacAddressUtil;
import com.yzk.xtools.utils.MapAppOpenUtil;
import com.yzk.xtools.utils.MeasureUtil;
import com.yzk.xtools.utils.NetWorkUtil;
import com.yzk.xtools.utils.PhoneUtil;
import com.yzk.xtools.utils.ReflectUtil;
import com.yzk.xtools.utils.ResourceUtil;
import com.yzk.xtools.utils.SDCardUtil;
import com.yzk.xtools.utils.ScreenUtil;
import com.yzk.xtools.utils.ShortCutUtil;
import com.yzk.xtools.utils.SpUtil;
import com.yzk.xtools.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:yang
 * Time:2019/4/16 17:51
 * Description:
 */
public class ToolsHelper {

    /**
     * 工具类信息列表
     *
     * @return
     */
    public static List<UtilBean> getToolsList() {
        List<UtilBean> beansList = new ArrayList<>();
        beansList.add(new UtilBean(1, ScreenUtil.UTIL_NAME, ScreenUtil.UTIL_DESC));
        beansList.add(new UtilBean(2, ToastUtil.UTIL_NAME, ToastUtil.UTIL_DESC));
        beansList.add(new UtilBean(3, IOFileUtil.UTIL_NAME, IOFileUtil.UTIL_DESC));
        beansList.add(new UtilBean(4, IOCloseUtil.UTIL_NAME, IOCloseUtil.UTIL_DESC));
        beansList.add(new UtilBean(5, FileUtil.UTIL_NAME, FileUtil.UTIL_DESC));
        beansList.add(new UtilBean(6, GsonUtil.UTIL_NAME, GsonUtil.UTIL_DESC));
        beansList.add(new UtilBean(7, LogUtil.UTIL_NAME, LogUtil.UTIL_DESC));
        beansList.add(new UtilBean(8, NetWorkUtil.UTIL_NAME, NetWorkUtil.UTIL_DESC));
        beansList.add(new UtilBean(9, ResourceUtil.UTIL_NAME, ResourceUtil.UTIL_DESC));
        beansList.add(new UtilBean(10, KeyboardUtil.UTIL_NAME, KeyboardUtil.UTIL_DESC));
        beansList.add(new UtilBean(11, ImageUtil.UTIL_NAME, ImageUtil.UTIL_DESC));
        beansList.add(new UtilBean(12, MacAddressUtil.UTIL_NAME, MacAddressUtil.UTIL_DESC));
        beansList.add(new UtilBean(13, MapAppOpenUtil.UTIL_NAME, MapAppOpenUtil.UTIL_DESC));
        beansList.add(new UtilBean(14, SDCardUtil.UTIL_NAME, SDCardUtil.UTIL_DESC));
        beansList.add(new UtilBean(15, SpUtil.UTIL_NAME, SpUtil.UTIL_DESC));
        beansList.add(new UtilBean(16, DeviceUtil.UTIL_NAME, DeviceUtil.UTIL_DESC));
        beansList.add(new UtilBean(17, DateUtil.UTIL_NAME, DateUtil.UTIL_DESC));
        beansList.add(new UtilBean(18, ColorUtil.UTIL_NAME, ColorUtil.UTIL_DESC));
        beansList.add(new UtilBean(19, AppManager.UTIL_NAME, AppManager.UTIL_DESC));
        beansList.add(new UtilBean(20, BitmapUtil.UTIL_NAME, BitmapUtil.UTIL_DESC));
        beansList.add(new UtilBean(21, MeasureUtil.UTIL_NAME, MeasureUtil.UTIL_DESC));
        beansList.add(new UtilBean(23, ExitActivityUtil.UTIL_NAME, ExitActivityUtil.UTIL_DESC));
        beansList.add(new UtilBean(24, PhoneUtil.UTIL_NAME, PhoneUtil.UTIL_DESC));
        beansList.add(new UtilBean(25, ReflectUtil.UTIL_NAME, ReflectUtil.UTIL_DESC));
        beansList.add(new UtilBean(26, ShortCutUtil.UTIL_NAME, ShortCutUtil.UTIL_DESC));

        return beansList;
    }
}

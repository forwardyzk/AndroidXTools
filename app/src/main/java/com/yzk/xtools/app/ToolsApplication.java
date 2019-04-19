package com.yzk.xtools.app;

import android.app.Application;

import com.yzk.xtools.utils.LogUtil;

/**
 * Author:yang
 * Time:2019/4/16 14:59
 * Description:
 */
public class ToolsApplication extends Application {
    private static ToolsApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LogUtil.init(true, "XTools");
    }

    public static ToolsApplication getInstance() {
        return instance;
    }


}

package com.yzk.xtools.app;

import android.app.Application;

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
    }

    public static ToolsApplication getInstance() {
        return instance;
    }


}

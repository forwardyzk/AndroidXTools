package com.yzk.xtools.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Author:yang
 * Time:2019/4/18 17:32
 * Description:Activity管理类
 */
public class AppManager {

    public static final String UTIL_DESC = "Activity管理工具类";
    public static final String UTIL_NAME = AppManager.class.getName();

    private static AppManager instance;
    private Stack<Activity> activityStack = new Stack<Activity>();

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            synchronized (AppManager.class) {
                if (instance == null) {
                    instance = new AppManager();
                }
            }
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activity != null) {
            activityStack.add(activity);
        }
    }


    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity getCurrentActivity() {
        if (activityStack.empty()) {
            return null;
        }
        return activityStack.lastElement();
    }

    /**
     * 获取前一个Activity
     *
     * @return
     */
    public Activity getPreActivity() {
        int size = activityStack.size();
        if (size < 2) {
            return null;
        }
        return activityStack.elementAt(size - 2);
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        if (activityStack.empty()) {
            return;
        }
        finishActivity(activityStack.lastElement());
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
            activityStack.remove(activity);
        }
    }

    /**
     * 结束最后一个指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            final Activity activity = activityStack.get(i);
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束所有指定类名的Activity
     */
    public void finishAllActivity(Class<?> cls) {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            final Activity activity = activityStack.get(i);
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            final Activity activity = activityStack.get(i);
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 获取指定的Activity
     */
    public Activity getActivity(Class<?> cls) {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            Activity activity = activityStack.get(i);
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 获取启动的Activity数量
     *
     * @return
     */
    public int getActivitySize() {
        return activityStack.size();
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        finishAllActivity();
        // 杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }


}
package com.yzk.xtools.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.yzk.xtools.app.ToolsApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 公共的本地缓存, 不需要清除缓存, SharedPreferences管理类
 * @Author: yang
 * @Time: 2019/4/18 16:39
 */
public class SpUtil {

    public static final String UTIL_DESC = "SharedPreferences工具类";
    public static final String UTIL_NAME = SpUtil.class.getName();


    private static SharedPreferences spUtils;
    private static SpUtil mSpUtil;

    private static Map<String, Integer> intMap;
    private static Map<String, Boolean> booleanMap;
    private static Map<String, String> stringMap;
    private static Map<String, Long> longMap;

    private SpUtil() {
        spUtils = ToolsApplication.getInstance().getSharedPreferences("SpUtil", Context.MODE_PRIVATE);
        intMap = new HashMap<String, Integer>();
        booleanMap = new HashMap<String, Boolean>();
        stringMap = new HashMap<String, String>();
        longMap = new HashMap<String, Long>();
    }

    public static SpUtil getInstance() {

        if (mSpUtil == null) {
            synchronized (SpUtil.class) {
                if (mSpUtil == null) {
                    mSpUtil = new SpUtil();
                }

            }
        }


        return mSpUtil;
    }

    /**
     * 移除对应的key
     *
     * @param key
     */
    public void remove(String key) {
        spUtils.edit().remove(key).commit();
        intMap.remove(key);
        booleanMap.remove(key);
        stringMap.remove(key);
        longMap.remove(key);
    }


    /**
     * 通过key 先从map中获取要是没有再从sp中获取 （boolean）
     *
     * @param key
     * @return
     */
    public boolean getBoolean(String key) {
        Boolean bool = booleanMap.get(key);
        if (bool == null) {
            bool = spUtils.getBoolean(key, false);
            booleanMap.put(key, bool);
        }
        return bool;
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        Boolean bool = booleanMap.get(key);
        if (bool == null) {
            bool = spUtils.getBoolean(key, defaultValue);
            booleanMap.put(key, bool);
        }
        return bool;
    }

    /**
     * 通过key存入value  （boolean）
     *
     * @param key
     * @param value
     */
    public void putBoolean(String key, boolean value) {
        booleanMap.put(key, value);
        spUtils.edit().putBoolean(key, value).commit();
    }

    /**
     * 通过key 先从map中获取要是没有再从sp中获取,默认值-1 （int）
     *
     * @param key
     * @return
     */
    public int getInt(String key) {
        Integer i = intMap.get(key);
        if (i == null) {
            i = spUtils.getInt(key, -1);
            intMap.put(key, i);
        }
        return i;
    }

    /**
     * 通过key存入value  （int）
     *
     * @param key
     * @param value
     */
    public void putInt(String key, int value) {
        intMap.put(key, value);
        spUtils.edit().putInt(key, value).commit();
    }

    /**
     * 通过key 先从map中获取要是没有再从sp中获取,默认值为空（String）
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return getString(key, "");
    }

    /**
     * 通过key 先从map中获取要是没有再从sp中获取,默认值为defStr（String）
     *
     * @param key
     * @param defStr
     * @return
     */
    public String getString(String key, String defStr) {
        String str = stringMap.get(key);
        if (str == null) {
            str = spUtils.getString(key, defStr);
            stringMap.put(key, str);
        }
        return str;
    }

    /**
     * 通过key 获取值在通过“,”拆分成String数组
     *
     * @param key
     * @return
     */
    public String[] getStringArray(String key) {
        return getString(key).split(",");
    }

    /**
     * 通过key存入value  （String）
     *
     * @param key
     * @param value
     */
    public void putString(String key, String value) {
        stringMap.put(key, value);
        spUtils.edit().putString(key, value).commit();
    }

    /**
     * 通过key存入value  （long）
     *
     * @param key
     * @param value
     */
    public void putLong(String key, long value) {
        longMap.put(key, value);
        spUtils.edit().putLong(key, value).commit();
    }

    /**
     * 通过key 先从map中获取要是没有再从sp中获取,默认值为0（long）
     *
     * @param key
     * @return
     */
    public long getLong(String key) {
        Long l = longMap.get(key);
        if (l == null) {
            l = spUtils.getLong(key, 0l);
            longMap.put(key, l);
        }
        return l;
    }

    /**
     * 清空所有数据
     */
    public void clear() {
        spUtils.edit().clear().commit();
        intMap.clear();
        booleanMap.clear();
        longMap.clear();
        stringMap.clear();
    }
}

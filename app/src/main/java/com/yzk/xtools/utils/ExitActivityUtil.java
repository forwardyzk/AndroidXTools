package com.yzk.xtools.utils;

import android.app.Activity;
import android.view.KeyEvent;

/**
 * Author:yang
 * Time:2019/4/18 18:09
 * Description:退出App方法
 */
public class ExitActivityUtil {

    public static final String UTIL_DESC = "退出Activity工具类";
    public static final String UTIL_NAME = ExitActivityUtil.class.getName();


    private long exitTime = 0;

    public boolean onKeyDown(int keyCode, KeyEvent event, Activity activity) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //2s之内按返回键就会推出
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.showToast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                activity.finish();
                System.exit(0);
            }
            return true;
        }
        return false;
    }

}

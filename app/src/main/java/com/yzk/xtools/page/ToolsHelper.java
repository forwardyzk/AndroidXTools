package com.yzk.xtools.page;

import com.yzk.xtools.page.data.ClassFlagBean;
import com.yzk.xtools.utils.ScreenUtils;
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
     * 工具列表
     *
     * @return
     */
    public static List<ClassFlagBean> getToolsList() {
        List<ClassFlagBean> beansList = new ArrayList<>();
        beansList.add(new ClassFlagBean(1, "屏幕工具类", ScreenUtils.class));
        beansList.add(new ClassFlagBean(2, "吐司工具类", ToastUtil.class));
        return beansList;
    }
}

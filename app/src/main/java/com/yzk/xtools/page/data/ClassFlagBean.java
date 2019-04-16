package com.yzk.xtools.page.data;

import com.yzk.xtools.page.annotation.KeepJson;

/**
 * Author:yang
 * Time:2019/4/16 17:39
 * Description:
 */
@KeepJson
public class ClassFlagBean {
    public int flag;
    public String desc;
    public Class aClass;

    public ClassFlagBean() {
    }

    public ClassFlagBean(int flag, String desc, Class aClass) {
        this.flag = flag;
        this.desc = desc;
        this.aClass = aClass;
    }
}

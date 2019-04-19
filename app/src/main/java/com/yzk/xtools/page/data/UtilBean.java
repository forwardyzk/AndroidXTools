package com.yzk.xtools.page.data;

import com.yzk.xtools.page.annotation.KeepJson;

/**
 * Author:yang
 * Time:2019/4/16 17:39
 * Description:
 */
@KeepJson
public class UtilBean {
    public int flag;
    public String utilName;
    public String desc;

    public UtilBean() {
    }

    public UtilBean(int flag, String utilName, String desc) {
        this.flag = flag;
        this.desc = desc;
        this.utilName = utilName;
    }

    public UtilBean(String utilName, String desc) {
        this.utilName = utilName;
        this.desc = desc;
    }
}

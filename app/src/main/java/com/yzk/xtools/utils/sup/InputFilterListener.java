package com.yzk.xtools.utils.sup;

/**
 * @Description: 在不符合正则表达式的时候,调用
 * @Author: yang
 * @Time: 2019/4/19 10:50
 */
public interface InputFilterListener {
    /**
     * 被过滤回调
     */
    void onFilter();
}

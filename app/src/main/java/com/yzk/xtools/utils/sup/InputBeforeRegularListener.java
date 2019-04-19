package com.yzk.xtools.utils.sup;

/**
 * @Description:
 * @Author: yang
 * @Time: 2019/4/19 10:51
 */
public interface InputBeforeRegularListener {
    /**
     * 转换文本
     * @param charSequence
     * @return
     */
    CharSequence toChange(CharSequence charSequence);
}

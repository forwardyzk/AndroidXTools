package com.yzk.xtools.utils.sup;

import android.text.method.ReplacementTransformationMethod;

/**
 * @Description: EdittextView自动转化为大写, 一定要设置在设置过滤器之后
 * text.setTransformationMethod(new InputLowerToUpper());
 * @Author: yang
 * @Time: 2019/4/19 10:51
 */
public class InputLowerToUpper extends ReplacementTransformationMethod {
    @Override
    protected char[] getOriginal() {
        char[] lower = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        return lower;
    }

    @Override
    protected char[] getReplacement() {
        char[] upper = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        return upper;
    }

}

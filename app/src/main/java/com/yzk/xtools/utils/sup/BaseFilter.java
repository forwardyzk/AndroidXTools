package com.yzk.xtools.utils.sup;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
/**
 * @Description:
 * @Author: yang
 * @Time: 2019/4/19 10:51
 */

public abstract class BaseFilter implements InputFilter {

    private InputFilterListener mInputFilterListener;
    private InputBeforeRegularListener mInputBeforeRegularListener;

    public BaseFilter() {
    }

    public BaseFilter(EditText editText, int inputType) {
        editText.setInputType(inputType);
    }

    public BaseFilter(EditText editText, final String acceptedChars) {
        if (!TextUtils.isEmpty(acceptedChars)) {
            editText.setKeyListener(DigitsKeyListener.getInstance(acceptedChars));//只是控制输入的内容,排序/格式 控制不了
        }
    }

    /**
     * @param editText      输入框
     * @param inputType     输入类型,这个控制的默认弹出的键盘,也会起到输入内容的限制
     * @param acceptedChars 控制可输入的字符
     */
    public BaseFilter(EditText editText, final int inputType, final String acceptedChars) {
        if (!TextUtils.isEmpty(acceptedChars)) {
            editText.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    return false;
                }
            });
            editText.setKeyListener(new DigitsKeyListener() {
                @Override
                public int getInputType() {
                    return inputType;
                }

                @Override
                protected char[] getAcceptedChars() {
                    return acceptedChars.toCharArray();
                }
            });
        } else {
            editText.setInputType(inputType);
        }
    }

    public BaseFilter setInputFilterListener(InputFilterListener listener) {
        this.mInputFilterListener = listener;
        return this;
    }

    public BaseFilter setInputBeforeRegularListener(InputBeforeRegularListener listener) {
        this.mInputBeforeRegularListener = listener;
        return this;
    }

    /**
     * 1.可以控制输入的数量,
     * 2.完善输入的内容
     * 3.通过正则控制输入的格式
     *
     * @param source 新输入的字符串
     * @param start  新输入的字符串起始下标，一般为0
     * @param end    新输入的字符串终点下标，一般为source长度-1
     * @param dest   输入之前文本框内容
     * @param dstart 原内容起始坐标，一般为0
     * @param dend   原内容终点坐标，一般为dest长度-1
     * @return 输入内容
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        //验证删除等按键
        if (TextUtils.isEmpty(source)) {
            return "";
        }
        if (mInputBeforeRegularListener != null) {
            source = mInputBeforeRegularListener.toChange(source);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dest.subSequence(0, dstart))
                .append(source)
                .append(dest.subSequence(dend, dest.length()));

        if (filter(sb.toString())) {
            if (mInputFilterListener != null) {
                mInputFilterListener.onFilter();
            }
            return "";
        }
        return source;
    }

    /**
     * @param text
     * @return true为过滤，false为不过滤
     */
    public abstract boolean filter(String text);
}

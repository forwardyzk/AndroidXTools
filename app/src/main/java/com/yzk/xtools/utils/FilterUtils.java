package com.yzk.xtools.utils;

import android.text.InputFilter;
import android.text.TextUtils;
import android.widget.EditText;

import com.yzk.xtools.utils.sup.BaseFilter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 正则表达式过滤器
 * @Author: yang
 * @Time: 2019/4/19 10:49
 */
public class FilterUtils {

    public static final String UTIL_DESC = "正则表达式过滤器工具类";
    public static final String UTIL_NAME = FilterUtils.class.getSimpleName();

    /**
     * 给EditFilter设置过滤器
     *
     * @param editText
     * @param filter
     */
    public static void setFilter(EditText editText, BaseFilter filter) {
        InputFilter[] filters = {filter};
        editText.setFilters(filters);
    }

    public static void setFilter(EditText editText, BaseFilter... filters) {
        editText.setFilters(filters);
    }


    private static boolean match(String regex, String str) {
        if (TextUtils.isEmpty(str))
            return false;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 输入时候,是否纯数字
     *
     * @param content
     * @param count
     * @return
     */
    public static boolean isOnlyNumber(String content, int count) {
        StringBuffer mStringBuffer = new StringBuffer();
        mStringBuffer.append("^").append("([0-9]{0,").append(count).append("})$");
        return match(mStringBuffer.toString(), content);
    }

    /**
     * 输入时,监听电话号码格式
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isInputPhoneFormat(String phoneNumber) {
        String regular = "^1[0-9]{0,10}$";
        return match(regular, phoneNumber);
    }

    /**
     * 电话号码正则表达式
     *
     * @param phoneNumber
     * @return
     */
    public static boolean regularPhone(String phoneNumber) {
        String regular = "^1[0-9]{10}$";
        return match(regular, phoneNumber);
    }

    /**
     * 输入时,密码格式
     *
     * @param str
     * @return
     */
    public static boolean isInputPasswordFormat(String str, int count) {
        String regular = "^[a-zA-Z0-9!@#$%^&*()-+=~:()><,.'?\"]{0," + count + "}$";
        return match(regular, str);
    }

    /**
     * 提交时 匹配车牌号是否符合规则
     * 此处的车牌号 phoneNumber 没有省份简称
     *
     * @param phoneNumber
     * @return
     */
    public static boolean regularInputLicenseNumFormat(String phoneNumber) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("^");
        stringBuffer.append("([A-Z]{1}[A-Z0-9]{5})|");
        stringBuffer.append("([A-Z]{1}[A-Z0-9]{6})");
        stringBuffer.append("$");
        String regular = stringBuffer.toString();
        return match(regular, phoneNumber);
    }


    /**
     * 提交时 匹配车牌号是否符合规则---带有省份:冀D1H7G9,带有省份
     *
     * @param phoneNumber
     * @return
     */
    public static boolean regularLicenseNumProvinceFormat(String phoneNumber) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("^");
        stringBuffer.append("([\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z0-9]{5})|");
        stringBuffer.append("([\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z0-9]{6})");
        stringBuffer.append("$");
        String regular = stringBuffer.toString();
        return match(regular, phoneNumber);
    }


    /**
     * 是否包含中文
     *
     * @param str
     * @return true 包含中文
     */
    public static boolean isContainsChinese(String str) {
        Pattern pattern = Pattern.compile("[\u4E00-\u9FA5]");
        Matcher m = pattern.matcher(str);
        // 返回true 包含中文
        return m.find();
    }

    /**
     * 判别发动机格式是否正确
     *
     * @param str
     * @return true  符合规则
     */
    public static boolean regularEngineFormat(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("^").append("[A-Z0-9]+").append("$");
        String regular = stringBuffer.toString();
        return match(regular, str);

    }


    /**
     * 输入时候,是否纯数字 开头不能为0
     *
     * @param content
     * @param count
     * @return
     */
    public static boolean isStartOneNumber(String content, int count) {
        StringBuffer mStringBuffer = new StringBuffer();
        mStringBuffer.append("^[1-9]{1}").append("([0-9]{0,").append(count - 1).append("})$");
        return match(mStringBuffer.toString(), content);
    }

    /**
     * 输入时,监听金额格式
     *
     * @param money
     * @param count 小数点后保留几位
     * @return
     */
    public static boolean isInputMoneyFormat(String money, int count) {
        StringBuffer sb = new StringBuffer();
        sb.append("^");
        //第一种,0开头
        sb.append("(0(\\.\\d{0,").append(count).append("})?)").append("|");
        //第二种非零开头
        sb.append("([1-9]\\d*(\\.\\d{0,").append(count).append("})?)");
        sb.append("$");
        String regular = sb.toString();
        boolean result = match(regular, money);
        return result;
    }

    /**
     * 金额正则表达式
     *
     * @param money
     * @param count
     * @return
     */
    public static boolean regularMoney(String money, int count) {
        StringBuffer sb = new StringBuffer();
        sb.append("^");
        //第一种,0开头
        sb.append("(0(\\.\\d{1,").append(count).append("})?)").append("|");
        //第二种非零开头
        sb.append("([1-9]\\d*(\\.\\d{1,").append(count).append("})?)");
        sb.append("$");
        String regular = sb.toString();
        boolean result = match(regular, money);
        return result;
    }


    /**
     * 输入时,是否 0到16位 仅字母或数字组合
     *
     * @param content
     * @return
     */
    public static boolean isInputOnlyLetterOrNumberFormat(String content) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("^").append("[a-zA-Z0-9]").append("{1,16}").append("$");
        String regular = stringBuffer.toString();
        return match(regular, content);
    }

    /**
     * 输入时,是否仅字母或数字组合
     *
     * @param content
     * @return
     */
    public static boolean isInputOnlyLetterOrNumberFormat(String content, int count) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("^").append("[a-zA-Z0-9]{0,").append(count).append("}").append("$");
        String regular = stringBuffer.toString();
        return match(regular, content);
    }

    /**
     * 输入时,监听车牌号输入格式--带有省份:冀D1H7G9
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isInpuLicenseNumFormat(String phoneNumber) {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("^");
        stringBuffer.append("(").append("[\u4e00-\u9fa5]{1}").append(")").append("|");
        stringBuffer.append("(").append("[\u4e00-\u9fa5]{1}").append("[A-Za-z]{1}").append(")").append("|");
        stringBuffer.append("(").append("[\u4e00-\u9fa5]{1}").append("[A-Za-z]{1}[A-Za-z0-9]{0,5}").append(")");
        stringBuffer.append("$");
        String regular = stringBuffer.toString();
        return match(regular, phoneNumber);
    }

}

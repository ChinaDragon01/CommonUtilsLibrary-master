package com.chinadragon.commonutilslibrary;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2016/10/18 14:43
 * Name: 校验工具类
 * Overview:
 * Usage:
 * **********************************************************************
 */
public class RegexUtil {
    /**
     * 手机号码Pattern
     */
    public static final Pattern MOBILE_NUMBER_PATTERN = Pattern
            .compile("\\d{11}");

    /**
     * 手机号码否正确
     *
     * @param s
     * @return
     */
    public static boolean isMobileNumber(String s) {
        Matcher m = MOBILE_NUMBER_PATTERN.matcher(s);
        return m.matches();
    }

    /**
     * 校验身份证号 是否合法
     *
     * @param idcard
     * @return
     */
    public static boolean checkIdCard(String idcard) {
        boolean flag = false;
        Pattern p1 = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");  //身份证正�?
        //是身份证
        if (p1.matcher(idcard).matches()) {
            //如果是，定义正则表达式提取出身份证中的出生日�?
            Pattern birthDatePattern = Pattern.compile("\\d{6}(\\d{4})(\\d{2})(\\d{2}).*");//身份证上的前6位以及出生年月日
            Matcher birthDateMather = birthDatePattern.matcher(idcard);
            //通过Matcher获得用户的出生年月日
            if (birthDateMather.find()) {
                Integer year = Integer.parseInt(birthDateMather.group(1));
                Integer month = Integer.parseInt(birthDateMather.group(2));
                Integer date = Integer.parseInt(birthDateMather.group(3));
                //输出用户的出生年月日
                if (year <= 1900) {
                    flag = false;
                } else if (month <= 0 || month > 12) {
                    flag = false;
                } else if (date <= 0 || date > 32) {
                    flag = false;
                } else {
                    flag = true;
                }
            } else {
                flag = false;
            }
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * 是否为中文
     *
     * @param name
     * @return
     */
    public static boolean checkName(String name) {
        Pattern p = Pattern.compile("^[\u4e00-\u9fa5]{0,}$"); //汉字正则
        return p.matcher(name).matches();
    }

    /**
     * 正数和小数点
     *
     * @param number
     * @return
     */
    public static boolean checkNumber(String number) {
        Pattern p = Pattern.compile("^(\\+)?\\d+(\\.\\d+)?$");
        return p.matcher(number).matches();
    }

    /**
     * 验证手机号
     *
     * @param phoneNumber
     * @return
     */
    public static boolean regexIsPhonNumber(String phoneNumber) {
        if (!TextUtils.isEmpty(phoneNumber) && !isMobileNumber(phoneNumber)) {
            ToastUtil.showShort(StringUtil.getResStr(R.string.please_input_correct_phoneNumber));
            return false;
        } else if (TextUtils.isEmpty(phoneNumber)) {
            ToastUtil.showShort(StringUtil.getResStr(R.string.please_input_phoneNumber));
            return false;
        } else {
            return true;
        }
    }

    /**
     * 验证验证码
     *
     * @param localVerifyCode
     * @param verifyCode
     * @return
     */
    public static boolean regexIsCorrectVerifyCode(String localVerifyCode, String verifyCode) {
        if (TextUtils.isEmpty(verifyCode)) {
            ToastUtil.showShort(StringUtil.getResStr(R.string.please_input_verify_code));
            return false;
        } else if (!verifyCode.equals(localVerifyCode)) {
            ToastUtil.showShort(StringUtil.getResStr(R.string.please_input_correct_verify_code));
            return false;
        } else if (verifyCode.length() != 6) {
            ToastUtil.showShort(StringUtil.getResStr(R.string.please_input_correct_verify_code));
            return false;
        } else {
            return true;
        }
    }

    /**
     * 验证验证码
     *
     * @param verifyCode
     * @return
     */
    public static boolean regexIsCorrectVerifyCode(String verifyCode) {
        if (TextUtils.isEmpty(verifyCode)) {
            ToastUtil.showShort(StringUtil.getResStr(R.string.please_input_verify_code));
            return false;
        } else if (verifyCode.length() != 4) {
            ToastUtil.showShort(StringUtil.getResStr(R.string.please_input_correct_verify_code));
            return false;
        } else {
            return true;
        }
    }

    /**
     * 验证两次密码是否一样
     *
     * @param password
     * @param passwordCertain
     * @return
     */
    public static boolean regexIsSamePassword(String password, String passwordCertain) {
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showShort(StringUtil.getResStr(R.string.please_input_new_password));
            return false;
        }else if (TextUtils.isEmpty(passwordCertain)) {
            ToastUtil.showShort(StringUtil.getResStr(R.string.please_input_new_password_again));
            return false;
        } else if (!password.equals(passwordCertain)) {
            ToastUtil.showShort(StringUtil.getResStr(R.string.not_same_password));
            return false;
        } else {
            return true;
        }
    }

    /**
     * 验证手机号和验证码
     *
     * @param phoneNumber
     * @param localVerifyCode
     * @param verifyCode
     * @return
     */
    public static boolean regexIsEmptyPhonNumberAndIsEmptyVerifyCode(String phoneNumber, String localVerifyCode, String verifyCode) {
        if (TextUtils.isEmpty(phoneNumber) && TextUtils.isEmpty(verifyCode)) {
            ToastUtil.showShort(StringUtil.getResStr(R.string.phoneNumber_verify_code_not_empty));
            return false;
        } else if (!regexIsPhonNumber(phoneNumber)) {
            return false;
        } else if (!regexIsCorrectVerifyCode(localVerifyCode, verifyCode)) {
            return false;
        } else {
            return true;
        }

    }
}

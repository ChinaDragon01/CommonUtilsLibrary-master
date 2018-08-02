package com.chinadragon.commonutilslibrary.simple.ui;

import android.widget.TextView;

import com.chinadragon.commonutilslibrary.RegexUtil;
import com.chinadragon.commonutilslibrary.simple.R;
import com.chinadragon.commonutilslibrary.simple.base.BaseAppCompatActivity;

import butterknife.BindView;

import static com.chinadragon.commonutilslibrary.simple.R.id.tv_checkName;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2018/7/29 10:41
 * Name:
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class RegexUtilActivity extends BaseAppCompatActivity {
    @BindView(R.id.tv_regexIsPhonNumber)
    TextView tvRegexIsPhonNumber;
    @BindView(R.id.tv_checkIdCard)
    TextView tvCheckIdCard;
    @BindView(tv_checkName)
    TextView tvCheckName;
    @BindView(R.id.tv_checkNumber)
    TextView tvCheckNumber;
    @BindView(R.id.tv_regexIsCorrectVerifyCode)
    TextView tvRegexIsCorrectVerifyCode;
    @BindView(R.id.tv_regexIsSamePassword)
    TextView tvRegexIsSamePassword;

    @Override
    public void initData() {
        super.initData();
        setText(tvRegexIsPhonNumber, "17612340000手机号码是否正确:" + RegexUtil.regexIsPhonNumber("17612340000") + "\n" + "176123400手机号码是否正确:" + RegexUtil.regexIsPhonNumber("176123400"));
        setText(tvCheckIdCard, "352621199408010000身份证号是否正确:" + RegexUtil.checkIdCard("352621199408010000"));
        setText(tvCheckName, "中国汉字是否全为中文:" + RegexUtil.checkName("中国汉字") + "\n" + "123是否全为中文:" + RegexUtil.checkName("123是否全为中文"));
        setText(tvCheckNumber, "12.3是否为正数和小数点:" + RegexUtil.checkNumber("12.3") + "\n" + "6是否为正数和小数点:" + RegexUtil.checkNumber("6") + "\n" + "-20是否为正数和小数点" + RegexUtil.checkNumber("-2"));
        setText(tvRegexIsCorrectVerifyCode, "6位数的验证码是否正确:" + RegexUtil.regexIsCorrectVerifyCode("123456", "554466"));// 用于本地验证
        setText(tvRegexIsSamePassword, "两次密码是否一致:" + RegexUtil.regexIsSamePassword("000111", "110011"));// 检验本地密码

    }

    private void setText(TextView textView, CharSequence text) {
        textView.setText(text);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_regexutil;
    }

}

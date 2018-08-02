package com.chinadragon.commonutilslibrary.simple.ui.encrypt;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.chinadragon.commonutilslibrary.LogUtil;
import com.chinadragon.commonutilslibrary.encrypt.Base64Encrypt;
import com.chinadragon.commonutilslibrary.encrypt.RSAUtils;
import com.chinadragon.commonutilslibrary.simple.R;
import com.chinadragon.commonutilslibrary.simple.base.BaseAppCompatActivity;
import com.chinadragon.commonutilslibrary.simple.constants.AppConstants;

import java.util.Map;

import butterknife.BindView;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2018/2/23 15:17
 * Name:
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class EncryptActivity extends BaseAppCompatActivity {
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.spinner_encryption_mode)
    Spinner spinnerEncryptionMode;
    @BindView(R.id.tv_ciphertext)
    TextView tvCiphertext;
    @BindView(R.id.tv_laws)
    TextView tvLaws;
    private int index;
    private RSAUtils mRsaUtils;
    private String privateKey;
    private String publicKey;

    @Override
    public void initData() {
        super.initData();
        mRsaUtils = new RSAUtils();
        initRsa();
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.encrypt_mode, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEncryptionMode.setAdapter(arrayAdapter);
        spinnerEncryptionMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                String text = et.getText().toString().trim();
                logInfo("加密前 text明文内容 ：" + text);
                if (!TextUtils.isEmpty(text)) {
                    encryptMode(text);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerEncryptionMode.setSelection(index);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        tvCiphertext.setMovementMethod(ScrollingMovementMethod.getInstance());
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                LogUtil.i("beforeTextChanged s：" + s);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LogUtil.i("onTextChanged s：" + s);
                encryptMode(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                LogUtil.i("afterTextChanged s：" + s);

            }
        });

    }

    private void encryptMode(String s) {
        logInfo("index = " + index + "，" + s);
        switch (index) {
            case AppConstants.ZERO:
                encryptDecode(Base64Encrypt.base64Encrypt(s), AppConstants.ZERO);
                break;
            case AppConstants.ONE:
                encryptDecode(s,AppConstants.ONE);
                break;
            case AppConstants.TWO:
                encryptDecode(s,AppConstants.TWO);
                break;
            case AppConstants.THREE:
                encryptDecode(s,AppConstants.THREE);
                break;
        }
    }

    /**
     * @param data
     */
    private void encryptDecode(String data, int type) {
        logInfo("type = " + type + "，密文 s = " + data);
        switch (type) {
            case AppConstants.ZERO:
                tvCiphertext.setText(data);
                tvLaws.setText("解密：" + Base64Encrypt.base64Decode(tvCiphertext.getText().toString()));
                break;
            case AppConstants.ONE:
                privatekeyRsa(data);
                break;
            case AppConstants.TWO:
                publickeyRsa(data);
                break;
            case AppConstants.THREE:
                signverify(data);
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_encrypt;
    }


    public void initRsa() {
        Map<String, Object> genKeyPair = null;
        try {
            genKeyPair = RSAUtils.genKeyPair();
            privateKey = RSAUtils.getPrivateKey(genKeyPair);
            publicKey = RSAUtils.getPublicKey(genKeyPair);
            logInfo("privateKey ：" + privateKey);
            logInfo("publicKey：" + publicKey);
        } catch (Exception e) {
            logInfo("initRsa 出现异常 e：" + e.toString());
            e.printStackTrace();
        }
    }

    public void privatekeyRsa(String source) {
        try {
            byte[] data = source.getBytes();
            byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
            String enBaseDate = RSAUtils.encode(encodedData);
            logInfo("私钥 加密后：" + enBaseDate);
            tvCiphertext.setText(enBaseDate);
            byte[] decryptByPrivateKey = RSAUtils.decryptByPublicKey(encodedData, publicKey);
            String decrypt = new String(decryptByPrivateKey);
            logInfo("公钥 解密后：" + decrypt);
            tvLaws.setText(decrypt);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void publickeyRsa(String source) {
        try {
            byte[] data = source.getBytes();
            byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
            String enBaseDate = RSAUtils.encode(encodedData);
            logInfo("公钥 加密后：" + enBaseDate);
            tvCiphertext.setText(enBaseDate);
            byte[] decryptByPrivateKey = RSAUtils.decryptByPrivateKey(encodedData, privateKey);
            String decrypt = new String(decryptByPrivateKey);
            logInfo("私钥解密后：" + decrypt);
            tvLaws.setText(decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signverify(String source) {

        try {
            byte[] data = source.getBytes();
            String sign = RSAUtils.sign(data, privateKey);
            logInfo("签名后的数据：" + sign);
            tvCiphertext.setText("签名后的数据：" + sign);
            boolean verify = RSAUtils.verify(data, publicKey, sign);
            logInfo("认证是否成功：" + verify);
            tvLaws.setText("认证是否成功："+verify);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

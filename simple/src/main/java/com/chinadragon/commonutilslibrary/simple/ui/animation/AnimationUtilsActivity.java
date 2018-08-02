package com.chinadragon.commonutilslibrary.simple.ui.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinadragon.commonutilslibrary.ScreenUtil;
import com.chinadragon.commonutilslibrary.animation.AnimationUtils;
import com.chinadragon.commonutilslibrary.simple.R;
import com.chinadragon.commonutilslibrary.simple.base.BaseAppCompatActivity;

import butterknife.BindView;

import static com.chinadragon.commonutilslibrary.simple.R.id.tv_alpha_scale;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2018/2/22 17:29
 * Name:
 * Overview:
 * Usage:
 * **********************************************************************
 */

public class AnimationUtilsActivity extends BaseAppCompatActivity {
    @BindView(tv_alpha_scale)
    TextView mTvAlphaScale;
    @BindView(R.id.tv_v)
    TextView tvV;
    @BindView(R.id.tv_h)
    TextView tvH;
    @BindView(R.id.imgBtn)
    ImageView imgBtn;
    @BindView(R.id.tv_animaton1)
    TextView tvAnimaton1;
    @BindView(R.id.imgBtn_left)
    ImageView imgBtnLeft;
    @BindView(R.id.tv_v_left)
    TextView tvVLeft;
    @BindView(R.id.imgBtn_topRight)
    ImageView imgBtnTopRight;
    private boolean isHide1, isHide2, isHide3;

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        setOnClickListener(tvAnimaton1);
        setOnClickListener(imgBtnLeft);
        setOnClickListener(imgBtn);
        setOnClickListener(imgBtnTopRight);
    }

    private void setOnClickListener(View view) {
        view.setOnClickListener(v -> {
            switch (v.getId()) {
                case R.id.tv_animaton1:
                    AnimatorSet animatorSet = AnimationUtils.setAnimationAlphaScale(mTvAlphaScale, 0f, 1f, 0f, 1f, 3000);
                    addListener(animatorSet);
                    break;
                case R.id.imgBtn:
                    if (isHide2) {
                        AnimationUtils.setAnimationRoation(imgBtn, 45, 0);
                        AnimationUtils.setAnimationAlphaTranslation(tvH, false, 0f, 1f, 1f, 0f, 500);
                        AnimationUtils.setAnimationAlphaTranslation(tvV, true, 0f, 1f, 1f, 0f, 500);
                    } else {
                        AnimationUtils.setAnimationRoation(imgBtn, 0, 45);
                        AnimationUtils.setAnimationAlphaTranslation(tvH, false, 0.7f, 0f, 0f, 1f, 500);
                        AnimationUtils.setAnimationAlphaTranslation(tvV, true, 0.7f, 0f, 0f, 1f, 500);
                    }
                    isHide2 = !isHide2;
                    break;

                case R.id.imgBtn_left:
                    if (isHide1) {
                        AnimationUtils.objectAnimatorTranY(tvVLeft, ScreenUtil.getScreenHeight(), 0f, new BounceInterpolator(), 1000);
                    } else {
                        AnimationUtils.objectAnimatorTranY(tvVLeft, 0f, ScreenUtil.getScreenHeight(), null, 1000);
                    }
                    isHide1 = !isHide1;
                    break;
                case R.id.imgBtn_topRight:
                    if (isHide3) {
                        AnimationUtils.objectAnimatorTranX(mTvAlphaScale, ScreenUtil.getScreenHeight(), 0f, new BounceInterpolator(), 1000);
                    } else {
                        AnimationUtils.objectAnimatorTranX(mTvAlphaScale, 0f, ScreenUtil.getScreenHeight(), null, 1000);
                    }
                    isHide3 = !isHide3;
                    break;
            }
        });
    }

    private void addListener(AnimatorSet animatorSet) {
        if (animatorSet == null) {
            return;
        }
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                logInfo("onAnimationStart 组合动画 透明度 和 缩放");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                logInfo("onAnimationEnd 组合动画 透明度 和 缩放");

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_animationutils;
    }

}

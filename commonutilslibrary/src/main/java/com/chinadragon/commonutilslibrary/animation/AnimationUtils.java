package com.chinadragon.commonutilslibrary.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2017/9/25 16:55
 * Name: 动画工具类
 * Overview: 设置动画效果
 * Usage:使用步骤
 * 根据对动画效果需求调用对应的方法
 * **********************************************************************
 */

public class AnimationUtils {
    /**
     * 旋转动画
     *
     * @param view
     * @param fromAngle
     * @param toAngle
     */
    public static void setAnimationRoation(View view, float fromAngle, float toAngle) {
        ObjectAnimator roation = ObjectAnimator.ofFloat(view, "rotation", fromAngle, toAngle);
        roation.setDuration(500);
        roation.start();
    }

    /**
     * 旋转动画
     *
     * @param view
     * @param fromAngle
     * @param toAngle
     * @param duration
     */
    public static void setAnimationRoation(View view, float fromAngle, float toAngle, long duration) {
        ObjectAnimator roation = ObjectAnimator.ofFloat(view, "rotation", fromAngle, toAngle);
        roation.setDuration(duration);
        roation.start();
    }

    /**
     * 组合动画 透明度 和 平移
     *
     * @param view
     * @param isY
     * @param fromAlpha
     * @param toAlpha
     * @param fromDegrees
     * @param toDegrees
     */
    public static void setAnimationAlphaTranslation(View view, boolean isY, float fromAlpha, float toAlpha, float fromDegrees, float toDegrees, int duration) {
        ObjectAnimator translation = null;
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", fromAlpha, toAlpha);
        if (isY) {
            translation = ObjectAnimator.ofFloat(view, "translationY", fromDegrees, toDegrees);
        } else {
            translation = ObjectAnimator.ofFloat(view, "translationX", fromDegrees, toDegrees);
        }
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(duration <= 0 ? 3000 : duration);
        animSet.play(alpha).with(translation);
        animSet.start();

    }

    /**
     * 组合动画 透明度 和 平移
     *
     * @param view
     * @param isY
     * @param fromAlpha
     * @param toAlpha
     * @param fromDegrees
     * @param toDegrees
     * @param duration
     */
    public static void setAnimationAlphaTranslation(View view, boolean isY, float fromAlpha, float toAlpha, float fromDegrees, float toDegrees, long duration) {
        ObjectAnimator translation = null;
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", fromAlpha, toAlpha);
        if (isY) {
            translation = ObjectAnimator.ofFloat(view, "translationY", fromDegrees, toDegrees);
        } else {
            translation = ObjectAnimator.ofFloat(view, "translationX", fromDegrees, toDegrees);
        }
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(duration);
        animSet.play(alpha).with(translation);
        animSet.start();

    }

    /**
     * 组合动画 透明度 和 缩放
     *
     * @param view
     * @param fromAlpha
     * @param toAlpha
     * @param fromTimes
     * @param toTimes
     * @param duration
     */
    public static AnimatorSet setAnimationAlphaScale(View view, float fromAlpha, float toAlpha, float fromTimes, float toTimes, long duration) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", fromAlpha, toAlpha);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", fromTimes, toTimes);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", fromTimes, toTimes);
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(duration);
        animSet.play(scaleX).with(scaleY).with(alpha);
        animSet.start();
        return animSet;
    }

    /**
     * 平移 回弹 动画效果
     *
     * @param view
     * @param fromXDelta
     * @param toXDelta
     * @param fromYDelta
     * @param toYDelta
     * @return
     */
    public static TranslateAnimation setTranslateAnimation(View view, float fromXDelta, float toXDelta, float fromYDelta, float toYDelta, long durationMillis) {
        TranslateAnimation translateAnimation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        translateAnimation.setInterpolator(new BounceInterpolator());// 回弹效果
        translateAnimation.setDuration(durationMillis);
        view.setAnimation(translateAnimation);
        view.startAnimation(translateAnimation);
        return translateAnimation;
    }

    /**
     * 平移 Y轴 属性动画
     * @param view
     * @param fromYDelta
     * @param toYDelta
     * @param timeInterpolator
     * @param durationMillis
     */
    public static void objectAnimatorTranY(View view, float fromYDelta, float toYDelta, TimeInterpolator timeInterpolator, long durationMillis) {
        Animator tranY = ObjectAnimator.ofFloat(view, "translationY", fromYDelta, toYDelta);
        //设置动画执行的持续时间
        tranY.setDuration(durationMillis);
        if (null != timeInterpolator){
            tranY.setInterpolator(timeInterpolator);// 回弹效果
        }
        tranY.start();
    }

    /**
     * 平移 x轴 属性动画
     * @param view
     * @param fromYDelta
     * @param toYDelta
     * @param durationMillis
     */
    public static void objectAnimatorTranX(View view, float fromYDelta, float toYDelta, TimeInterpolator timeInterpolator, long durationMillis) {
        Animator tranX = ObjectAnimator.ofFloat(view, "translationX", fromYDelta, toYDelta);
        //设置动画执行的持续时间
        tranX.setDuration(durationMillis);
        if (null != timeInterpolator){
            tranX.setInterpolator(timeInterpolator);// 回弹效果
        }
        tranX.start();
    }
}

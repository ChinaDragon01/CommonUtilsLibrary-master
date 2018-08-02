package com.chinadragon.commonutilslibrary;

import android.app.Activity;

import java.util.Stack;

/**
 * **********************************************************************
 * Author: zhangbenlong
 * Time: 2016/10/10 14:44
 * Name: Activity任务栈工具类
 * Overview: 管理以及退出（结束）多个Activity
 * Usage: 使用步骤
 * 在activity的onCreate方法通过AppActivityManager调用getInstance().add();
 * 或者通过AppActivityManager调用getInstance()得到实例之后定义一个变量，使用变量调用add();
 * **********************************************************************
 */
public class AppActivityManager {
    private static Stack<Activity> activityStack; //Activity任务栈
    private static AppActivityManager instance;

    private AppActivityManager() {

    }

    public static AppActivityManager getInstance(){
        if(instance == null){
            instance = new AppActivityManager();
        }
        return instance;
    }

    public void addActivity(Activity activity){
        if(activityStack==null){
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    public void finishActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
            activity=null;
        }
    }

    public void finishByClassName(Class<?> clazz){
        for (Activity activity : activityStack) {
            if(activity.getClass().equals(clazz)){
                finishActivity(activity);
                break;
            }
        }
    }

    public void finishAllActivity(){
        if(activityStack!=null){
            LogUtil.i("AppActivityManager activityStack size = "+activityStack.size());
            for (Activity activity : activityStack) {
                LogUtil.i("AppActivityManager activity ："+activity.getLocalClassName());
                if(activity!=null){
                    finishActivity(activity);
                }
            }
            activityStack.clear();
        }
    }

    public void exit(){
        try {
            finishAllActivity();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

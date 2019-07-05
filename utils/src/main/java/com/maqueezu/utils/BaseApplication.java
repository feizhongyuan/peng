package com.maqueezu.utils;


import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;
import com.maqueezu.utils.interf.Cons;
import com.maqueezu.utils.ui.base.BaseActivity;
import com.maqueezu.utils.ui.web.TBSManager;

import java.util.ArrayList;
import java.util.List;

public class BaseApplication extends MultiDexApplication {
    //自动存储或清除所有继承于BaseActivity的activity对象
    private List<BaseActivity> activityList = new ArrayList<>();
    private static BaseApplication application;
    private static Cons cons;

    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        BaseApplication.application = this;
        Thread.setDefaultUncaughtExceptionHandler(new MyBaseExceptionHandler());


        if(cons.isDebug()){
            Stetho.initializeWithDefaults(this);
        }

        //初始化x5
        TBSManager.getTbsManager();
    }

    public static BaseApplication getAppContext(){
        return BaseApplication.application;
    }

    public void addActivity(BaseActivity activity){
        activityList.add(activity);
    }

    public void removeActivity(BaseActivity activity){
        activityList.remove(activity);
    }

    public BaseActivity getTopActivity(){
        int x = activityList.size() -1;
        if(x < 0){
            return null;
        }
        return activityList.get(x);
    }

    public static Cons getCons() {
        return BaseApplication.cons;
    }

    public static void setCons(Cons cons) {
        BaseApplication.cons = cons;
    }

    /**
     * @return
     * 全局的上下文
     */
    public static Context getContext() {
        return mContext;
    }
}

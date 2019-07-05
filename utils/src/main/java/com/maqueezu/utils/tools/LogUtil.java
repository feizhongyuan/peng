package com.maqueezu.utils.tools;

import android.util.Log;

import com.maqueezu.utils.BaseApplication;

/**
 * Created by admin on 2019/4/9 0009.
 */

public class LogUtil {

    public static void print(String log){
        print("deftag",log);
    }

    public static void print(String tag,String log){
        if(!BaseApplication.getCons().isDebug()){
            return;
        }
        Log.e(tag,log);
    }
}

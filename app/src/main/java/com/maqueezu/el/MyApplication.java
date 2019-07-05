package com.maqueezu.el;


import android.content.Context;

import com.maqueezu.el.engine.JsApi;
import com.maqueezu.utils.BaseApplication;
import com.maqueezu.utils.ui.web.WebFragment;


/**
 * 客户端入口
 *
 * 
 *
 */
public class MyApplication extends BaseApplication {
    private MyApplication application;

    private static Context mContext;

    @Override
    public void onCreate() {
        setCons(Constant.getCons());
        super.onCreate();
        application = this;
        init();
    }

    private void init(){

        //创建默认的处理原生与js交互的对象
        WebFragment.setCreateJsApiListener((activity, dWebView) -> new JsApi(activity, dWebView));
    }

    public MyApplication getApplication(){
        return application;
    }

    /**
     * @return
     * 全局的上下文
     */
    public static Context getContext() {
        return mContext;
    }

}

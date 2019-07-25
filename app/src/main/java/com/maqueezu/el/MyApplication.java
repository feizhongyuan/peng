package com.maqueezu.el;


import android.app.Activity;
import android.content.Context;

import com.maqueezu.el.engine.JsApi;
import com.maqueezu.el.ui.webview.JsDSBridge;
import com.maqueezu.el.ui.webview.NewWebFragment;
import com.maqueezu.utils.BaseApplication;
import com.maqueezu.utils.ui.web.OnCreateJsApiListener;
import com.maqueezu.utils.ui.web.WebFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import wendu.dsbridge.DWebView;


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
        NewWebFragment.setCreateJsApiListener(new OnCreateJsApiListener() {
            @Override
            public Object createJsObj(Activity activity, DWebView dWebView) {
                return new JsDSBridge(activity,dWebView);
            }
        });
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

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

}

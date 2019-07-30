package com.maqueezu.utils.ui.base;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.maqueezu.utils.tools.NetBroadcastReceiver;

/**
 * Created by fei .
 * Created by Date 2019/7/29 17:28
 */

public abstract class OtherBaseActivity extends AppCompatActivity implements NetBroadcastReceiver.NetChangeListener{

    public static NetBroadcastReceiver.NetChangeListener netEvent;// 网络状态改变监听事件

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 添加到Activity工具类
//        BaseApplication.getAppContext().addActivity(this);

        // 初始化netEvent
        netEvent = this;

//		设置布局
        setContentView(getLayoutId());
//		初始化View
        initView();
//		初始化数据
        initData();
//		初始化监听
        initListener();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    @Override
    protected void onResume() {
        super.onResume();
//        更改应用程序字体大小
        Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.fontScale = 1;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 点击手机上的返回键，返回上一层
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 移除Activity
//            BaseApplication.getAppContext().removeActivity(this);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 网络状态改变时间监听
     *
     * @param netWorkState true有网络，false无网络
     */
    @Override
    public void onNetChange(boolean netWorkState) {
    }

    @Override
    protected void onDestroy() {
        // Activity销毁时，提示系统回收
        // System.gc();
        netEvent = null;
        // 移除Activity
//        BaseApplication.getAppContext().removeActivity(this);
        super.onDestroy();
    }
}

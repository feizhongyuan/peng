package com.maqueezu.el.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

import com.maqueezu.el.MainActivity;
import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.pojo.GoodsCatBean;
import com.maqueezu.el.presenter.HomePresenter;
import com.maqueezu.el.ui.iview.HomeContract;
import com.maqueezu.utils.tools.LogUtil;
import com.maqueezu.utils.ui.base.BaseActivity;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;

public class WelcomeActivity extends BaseActivity<HomeContract.View, HomePresenter<HomeContract.View>> implements HomeContract.View {

    private ImageView mImage_qidong;
    private Handler handler = new Handler();
    boolean isFirstIn = false;
    private Intent intent;
    private AutoRelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        final SharedPreferences sp = getSharedPreferences("first_in_data", MODE_PRIVATE);
        isFirstIn = sp.getBoolean("isFirstIn", true);

        presenter.fetchData();

        initIntent();


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected HomePresenter<HomeContract.View> createPresenter() {
        return new HomePresenter<>();
    }

    @Override
    protected void initView() {
        mImage_qidong = (ImageView) findViewById(R.id.mImage_qidong);
        layout = (AutoRelativeLayout) findViewById(R.id.layout);
        layout.setSystemUiVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    private void initIntent() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                AnimationSet animationSet = new AnimationSet(true);
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0f);
                alphaAnimation.setDuration(3000);
                animationSet.addAnimation(alphaAnimation);
                mImage_qidong.startAnimation(animationSet);
                if (isFirstIn) {
                    intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    WelcomeActivity.this.startActivity(intent);
                    WelcomeActivity.this.finish();
                    //取消界面跳转时的动画
                    overridePendingTransition(0, 0);

                } else {
                    intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    WelcomeActivity.this.startActivity(intent);
                    WelcomeActivity.this.finish();
                    //取消界面跳转时的动画
                    overridePendingTransition(0, 0);

                }
            }
        }, 3000);
    }

    /**
     * 屏蔽物理返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (handler != null) {
            //If token is null, all callbacks and messages will be removed.
            handler.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    static void setStatusBarColor(Activity activity, int statusColor) {
        Window window = activity.getWindow();
        //取消状态栏透明
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(statusColor);
        //设置系统状态栏处于可见状态
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        //让view不根据系统窗口来调整自己的布局
        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
            ViewCompat.requestApplyInsets(mChildView);
        }
    }


    @Override
    public void getAd(AdvertBean advert) {
        LogUtil.print("TAG1", advert.toString());
        EventBus.getDefault().post(advert);
    }

    @Override
    public void getGoodsCatList(GoodsCatBean goodsCatBean) {
        Log.e("TAG4","welcome---"+goodsCatBean.toString());
    }
}

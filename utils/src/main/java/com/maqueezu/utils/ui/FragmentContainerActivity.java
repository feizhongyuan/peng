package com.maqueezu.utils.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.maqueezu.utils.R;
import com.maqueezu.utils.tools.LogUtil;
import com.maqueezu.utils.tools.presenter.BasePresenter;
import com.maqueezu.utils.ui.base.BaseActivity;
import com.maqueezu.utils.ui.base.BaseFragment;

/**
 * 装载一个无参fragment的通用activity，以减少activity数量，降低耦合性，便于页面随意组装
 *
 */
public class FragmentContainerActivity extends BaseActivity {

    public static final String FragmentClassName = "AbsolutePath";
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        initFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_single_fragment;
    }

    @Override
    protected BasePresenter createPresenter() {
        return new BasePresenter() {
            @Override
            public void attachView(Object view) {
                super.attachView(view);
            }
        };
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @SuppressLint("Recycle")
    private void initFragment() {
        // 获取fragment的全类名
        String path = getIntent().getStringExtra(FragmentClassName);

        if (!TextUtils.isEmpty(path)) {
            try {

                fragment = getSupportFragmentManager().findFragmentByTag(path);
                if (fragment != null) {
                    LogUtil.print("FragmentContainerActivity","fragment已存在,不再创建");
                    return;
                }
                fragment = (Fragment) Class.forName(path).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (fragment != null) {
                // 添加到activity上
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_parent, fragment, path).commitAllowingStateLoss();
            } else {
                throw new RuntimeException("指定的绝对路径无法构造fragment");
            }
        } else {
            LogUtil.print("FragmentContainerActivity","无法创建无参fragment");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        fragment.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    public void onBackPressed() {
        if (fragment instanceof BaseFragment) {
            if (!((BaseFragment) fragment).onBackPressed()) {
                super.onBackPressed();
            }
        }
    }
}

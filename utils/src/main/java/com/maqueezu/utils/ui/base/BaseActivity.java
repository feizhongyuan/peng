package com.maqueezu.utils.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.view.KeyEvent;
import android.view.View;


import com.maqueezu.utils.BaseApplication;
import com.maqueezu.utils.tools.LogUtil;
import com.maqueezu.utils.tools.presenter.BasePresenter;
import com.maqueezu.utils.view.MyProgressDialog;

import java.util.List;


/**
 * 基
 * @author xianlai
 *
 */
public abstract class BaseActivity<V,T extends BasePresenter<V>> extends FragmentActivity {
	private MyProgressDialog dialog;

	protected View view;

	public T presenter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		BaseApplication.getAppContext().addActivity(this);

//		设置布局
		setContentView(getLayoutId());

		presenter = createPresenter();
		presenter.attachView((V) this);
//		初始化View
		initView();
//		初始化数据
		initData();
//		初始化监听
		initListener();
	}

	protected abstract int getLayoutId();
	//		创建所需要的Presenter
	protected abstract T createPresenter();

	protected abstract void initView();
	protected abstract void initData();
	protected abstract void initListener();

	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	public MyProgressDialog showDialog() {
		if (dialog != null) {
			dialog.dismissDialog();
			dialog = null;
		}
		dialog = new MyProgressDialog(this);
		dialog.showDialog();
		return dialog;
	}

	public void showDialog(String str) {
		if (dialog != null) {
			dialog.dismissDialog();
			dialog = null;
		}
		dialog = new MyProgressDialog(this);

		dialog.setDialogText(str);
		dialog.showDialog();
	}
	
	public void dismisDialog() {
		if (dialog != null) {
			dialog.dismissDialog();
		}
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		LogUtil.print("BaseActivity","调用fragment的onActivityResult方法一次");
		List<Fragment> fragments = getSupportFragmentManager().getFragments();
		if(fragments == null || fragments.size() < 1){
			return;
		}
		
		for(Fragment fragment : fragments){
			if(fragment == null){
				continue;
			}
			fragment.onActivityResult(requestCode, resultCode, data);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 点击手机上的返回键，返回上一层
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
			BaseApplication.getAppContext().removeActivity(this);
		}
		return super.onKeyDown(keyCode, event);
	}

	//    获取状态栏高度
	public int getStatusBarHeight(Context context) {
		int result = 0;
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
				"android");
		if (resourceId > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}


	@Override
	protected void onDestroy() {
		BaseApplication.getAppContext().removeActivity(this);
		super.onDestroy();
	}
}

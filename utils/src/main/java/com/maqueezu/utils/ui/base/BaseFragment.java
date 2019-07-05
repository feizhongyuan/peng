package com.maqueezu.utils.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.maqueezu.utils.tools.LogUtil;
import com.maqueezu.utils.view.MyProgressDialog;

import java.util.List;

public abstract class BaseFragment extends Fragment {
	public static MyProgressDialog progressDialog = null;//进度对话框
	/**
	 * 贴附的activity
	 */
	protected FragmentActivity mActivity;
	/**
	 * 根view
	 */
	protected View mRootView;
	/**
	 * 是否对用户可见
	 */
	protected boolean mIsVisible;
	/**
	 * 是否加载完成
	 * 当执行完oncreatview,View的初始化方法后方法后即为true
	 */
	protected boolean mIsPrepare;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mActivity = getActivity();
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mRootView = inflater.inflate(setLayoutResouceId(), container, false);

		mIsPrepare = true;

		return mRootView;
	}

	protected abstract int setLayoutResouceId();

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView(mRootView);
		initData(getArguments());
		initListener();
	}

	protected abstract void initView(View mRootView);

	protected abstract void initData(Bundle arguments);

	protected abstract void initListener();

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		this.mIsVisible = isVisibleToUser;

		if (isVisibleToUser) {
			onVisibleToUser();
		}
	}
	/**
	 * 用户可见时执行的操作
	 */
	protected void onVisibleToUser() {
		if (mIsPrepare && mIsVisible) {
			onLazyLoad();
		}
	}
	/**
	 * 懒加载，仅当用户可见切view初始化结束后才会执行
	 */
	protected void onLazyLoad() {
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		List<Fragment> fragments = getChildFragmentManager().getFragments();
		if(fragments == null || fragments.size() < 1){
			return;
		}
		
		for(Fragment fragment : fragments){
			if(fragment == null){
				continue;
			}
			fragment.onResume();
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		
		List<Fragment> fragments = getChildFragmentManager().getFragments();
		if(fragments == null || fragments.size() < 1){
			return;
		}
		
		for(Fragment fragment : fragments){
			if(fragment == null){
				continue;
			}
			fragment.onPause();
		}
	}
	

	public MyProgressDialog showProgressDialog() {
		return showProgressDialog(null, false, true);
	}
	
	public void dismisProgressDialog() {
		if (progressDialog == null) {
			return;
		} else {
			progressDialog.dismissDialog();
		}
	}
	
	public MyProgressDialog showProgressDialog(String message) {
		return showProgressDialog(message, false, true);
	}
	
	public MyProgressDialog showProgressDialog(String message, boolean canCancel) {
		return showProgressDialog(message, false, canCancel);
	}

	public MyProgressDialog showProgressDialog(boolean canCancel) {
		return showProgressDialog(null, false, canCancel);
	}
	
	public MyProgressDialog showProgressDialog(String message, boolean touchOutside,
			boolean canCancel) {
		Context context = getActivity();
		if (progressDialog != null) {
			progressDialog.dismissDialog();
			progressDialog = null;
		}
		progressDialog = new MyProgressDialog(context);
		progressDialog.setDialogTouchOutside(touchOutside);
		progressDialog.setDialogCancelable(canCancel);
		if (!TextUtils.isEmpty(message)) {
			progressDialog.setDialogText(message);
		}
		progressDialog.showDialog();
		return progressDialog;
	}
	
	public boolean onBackPressed() {
		return false;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 将事件传递给正在显示的fragment
		List<Fragment> fragments = getChildFragmentManager().getFragments();
		
		if(fragments == null){
			return;
		}
		LogUtil.print("BaseFragment-onActivityResult", "调用fragment的onActivityResult方法一次");
		for(int i = 0; i < fragments.size(); i++){
			Fragment fragment = fragments.get(i);
			if (fragment != null) {
				fragment.onActivityResult(requestCode, resultCode, data);
			}
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		// 将事件传递给正在显示的fragment
		List<Fragment> fragments = getChildFragmentManager().getFragments();

		if(fragments == null){
			return;
		}
		LogUtil.print("BaseFragment-onRequestPermissionsResult", "调用fragment的onRequestPermissionsResult方法一次");
		for(int i = 0; i < fragments.size(); i++){
			Fragment fragment = fragments.get(i);
			if (fragment != null) {
				fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
			}
		}
	}

	@Override
	public void onDestroy() {
		if(progressDialog != null){
			progressDialog.dismissDialog();
		}
		super.onDestroy();
	}
}

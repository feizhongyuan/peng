package com.maqueezu.utils.ui.recyclerView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.maqueezu.utils.R;
import com.maqueezu.utils.ui.base.BaseFragment;


/**
 * 数据加载状态的显示
 * 
 * @author qian
 * 
 */
public class TipsFragment extends BaseFragment implements OnClickListener {

	private View rootView;

	public static enum TipType {
		/**
		 * 显示正在加载提示
		 */
		TYPE_LOADING(1),
		/**
		 * 显示失败提示
		 */
		TYPE_FAILE(2),
		/**
		 * 没有数据时的提示
		 */
		TYPE_NULL(3),
		/**
		 * 没有网络时
		 */
		TYPE_NET_ERROR(5),
		/**
		 * 显示自定义提示界面
		 */
		TYPE_CUSTOM_VIEW(4);

		int type = 0;

		private TipType(int type) {
			this.type = type;
		}
	}

	private FrameLayout tip_parent;
	private RefreshListener refreshListener;
	String loadMeg, loadError, loadNull, loadNetError;

	private int null_layout = R.layout.layout_load_null;
	private int faile_layout = R.layout.layout_load_error;
	private int loading_layout = R.layout.layout_loading;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = super.onCreateView(inflater, container, savedInstanceState);
		tip_parent = (FrameLayout) rootView.findViewById(R.id.tip_parent);
		return rootView;
	}

	@Override
	protected int setLayoutResouceId() {
		return R.layout.tips_fragment;
	}

	@Override
	protected void initView(View mRootView) {

	}

	@Override
	protected void initData(Bundle arguments) {

	}

	@Override
	protected void initListener() {

	}

	public void resetLayout(int null_layout,int faile_layout,int loading_layout){
		if(null_layout > 0){
			this.null_layout = null_layout;
		}

		if(faile_layout > 0){
			this.faile_layout = faile_layout;
		}

		if(loading_layout > 0){
			this.loading_layout = loading_layout;
		}
	}
	
	public void setHind(){
		tip_parent.setVisibility(View.GONE);
	}
	
	public boolean isShow(){
		return tip_parent.getVisibility() == View.VISIBLE;
	}

	/**
	 * 设置显示消息
	 * 
	 * @param loadMeg
	 * @param loadError
	 * @param loadNull
	 * @param loadNetError
	 */
	public void setHintMessage(String loadMeg, String loadNull, String loadError, String loadNetError) {
		this.loadMeg = loadMeg;
		this.loadError = loadError;
		this.loadNull = loadNull;
		this.loadNetError = loadNetError;
	}


	/**
	 * 显示空值
	 * 
	 * @param message
	 */
	public void showNull(String message) {
		tip_parent.setVisibility(View.VISIBLE);
		tip_parent.removeAllViews();
		
		View view = View.inflate(getActivity(), null_layout, null);
		TextView tv = (TextView) view.findViewById(R.id.tv_load_null);

		if(tv != null){
			if (!TextUtils.isEmpty(loadNull)) {
				tv.setText(loadNull);
			}else if (!TextUtils.isEmpty(message)) {
				tv.setText(message);
			}
		}

		tip_parent.addView(view);
	}

	/**
	 * 显示失败
	 * 
	 * @param message
	 */
	public void showFaile(TipType type, String message) {
		tip_parent.setVisibility(View.VISIBLE);
		tip_parent.removeAllViews();
		
		View view = View.inflate(getActivity(), faile_layout, null);
		TextView tv = (TextView) view.findViewById(R.id.tv_error);
		if (!TextUtils.isEmpty(message)) {
			tv.setText(message);
		} else if (type == TipType.TYPE_FAILE) {
			if (!TextUtils.isEmpty(loadError)) {
				tv.setText(loadError);
			}
		} else if (type == TipType.TYPE_NET_ERROR) {
			if (TextUtils.isEmpty(loadNetError)) {
				tv.setText(loadNetError);
			}
		}
		view.setOnClickListener(this);
		tip_parent.addView(view);
	}

	/**
	 * 显示正在加载
	 * 
	 * @param message
	 */
	public void showloading(String message) {
		tip_parent.setVisibility(View.VISIBLE);
		tip_parent.removeAllViews();
		
		View view = View.inflate(getActivity(), loading_layout, null);
		if (!TextUtils.isEmpty(loadMeg)) {
			TextView tv = (TextView) view.findViewById(R.id.tv_loading_tips);
			tv.setText(loadMeg);
		} else if (!TextUtils.isEmpty(message)) {
			TextView tv = (TextView) view.findViewById(R.id.tv_loading_tips);
			tv.setText(message);
		}
		tip_parent.addView(view);
	}

	@Override
	public void onClick(View v) {
		if (refreshListener != null) {
			refreshListener.onRefresh();
		}
	}

	/**
	 * 设置刷新事件
	 * 
	 * @param refreshListener
	 */
	public void setRefreshListener(RefreshListener refreshListener) {
		this.refreshListener = refreshListener;
	}

	/**
	 * 刷新加载
	 * 
	 * @author qian
	 * 
	 */
	public interface RefreshListener {
		void onRefresh();
	}
}

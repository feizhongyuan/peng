package com.maqueezu.utils.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.maqueezu.utils.R;


/**
 * 进度框
 * 
 * @author txl
 * 
 */
public class MyProgressDialog {
	private Dialog dialog;


	private Animation loadAnimation;

	private TextView textView;

	private View inflate;
	
	private Activity activity;

	public MyProgressDialog(Context context) {
		this.activity = (Activity) context;

		inflate = View.inflate(context, R.layout.view_progress_dialog, null);

		textView = (TextView) inflate.findViewById(R.id.progress_text);

		loadAnimation = AnimationUtils.loadAnimation(context,
				R.anim.custom_progressbar);

		LinearInterpolator lir = new LinearInterpolator();
		loadAnimation.setInterpolator(lir);

		if (dialog == null) {

			dialog = new Dialog(context, R.style.loading_dialog);
			dialog.setCancelable(true);
			dialog.setCanceledOnTouchOutside(false);

			dialog.setContentView(inflate);
			dialog.getWindow().getAttributes().gravity = Gravity.CENTER;

		}
	}
	/**
	 * 获取对话框
	 * @return
	 */
	public Dialog getDialog(){
		return dialog;
	}

	public void setBackgroundResource(int resid) {
		inflate.setBackgroundResource(resid);
	}

	/**
	 * 设置是否需要在点击屏幕时对话框消失,默认不消失
	 * 
	 * @param b
	 */
	public void setDialogTouchOutside(boolean b) {
		dialog.setCanceledOnTouchOutside(b);
	}

	/**
	 * 设置是否在按下回退键时对话框是否消失，默认消失
	 * 
	 * @param b
	 */
	public void setDialogCancelable(boolean b) {
		dialog.setCancelable(b);
	}

	public boolean dialogIsShowing() {
		return dialog != null ? dialog.isShowing() : false;
	}
	
	public void setTitle(String title){
		
	}
	public void setMessage(String message){
		textView.setText(message);
	}

	public void showDialog(String message) {
		if (dialog != null && activity!=null && !activity.isFinishing()) {
			textView.setText(message);
			dialog.show();
		}
	}
	public void showDialog() {
		if (dialog != null && activity!=null && !activity.isFinishing()) {
			dialog.show();
		}
	}

	public void dismissDialog() {
		if (dialog != null) {
			dialog.dismiss();
			dialog = null;
		}
	}

	public void setDialogText(String str) {
		if (textView != null) {
			textView.setText(str);
		}
	}

	public void setDialogText(int resId) {
		if (textView != null) {
			textView.setText(resId);
		}
	}
	

}

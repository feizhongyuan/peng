package com.maqueezu.utils.tools;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;


import com.maqueezu.utils.BaseApplication;

import java.util.ArrayList;

/**
 * 请求权限工具类
 */
public class PermissionsUtils {
	public final static int RECORD_AUDIO = 0x12;
	public final static int WRITE_EXTERNAL_ACCESS_FINE_LOCATION_CODE = 0x13;
	public final static int WRITE_EXTERNAL_CAMERA = 0x14;

	private Activity context;

	public PermissionsUtils(Activity context) {
		this.context = context;
	}

	/**
	 * 检查必须权限
	 * 
	 * @return
	 */
	public boolean checkPermissions(Activity activity) {
		String[] permissions = {
				Manifest.permission.ACCESS_NETWORK_STATE,
				Manifest.permission.INTERNET,
				Manifest.permission.READ_PHONE_STATE,
				Manifest.permission.WRITE_EXTERNAL_STORAGE
		};

		ArrayList<String> toApplyList = new ArrayList<String>();

		for (String perm : permissions) {
			if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(activity, perm)) {
				toApplyList.add(perm);
				// 进入到这里代表没有权限.
			}
		}
		String[] tmpList = new String[toApplyList.size()];
		if (!toApplyList.isEmpty()) {
			ActivityCompat.requestPermissions(activity, toApplyList.toArray(tmpList), 123);
			return false;
		}
		return true;
	}

	private void showDialog(final OnClickListener clickListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("提示");
		builder.setMessage("检测应用未获取到正常运行所需的权限，点击确认开启！");
		builder.setNegativeButton("确认", clickListener);
		builder.setCancelable(false);
		builder.show();
	}


	/**
	 * 检查话筒的权限
	 */
	public boolean checkRecordAudioPermissions() {
		if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(context, new String[] { Manifest.permission.RECORD_AUDIO }, RECORD_AUDIO);
			return false;
		}
		return true;
	}

	/**
	 * 检查位置权限
	 */
	public boolean checkLocationPermissions() {
		// 申请高精度定位权限
		if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(context, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, WRITE_EXTERNAL_ACCESS_FINE_LOCATION_CODE);
			return false;
		}
		return true;
	}

	/**
	 * 检查相机权限
	 */
	public boolean checkCameraPermissions() {
		if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(context, new String[] { Manifest.permission.CAMERA }, WRITE_EXTERNAL_CAMERA);
			return false;
		}
		return true;
	}

	public boolean doNext(Activity activity,int requestCode,int[] grantResults) {
		if(grantResults == null || grantResults.length < 1){
			return false;
		}

		if(requestCode == RECORD_AUDIO){
			if(grantResults[0] == PackageManager.PERMISSION_DENIED){
				Toast.makeText(BaseApplication.getAppContext(), "麦克风权限被拒绝，无法开启语音输入", Toast.LENGTH_SHORT).show();
				return false;
			}else{
				return true;
			}
		}

		for(int result : grantResults){
			if(result == PackageManager.PERMISSION_DENIED){
				onBackPressed();
				return false;
			}
		}
		return true;
	}

	public void onBackPressed() {
		if (context != null) {
			Toast.makeText(context, "必须权限被拒绝,程序退出", Toast.LENGTH_LONG).show();
			context.finish();
		}
	}

}

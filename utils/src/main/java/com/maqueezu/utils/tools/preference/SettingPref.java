
package com.maqueezu.utils.tools.preference;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.maqueezu.utils.BaseApplication;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * 统一的简单保存数据到 SharedPreferences
 */
public class SettingPref extends PreferenceOpenHelper {
	/**
	 * 使用说明
	//存储     SettingPref.getInstance().setFirstOpen(true);
	//获取     boolean b = SettingPref.getInstance().isFirstOpen();
	 */

	private static SettingPref mSettingPref;

	public SettingPref(Context context, String prefname) {
		super(context, prefname);
	}

	public synchronized static SettingPref getInstance() {
		if (mSettingPref == null) {
			Context context = BaseApplication.getAppContext();
			String name = context.getPackageName();
			mSettingPref = new SettingPref(context, name);
		}
		return mSettingPref;
	}

	public Map<String, ?> getAll() {
		Map<String, ?> all = super.getAll();
		Log.i("SettingPref","getAll:"+all);
		if (all == null || all.size() < 1) {
			return null;
		}

		Set<String> keySet = all.keySet();
		if (keySet == null || keySet.size() < 1) {
			return null;
		}

		Iterator<String> iterator = keySet.iterator();
		if (iterator == null) {
			return null;
		}

		Map<String, Object> decryptMap = new HashMap<String, Object>();
		while (iterator.hasNext()) {
			String key = iterator.next() + "";
			String value = all.get(key) + "";

			if (TextUtils.isEmpty(key)) {
				continue;
			}
			decryptMap.put(key, value);
		}
		return decryptMap;
	}




	public void setValue(String key, String value) {
		if (TextUtils.isEmpty(key)) {
			return;
		}
		if (TextUtils.isEmpty(value)) {
			value = new String();
		}
		mSettingPref.putString(key, value + "");
	}

	public String getValue(String key) {
		if (TextUtils.isEmpty(key)) {
			return null;
		}
		String value = mSettingPref.getString(key, "");
		return value;
	}


	/**
	 * 存取示例
	 * @return
	 */
	public boolean isFirstOpen() {
		return mSettingPref.getBoolean(Key.isFirstOpen, false);
	}
	public void setFirstOpen(boolean isFirstOpen) {
		mSettingPref.putBoolean(Key.isFirstOpen, isFirstOpen);
	}
	

	private static interface Key {
		//app是否第一次打开
		public static final String isFirstOpen = "isFirstOpen";
	}
}

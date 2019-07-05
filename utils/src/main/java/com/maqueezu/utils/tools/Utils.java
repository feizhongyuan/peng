/**
 * 
 */
package com.maqueezu.utils.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.maqueezu.utils.BaseApplication;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	/*
	 * 计算两点间距离
	 */

	public static int distance(double lat1, double lng1, double lat2, double lng2) {
		double dd = Math.PI / 180;
		double x1 = lat1 * dd, x2 = lat2 * dd;
		double y1 = lng1 * dd, y2 = lng2 * dd;
		double R = 6371004;
		double distance = (2 * R * Math.asin(Math.sqrt(2 - 2 * Math.cos(x1) * Math.cos(x2) * Math.cos(y1 - y2) - 2 * Math.sin(x1) * Math.sin(x2)) / 2));
		// km 返回
		// return distance*1000;
		return (int) distance;
	}

	// 格式化时间
	private static SimpleDateFormat dateformat = null;
	
	 public static String md5(String str)
	    {
	        MessageDigest md;
	        StringBuffer sb = new StringBuffer();
	        try {
	            md = MessageDigest.getInstance("MD5");
	            md.update(str.getBytes());
	            byte[] data = md.digest();
	            int index;
	            for(byte b : data) {
	                index = b;
	                if(index < 0) index += 256;
	                if(index < 16) sb.append("0");
	                sb.append(Integer.toHexString(index));
	            }
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return sb.toString();
	    }

	    public static String md5_16(String str) {
	        String result = md5(str);
	        return result.substring(8, 24);
	    }

	/**
	 * 时间转化器
	 * 
	 *            例如2012-12-20 12:15:45
	 * @return
	 */
	public static String getTimeFormat(Date date) {
		long i1 = date.getTime();
		long i2 = Calendar.getInstance().getTimeInMillis();
		long time = (i2 - i1) / 1000;

		if (time < 60) {
			return "1分钟前";
		} else if (time < 3600) {
			int num = Math.round(time / 60);
			return num + "分钟前";
		} else if (time < 86400) {
			int num = Math.round(time / 60 / 60);
			return num + "小时前";
		} else if (time < 604800) {
			int num = Math.round(time / 60 / 60 / 24);
			if (num == 1) {
				return num + "天前";
			}
			if (num > 7) {
				int weeks = num / 7;
				return weeks + "星期前";
			}
			return num + "天前";
		} else {
			int num = Math.round(time / 60 / 60 / 24 / 7);
			return num + "星期前";
		}
	}

	/**
	 * 时间转化器
	 * 
	 *            例如2012-12-20 12:15:45
	 * @return
	 */
	public static String getTimeFormat(String timeStr) {
		if (dateformat == null) {
			dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		}
		StringBuffer str = new StringBuffer();
		try {
			Date date = dateformat.parse(timeStr);
			Calendar c1 = Calendar.getInstance();
			c1.setTime(date);
			Calendar c2 = Calendar.getInstance();
			long i1 = date.getTime();
			long i2 = Calendar.getInstance().getTimeInMillis();
			long time = (i2 - i1) / 1000;
			int month = (int) (time / 2592000);
			int year = month / 12;
			System.out.println("year=" + year);
			System.out.println("month=" + month);
			System.out.println("time=" + time);
			if (year == 0 && month == 0) {
				if (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {
					String strtime = timeStr.substring(timeStr.indexOf(":") - 2, timeStr.lastIndexOf(":"));
					str.append("Today ");
					str.append(strtime);
				} else if (c1.get(Calendar.DAY_OF_MONTH) == (c2.get(Calendar.DAY_OF_MONTH) - 1)) {
					str.append("Yesterday ");
				} else if (time > 0 && time <= 2592000) {
					try {
						if (timeStr.contains("-")) {
							timeStr = timeStr.substring(0, timeStr.lastIndexOf("-") + 3);
							str.append(timeStr);
						}
					} catch (Exception e) {
					}
				} else {

				}
			} else if (month > 0 && year == 0) {
				str.append(month);
				str.append("Month ago ");
			} else {
				str.append(year);
				str.append("Years ago ");
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return timeStr;
		}
		return str.toString();
	}


	public static boolean isNotNull(String value) {
		return !isNull(value);
	}

	public static Boolean isNull(String value) {
		return value == null || value.trim().length() == 0;
	}



	private static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/*
	 * public static String getStringByURL(String url, List<NameValuePair>
	 * parameters) { // 请求开始时间 long beginTime = System.currentTimeMillis();
	 * SimpleDateFormat datesFormat = new SimpleDateFormat(
	 * "yyyy-MM-dd-HH-mm-ss"); Date date = new Date(beginTime);
	 * 
	 * HttpClient client = new DefaultHttpClient();
	 * client.getParams().setParameter( CoreConnectionPNames.CONNECTION_TIMEOUT,
	 * 60000); client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
	 * 60000); HttpPost post = new HttpPost(url);
	 * 
	 * HttpResponse response = null; HttpEntity entity = null; String
	 * inputStream = null; try { entity = new UrlEncodedFormEntity(parameters);
	 * post.setEntity(entity); response = client.execute(post);
	 * 
	 * 
	 * 
	 * if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	 * inputStream = EntityUtils.toString(response.getEntity(), "gbk"); } else {
	 * } } catch (UnsupportedEncodingException e) { e.printStackTrace(); return
	 * null; } catch (ClientProtocolException e) { e.printStackTrace(); return
	 * null; } catch (IOException e) { e.printStackTrace(); return null; } long
	 * endTime = System.currentTimeMillis(); SimpleDateFormat datesFormats = new
	 * SimpleDateFormat( "yyyy-MM-dd-HH-mm-ss"); Date dates = new Date(endTime);
	 * return inputStream; }
	 */




	/**
	 * 删除文件夹
	 * 
	 * @return boolean
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * 删除文件夹里面的所有文件
	 * 
	 * @param path
	 *            String 文件夹路径
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
			}
		}
	}



	/**
	 * 
	 * @Title: getResolutionWidth
	 * @Description: 获得手机的分辨率 宽度
	 * @param context
	 * @return
	 */
	public static int getResolutionWidth(Activity context) {
		int result = 480;
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		result = dm.widthPixels;
		return result;
	}
	
	public static int dip2px(Context context,float dpValue){
		final float scale = context.getResources().getDisplayMetrics().density;
		return(int)(dpValue*scale+0.5f);
	}


	/**
	 * 根据参数生成MD5签名, 获得签名 注：方法中参数paramMap即参数键值对的Map存储，参数secret即应用的Secret Key。
	 * 
	 * @param paramMap
	 * @param secret
	 * @return
	 */
	private static String getSignature(Map<String, String> paramMap, String secret) {
		List<String> paramList = new ArrayList<String>(paramMap.size());
		List<String> keyList = new ArrayList<String>(paramMap.size());
		// 1、参数格式化
		for (Entry<String, String> param : paramMap.entrySet()) {
			keyList.add(param.getKey());
		}
		// 2、排序并拼接成一个字符串
		StringBuffer buffer = new StringBuffer();
		Collections.sort(keyList);
		for (String k : keyList) {
			String v = paramMap.get(k);
			paramList.add(k + "=" + v);
			if (TextUtils.isEmpty(v)) {
//				System.out.println("k=" + k);
				continue;
			}
			buffer.append(k + "=" + v);
		}
		// 3、追加script key
		buffer.append(secret);
		// 4、将拼好的字符串转成MD5值
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			StringBuffer result = new StringBuffer();
			try {
				for (byte b : md.digest(buffer.toString().getBytes("UTF-8"))) {
					result.append(Integer.toHexString((b & 0xf0) >>> 4));
					result.append(Integer.toHexString(b & 0x0f));
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				for (byte b : md.digest(buffer.toString().getBytes())) {
					result.append(Integer.toHexString((b & 0xf0) >>> 4));
					result.append(Integer.toHexString(b & 0x0f));
				}
			}
			return result.toString();

		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return null;
	}


	/**
	 * 正则表达式判定
	 * 
	 * @param syntax
	 * @param content
	 * @return
	 */
	public static boolean getPatternSyntax(String syntax, String content) {
		Pattern pattern = Pattern.compile(syntax);
		Matcher matcher = pattern.matcher(content);
		return matcher.find();
	}

	/**
	 * 判断邮箱格式正确
	 * 
	 * @return boolean
	 */
	public static boolean matcherEmail(String email) {
		String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern pt = Pattern.compile(regex);
		Matcher mc = pt.matcher(email);
		return !mc.matches();
	}

	/**
	 * 根据性别编号来返回性别
	 * 
	 * @param sexId
	 * @return
	 */
	public static String parseSexBySexId(String sexId) {
		// 1:男，2：女，0：未指定
		try {

			switch (Integer.parseInt(sexId)) {
			case 1:
				return "男";
			case 2:
				return "女";
			default:
				return "未指定";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "未指定";
		}
	}


	//保留两位小数
	public static String getRounded2Num(Float num){
		 return new DecimalFormat("#0.00").format(num);
	}
	
	//保留两位小数
	public static String getRounded2Num(String num){
		return new DecimalFormat("#0.00").format(Float.parseFloat(num));
	}
	public static String getRounded2Num(int num){
		return getRounded2Num(num+"");
	}
	
	public static String getUTF8StringFromGBKString(String gbkStr) {  
        try {  
            return new String(getUTF8BytesFromGBKString(gbkStr), "UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            throw new InternalError();  
        }  
    }  
      /** 
       * GBK转为  utf-8
       * @param gbkStr
       * @return
       */
    public static byte[] getUTF8BytesFromGBKString(String gbkStr) {  
        int n = gbkStr.length();  
        byte[] utfBytes = new byte[3 * n];  
        int k = 0;  
        for (int i = 0; i < n; i++) {  
            int m = gbkStr.charAt(i);  
            if (m < 128 && m >= 0) {  


                utfBytes[k++] = (byte) m;  
                continue;  
            }  
            utfBytes[k++] = (byte) (0xe0 | (m >> 12));  
            utfBytes[k++] = (byte) (0x80 | ((m >> 6) & 0x3f));  
            utfBytes[k++] = (byte) (0x80 | (m & 0x3f));  
        }  
        if (k < utfBytes.length) {  
            byte[] tmp = new byte[k];  
            System.arraycopy(utfBytes, 0, tmp, 0, k);  
            return tmp;  
        }  
        return utfBytes;  
    } 
    
    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.PNG, 100, output);
		if (needRecycle) {
			bmp.recycle();
		}

		byte[] result = output.toByteArray();
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
    
    /**
     * 判断是否包含中文
     * @param str
     * @return
     */
	public static boolean isChineseChar(String str) {
		boolean temp = false;
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			temp = true;
		}
		return temp;
	}
	
	/**
	 * 通过包名 在应用商店打开应用
	 */
	public static boolean openApplicationMarket(Context context) {
	    try {
	    	String packageName = BaseApplication.getAppContext().getPackageName();
	        String packageNameUrl = "market://details?id=" + packageName;
	        Intent localIntent = new Intent(Intent.ACTION_VIEW);
	        localIntent.setData(Uri.parse(packageNameUrl));
	        context.startActivity(localIntent);
	        return true;
	    } catch (Exception e) {
	        // 打开应用商店失败 可能是没有手机没有安装应用市场
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static int getScreenWidth(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		// 获取屏幕信息
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	public static int getSreenHeight(Activity activity, boolean hasStatusBar) {
		int heightPixels = BaseApplication.getAppContext().getResources().getDisplayMetrics().heightPixels;
		if (!hasStatusBar) {
			return heightPixels - getStatusBarHeight();
		}
		return heightPixels;
	}

	/**
	 * 获取状态栏的高度
	 * 
	 * @return
	 */
	public static int getStatusBarHeight() {
		int result = 0;
		int resourceId = BaseApplication.getAppContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = BaseApplication.getAppContext().getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
	
	/**
	 * 获取软件版本号
	 * 
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context) {
		int versionCode = 0;
		// 获取软件版本号，对应AndroidManifest.xml
		try {
			versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}
	
	public static String getVersionName(Context context) {
		String versionName = "";
		// 获取软件版本号，对应AndroidManifest.xml
		try {
			versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionName;
	}
}

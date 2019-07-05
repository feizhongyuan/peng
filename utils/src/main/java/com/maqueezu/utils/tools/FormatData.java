package com.maqueezu.utils.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatData {
	public static final String yyyyMMdd = "yyyyMMdd";
	public static final String yyyy_MM_dd = "yyyy-MM-dd";
	public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public static final String yyyy_MM_dd_HH_mm_ss_1 = "yyyy-MM-dd HH:mm:ss";
	public static final String yyyy_MM_dd_HH_mm_ss = "yyyy/MM/dd HH:mm:ss";

	/**
	 * 使用指定的时间获取格式化时间字符串
	 * 
	 * @param template
	 * @param date
	 * @return
	 */
	public static String getFoematDate(String template, Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(template, Locale.getDefault());
		return dateFormat.format(date);
	}

	/**
	 * 使用当前的时间获取格式化时间字符串
	 * 
	 * @param template
	 * @param date
	 * @return
	 */
	public static String getFoematDate(String template) {
		return getFoematDate(template, new Date());
	}

	/**
	 * 使用时间格式字符串来从字符串解析出date类型
	 * @param DateValue
	 * @param format
	 * @return
	 */
	public static Date parseString(String DateValue, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format,Locale.getDefault());
		Date parse = null;
		try {
			parse = dateFormat.parse(DateValue);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parse;
	}
}

package com.maqueezu.utils;

import java.lang.Thread.UncaughtExceptionHandler;


/**
 * 
*    
* 项目名称：CEB_insurance   
* 类名称：MyExceptionHandler   
* 类描述：   整个app未抓取异常的处理者，保存异常信息，同时做出相应响应，提升用户体验
* 创建人：txl   
* 创建时间：2014-4-16 下午12:54:55   
* 修改人：txl   
* 修改时间：2014-4-16 下午12:54:55   
* 修改备注：   
* @version    
*
 */
public class MyBaseExceptionHandler implements UncaughtExceptionHandler {
	/** 系统默认的UncaughtException处理类 */
    private UncaughtExceptionHandler mDefaultHandler;
    
    public MyBaseExceptionHandler(){
    	mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }
	
	/**
	 * 处理未抓到的异常
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		
		handleException(ex);
	}
	
	private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        ex.printStackTrace();
		System.exit(0);
        
        return true;
    }
}

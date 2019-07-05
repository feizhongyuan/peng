package com.maqueezu.el;

import com.maqueezu.utils.interf.Cons;

/**
 * Created by admin on 2019/5/5 0005.
 *
 * App运行配置参数，app modle中的build.gradle 中配置
 */
public class Constant implements Cons{

    private final static boolean debug = BuildConfig.isDebug;//debug模式
    private final static String host = BuildConfig.Host;//主页url
    private final static String webPageUrl = BuildConfig.webPageUrl;//web页Url
    private final static String channelid = BuildConfig.channelid;//渠道id
    private final static String secret = BuildConfig.secret;//机密

    private static Constant cons;

    private Constant() {
    }

    public static Cons getCons(){
        if(cons == null){
            synchronized (Constant.class){
                if(cons == null){
                    cons = new Constant();
                }
            }
        }
        return cons;
    }

    @Override
    public  boolean isDebug() {
        return debug;
    }
    @Override
    public  String getHost() {
        return host;
    }
    @Override
    public  String getChannelid() {
        return channelid;
    }
    @Override
    public  String getSecret() {
        return secret;
    }

    @Override
    public String getWebPageUrl() {
        return webPageUrl;
    }


//    静态常量参数区
    public static class HTTP{
        public static final String AD_ACID = "24";
        public static final String CODE = "111111";
        public static final String VALID = "1";
    }

}

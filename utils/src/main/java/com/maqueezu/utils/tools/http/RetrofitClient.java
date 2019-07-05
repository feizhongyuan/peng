package com.maqueezu.utils.tools.http;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maqueezu.utils.BaseApplication;
import com.maqueezu.utils.tools.FormatData;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 传递不同的请求地址，创建不同的RetrofitClient
 * <p/>
 * Created by xianlai on 2017/1/12 0012.
 */
public class RetrofitClient {

    //设定了debug 和 release 状态下各种超时时间
    private static final int TIMEOUT_READ = BaseApplication.getCons().isDebug()?30:60;
    private static final int TIMEOUT_CONNECTION = BaseApplication.getCons().isDebug()?30:60;
    private static final int TIMEOUT_WRITE = BaseApplication.getCons().isDebug()?30:60;

    private static Map<String, Retrofit> maps = new HashMap<>();

    public static Retrofit create(String baseUrl,boolean isDebug, Interceptor[] interceptors) {
        Retrofit retrofit = getRetrofit(baseUrl);

        if (retrofit == null) {
            //自定义Gson对象  调整json里面的一些格式，比如，Date Format。
            Gson gson = new GsonBuilder().setDateFormat(FormatData.yyyy_MM_dd_HH_mm_ss_1).create();
            URL url = null;
            try {
                url = new URL(baseUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            String  cachePath = url.getAuthority();
            OkHttpClient okHttpClient = createClient(isDebug, cachePath, interceptors);
            retrofit = new Retrofit.Builder()
                    //设置OKHttpClient
                    .client(okHttpClient)

                    //baseUrl
                    .baseUrl(url.getProtocol()+"://"+url.getAuthority()+"/")

                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //gson转化器
                    .addConverterFactory(GsonConverterFactory.create(gson))

                    .build();

            maps.put(baseUrl, retrofit);
        }
        return retrofit;
    }


    private static OkHttpClient createClient(boolean isDebug, String cachePath, Interceptor[] interceptors) {
        //打印请求Log
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();

        if (isDebug) {
            //debug 模式下开启日志
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            //非debug 模式下关闭日志
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        //缓存目录
        File cacheFile = new File(cachePath, "netWorkCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 10);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()

                //必须是设置Cache目录
                .cache(cache)
                //失败重连
                .retryOnConnectionFailure(true)
                //read time out
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                //write time out
                .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
                //connect time out
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS);
        //设置拦截器
        if (interceptors != null && interceptors.length > 0) {
            for (int i = 0; i < interceptors.length; i++) {
                Interceptor interceptor = interceptors[i];
                if (interceptor == null) {
                    continue;
                }
                builder.addInterceptor(interceptor);
            }
        }
        //设置 Stetho 调试开关
        if(BaseApplication.getCons().isDebug()){
            builder.addNetworkInterceptor(new StethoInterceptor());
        }
        //打印请求log
        builder.addInterceptor(logInterceptor);
        return builder.build();
    }

    public static Retrofit getRetrofit(String baseUrl){
        return maps.get(baseUrl);
    }
}

package com.maqueezu.utils.tools.http.interceptor;




import com.maqueezu.utils.tools.http.ApiFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求拦截器
 *
 * 公共的请求头、请求参数、api签名操作等 都需要在ApiType 里写好，然后再这里进行追加
 *
 */
public class BasicParamsInterceptor implements Interceptor {



    private ApiFactory.ApiType apiType;

    public BasicParamsInterceptor(ApiFactory.ApiType apiType){
        this.apiType = apiType;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();

        // 重新创建请求,添加参数
        Request request = addHeader(builder).build();
        Response response = chain.proceed(request);

        return response;
    }


    private Request.Builder addHeader(Request.Builder builder){
        Map<String, String> headerParam = apiType.getHeaderParam();
        if(headerParam == null || headerParam.size() < 1){
            return builder;
        }

        Set<String> keySet = headerParam.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            String value = headerParam.get(next);
            builder.addHeader(next,value);
        }
        return builder;
    }
}


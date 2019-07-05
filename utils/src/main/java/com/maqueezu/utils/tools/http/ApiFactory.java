package com.maqueezu.utils.tools.http;


import com.maqueezu.utils.BaseApplication;
import com.maqueezu.utils.tools.http.interceptor.BasicParamsInterceptor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

/**
 * 请求创建工厂
 */
public class ApiFactory {

    /**
     * 当前枚举代表不同的Rest api
     *
     * 很多情况下，一个客户端不止连接一种api类型，这些api请求结构可能不同，响应结果结构也可能不同，
     * 所以需要根据不同的api来做对应的请求数据打包、响应数据解包处理
     * 当前这里仅有def一种示例类型
     */
    public static enum ApiType{
        def;
        private String url = "";
        public void setUrl(String url){
            this.url = url;
        }


        /**
         * 获取对应api的地址
         * @return
         */
        public String getHost(){
            if(this == def){
                return BaseApplication.getCons().getHost();
            }
            return "";
        }

        /**
         * 创建对应api的请求
         * @param clz
         * @param <T>
         * @return
         */
        public  <T> T getApi(Class<T> clz){
            boolean isDebug = BaseApplication.getCons().isDebug();
            if(this == def){
                return ApiFactory.getApi(isDebug, clz, ApiType.def);
            }
            return null;
        }

        /**
         * 获取对应api可能需要的请求头，一些api将签名放在了请求头中，可以在这里根据不同的请求统一创建请求头
         * @return
         */
        public Map<String,String> getHeaderParam(){
            Map<String,String> headerParam = new HashMap<>();

            if(this == def){
                headerParam.put("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
            }
//            这个请求头设置请求体是json数据，看实际api是否需要
//            else if(this == ca){
//                headerParam.put("Content-Type","application/json;charset=UTF-8");
//            }
            return headerParam;
        }

        /**
         * 获取各个api的公用请求体，可以在这里根据api要求做相应的签名操作
         *
         * @return
         */
        public RequestBody getRequestBody(Map<String,String> parmMap) {

            //组合公共参数
            Map<String,String> mapParm = getParmMap(parmMap);

            if(this == def){
                FormBody.Builder builder = new FormBody.Builder();
                Iterator<String> iterator = mapParm.keySet().iterator();
                while (iterator.hasNext()){
                    String key = iterator.next();
                    String value = mapParm.get(key);
                    builder.addEncoded(key,value);
                }
                return builder.build();
            }
//            这里是创建一个json请求体，看实际api是否需要
//            else if( this == ca){
//                JSONObject requestData = new JSONObject();
//                RequestBody requestBody = null;
//                try {
//                    Set<String> keySet = mapParm.keySet();
//                    Iterator<String> iterator = keySet.iterator();
//                    while (iterator.hasNext()) {
//                        String key = iterator.next();
//                        String value = mapParm.get(key);
//                        requestData.put(key, value);
//                    }
//                    requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                return requestBody;
//            }
            return null;
        }

        /**
         * 返回公共参数
         * @return
         */
        private static Map<String,String> getParmMap(Map<String,String> parm){

            return parm;
        }

    }


    /**
     * 创建 Api
     *
     * @param isDebug      是否debug
     * @param clz          模型字节码
     * @param <T>
     * @return
     */
    public static <T> T getApi( boolean isDebug, Class<T> clz, ApiType apiType) {
        Interceptor[] interceptors = null;
        Retrofit retrofit = RetrofitClient.getRetrofit(apiType.getHost());
        if(retrofit == null){
            interceptors = new Interceptor[]{ new BasicParamsInterceptor(apiType)};
            retrofit = RetrofitClient.create(apiType.getHost(), isDebug, interceptors);
        }
        return retrofit.create(clz);
    }
}

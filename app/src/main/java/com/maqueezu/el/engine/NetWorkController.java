package com.maqueezu.el.engine;



import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.pojo.DefApiModel;
import com.maqueezu.el.pojo.UserModel;
import com.maqueezu.utils.tools.http.ApiFactory;
import com.maqueezu.utils.tools.http.OnResultListener;
import com.maqueezu.utils.tools.http.interceptor.ApiCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 业务网络请求处理类
 * <p/>
 * Created by xianlai on 2017/1/13 0013.
 * Android官网不建议使用enums，
 * 占用内存多（Enums often require more than twice as much memory as static constants.
 * You should strictly avoid using enums on Android.）
 */
public enum NetWorkController {
    INSTANCE;


    /**
     * 利用登录演示了 请求的整个流程
     * @param username
     * @param password
     * @param listener  为null则同步，非null则异步，看实际业务需求来修改
     * @return
     */
    public  UserModel loginTest(String username,String password,OnResultListener<UserModel> listener) {
        Api.Api1 api = ApiFactory.ApiType.def.getApi(Api.Api1.class);
        Call<UserModel> call = api.get(username,password);

        if(listener == null){
            try{
                Response<UserModel> response = call.execute();
                return response.body();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            call.enqueue(new ApiCallback<>(call, UserModel.class, listener));
        }
        return null;
    }


    /**
     * 同样模拟了登录流程，这里是传递 json 参数进行登录
     *
     * @param
     */
    public void cAUserByPassport(OnResultListener<UserModel> lis) {
//        Api.Api2 api = ApiFactory.ApiType.ca.getApi(Api.Api2.class);
//        Map<String,String> map = new HashMap<>();
//
//        RequestBody requestBody = ApiFactory.ApiType.ca.getRequestBody(map);
//        Call<UserModel> call = api.get(requestBody);
//        call.enqueue(new ApiCallback<>(call, UserModel.class, lis));
    }


    /**
     * 上传ca签章图片到应用服务器
     * @param msspID
     * @param signImage    图片的base64格式
     * @param lis
     */
    public void saveUserSignImage(String msspID,String signImage,OnResultListener<DefApiModel> lis) {
        Api.uploadImageApi api = ApiFactory.ApiType.def.getApi(Api.uploadImageApi.class);
        Map<String,String> map = new HashMap<>();
        map.put("msspID",msspID);
        map.put("signImage",signImage);

        RequestBody requestBody = ApiFactory.ApiType.def.getRequestBody(map);
        Call<DefApiModel> call = api.get(requestBody);
        call.enqueue(new ApiCallback<>(call, DefApiModel.class, lis));
    }

//    广告图
//    public AdvertBean LoadAdInfo(String acid, OnResultListener<AdvertBean> listener){
//        Api.getAdInfo api = ApiFactory.ApiType.def.getApi(Api.getAdInfo.class);
//        Map<String,String> map = new HashMap<>();
//        map.put("acid",acid);
////        Call<AdvertBean> adPhoto = api.getAdPhoto(acid);
////
////        if(listener == null){
////            try{
////                Response<AdvertBean> response = adPhoto.execute();
////                return response.body();
////            }catch (Exception e){
////                e.printStackTrace();
////            }
////        }else{
////            adPhoto.enqueue(new ApiCallback<>(adPhoto,AdvertBean.class,listener));
////        }
////        return null;
//
//
//        api.getAdPhoto(map).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<AdvertBean>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(AdvertBean advertBean) {
//                        listener.onResult(advertBean);
//                    }
//                });
//
//
//    }

}

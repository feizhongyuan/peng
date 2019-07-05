package com.maqueezu.el.engine;

import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.pojo.CodeBean;
import com.maqueezu.el.pojo.DefApiModel;
import com.maqueezu.el.pojo.GoodsCatBean;
import com.maqueezu.el.pojo.UserBean;
import com.maqueezu.el.pojo.UserInfoBean;
import com.maqueezu.el.pojo.UserModel;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by admin on 2019/5/6 0006.
 */

public class Api {

    /**
     * 这是对post提交键值对参数 api 的一种抽象描述
     */
    public interface Api1 {
        @FormUrlEncoded
        @POST("api/xxxx/xxxxx")
        Call<UserModel> get(@Field("username") String username, @Field("password") String password);
    }


    /**
     * 这是对post提交json参数 api 的一种抽象描述
     */
    public interface Api2 {
        @POST("api/xxxx/xxxxx")
        Call<UserModel> get(@Body RequestBody requestBody);
    }


    /**
     * 上传图片到应用服务器
     */
    public interface uploadImageApi {
        @POST("api/xxxx/xxxxxxx")
        Call<DefApiModel> get(@Body RequestBody requestBody);
    }


    public interface getUserInfo {
        //登录接口  POST /api/mobile/member/login-app.do
        @FormUrlEncoded
        @POST("/api/mobile/member/login-app.do")
        Observable<UserBean> getUserInfo(@Field("username") String username, @Field
                ("password") String pwd);

//        获取验证码 POST /api/mobile/member/send-login-code.do
        @FormUrlEncoded
        @POST("/api/mobile/member/send-login-code.do")
        Observable<CodeBean> getCode(@Field("mobile")String mobile, @Field("code")String code);

//       使用验证码登录 POST /api/mobile/member/login.do
        @FormUrlEncoded
        @POST("/api/mobile/member/login.do")
        Observable<UserInfoBean> LoginCode(@FieldMap Map<String,String> map);

    }

    /**
     * 广告位
     */

    public interface getAdInfo {
        //    http://www.maqueezu.com/api/mobile/adv/adv-list.do   获取广告列表
        @POST("api/mobile/adv/adv-list.do")
        Observable<AdvertBean> getAdPhoto(@QueryMap Map<String, String> map);
//        @POST("/api/mobile/adv/adv-list.do")
//        Call<AdvertBean> getAdPhoto(@Query("acid") String acid);
    }

    /**
     *  商品（套餐）
     */
    public interface getGoodsCatList{
//        POST /api/mobile/goodscat/list.do      获取商品分类
        @POST("/api/mobile/goodscat/list.do")
        Observable<GoodsCatBean> getGoodsListInfo();
    }


//    //    获取广告信息   /api/mobile/adv/get-one-adv.do
//    @POST("/api/mobile/adv/get-one-adv.do")
//    Call<T> getAdInfo(@Query("advid") String advid);
//
//    //   获取分类文章详情  /api/mobile/cms/detail.do
//    @POST("/api/mobile/cms/detail.do")
//    Call<T> getAdEssay(@Query("catid") String catid, @Query("articleid") String articleid);
//
//    //   获取分类ID对应文章列表  /api/mobile/cms/list.do
//    @POST("/api/mobile/cms/list.do")
//    Call<T> getAdEssayList(@Query("catid") String catid);
//
//
//    /**
//     * 套餐
//     */
//
////    甄选套餐 GET /api/mobile/medical/findHasMedicalList.do
//    @GET("/api/mobile/medical/findHasMedicalList.do")
//    Observable<SetmealBean> getSetmealList(@QueryMap Map<String,String> map);
//
//    //    套餐详情 GET /api/mobile/medical/getMedical.do
//    @GET("/api/mobile/medical/getMedical.do")
//    Observable<SetmealDetailBean> getSetmealDetail(@Query("id") String id);
//
//    //    甄选机构 GET /api/mobile/medical/findHasOrganList.do
//    @GET("/api/mobile/medical/findHasOrganList.do")
//    Observable<MechanismBean> getMechanismList(@QueryMap Map<String,String> map);


//
//    /**
//     * 商品
//     */
//
//    //    商品属性 POST /api/mobile/goods/attr-list.do
//    @POST("/api/mobile/goods/attr-list.do")
//    Call<T> getGoodsAttribute(@Query("id") String id);
//
//    //    商品评论 POST /api/mobile/goods/comment.do    商品id id,类型 type, 页码 page
//    @POST("/api/mobile/goods/comment.do")
//    Call<T> getGoodsReview();
//
//    //    商品详细 POST /api/mobile/goods/detail.do
//    @POST("/api/mobile/goods/detail.do")
//    Call<T> getGoodsDetail(@Query("id")String id, @Query("colorname")String colorname);
//
//    //   商品相册 POST /api/mobile/goods/gallery.do
//    @POST("/api/mobile/goods/gallery.do")
//    Call<T> getGoodsPhoto(@Query("id")String id);
//
//    //   根据标签获得商品列表 POST /api/mobile/goods/list-by-tag.do  分类id catid,标签id tagid,数量 goodsnum
//    @POST("/api/mobile/goods/list-by-tag.do")
//    Call<T> getGoodPhoto(@QueryMap Map<String,String> map);
//
//    //   获得商品列表 POST /api/mobile/goods/list.do
//    @POST("/api/mobile/goods/list.do")
//    Call<T> getGoodsList();
//
//    //   签获得商品列表查询 POST /api/mobile/goods/listgoods.do 分类id cat,品牌 brand, 关键字 keyword,页码 page
//    @POST("/api/mobile/goods/listgoods.do")
//    Call<T> getGoodsListQuiry(@QueryMap Map<String,String> map);
//
//    //   商品参数 POST /api/mobile/goods/parame-list.do
//    @POST("/api/mobile/goods/parame-list.do")
//    Call<T> getGoodsParameter(@Query("id")String id);
//
//    //   搜索商品 POST /api/mobile/goods/search.do  分类id catid，设计师id store_id，商品名称 goods_name，页码 page
//    @POST("/api/mobile/goods/search.do")
//    Call<T> getSearchGoods(@QueryMap Map<String,String> map);
//
//    //   商品规格 POST /api/mobile/goods/spec.do
//    @POST("/api/mobile/goods/spec.do")
//    Call<T> getGoodPhoto(@Query("id")String id);
//
//
//
//
//
//
//
//    //    天气 GET /api/mobile/config/weather.do
//    @GET("/api/mobile/config/weather.do")
//    Observable<WeatherBean> getWeatherInfo(@QueryMap Map<String,String> map);
//


}

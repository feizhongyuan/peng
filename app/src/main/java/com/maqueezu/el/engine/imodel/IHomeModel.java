package com.maqueezu.el.engine.imodel;

import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.pojo.GoodsCatBean;
import com.maqueezu.utils.tools.http.OnResultListener;

/**
 * Created by fei .
 * Created by Date 2019/5/31 10:12
 */

public interface IHomeModel {
//    获取广告轮播
    void LoadAd(OnResultListener<AdvertBean> listener,String acid);

//    获取商品分类
    void LoadGoodsCatList(OnResultListener<GoodsCatBean> listener);

}

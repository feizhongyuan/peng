package com.maqueezu.el.presenter;

import android.util.Log;

import com.maqueezu.el.Constant;
import com.maqueezu.el.engine.HomeModelImpl;
import com.maqueezu.el.engine.imodel.IHomeModel;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.pojo.GoodsCatBean;
import com.maqueezu.el.ui.iview.HomeContract;
import com.maqueezu.utils.tools.LogUtil;
import com.maqueezu.utils.tools.http.OnResultListener;
import com.maqueezu.utils.tools.presenter.BasePresenter;

/**
 * Created by fei .
 * Created by Date 2019/5/31 9:58
 */

public class HomePresenter<T extends HomeContract.View> extends BasePresenter<T> {
//      model引用
    private IHomeModel homeModel = new HomeModelImpl();

//     初始化
    public HomePresenter() {
    }

    public void fetchData(){
        if (mViewRef.get() != null){
            if (homeModel != null){
//                获取广告
                homeModel.LoadAd(new OnResultListener<AdvertBean>() {
                    @Override
                    public void onResult(AdvertBean advertBean) {
                        mViewRef.get().getAd(advertBean);
                    }

                    @Override
                    public void onFailse(String s) {
                        LogUtil.print("TAG-fail",s);
                    }
                }, Constant.HTTP.AD_ACID);
//                获取商品分类
                homeModel.LoadGoodsCatList(new OnResultListener<GoodsCatBean>() {
                    @Override
                    public void onResult(GoodsCatBean goodsCatBean) {
                        mViewRef.get().getGoodsCatList(goodsCatBean);
                        LogUtil.print("TAG3",goodsCatBean.toString());
                    }

                    @Override
                    public void onFailse(String s) {
                        LogUtil.print("TAG-fail",s);
                    }
                });

            }
        }
    }
}

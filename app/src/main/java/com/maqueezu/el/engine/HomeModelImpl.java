package com.maqueezu.el.engine;

import com.maqueezu.el.engine.imodel.IHomeModel;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.pojo.GoodsCatBean;
import com.maqueezu.utils.tools.LogUtil;
import com.maqueezu.utils.tools.http.ApiFactory;
import com.maqueezu.utils.tools.http.OnResultListener;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fei .
 * Created by Date 2019/5/31 10:13
 */

public class HomeModelImpl implements IHomeModel {
    @Override
    public void LoadAd(OnResultListener<AdvertBean> listener, String acid) {
        Api.getAdInfo api = ApiFactory.ApiType.def.getApi(Api.getAdInfo.class);
        Map<String,String> map = new HashMap<>();
        map.put("acid",acid);
        api.getAdPhoto(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AdvertBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailse(e.getMessage());
                    }

                    @Override
                    public void onNext(AdvertBean advertBean) {
                        listener.onResult(advertBean);
                    }
                });
    }

    @Override
    public void LoadGoodsCatList(OnResultListener<GoodsCatBean> listener) {
        Api.getGoodsCatList api = ApiFactory.ApiType.def.getApi(Api.getGoodsCatList.class);
        api.getGoodsListInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GoodsCatBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailse(e.getMessage());
                    }

                    @Override
                    public void onNext(GoodsCatBean goodsCatBean) {
                        listener.onResult(goodsCatBean);
                    }
                });
    }
}

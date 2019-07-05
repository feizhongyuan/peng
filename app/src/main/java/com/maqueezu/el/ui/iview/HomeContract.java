package com.maqueezu.el.ui.iview;

import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.pojo.GoodsCatBean;

/**
 * Created by fei .
 * Created by Date 2019/6/17 9:19
 */

public interface HomeContract {
    interface View{
        //    获取广告
        void getAd(AdvertBean advertBean);

        //   获取商品分类
        void getGoodsCatList(GoodsCatBean goodsCatBean);
    }

    interface Presenter{
        void onViewCreate();
    }
}

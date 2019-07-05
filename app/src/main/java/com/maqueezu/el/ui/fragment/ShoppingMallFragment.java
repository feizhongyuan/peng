package com.maqueezu.el.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.pojo.GoodsCatBean;
import com.maqueezu.el.ui.activity.child.physicalexamination_child.AddCardProcessActivity;
import com.maqueezu.el.ui.activity.child.shopping_child.GoodsSortActivity;
import com.maqueezu.el.ui.activity.child.shopping_child.ShoppingCartActivity;
import com.maqueezu.el.ui.activity.child.shopping_child.ThroughTrainActivity;
import com.maqueezu.el.ui.adapter.SetmealAdapter;
import com.maqueezu.utils.ui.base.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 商城模块
 */
public class ShoppingMallFragment extends BaseFragment implements View.OnClickListener, OnBannerListener, AdapterView.OnItemClickListener {


    private View rootView;
    private Banner mBanner_shoppingMall;//轮播
    private ImageView img_jiankangyundong;
    private TextView tv_jiankangyundong;
    private AutoRelativeLayout rl_jiankangyundong;//健康运动
    private ImageView img_muyinghuli;
    private TextView tv_muyinghuli;
    private AutoRelativeLayout rl_muyinghuli;//母婴护理
    private ImageView img_jiankangshebei;
    private TextView tv_jiankangshebei;
    private AutoRelativeLayout rl_jiankangshebei;//健康设备
    private ImageView img_shipinbaojian;
    private TextView tv_shipinbaojian;
    private AutoRelativeLayout rl_shipinbaojian;//食品保健
    private AutoLinearLayout ll_base_1;
    private ImageView img_jiankangjiaju;
    private TextView tv_jiankangjiaju;
    private AutoRelativeLayout rl_jiankangjiaju;//健康家居
    private ImageView img_meizhuangxihu;
    private TextView tv_meizhuangxihu;
    private AutoRelativeLayout rl_meizhuangxihu;//美妆洗护
    private ImageView img_jiankangbaoxian;
    private TextView tv_jiankangbaoxian;
    private AutoRelativeLayout rl_jiankangbaoxian;//健康保险
    private ImageView img_fenlei;
    private TextView tv_fenlei;
    private AutoRelativeLayout rl_fenlei;//分类
    private AutoLinearLayout ll_base_2;
    private TextView maqueyoupinchengnuo;
    private AutoRelativeLayout rl_base_1;
    private TextView mTv_gengduo_remaituijian;//热卖推荐-更多
    private AutoRelativeLayout rl_remaituijian;
    private ImageView img_remaituijian_1;
    private ImageView img_remaituijian_2;
    private TextView tv_remaituijian_2_name;
    private TextView tv_remaituijian_2_price;
    private ImageView img_remaituijian_3;
    private TextView tv_remaituijian_3_name;
    private TextView tv_remaituijian_3_price;
    private ImageView img_remaituijian_4;
    private TextView tv_remaituijian_4_name;
    private TextView tv_remaituijian_4_price;
    private AutoRelativeLayout rl_base_2;
    private TextView mTv_gengduo_pinzhishenghuo;//品质生活-更多
    private AutoRelativeLayout rl_pinzhishenghuo;
    private ImageView img_pinzhishenghuo;
    private ImageView img_pinzhishenghuo_1;
    private TextView tv_pinzhishenghuo_1_name;
    private TextView tv_pinzhishenghuo_1_price;
    private ImageView img_pinzhishenghuo_2;
    private TextView tv_pinzhishenghuo_2_name;
    private TextView tv_pinzhishenghuo_2_price;
    private ImageView img_pinzhishenghuo_3;
    private TextView tv_pinzhishenghuo_3_name;
    private TextView tv_pinzhishenghuo_3_price;
    private AutoRelativeLayout rl_base_3;
    private TextView mTv_gengduo_pinzhishenghuo_1;//品质生活1-更多
    private AutoRelativeLayout rl_pinzhishenghuo_1;
    private ImageView img_pinzhishenghuo_;
    private RecyclerView mRecycler_pinzhishenghuo;
    private AutoRelativeLayout rl_base_4;
    private ImageView img_shoppingCart;//购物车
    private TextView tv_shoppingCart_num;//购物车数量
    private AutoLinearLayout shoppingCart_num_background;
    private AutoRelativeLayout rl_shoppingCart;

    private TextView tv_chakanxiangqing;
    private List<String> list_path;
    private List<String> list_title;
    private AdvertBean.DataBean data;
    private List<AdvertBean.DataBean.AdvListBean> advList;

    private GoodsCatBean goodsCatBean;

    public ShoppingMallFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        initView(rootView);
        EventBus.getDefault().register(this);

        return rootView;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_shopping_mall;
    }

    @Override
    protected void initView(View mRootView) {
        mBanner_shoppingMall = (Banner) rootView.findViewById(R.id.mBanner_shoppingMall);
        mBanner_shoppingMall.setOnClickListener(this);
        img_jiankangyundong = (ImageView) rootView.findViewById(R.id.img_jiankangyundong);
        img_jiankangyundong.setOnClickListener(this);
        tv_jiankangyundong = (TextView) rootView.findViewById(R.id.tv_jiankangyundong);
        tv_jiankangyundong.setOnClickListener(this);
        rl_jiankangyundong = (AutoRelativeLayout) rootView.findViewById(R.id.rl_jiankangyundong);
        rl_jiankangyundong.setOnClickListener(this);
        img_muyinghuli = (ImageView) rootView.findViewById(R.id.img_muyinghuli);
        img_muyinghuli.setOnClickListener(this);
        tv_muyinghuli = (TextView) rootView.findViewById(R.id.tv_muyinghuli);
        tv_muyinghuli.setOnClickListener(this);
        rl_muyinghuli = (AutoRelativeLayout) rootView.findViewById(R.id.rl_muyinghuli);
        rl_muyinghuli.setOnClickListener(this);
        img_jiankangshebei = (ImageView) rootView.findViewById(R.id.img_jiankangshebei);
        img_jiankangshebei.setOnClickListener(this);
        tv_jiankangshebei = (TextView) rootView.findViewById(R.id.tv_jiankangshebei);
        tv_jiankangshebei.setOnClickListener(this);
        rl_jiankangshebei = (AutoRelativeLayout) rootView.findViewById(R.id.rl_jiankangshebei);
        rl_jiankangshebei.setOnClickListener(this);
        img_shipinbaojian = (ImageView) rootView.findViewById(R.id.img_shipinbaojian);
        img_shipinbaojian.setOnClickListener(this);
        tv_shipinbaojian = (TextView) rootView.findViewById(R.id.tv_shipinbaojian);
        tv_shipinbaojian.setOnClickListener(this);
        rl_shipinbaojian = (AutoRelativeLayout) rootView.findViewById(R.id.rl_shipinbaojian);
        rl_shipinbaojian.setOnClickListener(this);
        ll_base_1 = (AutoLinearLayout) rootView.findViewById(R.id.ll_base_1);
        ll_base_1.setOnClickListener(this);
        img_jiankangjiaju = (ImageView) rootView.findViewById(R.id.img_jiankangjiaju);
        img_jiankangjiaju.setOnClickListener(this);
        tv_jiankangjiaju = (TextView) rootView.findViewById(R.id.tv_jiankangjiaju);
        tv_jiankangjiaju.setOnClickListener(this);
        rl_jiankangjiaju = (AutoRelativeLayout) rootView.findViewById(R.id.rl_jiankangjiaju);
        rl_jiankangjiaju.setOnClickListener(this);
        img_meizhuangxihu = (ImageView) rootView.findViewById(R.id.img_meizhuangxihu);
        img_meizhuangxihu.setOnClickListener(this);
        tv_meizhuangxihu = (TextView) rootView.findViewById(R.id.tv_meizhuangxihu);
        tv_meizhuangxihu.setOnClickListener(this);
        rl_meizhuangxihu = (AutoRelativeLayout) rootView.findViewById(R.id.rl_meizhuangxihu);
        rl_meizhuangxihu.setOnClickListener(this);
        img_jiankangbaoxian = (ImageView) rootView.findViewById(R.id.img_jiankangbaoxian);
        img_jiankangbaoxian.setOnClickListener(this);
        tv_jiankangbaoxian = (TextView) rootView.findViewById(R.id.tv_jiankangbaoxian);
        tv_jiankangbaoxian.setOnClickListener(this);
        rl_jiankangbaoxian = (AutoRelativeLayout) rootView.findViewById(R.id.rl_jiankangbaoxian);
        rl_jiankangbaoxian.setOnClickListener(this);
        img_fenlei = (ImageView) rootView.findViewById(R.id.img_fenlei);
        img_fenlei.setOnClickListener(this);
        tv_fenlei = (TextView) rootView.findViewById(R.id.tv_fenlei);
        tv_fenlei.setOnClickListener(this);
        rl_fenlei = (AutoRelativeLayout) rootView.findViewById(R.id.rl_fenlei);
        rl_fenlei.setOnClickListener(this);
        ll_base_2 = (AutoLinearLayout) rootView.findViewById(R.id.ll_base_2);
        ll_base_2.setOnClickListener(this);
        maqueyoupinchengnuo = (TextView) rootView.findViewById(R.id.maqueyoupinchengnuo);
        maqueyoupinchengnuo.setOnClickListener(this);
        rl_base_1 = (AutoRelativeLayout) rootView.findViewById(R.id.rl_base_1);
        rl_base_1.setOnClickListener(this);
        mTv_gengduo_remaituijian = (TextView) rootView.findViewById(R.id.mTv_gengduo_remaituijian);
        mTv_gengduo_remaituijian.setOnClickListener(this);
        rl_remaituijian = (AutoRelativeLayout) rootView.findViewById(R.id.rl_remaituijian);
        rl_remaituijian.setOnClickListener(this);
        img_remaituijian_1 = (ImageView) rootView.findViewById(R.id.img_remaituijian_1);
        img_remaituijian_1.setOnClickListener(this);
        img_remaituijian_2 = (ImageView) rootView.findViewById(R.id.img_remaituijian_2);
        img_remaituijian_2.setOnClickListener(this);
        tv_remaituijian_2_name = (TextView) rootView.findViewById(R.id.tv_remaituijian_2_name);
        tv_remaituijian_2_name.setOnClickListener(this);
        tv_remaituijian_2_price = (TextView) rootView.findViewById(R.id.tv_remaituijian_2_price);
        tv_remaituijian_2_price.setOnClickListener(this);
        img_remaituijian_3 = (ImageView) rootView.findViewById(R.id.img_remaituijian_3);
        img_remaituijian_3.setOnClickListener(this);
        tv_remaituijian_3_name = (TextView) rootView.findViewById(R.id.tv_remaituijian_3_name);
        tv_remaituijian_3_name.setOnClickListener(this);
        tv_remaituijian_3_price = (TextView) rootView.findViewById(R.id.tv_remaituijian_3_price);
        tv_remaituijian_3_price.setOnClickListener(this);
        img_remaituijian_4 = (ImageView) rootView.findViewById(R.id.img_remaituijian_4);
        img_remaituijian_4.setOnClickListener(this);
        tv_remaituijian_4_name = (TextView) rootView.findViewById(R.id.tv_remaituijian_4_name);
        tv_remaituijian_4_name.setOnClickListener(this);
        tv_remaituijian_4_price = (TextView) rootView.findViewById(R.id.tv_remaituijian_4_price);
        tv_remaituijian_4_price.setOnClickListener(this);
        rl_base_2 = (AutoRelativeLayout) rootView.findViewById(R.id.rl_base_2);
        rl_base_2.setOnClickListener(this);
        mTv_gengduo_pinzhishenghuo = (TextView) rootView.findViewById(R.id.mTv_gengduo_pinzhishenghuo);
        mTv_gengduo_pinzhishenghuo.setOnClickListener(this);
        rl_pinzhishenghuo = (AutoRelativeLayout) rootView.findViewById(R.id.rl_pinzhishenghuo);
        rl_pinzhishenghuo.setOnClickListener(this);
        img_pinzhishenghuo = (ImageView) rootView.findViewById(R.id.img_pinzhishenghuo);
        img_pinzhishenghuo.setOnClickListener(this);
        img_pinzhishenghuo_1 = (ImageView) rootView.findViewById(R.id.img_pinzhishenghuo_1);
        img_pinzhishenghuo_1.setOnClickListener(this);
        tv_pinzhishenghuo_1_name = (TextView) rootView.findViewById(R.id.tv_pinzhishenghuo_1_name);
        tv_pinzhishenghuo_1_name.setOnClickListener(this);
        tv_pinzhishenghuo_1_price = (TextView) rootView.findViewById(R.id.tv_pinzhishenghuo_1_price);
        tv_pinzhishenghuo_1_price.setOnClickListener(this);
        img_pinzhishenghuo_2 = (ImageView) rootView.findViewById(R.id.img_pinzhishenghuo_2);
        img_pinzhishenghuo_2.setOnClickListener(this);
        tv_pinzhishenghuo_2_name = (TextView) rootView.findViewById(R.id.tv_pinzhishenghuo_2_name);
        tv_pinzhishenghuo_2_name.setOnClickListener(this);
        tv_pinzhishenghuo_2_price = (TextView) rootView.findViewById(R.id.tv_pinzhishenghuo_2_price);
        tv_pinzhishenghuo_2_price.setOnClickListener(this);
        img_pinzhishenghuo_3 = (ImageView) rootView.findViewById(R.id.img_pinzhishenghuo_3);
        img_pinzhishenghuo_3.setOnClickListener(this);
        tv_pinzhishenghuo_3_name = (TextView) rootView.findViewById(R.id.tv_pinzhishenghuo_3_name);
        tv_pinzhishenghuo_3_name.setOnClickListener(this);
        tv_pinzhishenghuo_3_price = (TextView) rootView.findViewById(R.id.tv_pinzhishenghuo_3_price);
        tv_pinzhishenghuo_3_price.setOnClickListener(this);
        rl_base_3 = (AutoRelativeLayout) rootView.findViewById(R.id.rl_base_3);
        rl_base_3.setOnClickListener(this);
        mTv_gengduo_pinzhishenghuo_1 = (TextView) rootView.findViewById(R.id.mTv_gengduo_pinzhishenghuo_1);
        mTv_gengduo_pinzhishenghuo_1.setOnClickListener(this);
        rl_pinzhishenghuo_1 = (AutoRelativeLayout) rootView.findViewById(R.id.rl_pinzhishenghuo_1);
        rl_pinzhishenghuo_1.setOnClickListener(this);
        img_pinzhishenghuo_ = (ImageView) rootView.findViewById(R.id.img_pinzhishenghuo_);
        img_pinzhishenghuo_.setOnClickListener(this);
        mRecycler_pinzhishenghuo = (RecyclerView) rootView.findViewById(R.id.mRecycler_pinzhishenghuo);
        mRecycler_pinzhishenghuo.setLayoutManager(new GridLayoutManager(mActivity, 3));
        rl_base_4 = (AutoRelativeLayout) rootView.findViewById(R.id.rl_base_4);
        rl_base_4.setOnClickListener(this);
        tv_chakanxiangqing = (TextView) rootView.findViewById(R.id.tv_chakanxiangqing);
        tv_chakanxiangqing.setOnClickListener(this);
        img_shoppingCart = (ImageView) rootView.findViewById(R.id.img_shoppingCart);
        img_shoppingCart.setOnClickListener(this);
        tv_shoppingCart_num = (TextView) rootView.findViewById(R.id.tv_shoppingCart_num);
        tv_shoppingCart_num.setOnClickListener(this);
        shoppingCart_num_background = (AutoLinearLayout) rootView.findViewById(R.id.shoppingCart_num_background);
        shoppingCart_num_background.setOnClickListener(this);
        rl_shoppingCart = (AutoRelativeLayout) rootView.findViewById(R.id.rl_shoppingCart);
        rl_shoppingCart.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle arguments) {


    }

    private void initBanner(AdvertBean advert) {
        list_path = new ArrayList<>();
        list_title = new ArrayList<>();
        advList = advert.getData().getAdvList();
        for (int i = 0; i < advList.size(); i++) {
            list_path.add(advList.get(i).getAtturl());
            list_title.add(advList.get(i).getAname());
        }

        mBanner_shoppingMall.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mBanner_shoppingMall.setImageLoader(new MyLoader());
        mBanner_shoppingMall.setBannerAnimation(Transformer.Default);
        mBanner_shoppingMall.setBannerTitles(list_title);
        mBanner_shoppingMall.setDelayTime(3000);
        mBanner_shoppingMall.isAutoPlay(true);
        mBanner_shoppingMall.setIndicatorGravity(BannerConfig.CENTER);
        mBanner_shoppingMall.setImages(list_path)
                .setOnBannerListener(this)
                .start();

    }

    @Override
    protected void initListener() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent1(AdvertBean advert) {
        getAdvert(advert);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent2(GoodsCatBean goodsCatBean) {
        getGoodsCat(goodsCatBean);
    }

    private void getGoodsCat(GoodsCatBean goodsCat) {
        goodsCatBean = goodsCat;
    }

    private void getAdvert(AdvertBean advert) {
        initBanner(advert);
        data = advert.getData();
        mRecycler_pinzhishenghuo.setAdapter(new SetmealAdapter(mActivity, data.getAdvList(), this));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_jiankangyundong:
            case R.id.img_jiankangyundong:
            case R.id.tv_jiankangyundong:
                String s = tv_jiankangyundong.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("name", s);
                bundle.putSerializable("data", data);
                multiplexIntent(mActivity, bundle, ThroughTrainActivity.class);
                break;
            case R.id.rl_muyinghuli:
            case R.id.img_muyinghuli:
            case R.id.tv_muyinghuli:
                String s1 = tv_muyinghuli.getText().toString();
                Bundle bundle1 = new Bundle();
                bundle1.putString("name", s1);
                bundle1.putSerializable("data", data);
                multiplexIntent(mActivity, bundle1, ThroughTrainActivity.class);
                break;
            case R.id.rl_jiankangshebei:
            case R.id.img_jiankangshebei:
            case R.id.tv_jiankangshebei:
                String s2 = tv_jiankangshebei.getText().toString();
                Bundle bundle2 = new Bundle();
                bundle2.putString("name", s2);
                bundle2.putSerializable("data", data);
                multiplexIntent(mActivity, bundle2, ThroughTrainActivity.class);
                break;
            case R.id.rl_shipinbaojian:
            case R.id.img_shipinbaojian:
            case R.id.tv_shipinbaojian:
                String s3 = tv_shipinbaojian.getText().toString();
                Bundle bundle3 = new Bundle();
                bundle3.putString("name", s3);
                bundle3.putSerializable("data", data);
                multiplexIntent(mActivity, bundle3, ThroughTrainActivity.class);
                break;
            case R.id.rl_jiankangjiaju:
            case R.id.img_jiankangjiaju:
            case R.id.tv_jiankangjiaju:
                String s4 = tv_jiankangjiaju.getText().toString();
                Bundle bundle4 = new Bundle();
                bundle4.putString("name", s4);
                bundle4.putSerializable("data", data);
                multiplexIntent(mActivity, bundle4, ThroughTrainActivity.class);
                break;
            case R.id.rl_meizhuangxihu:
            case R.id.img_meizhuangxihu:
            case R.id.tv_meizhuangxihu:
                String s5 = tv_meizhuangxihu.getText().toString();
                Bundle bundle5 = new Bundle();
                bundle5.putString("name", s5);
                bundle5.putSerializable("data", data);
                multiplexIntent(mActivity, bundle5, ThroughTrainActivity.class);
                break;
            case R.id.rl_jiankangbaoxian:
            case R.id.img_jiankangbaoxian:
            case R.id.tv_jiankangbaoxian:
                String s6 = tv_jiankangbaoxian.getText().toString();
                Bundle bundle6 = new Bundle();
                bundle6.putString("name", s6);
                bundle6.putSerializable("data", data);
                multiplexIntent(mActivity, bundle6, ThroughTrainActivity.class);
                break;
            case R.id.rl_fenlei:
            case R.id.img_fenlei:
            case R.id.tv_fenlei:
                Bundle bundle7 = new Bundle();
                bundle7.putString("name", "全部分类");
                bundle7.putSerializable("data", goodsCatBean);
                multiplexIntent(mActivity, bundle7, GoodsSortActivity.class);
                break;
            case R.id.tv_chakanxiangqing://查看详情
                Intent intent1 = new Intent(mActivity,AddCardProcessActivity.class);
                Bundle bundle8 = new Bundle();
                bundle8.putString("name", "商城承诺");
                intent1.putExtras(bundle8);
                startActivity(intent1);
                break;
            case R.id.mTv_gengduo_remaituijian:
                Bundle bundle9 = new Bundle();
                bundle9.putString("name", "热卖推荐");
                bundle9.putSerializable("data", data);
                multiplexIntent(mActivity, bundle9, ThroughTrainActivity.class);
                break;
            case R.id.mTv_gengduo_pinzhishenghuo:
                Bundle bundle10 = new Bundle();
                bundle10.putString("name", "品质生活");
                bundle10.putSerializable("data", data);
                multiplexIntent(mActivity, bundle10, ThroughTrainActivity.class);
                break;
            case R.id.mTv_gengduo_pinzhishenghuo_1:
                Bundle bundle11 = new Bundle();
                bundle11.putString("name", "品质生活");
                bundle11.putSerializable("data", data);
                multiplexIntent(mActivity, bundle11, ThroughTrainActivity.class);
                break;
            case R.id.rl_shoppingCart:
            case R.id.img_shoppingCart:
            case R.id.shoppingCart_num_background:
            case R.id.tv_shoppingCart_num:
                Bundle bundle12 = new Bundle();
                bundle12.putString("name", "购物车");
                bundle12.putSerializable("data", data);
                multiplexIntent(mActivity, bundle12, ShoppingCartActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(mActivity, "" + (position + 1), Toast.LENGTH_SHORT).show();
        AdvertBean.DataBean.AdvListBean advListBean = advList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("name", advListBean.getAname());
        bundle.putSerializable("data", data);
        multiplexIntent(mActivity, bundle, ThroughTrainActivity.class);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mActivity, "" + (position + 1), Toast.LENGTH_SHORT).show();
    }

    private void multiplexIntent(Context context, Bundle bundle, Class cla) {
        Intent intent = new Intent(context, cla);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load((String) path)
                    .into(imageView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}

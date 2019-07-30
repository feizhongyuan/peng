package com.maqueezu.el.ui.activity.child.physicalexamination_child;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.utils.ui.base.OtherBaseActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 套餐详情
 */
public class SetMealItemActivity extends OtherBaseActivity implements View.OnClickListener, OnBannerListener {

    private ImageView img_setmeal_item_back;//返回上一级
    private ImageView img_setmeal_item_share;//分享
    private TextView tv_setmeal_item_title;//套餐名称
    private TextView tv_setmeal_item_title1;//套餐副标题
    private TextView tv_setmeal_item_price;//套餐价格
    private TextView tv_setmeal_item_sum;//套餐已售
    private TextView tv_setmeal_item_note;//注意事项
    private AutoRelativeLayout rl_base_1;
    private ImageView img_setmeal_item_xiangqing;//详情图
    private AutoRelativeLayout rl_base_xiangqing;//套餐详情
    private AutoRelativeLayout rl_base_taocanpingjia;//套餐评价模块
    private TextView tv_taocanpingjia;//套餐评价
    private TextView tv_haopinglv;//好评率
    private AutoLinearLayout ll_base_1;
    private ImageView img_setmeal_item_jigou;
    private TextView tv_setmeal_item_jigou;
    private AutoLinearLayout rL_base_jigou;//机构模块
    private ImageView img_setmeal_item_kefu;
    private TextView tv_setmeal_item_kefu;
    private AutoLinearLayout rL_base_kefu;//客服模块
    private TextView tv_setmeal_item_buy;
    private AutoRelativeLayout rl_base_buy;//立即购买模块
    private AdvertBean.DataBean.AdvListBean advListBean;
    private Banner mBanner_SetMealItem;//轮播图

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_meal_item;
    }

    @Override
    protected void initView() {

        img_setmeal_item_back = (ImageView) findViewById(R.id.img_setmeal_item_back);
        img_setmeal_item_back.setOnClickListener(this);
        img_setmeal_item_share = (ImageView) findViewById(R.id.img_setmeal_item_share);
        img_setmeal_item_share.setOnClickListener(this);
        tv_setmeal_item_title = (TextView) findViewById(R.id.tv_setmeal_item_title);
        tv_setmeal_item_title.setOnClickListener(this);
        tv_setmeal_item_title1 = (TextView) findViewById(R.id.tv_setmeal_item_title1);
        tv_setmeal_item_title1.setOnClickListener(this);
        tv_setmeal_item_price = (TextView) findViewById(R.id.tv_setmeal_item_price);
        tv_setmeal_item_price.setOnClickListener(this);
        tv_setmeal_item_sum = (TextView) findViewById(R.id.tv_setmeal_item_sum);
        tv_setmeal_item_sum.setOnClickListener(this);
        tv_setmeal_item_note = (TextView) findViewById(R.id.tv_setmeal_item_note);
        tv_setmeal_item_note.setOnClickListener(this);
        rl_base_1 = (AutoRelativeLayout) findViewById(R.id.rl_base_1);
        rl_base_1.setOnClickListener(this);
        img_setmeal_item_xiangqing = (ImageView) findViewById(R.id.img_setmeal_item_xiangqing);
        img_setmeal_item_xiangqing.setOnClickListener(this);
        rl_base_xiangqing = (AutoRelativeLayout) findViewById(R.id.rl_base_xiangqing);
        rl_base_xiangqing.setOnClickListener(this);
        rl_base_taocanpingjia = (AutoRelativeLayout) findViewById(R.id.rl_base_taocanpingjia);
        rl_base_taocanpingjia.setOnClickListener(this);
        img_setmeal_item_jigou = (ImageView) findViewById(R.id.img_setmeal_item_jigou);
        img_setmeal_item_jigou.setOnClickListener(this);
        tv_setmeal_item_jigou = (TextView) findViewById(R.id.tv_setmeal_item_jigou);
        tv_setmeal_item_jigou.setOnClickListener(this);
        rL_base_jigou = (AutoLinearLayout) findViewById(R.id.rL_base_jigou);
        rL_base_jigou.setOnClickListener(this);
        img_setmeal_item_kefu = (ImageView) findViewById(R.id.img_setmeal_item_kefu);
        img_setmeal_item_kefu.setOnClickListener(this);
        tv_setmeal_item_kefu = (TextView) findViewById(R.id.tv_setmeal_item_kefu);
        tv_setmeal_item_kefu.setOnClickListener(this);
        rL_base_kefu = (AutoLinearLayout) findViewById(R.id.rL_base_kefu);
        rL_base_kefu.setOnClickListener(this);
        tv_setmeal_item_buy = (TextView) findViewById(R.id.tv_setmeal_item_buy);
        tv_setmeal_item_buy.setOnClickListener(this);
        rl_base_buy = (AutoRelativeLayout) findViewById(R.id.rl_base_buy);
        rl_base_buy.setOnClickListener(this);
        tv_taocanpingjia = (TextView) findViewById(R.id.tv_taocanpingjia);
        tv_taocanpingjia.setOnClickListener(this);
        tv_haopinglv = (TextView) findViewById(R.id.tv_haopinglv);
        tv_haopinglv.setOnClickListener(this);
        ll_base_1 = (AutoLinearLayout) findViewById(R.id.ll_base_1);
        ll_base_1.setOnClickListener(this);
        mBanner_SetMealItem = (Banner) findViewById(R.id.mBanner_SetMealItem);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        advListBean = (AdvertBean.DataBean.AdvListBean) intent.getSerializableExtra("date");

        Glide.with(this).load(advListBean.getAtturl()).into(img_setmeal_item_xiangqing);
        tv_setmeal_item_title.setText(advListBean.getAname());
        tv_setmeal_item_title1.setText(advListBean.getAname());
        tv_setmeal_item_price.setText("￥" + advListBean.getAid());
        tv_setmeal_item_sum.setText("已售 " + advListBean.getAcid());
        tv_setmeal_item_note.setText(advListBean.getAtturl());

        List<String> list_title = new ArrayList<>();
        List<String> list_path = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list_title.add(advListBean.getAname());
            list_path.add(advListBean.getAtturl());
        }

        mBanner_SetMealItem.setImageLoader(new MyLoader());
        mBanner_SetMealItem.setBannerAnimation(Transformer.Default);
        mBanner_SetMealItem.setBannerTitles(list_title);
        mBanner_SetMealItem.setDelayTime(3000);
        mBanner_SetMealItem.isAutoPlay(true);
        mBanner_SetMealItem.setIndicatorGravity(BannerConfig.CENTER);
        mBanner_SetMealItem.setImages(list_path)
                .setOnBannerListener(this)
                .start();
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_setmeal_item_back://返回
                finish();
                break;
            case R.id.img_setmeal_item_share://分享
                break;
            case R.id.rl_base_taocanpingjia://评价
            case R.id.tv_taocanpingjia:
            case R.id.tv_haopinglv:
                Intent intent = new Intent(this, SetMealEvaluateActivity.class);
                startActivity(intent);
                break;
            case R.id.rL_base_jigou://机构
            case R.id.img_setmeal_item_jigou:
            case R.id.tv_setmeal_item_jigou:
                break;
            case R.id.rL_base_kefu://客服
            case R.id.img_setmeal_item_kefu:
            case R.id.tv_setmeal_item_kefu:
                break;
            case R.id.rl_base_buy://立即购买
            case R.id.tv_setmeal_item_buy:
                Intent intent1 = new Intent(this, SubmitOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("date", advListBean);
                intent1.putExtras(bundle);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

//    套餐详情页轮播图监听
    @Override
    public void OnBannerClick(int position) {

    }

    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load((String) path)
                    .into(imageView);
        }
    }

}

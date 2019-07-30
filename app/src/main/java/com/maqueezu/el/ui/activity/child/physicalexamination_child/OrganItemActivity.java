package com.maqueezu.el.ui.activity.child.physicalexamination_child;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.ui.adapter.FragmentAdapter2;
import com.maqueezu.el.ui.fragment.physicalexamination_child.OrganItem2Fragment;
import com.maqueezu.el.ui.fragment.physicalexamination_child.OrganItemFragment;
import com.maqueezu.utils.ui.base.OtherBaseActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 机构详情
 */
public class OrganItemActivity extends OtherBaseActivity implements View.OnClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text;
    private AutoRelativeLayout rl_statusbar;
    private ImageView img_organ_item_tu;
    private TextView tv_organ_item_title;
    private TextView tv_organ_item_time;
    private AutoLinearLayout ll_base_1;
    private ImageView img_organ_item_tijianjigou_img;
    private TextView tv_organ_item_tijianjigou_tv;
    private ImageView img_organ_item_jigoutaocan_img;
    private TextView tv_organ_item_jigoutaocan_tv;
    private AutoLinearLayout ll_base_2;
    private ViewPager mViewPager_organ;
    private AdvertBean.DataBean.AdvListBean advListBean;
    private TextView tv_organ_item_address;
    private AutoLinearLayout rl_tijianjigou;
    private AutoLinearLayout rl_jigoutaocan;
    private AutoRelativeLayout rl_base_viewpager;
    private ImageView img_organ_telphone;//电话按钮
    private AutoLinearLayout ll_telPhone;//电话模块

    private List<Fragment> fragments;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_organ_item;
    }

    @Override
    protected void initView() {

        title_back_image = (ImageView) findViewById(R.id.title_back_image);
        title_back_image.setOnClickListener(this);
        back_layout = (AutoLinearLayout) findViewById(R.id.back_layout);
        back_layout.setOnClickListener(this);
        title_text = (TextView) findViewById(R.id.title_text);
        title_text.setOnClickListener(this);
        rl_statusbar = (AutoRelativeLayout) findViewById(R.id.rl_statusbar);
        rl_statusbar.setOnClickListener(this);
        img_organ_item_tu = (ImageView) findViewById(R.id.img_organ_item_tu);
        img_organ_item_tu.setOnClickListener(this);
        tv_organ_item_title = (TextView) findViewById(R.id.tv_organ_item_title);
        tv_organ_item_title.setOnClickListener(this);
        tv_organ_item_time = (TextView) findViewById(R.id.tv_organ_item_time);
        tv_organ_item_time.setOnClickListener(this);
        ll_base_1 = (AutoLinearLayout) findViewById(R.id.ll_base_1);
        ll_base_1.setOnClickListener(this);
        img_organ_item_tijianjigou_img = (ImageView) findViewById(R.id.img_organ_item_tijianjigou_img);
        img_organ_item_tijianjigou_img.setOnClickListener(this);
        tv_organ_item_tijianjigou_tv = (TextView) findViewById(R.id.tv_organ_item_tijianjigou_tv);
        tv_organ_item_tijianjigou_tv.setOnClickListener(this);
        img_organ_item_jigoutaocan_img = (ImageView) findViewById(R.id.img_organ_item_jigoutaocan_img);
        img_organ_item_jigoutaocan_img.setOnClickListener(this);
        tv_organ_item_jigoutaocan_tv = (TextView) findViewById(R.id.tv_organ_item_jigoutaocan_tv);
        tv_organ_item_jigoutaocan_tv.setOnClickListener(this);
        ll_base_2 = (AutoLinearLayout) findViewById(R.id.ll_base_2);
        ll_base_2.setOnClickListener(this);
        mViewPager_organ = (ViewPager) findViewById(R.id.mViewPager_organ);
        mViewPager_organ.setOnClickListener(this);
        tv_organ_item_address = (TextView) findViewById(R.id.tv_organ_item_address);
        tv_organ_item_address.setOnClickListener(this);
        rl_tijianjigou = (AutoLinearLayout) findViewById(R.id.rl_tijianjigou);
        rl_jigoutaocan = (AutoLinearLayout) findViewById(R.id.rl_jigoutaocan);
        rl_base_viewpager = (AutoRelativeLayout) findViewById(R.id.rl_base_viewpager);
        img_organ_telphone = (ImageView) findViewById(R.id.img_organ_telphone);
        img_organ_telphone.setOnClickListener(this);
        ll_telPhone = (AutoLinearLayout) findViewById(R.id.ll_telPhone);
        ll_telPhone.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        advListBean = (AdvertBean.DataBean.AdvListBean) intent.getSerializableExtra("date");

        title_text.setText(R.string.name_tijianjigouxiangqing);
        tv_organ_item_title.setText(advListBean.getAname());
        tv_organ_item_time.setText(advListBean.getUrl());
        tv_organ_item_address.setText(advListBean.getAtturl());

        Glide.with(this).load(advListBean.getAtturl()).into(img_organ_item_tu);

        fragments = new ArrayList<>();

        fragments.add(OrganItemFragment.newInstance(advListBean));
        fragments.add(OrganItem2Fragment.newInstance());

        FragmentAdapter2 adapter = new FragmentAdapter2(getSupportFragmentManager(), fragments);
        mViewPager_organ.setAdapter(adapter);

        mViewPager_organ.setCurrentItem(0);


//        rl_base_viewpager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return mViewPager_organ.dispatchTouchEvent(event);
//            }
//        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
            case R.id.title_back_image:
                this.finish();
                break;
            case R.id.rl_tijianjigou://体检机构
            case R.id.img_organ_item_tijianjigou_img:
            case R.id.tv_organ_item_tijianjigou_tv:
                mViewPager_organ.setCurrentItem(0);
                break;
            case R.id.rl_jigoutaocan:
            case R.id.img_organ_item_jigoutaocan_img:
            case R.id.tv_organ_item_jigoutaocan_tv:
                mViewPager_organ.setCurrentItem(1);
                break;
            case R.id.ll_telPhone:
            case R.id.img_organ_telphone:
                //跳转到拨号界面
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "10086"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }

}

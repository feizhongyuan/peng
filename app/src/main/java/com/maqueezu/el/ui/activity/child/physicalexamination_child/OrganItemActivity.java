package com.maqueezu.el.ui.activity.child.physicalexamination_child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 机构详情
 */
public class OrganItemActivity extends AppCompatActivity implements View.OnClickListener {

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

    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organ_item);

        initView();
        initData();
        initListener();
    }

    private void initView() {

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
    }

    private void initData() {
        Intent intent = getIntent();
        advListBean = (AdvertBean.DataBean.AdvListBean) intent.getSerializableExtra("date");

        title_text.setText("体检机构详情");
        tv_organ_item_title.setText(advListBean.getAname());
        tv_organ_item_time.setText(advListBean.getUrl());
        tv_organ_item_address.setText(advListBean.getAtturl());

        Glide.with(this).load(advListBean.getAtturl()).into(img_organ_item_tu);

        fragments = new ArrayList<>();




    }

    private void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
                this.finish();
                break;
        }
    }
}

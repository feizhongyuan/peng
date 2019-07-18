package com.maqueezu.el.ui.activity.child.shopping_child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.ui.adapter.FragmentAdapter2;
import com.maqueezu.el.ui.fragment.physicalexamination_child.SetMealListItemFragment;
import com.maqueezu.el.ui.fragment.shopping_child.ThroughTrainListItemFragment;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 直通车、分类跳转
 *  双列表展示（复用）
 */
public class ThroughTrainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;
    private TextView tv_jiage;
    private AutoRelativeLayout rl_jiage;
    private TextView tv_xiaoliang;
    private AutoRelativeLayout rl_xiaoliang;
    private AutoLinearLayout ll_base_1;
    private ViewPager view_pager_shangpinliebiao;

    private ArrayList<Fragment> fragments;
    private AdvertBean.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_through_train);

        initView();
        initDate();
        initListener();
    }

    private void initView() {

        title_back_image = (ImageView) findViewById(R.id.title_back_image);
        title_back_image.setOnClickListener(this);
        back_layout = (AutoLinearLayout) findViewById(R.id.back_layout);
        back_layout.setOnClickListener(this);
        title_text_buttn = (TextView) findViewById(R.id.title_text_buttn);
        title_text_buttn.setOnClickListener(this);
        title_buttn_layout = (AutoLinearLayout) findViewById(R.id.title_buttn_layout);
        title_buttn_layout.setOnClickListener(this);
        title_text = (TextView) findViewById(R.id.title_text);
        title_text.setOnClickListener(this);
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setOnClickListener(this);
        rl_statusbar = (AutoRelativeLayout) findViewById(R.id.rl_statusbar);
        rl_statusbar.setOnClickListener(this);
        tv_jiage = (TextView) findViewById(R.id.tv_jiage);
        tv_jiage.setOnClickListener(this);
        rl_jiage = (AutoRelativeLayout) findViewById(R.id.rl_jiage);
        rl_jiage.setOnClickListener(this);
        tv_xiaoliang = (TextView) findViewById(R.id.tv_xiaoliang);
        tv_xiaoliang.setOnClickListener(this);
        rl_xiaoliang = (AutoRelativeLayout) findViewById(R.id.rl_xiaoliang);
        rl_xiaoliang.setOnClickListener(this);
        ll_base_1 = (AutoLinearLayout) findViewById(R.id.ll_base_1);
        ll_base_1.setOnClickListener(this);
        view_pager_shangpinliebiao = (ViewPager) findViewById(R.id.view_pager_shangpinliebiao);
        view_pager_shangpinliebiao.setOnClickListener(this);
    }

    private void initDate() {
        String name = getIntent().getStringExtra("name");
        String dataName = getIntent().getStringExtra("dataName");

        if ("jiankangyundong".equals(dataName)){
            dataBean = selectData("jiankangyundong");
        }else if ("muyinghuli".equals(dataName)){
            dataBean = selectData("muyinghuli");
        }else if ("jiankangshebei".equals(dataName)){
            dataBean = selectData("jiankangshebei");
        }else if ("shipinbaojian".equals(dataName)){
            dataBean = selectData("shipinbaojian");
        }else if ("jiankangjiaju".equals(dataName)){
            dataBean = selectData("jiankangjiaju");
        }else if ("meizhuangxihu".equals(dataName)){
            dataBean = selectData("meizhuangxihu");
        }else if ("jiankangbaoxian".equals(dataName)){
            dataBean = selectData("jiankangbaoxian");
        }else if ("remaituijian".equals(dataName)){
            dataBean = selectData("remaituijian");
        }else if ("pinzhishenghuo_1".equals(dataName)){
            dataBean = selectData("pinzhishenghuo_1");
        }else if ("pinzhishenghuo_2".equals(dataName)){
            dataBean = selectData("pinzhishenghuo_2");
        }else if ("data".equals(dataName)){
            dataBean = selectData("data");
        }

        title_text.setText(name);
        fragments = new ArrayList<>();
        fragments.add(ThroughTrainListItemFragment.newInstance(dataBean));
        fragments.add(ThroughTrainListItemFragment.newInstance(dataBean));

        FragmentAdapter2 adapter = new FragmentAdapter2(getSupportFragmentManager(), fragments);
        view_pager_shangpinliebiao.setAdapter(adapter);
    }

    private AdvertBean.DataBean selectData(String name) {
        Intent intent = getIntent();
        return (AdvertBean.DataBean) intent.getSerializableExtra(name);
    }

    private void initListener() {

    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEvent1(AdvertBean advert) {
//        getAdvert(advert);
//    }
//
//    private void getAdvert(AdvertBean advert) {
//        dataBean = advert.getData();
//        WeakReference<AdvertBean.DataBean> weakReference = new WeakReference<>(dataBean);
//
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_layout:
            case R.id.title_back_image:
                finish();
                break;
            case R.id.rl_jiage:
            case R.id.tv_jiage:
                view_pager_shangpinliebiao.setCurrentItem(0);
                break;
            case R.id.rl_xiaoliang:
            case R.id.tv_xiaoliang:
                view_pager_shangpinliebiao.setCurrentItem(1);
                break;
            default:
                break;
        }
    }


}

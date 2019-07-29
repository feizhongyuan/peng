package com.maqueezu.el.ui.activity.child.physicalexamination_child;

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
import com.maqueezu.el.ui.view.NoScrollViewPager;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 套餐列表
 */
public class SetMealListActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private AutoRelativeLayout rl_statusbar;
    private NoScrollViewPager view_pager_taocanliebiao;

    private List<Fragment> fragments;
    private TextView tv_jigoudengji;
    private TextView tv_xiaoliang;
    private AutoLinearLayout ll_base_1;
    private AdvertBean.DataBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_meal_list);
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
        rl_statusbar = (AutoRelativeLayout) findViewById(R.id.rl_statusbar);
        rl_statusbar.setOnClickListener(this);
        view_pager_taocanliebiao = (NoScrollViewPager) findViewById(R.id.view_pager_taocanliebiao);
        view_pager_taocanliebiao.setOnClickListener(this);
        tv_jigoudengji = (TextView) findViewById(R.id.tv_jigoudengji);
        tv_jigoudengji.setOnClickListener(this);
        tv_xiaoliang = (TextView) findViewById(R.id.tv_xiaoliang);
        tv_xiaoliang.setOnClickListener(this);
        ll_base_1 = (AutoLinearLayout) findViewById(R.id.ll_base_1);
        ll_base_1.setOnClickListener(this);
    }

    private void initDate() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        data = (AdvertBean.DataBean) intent.getSerializableExtra("data");
//        TODO 搜索未配数据传空值展示列表
        if (data == null){
            data = new AdvertBean.DataBean();
        }
        title_text.setText(name);
        fragments = new ArrayList<>();
        fragments.add(SetMealListItemFragment.newInstance(data));
        fragments.add(SetMealListItemFragment.newInstance(data));

        FragmentAdapter2 adapter = new FragmentAdapter2(getSupportFragmentManager(), fragments);
        view_pager_taocanliebiao.setAdapter(adapter);

    }

    private void initListener() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
            case R.id.title_back_image:
                finish();
                break;
            case R.id.tv_jigoudengji:
                view_pager_taocanliebiao.setCurrentItem(0);
                break;
            case R.id.tv_xiaoliang:
                view_pager_taocanliebiao.setCurrentItem(1);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

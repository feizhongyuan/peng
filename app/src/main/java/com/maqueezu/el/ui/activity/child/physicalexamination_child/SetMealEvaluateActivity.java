package com.maqueezu.el.ui.activity.child.physicalexamination_child;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.el.ui.adapter.FragmentAdapter;
import com.maqueezu.el.ui.adapter.FragmentAdapter2;
import com.maqueezu.el.ui.fragment.physicalexamination_child.SetMealEvaluateFragment;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 套餐评价页
 */
public class SetMealEvaluateActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;
    private TextView tv_taocanpingjia;//套餐评价
    private TextView tv_haopingdu;//好评度
    private AutoRelativeLayout rl_base_taocanpingjia;//套餐评价模块
    private TextView tv_all;//全部
    private TextView tv_all_count;//全部评价数
    private AutoRelativeLayout rl_base_all;
    private TextView tv_graph;//有图评价数
    private TextView tv_graph_count;//有图
    private AutoRelativeLayout rl_base_graph;
    private AutoRelativeLayout rl_base_zongping;
    private ViewPager mViewPager_SetMealEvaluate;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_meal_evaluate);

        initView();
        initData();
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
        tv_taocanpingjia = (TextView) findViewById(R.id.tv_taocanpingjia);
        tv_taocanpingjia.setOnClickListener(this);
        tv_haopingdu = (TextView) findViewById(R.id.tv_haopingdu);
        tv_haopingdu.setOnClickListener(this);
        rl_base_taocanpingjia = (AutoRelativeLayout) findViewById(R.id.rl_base_taocanpingjia);
        rl_base_taocanpingjia.setOnClickListener(this);
        tv_all = (TextView) findViewById(R.id.tv_all);
        tv_all.setOnClickListener(this);
        tv_all_count = (TextView) findViewById(R.id.tv_all_count);
        tv_all_count.setOnClickListener(this);
        rl_base_all = (AutoRelativeLayout) findViewById(R.id.rl_base_all);
        rl_base_all.setOnClickListener(this);
        tv_graph = (TextView) findViewById(R.id.tv_graph);
        tv_graph.setOnClickListener(this);
        tv_graph_count = (TextView) findViewById(R.id.tv_graph_count);
        tv_graph_count.setOnClickListener(this);
        rl_base_graph = (AutoRelativeLayout) findViewById(R.id.rl_base_graph);
        rl_base_graph.setOnClickListener(this);
        rl_base_zongping = (AutoRelativeLayout) findViewById(R.id.rl_base_zongping);
        rl_base_zongping.setOnClickListener(this);

        mViewPager_SetMealEvaluate = (ViewPager) findViewById(R.id.mViewPager_SetMealEvaluate);
        mViewPager_SetMealEvaluate.setOnClickListener(this);
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(SetMealEvaluateFragment.newInstance());
        fragments.add(SetMealEvaluateFragment.newInstance());

        FragmentAdapter2 adapter = new FragmentAdapter2(getSupportFragmentManager(),fragments);
        mViewPager_SetMealEvaluate.setAdapter(adapter);
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
            case R.id.rl_base_all://全部展示
            case R.id.tv_all:
            case R.id.tv_all_count:
                mViewPager_SetMealEvaluate.setCurrentItem(0);
                break;
            case R.id.rl_base_graph://有图展示
            case R.id.tv_graph:
            case R.id.tv_graph_count:
                mViewPager_SetMealEvaluate.setCurrentItem(1);
                break;
            default:
                break;
        }
    }
}

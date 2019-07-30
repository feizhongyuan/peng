package com.maqueezu.el.ui.activity.child.healthy_child;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.ui.adapter.RelationApplyAdapter;
import com.maqueezu.utils.ui.base.OtherBaseActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 健康- 新的关联申请
 */
public class NewRelationApplyActivity extends OtherBaseActivity implements View.OnClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;
    private TextView tv_relationApply;//申请通知
    private RecyclerView mRecycler_relationApply;//申请列表
    private TextView mTv_seeMore;//查看更多
    private AutoRelativeLayout rl_base_1;
    private TextView tv_possibleKnowledge;//可能认识
    private RecyclerView mRecycler_possibleKnowledge;//认识列表
    private TextView mTv_moreRelevance;//关联更多档案
    private AutoRelativeLayout rl_base_2;
    private NestedScrollView mNestedScrollView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_relation_apply;
    }

    @Override
    protected void initView() {

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
        tv_relationApply = (TextView) findViewById(R.id.tv_relationApply);
        tv_relationApply.setOnClickListener(this);
        mRecycler_relationApply = (RecyclerView) findViewById(R.id.mRecycler_relationApply);
        mRecycler_relationApply.setLayoutManager(new LinearLayoutManager(this));
        mRecycler_relationApply.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mTv_seeMore = (TextView) findViewById(R.id.mTv_seeMore);
        mTv_seeMore.setOnClickListener(this);
        rl_base_1 = (AutoRelativeLayout) findViewById(R.id.rl_base_1);
        rl_base_1.setOnClickListener(this);
        tv_possibleKnowledge = (TextView) findViewById(R.id.tv_possibleKnowledge);
        tv_possibleKnowledge.setOnClickListener(this);
        mRecycler_possibleKnowledge = (RecyclerView) findViewById(R.id.mRecycler_possibleKnowledge);
        mRecycler_possibleKnowledge.setLayoutManager(new LinearLayoutManager(this));
        mRecycler_possibleKnowledge.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mTv_moreRelevance = (TextView) findViewById(R.id.mTv_moreRelevance);
        mTv_moreRelevance.setOnClickListener(this);
        rl_base_2 = (AutoRelativeLayout) findViewById(R.id.rl_base_2);
        rl_base_2.setOnClickListener(this);
        mNestedScrollView = (NestedScrollView) findViewById(R.id.mNestedScrollView);
    }

    @Override
    protected void initData() {
        List<AdvertBean.DataBean.AdvListBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AdvertBean.DataBean.AdvListBean advListBean = new AdvertBean.DataBean.AdvListBean();
            advListBean.setAname("姓名");
            advListBean.setCname("三十");
            advListBean.setDisabled("姓名查找");
            advListBean.setAid(1);
            list.add(advListBean);
        }
        RelationApplyAdapter relationApplyAdapter = new RelationApplyAdapter(this, list, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        mRecycler_relationApply.setAdapter(relationApplyAdapter);
        mRecycler_possibleKnowledge.setAdapter(relationApplyAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
            case R.id.title_back_image:
                finish();
                break;
            case R.id.mTv_seeMore://查看更多

                break;
            case R.id.mTv_moreRelevance://关联更多档案

                break;
            default:
                break;
        }
    }
}

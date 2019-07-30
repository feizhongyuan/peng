package com.maqueezu.el.ui.activity.child.physicalexamination_child;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.ui.adapter.RecommendListAdapter;
import com.maqueezu.utils.ui.base.OtherBaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 甄选套餐--更多
 */

public class RecommendSetmealActivity extends OtherBaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;
    private RecyclerView mRecycler_Recommend;
    private SmartRefreshLayout mSmart_refresh_layout;

    private AdvertBean.DataBean dataBean;
    private RecommendListAdapter recommendListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recommend_setmeal;
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
        mRecycler_Recommend = (RecyclerView) findViewById(R.id.mRecycler_Recommend);
        mRecycler_Recommend.setLayoutManager(new LinearLayoutManager(this));
        mSmart_refresh_layout = (SmartRefreshLayout) findViewById(R.id.mSmart_refresh_layout);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        dataBean = (AdvertBean.DataBean) intent.getSerializableExtra("data");

        title_text.setText(title);

        recommendListAdapter = new RecommendListAdapter(this, dataBean.getAdvList(), this);
        mRecycler_Recommend.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecycler_Recommend.setAdapter(recommendListAdapter);
    }

    @Override
    protected void initListener() {
//        下拉刷新
        mSmart_refresh_layout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                List<AdvertBean.DataBean.AdvListBean> advListBeans = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    AdvertBean.DataBean.AdvListBean advListBean = new AdvertBean.DataBean.AdvListBean();
                    advListBean.setAname("标题标题标题标题标题标题标题标题"+i);
                    int i1 = new Random().nextInt(1000);
                    advListBean.setAid(i1+i);
                    advListBean.setAtturl(String.valueOf(R.drawable.ic_launcher));
                    advListBeans.add(advListBean);
                }

                if (advListBeans != null){
                    recommendListAdapter.refresh(advListBeans);
                    refreshLayout.finishRefresh(2000);
                }else {
                    refreshLayout.finishRefresh(false);
                }
            }
        });
//        上拉加载
        mSmart_refresh_layout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                List<AdvertBean.DataBean.AdvListBean> advListBeans = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    AdvertBean.DataBean.AdvListBean advListBean = new AdvertBean.DataBean.AdvListBean();
                    advListBean.setAname("标题标题标题标题标题标题标题标题"+i);
                    int i1 = new Random().nextInt(1000);
                    advListBean.setAid(i1+i);
                    advListBean.setAtturl(String.valueOf(R.drawable.ic_launcher));
                    advListBeans.add(advListBean);
                }
                if (advListBeans != null){
                    recommendListAdapter.addList(advListBeans);
                    refreshLayout.finishLoadMore(2000);
                }else {
                    refreshLayout.finishLoadMore(false);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
            case R.id.title_back_image:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "" + (position + 1), Toast.LENGTH_SHORT).show();
    }
}

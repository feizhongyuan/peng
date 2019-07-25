package com.maqueezu.el.ui.fragment.physicalexamination_child;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.ui.adapter.CustomProblemAdapter;
import com.maqueezu.utils.ui.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 1V1定制沟通模块
 */
public class CustomProblemFragment extends BaseFragment {


    private RecyclerView mRecycler_CustomProblem;
//    private SmartRefreshLayout mSmart_refresh_layout;
    private CustomProblemAdapter customProblemAdapter;

    public CustomProblemFragment() {
    }

    public static CustomProblemFragment newInstance(String name) {
        CustomProblemFragment customProblemFragment = new CustomProblemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        customProblemFragment.setArguments(bundle);
        return customProblemFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mRootView = super.onCreateView(inflater, container, savedInstanceState);
        initView(mRootView);
        return mRootView;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_custom_problem;
    }

    @Override
    protected void initView(View mRootView) {

        mRecycler_CustomProblem = (RecyclerView) mRootView.findViewById(R.id.mRecycler_CustomProblem);
//        mSmart_refresh_layout = (SmartRefreshLayout) mRootView.findViewById(R.id.mSmart_refresh_layout);
        mRecycler_CustomProblem.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData(Bundle arguments) {
        String name = arguments.getString("name");

        List<AdvertBean.DataBean.AdvListBean> advListBeans = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            AdvertBean.DataBean.AdvListBean advListBean = new AdvertBean.DataBean.AdvListBean();
            advListBean.setAname((i+1)+"、体检人婚史"+i);
            advListBean.setAtturl("已婚"+i);
            advListBean.setUrl("未婚"+i);
            advListBeans.add(advListBean);
        }

        customProblemAdapter = new CustomProblemAdapter(getContext(),advListBeans,null);

        mRecycler_CustomProblem.setAdapter(customProblemAdapter);
        mRecycler_CustomProblem.setItemViewCacheSize(100);//设置缓存项目

    }

    @Override
    protected void initListener() {
////        上拉刷新
//        mSmart_refresh_layout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                List<AdvertBean.DataBean.AdvListBean> advListBeans = new ArrayList<>();
//                for (int i = 0; i < 10; i++) {
//                    AdvertBean.DataBean.AdvListBean advListBean = new AdvertBean.DataBean.AdvListBean();
//                    advListBean.setAname((i+1)+"、体检人婚史"+i);
//                    advListBean.setAtturl("已婚"+i);
//                    advListBean.setUrl("未婚"+i);
//                    advListBeans.add(advListBean);
//                }
//                if (advListBeans != null){
//                    customProblemAdapter.refresh(advListBeans);
//                    refreshLayout.finishRefresh(2000);
//                }else {
//                    refreshLayout.finishRefresh(false);
//                }
//            }
//        });
////        下拉加载
//        mSmart_refresh_layout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                List<AdvertBean.DataBean.AdvListBean> advListBeans = new ArrayList<>();
//                for (int i = 0; i < 6; i++) {
//                    AdvertBean.DataBean.AdvListBean advListBean = new AdvertBean.DataBean.AdvListBean();
//                    advListBean.setAname((i+1)+"、体检人是否在1年内做过X光检查？"+i);
//                    advListBean.setAtturl("是"+i);
//                    advListBean.setUrl("否"+i);
//                    advListBeans.add(advListBean);
//                }
//                if (advListBeans != null){
//                    customProblemAdapter.addList(advListBeans);
//                    refreshLayout.finishLoadMore(2000);
//                }else {
//                    refreshLayout.finishLoadMore(false);
//                }
//            }
//        });
    }

}

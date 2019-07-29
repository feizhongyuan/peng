package com.maqueezu.el.ui.fragment.healthy_child;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.ui.adapter.HealthyServiceAdapter;
import com.maqueezu.el.ui.adapter.PlatformAdapter;
import com.maqueezu.utils.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 健康模块-健康服务页
 */
public class HealthyServiceFragment extends BaseFragment {

    private RecyclerView mRecycler_healthyService;

    public HealthyServiceFragment() {
        // Required empty public constructor
    }

    public static HealthyServiceFragment newInstance(int index) {
        HealthyServiceFragment healthyServiceFragment = new HealthyServiceFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index",index);
        healthyServiceFragment.setArguments(bundle);
        return healthyServiceFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initView(view);
        return view;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_healthy_service;
    }

    @Override
    protected void initView(View mRootView) {

        mRecycler_healthyService = (RecyclerView) mRootView.findViewById(R.id.mRecycler_healthyService);
        mRecycler_healthyService.setLayoutManager(new GridLayoutManager(getContext(),3));
        mRecycler_healthyService.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        mRecycler_healthyService.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));

    }

    @Override
    protected void initData(Bundle arguments) {
        List<AdvertBean.DataBean.AdvListBean> list_daily = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            AdvertBean.DataBean.AdvListBean advListBean = new AdvertBean.DataBean.AdvListBean();
            advListBean.setAname("用药提醒"+(i+1));
            advListBean.setUrl("https://www.maqueezu.com/statics/attachment/adv/2018/12/12/9//16345000.jpg");
            list_daily.add(advListBean);
        }
        HealthyServiceAdapter serviceAdapter = new HealthyServiceAdapter(getContext(), list_daily, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        mRecycler_healthyService.setAdapter(serviceAdapter);
    }

    @Override
    protected void initListener() {

    }


}

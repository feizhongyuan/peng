package com.maqueezu.el.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.GoodsCatBean;
import com.maqueezu.el.ui.adapter.HealthyInformationAdapter;
import com.maqueezu.utils.ui.base.BaseFragment;

/**
 * 今日-健康资讯
 */
public class HealthyInformationFragment extends BaseFragment implements AdapterView.OnItemClickListener{

    private View rootView;
    private TextView tv;
    private String titleName;
    private GoodsCatBean goodsCatBean;
    private RecyclerView mRecyclerView_HealthyInformation;
    private SwipeRefreshLayout srl_swipe_refresh_layout;

    public HealthyInformationFragment() {
        // Required empty public constructor
    }

    public static HealthyInformationFragment newInstance(String string, GoodsCatBean goodsCatBean) {
        HealthyInformationFragment healthyInformationFragment = new HealthyInformationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("titleName", string);
        bundle.putSerializable("goods", goodsCatBean);
        healthyInformationFragment.setArguments(bundle);
        return healthyInformationFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);

//        同一fragment不同界面的展示
//        View rootView = null;
//        if (titleName.equals("关注")){
//            inflater.inflate(R.layout.healthy_information_item1,null);
//            initView(rootView);
//        }else if (titleName.equals("推荐")){
//            inflater.inflate(R.layout.healthy_information_item2,null);
//            initView(rootView);
//        }

        initView(rootView);
        return rootView;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_healthy_information;
    }

    @Override
    protected void initView(View mRootView) {


        mRecyclerView_HealthyInformation = (RecyclerView) rootView.findViewById(R.id.mRecyclerView_HealthyInformation);
//        srl_swipe_refresh_layout = (SwipeRefreshLayout) rootView.findViewById(R.id.srl_swipe_refresh_layout);

        mRecyclerView_HealthyInformation.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData(Bundle arguments) {
        if (arguments != null) {
            titleName = arguments.getString("titleName");
            goodsCatBean = (GoodsCatBean) arguments.getSerializable("goods");
        }
        Log.e("TAG","-----设置goodsCatBean："+goodsCatBean);
        HealthyInformationAdapter healthyInformationAdapter = new HealthyInformationAdapter(getContext(), goodsCatBean.getData(), this);
        mRecyclerView_HealthyInformation.setAdapter(healthyInformationAdapter);

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mActivity, "点击第"+(position+1)+"条", Toast.LENGTH_SHORT).show();
    }
}

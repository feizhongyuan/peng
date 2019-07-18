package com.maqueezu.el.ui.fragment.physicalexamination_child;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.ui.activity.child.physicalexamination_child.SetMealItemActivity;
import com.maqueezu.el.ui.adapter.SetMealListAdapter;
import com.maqueezu.utils.ui.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 体检-套餐列表
 */
public class SetMealListItemFragment extends BaseFragment implements AdapterView.OnItemClickListener {


    private View rootView;
    private RecyclerView mRecycler_SetMealList;
    private SmartRefreshLayout mSmart_refresh_layout;

    private AdvertBean.DataBean dataBean;
    private SetMealListAdapter setMealListAdapter;

    public SetMealListItemFragment() {
        // Required empty public constructor
    }

    public static SetMealListItemFragment newInstance(AdvertBean.DataBean data) {
        SetMealListItemFragment setMealListItemFragment = new SetMealListItemFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        setMealListItemFragment.setArguments(bundle);
        return setMealListItemFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        initView(rootView);

        return rootView;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_set_meal_list_item;
    }

    @Override
    protected void initView(View mRootView) {

        mRecycler_SetMealList = (RecyclerView) rootView.findViewById(R.id.mRecycler_SetMealList);
        mRecycler_SetMealList.setLayoutManager(new GridLayoutManager(mActivity, 2));
//       设置默认分割线
        mRecycler_SetMealList.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        mRecycler_SetMealList.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.HORIZONTAL));
        mSmart_refresh_layout = (SmartRefreshLayout) rootView.findViewById(R.id.mSmart_refresh_layout);
    }

    @Override
    protected void initData(Bundle arguments) {
        dataBean = (AdvertBean.DataBean) arguments.getSerializable("data");
        setMealListAdapter = new SetMealListAdapter(mActivity, dataBean.getAdvList(), this);
        mRecycler_SetMealList.setAdapter(setMealListAdapter);
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
                    setMealListAdapter.refresh(advListBeans);
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
                    setMealListAdapter.addList(advListBeans);
                    refreshLayout.finishLoadMore(2000);
                }else {
                    refreshLayout.finishLoadMore(false);
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mActivity, "" + (position + 1), Toast.LENGTH_SHORT).show();
        AdvertBean.DataBean.AdvListBean advListBean = dataBean.getAdvList().get(position);
        Intent intent = new Intent(mActivity, SetMealItemActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("date", advListBean);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

package com.maqueezu.el.ui.fragment.shopping_child;


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
import com.maqueezu.el.ui.activity.child.shopping_child.GoodsDetailActivity;
import com.maqueezu.el.ui.adapter.ThroughTrainListAdapter;
import com.maqueezu.utils.ui.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Fragment商品展示（价格 | 销量）
 */
public class ThroughTrainListItemFragment extends BaseFragment implements AdapterView.OnItemClickListener {


    private RecyclerView mRecycler_ThroughTrainList;
    private AdvertBean.DataBean dataBean;
    private SmartRefreshLayout mSmart_refresh_layout;
    private ThroughTrainListAdapter throughTrainListAdapter;

    public ThroughTrainListItemFragment() {
        // Required empty public constructor
    }

    public static ThroughTrainListItemFragment newInstance(AdvertBean.DataBean data) {
        ThroughTrainListItemFragment throughTrainListItemFragment = new ThroughTrainListItemFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        throughTrainListItemFragment.setArguments(bundle);
        return throughTrainListItemFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        initView(rootView);
        return rootView;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_through_train_list_item;
    }

    @Override
    protected void initView(View mRootView) {

        mRecycler_ThroughTrainList = (RecyclerView) mRootView.findViewById(R.id.mRecycler_ThroughTrainList);
        mRecycler_ThroughTrainList.setLayoutManager(new GridLayoutManager(mActivity, 2));
//       设置默认分割线
        mRecycler_ThroughTrainList.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.HORIZONTAL));
        mRecycler_ThroughTrainList.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        mSmart_refresh_layout = (SmartRefreshLayout) mRootView.findViewById(R.id.mSmart_refresh_layout);
    }

    @Override
    protected void initData(Bundle arguments) {
        dataBean = (AdvertBean.DataBean) arguments.getSerializable("data");
        throughTrainListAdapter = new ThroughTrainListAdapter(mActivity, dataBean.getAdvList(), this);
        mRecycler_ThroughTrainList.setAdapter(throughTrainListAdapter);

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
                    throughTrainListAdapter.refresh(advListBeans);
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
                    throughTrainListAdapter.addList(advListBeans);
                    refreshLayout.finishLoadMore(2000);
                }else {
                    refreshLayout.finishLoadMore(false);
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mActivity, "点击条目--" + (position + 1), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(mActivity, GoodsDetailActivity.class);
        intent.putExtra("date", dataBean.getAdvList().get(position));
        startActivity(intent);
    }
}

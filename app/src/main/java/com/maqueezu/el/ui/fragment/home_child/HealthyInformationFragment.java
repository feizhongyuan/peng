package com.maqueezu.el.ui.fragment.home_child;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
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
import com.maqueezu.el.ui.activity.child.home_child.HealthyInformationItemActivity;
import com.maqueezu.el.ui.adapter.HealthyInformationAdapter;
import com.maqueezu.utils.ui.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 今日-健康资讯
 */
public class HealthyInformationFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private View rootView;
    private TextView tv;
    private String titleName;
    private GoodsCatBean goodsCatBean;
    private RecyclerView mRecyclerView_HealthyInformation;
    private SmartRefreshLayout smart_refresh_layout;//下拉刷新
    private HealthyInformationAdapter healthyInformationAdapter;

    public HealthyInformationFragment() {
        // Required empty public constructor
    }

    public static HealthyInformationFragment newInstance(String string) {
        HealthyInformationFragment healthyInformationFragment = new HealthyInformationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("titleName", string);
        healthyInformationFragment.setArguments(bundle);
        return healthyInformationFragment;
    }
//    public static HealthyInformationFragment newInstance(String string, GoodsCatBean goodsCatBean) {
//        HealthyInformationFragment healthyInformationFragment = new HealthyInformationFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("titleName", string);
//        bundle.putSerializable("goods", goodsCatBean);
//        healthyInformationFragment.setArguments(bundle);
//        return healthyInformationFragment;
//    }

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


        mRecyclerView_HealthyInformation = (RecyclerView) mRootView.findViewById(R.id.mRecyclerView_HealthyInformation);
        smart_refresh_layout = (SmartRefreshLayout) mRootView.findViewById(R.id.smart_refresh_layout);

        mRecyclerView_HealthyInformation.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData(Bundle arguments) {
        if (arguments != null) {
            titleName = arguments.getString("titleName");
        }
        goodsCatBean = new GoodsCatBean();
        List<GoodsCatBean.DataBean> dataBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GoodsCatBean.DataBean dataBean = new GoodsCatBean.DataBean();
            dataBean.setName("标题"+i);
            int i1 = new Random().nextInt(1000);
            dataBean.setLevel(i1+i);
            dataBeans.add(dataBean);
        }

        goodsCatBean.setData(dataBeans);
        healthyInformationAdapter = new HealthyInformationAdapter(getContext(), goodsCatBean.getData(), this);
        mRecyclerView_HealthyInformation.setAdapter(healthyInformationAdapter);
        mRecyclerView_HealthyInformation.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        smart_refresh_layout.setEnableLoadMore(false);//是否启用上拉加载功能
        smart_refresh_layout.setEnableAutoLoadMore(true);//是否启用列表惯性滑动到底部时自动加载更多
//         刷新完成
        smart_refresh_layout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                TODO 执行有问题
                goodsCatBean = new GoodsCatBean();
                List<GoodsCatBean.DataBean> dataBeans = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    GoodsCatBean.DataBean dataBean = new GoodsCatBean.DataBean();
                    dataBean.setName("标题标题标题标题标题标题标题标题"+i);
                    int i1 = new Random().nextInt(1000);
                    dataBean.setLevel(i1+i);
                    dataBeans.add(dataBean);
                }

                goodsCatBean.setData(dataBeans);
                healthyInformationAdapter.notifyDataSetChanged();
                refreshLayout.finishRefresh(2000);
            }
        });
//        加载更多
        smart_refresh_layout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
            }
        });

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mActivity, "点击第" + (position + 1) + "条", Toast.LENGTH_SHORT).show();
//        goodsCatBean = new GoodsCatBean();
//        List<GoodsCatBean.DataBean> dataBeans = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            GoodsCatBean.DataBean dataBean = new GoodsCatBean.DataBean();
//            dataBean.setName("标题标题标题标题标题标题标题标题"+i);
//            int i1 = new Random().nextInt(1000);
//            dataBean.setLevel(i1+i);
//            dataBeans.add(dataBean);
//        }
//
//        goodsCatBean.setData(dataBeans);

        Intent intent = new Intent(getContext(), HealthyInformationItemActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("goods",goodsCatBean);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
//        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
//            @Override
//            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
//                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
//                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
//            }
//        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
}

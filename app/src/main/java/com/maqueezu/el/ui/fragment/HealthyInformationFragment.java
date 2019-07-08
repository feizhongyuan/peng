package com.maqueezu.el.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * 今日-健康资讯
 */
public class HealthyInformationFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private View rootView;
    private TextView tv;
    private String titleName;
    private GoodsCatBean goodsCatBean;
    private RecyclerView mRecyclerView_HealthyInformation;
    private SmartRefreshLayout smart_refresh_layout;

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


        mRecyclerView_HealthyInformation = (RecyclerView) mRootView.findViewById(R.id.mRecyclerView_HealthyInformation);
        smart_refresh_layout = (SmartRefreshLayout) rootView.findViewById(R.id.smart_refresh_layout);

        mRecyclerView_HealthyInformation.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData(Bundle arguments) {
        if (arguments != null) {
            titleName = arguments.getString("titleName");
            goodsCatBean = (GoodsCatBean) arguments.getSerializable("goods");
        }
        Log.e("TAG", "-----设置goodsCatBean：" + goodsCatBean);
        HealthyInformationAdapter healthyInformationAdapter = new HealthyInformationAdapter(getContext(), goodsCatBean.getData(), this);
        mRecyclerView_HealthyInformation.setAdapter(healthyInformationAdapter);

//         刷新完成
        smart_refresh_layout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
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

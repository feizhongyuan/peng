package com.maqueezu.el.ui.fragment.healthy_child;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.ui.activity.child.healthy_child.NewRelationApplyActivity;
import com.maqueezu.el.ui.adapter.PlatformAdapter;
import com.maqueezu.utils.ui.base.BaseFragment;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 健康模块-健康档案页
 */
public class HealthyArchivesFragment extends BaseFragment implements View.OnClickListener {


    private ImageView mImg_touxiang;//头像
    private TextView mTv_healthy_Sex;//性别
    private TextView mTv_healthy_Name;//名字
    private TextView mTv_healthy_Age;//年龄
    private ImageView mImg_newApply;
    private TextView mTv_newApply;
    private AutoRelativeLayout rl_newApply;//新的申请 模块
    private ImageView mImg_switchArchives;
    private TextView mTv_switchArchives;
    private AutoRelativeLayout rl_switchArchives;//切换档案 模块
    private AutoLinearLayout ll_base_1;
    private TextView mTv_healthy_jibenxinxi;//基本信息
    private TextView mTv_healthy_jiankangpinggu;//健康评估
    private AutoLinearLayout ll_base_2;
    private TextView tv_daily;
    private RecyclerView mRecycler_daily;
    private AutoRelativeLayout rl_daily;//日常健康数据 模块
    private TextView tv_physicalExamination;
    private RecyclerView mRecycler_physicalExamination;
    private AutoRelativeLayout rl_physicalExamination;//体检健康数据 模块
    private TextView tv_medicalAdvice;
    private RecyclerView mRecycler_medicalAdvice;
    private AutoRelativeLayout rl_medicalAdvice;//就医健康数据 模块
    private NestedScrollView mNestedScrollView;


    public HealthyArchivesFragment() {
        // Required empty public constructor
    }

    public static HealthyArchivesFragment newInstance(int index) {
        HealthyArchivesFragment healthyArchivesFragment = new HealthyArchivesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index",index);
        healthyArchivesFragment.setArguments(bundle);
        return healthyArchivesFragment;
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
        return R.layout.fragment_healthy_archives;
    }

    @Override
    protected void initView(View mRootView) {

        mImg_touxiang = (ImageView) mRootView.findViewById(R.id.mImg_touxiang);
        mImg_touxiang.setOnClickListener(this);
        mTv_healthy_Sex = (TextView) mRootView.findViewById(R.id.mTv_healthy_Sex);
        mTv_healthy_Sex.setOnClickListener(this);
        mTv_healthy_Name = (TextView) mRootView.findViewById(R.id.mTv_healthy_Name);
        mTv_healthy_Name.setOnClickListener(this);
        mTv_healthy_Age = (TextView) mRootView.findViewById(R.id.mTv_healthy_Age);
        mTv_healthy_Age.setOnClickListener(this);
        mImg_newApply = (ImageView) mRootView.findViewById(R.id.mImg_newApply);
        mImg_newApply.setOnClickListener(this);
        mTv_newApply = (TextView) mRootView.findViewById(R.id.mTv_newApply);
        mTv_newApply.setOnClickListener(this);
        rl_newApply = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_newApply);
        rl_newApply.setOnClickListener(this);
        mImg_switchArchives = (ImageView) mRootView.findViewById(R.id.mImg_switchArchives);
        mImg_switchArchives.setOnClickListener(this);
        mTv_switchArchives = (TextView) mRootView.findViewById(R.id.mTv_switchArchives);
        mTv_switchArchives.setOnClickListener(this);
        rl_switchArchives = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_switchArchives);
        rl_switchArchives.setOnClickListener(this);
        ll_base_1 = (AutoLinearLayout) mRootView.findViewById(R.id.ll_base_1);
        ll_base_1.setOnClickListener(this);
        mTv_healthy_jibenxinxi = (TextView) mRootView.findViewById(R.id.mTv_healthy_jibenxinxi);
        mTv_healthy_jibenxinxi.setOnClickListener(this);
        mTv_healthy_jiankangpinggu = (TextView) mRootView.findViewById(R.id.mTv_healthy_jiankangpinggu);
        mTv_healthy_jiankangpinggu.setOnClickListener(this);
        ll_base_2 = (AutoLinearLayout) mRootView.findViewById(R.id.ll_base_2);
        ll_base_2.setOnClickListener(this);
        tv_daily = (TextView) mRootView.findViewById(R.id.tv_daily);
        tv_daily.setOnClickListener(this);
        mRecycler_daily = (RecyclerView) mRootView.findViewById(R.id.mRecycler_daily);
        mRecycler_daily.setLayoutManager(new GridLayoutManager(getContext(),4));
        rl_daily = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_daily);
        rl_daily.setOnClickListener(this);
        tv_physicalExamination = (TextView) mRootView.findViewById(R.id.tv_physicalExamination);
        tv_physicalExamination.setOnClickListener(this);
        mRecycler_physicalExamination = (RecyclerView) mRootView.findViewById(R.id.mRecycler_physicalExamination);
        mRecycler_physicalExamination.setLayoutManager(new GridLayoutManager(getContext(),4));
        rl_physicalExamination = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_physicalExamination);
        rl_physicalExamination.setOnClickListener(this);
        tv_medicalAdvice = (TextView) mRootView.findViewById(R.id.tv_medicalAdvice);
        tv_medicalAdvice.setOnClickListener(this);
        mRecycler_medicalAdvice = (RecyclerView) mRootView.findViewById(R.id.mRecycler_medicalAdvice);
        mRecycler_medicalAdvice.setLayoutManager(new GridLayoutManager(getContext(),4));
        rl_medicalAdvice = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_medicalAdvice);
        rl_medicalAdvice.setOnClickListener(this);
        mNestedScrollView = (NestedScrollView) mRootView.findViewById(R.id.mNestedScrollView);
    }

    @Override
    protected void initData(Bundle arguments) {
//        日常健康数据
        List<AdvertBean.DataBean.AdvListBean> list_daily = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            AdvertBean.DataBean.AdvListBean advListBean = new AdvertBean.DataBean.AdvListBean();
            advListBean.setAname("血压"+(i+1));
            advListBean.setUrl("https://www.maqueezu.com/statics/attachment/adv/2018/12/12/9//16345000.jpg");
            list_daily.add(advListBean);
        }
        PlatformAdapter platformAdapter = new PlatformAdapter(getContext(), list_daily, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        mRecycler_daily.setAdapter(platformAdapter);

//        体检健康数据
        List<AdvertBean.DataBean.AdvListBean> list_physicalExamination = new ArrayList<>();
            AdvertBean.DataBean.AdvListBean advListBean = new AdvertBean.DataBean.AdvListBean();
            advListBean.setAname("体检报告");
            advListBean.setUrl("https://www.maqueezu.com/statics/attachment/adv/2018/12/12/9//16345000.jpg");
            AdvertBean.DataBean.AdvListBean advListBean2 = new AdvertBean.DataBean.AdvListBean();
            advListBean2.setAname("电话解读");
            advListBean2.setUrl("https://www.maqueezu.com/statics/attachment/adv/2018/12/12/9//16345000.jpg");
            list_physicalExamination.add(advListBean);
            list_physicalExamination.add(advListBean2);
        PlatformAdapter platformAdapter1 = new PlatformAdapter(getContext(), list_physicalExamination, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        mRecycler_physicalExamination.setAdapter(platformAdapter1);

//        就医健康数据
        List<AdvertBean.DataBean.AdvListBean> list_medicalAdvice = new ArrayList<>();
        AdvertBean.DataBean.AdvListBean advListBean3 = new AdvertBean.DataBean.AdvListBean();
        advListBean3.setAname("就医记录");
        advListBean3.setUrl("https://www.maqueezu.com/statics/attachment/adv/2018/12/12/9//16345000.jpg");
        list_medicalAdvice.add(advListBean3);
        PlatformAdapter platformAdapter2 = new PlatformAdapter(getContext(), list_medicalAdvice, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        mRecycler_medicalAdvice.setAdapter(platformAdapter2);

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_newApply://新的申请
            case R.id.mImg_newApply:
            case R.id.mTv_newApply:
                multiplexIntent(getContext(),null, NewRelationApplyActivity.class);
                break;
            case R.id.rl_switchArchives://切换档案
            case R.id.mImg_switchArchives:
            case R.id.mTv_switchArchives:

                break;
            case R.id.mTv_healthy_jibenxinxi://基本信息

                break;
            case R.id.mTv_healthy_jiankangpinggu://健康评估

                break;
            default:
                break;
        }
    }

    private void multiplexIntent(Context context,Bundle bundle,Class cla){
        Intent intent = new Intent(context,cla);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}

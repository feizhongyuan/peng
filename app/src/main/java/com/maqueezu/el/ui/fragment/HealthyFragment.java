package com.maqueezu.el.ui.fragment;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.el.ui.adapter.FragmentAdapter2;
import com.maqueezu.el.ui.fragment.healthy_child.HealthyArchivesFragment;
import com.maqueezu.el.ui.fragment.healthy_child.HealthyServiceFragment;
import com.maqueezu.utils.ui.base.BaseFragment;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 健康模块
 */
public class HealthyFragment extends BaseFragment implements View.OnClickListener {

    private TextView mTv_healthyArchives;
    private AutoRelativeLayout rl_healthyArchives;
    private TextView mTv_healthyService;
    private AutoRelativeLayout rl_healthyService;
    private AutoLinearLayout ll_base_1;
    private ViewPager mViewPager_Healthy;

    private List<Fragment> fragments;

    public HealthyFragment() {
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
        return R.layout.fragment_healthy;
    }

    @Override
    protected void initView(View mRootView) {

        mTv_healthyArchives = (TextView) mRootView.findViewById(R.id.mTv_healthyArchives);
        mTv_healthyArchives.setOnClickListener(this);
        rl_healthyArchives = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_healthyArchives);
        rl_healthyArchives.setOnClickListener(this);
        mTv_healthyService = (TextView) mRootView.findViewById(R.id.mTv_healthyService);
        mTv_healthyService.setOnClickListener(this);
        rl_healthyService = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_healthyService);
        rl_healthyService.setOnClickListener(this);
        ll_base_1 = (AutoLinearLayout) mRootView.findViewById(R.id.ll_base_1);
        ll_base_1.setOnClickListener(this);
        mViewPager_Healthy = (ViewPager) mRootView.findViewById(R.id.mViewPager_Healthy);
        mViewPager_Healthy.setOnClickListener(this);

    }

    @Override
    protected void initData(Bundle arguments) {
        fragments = new ArrayList<>();
        fragments.add(HealthyArchivesFragment.newInstance(0));
        fragments.add(HealthyServiceFragment.newInstance(1));
        FragmentAdapter2 adapter2 = new FragmentAdapter2(getChildFragmentManager(),fragments);
        mViewPager_Healthy.setAdapter(adapter2);

//        默认展示第一页
        setTextView(mTv_healthyArchives);
        mViewPager_Healthy.setCurrentItem(0);
    }

    @Override
    protected void initListener() {
        mViewPager_Healthy.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    setTextView(mTv_healthyArchives);
                    mTv_healthyService.getPaint().setFlags(0);
                    mTv_healthyService.setTextColor(getResources().getColor(R.color.huise));
    //                    mTv_healthyService.invalidate();
                }else if (position == 1){
                    setTextView(mTv_healthyService);
                    mTv_healthyArchives.getPaint().setFlags(0);
                    mTv_healthyArchives.setTextColor(getResources().getColor(R.color.huise));
//                    mTv_healthyArchives.invalidate();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_healthyArchives:
            case R.id.mTv_healthyArchives:
                mViewPager_Healthy.setCurrentItem(0);
//                setTextView(mTv_healthyArchives);
//                mTv_healthyService.getPaint().setFlags(0);
//                mTv_healthyService.invalidate();
                break;
            case R.id.rl_healthyService:
            case R.id.mTv_healthyService:
                mViewPager_Healthy.setCurrentItem(1);
//                setTextView(mTv_healthyService);
//                mTv_healthyArchives.getPaint().setFlags(0);
//                mTv_healthyArchives.invalidate();
                break;

            default:
                break;
        }
    }

    private void setTextView(TextView textView){
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.getPaint().setFakeBoldText(true);
        textView.getPaint().setAntiAlias(true);//抗锯齿
    }
}

package com.maqueezu.el.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maqueezu.el.R;
import com.maqueezu.utils.ui.base.BaseFragment;

/**
 * 健康模块
 */
public class HealthyFragment extends BaseFragment {

    private View rootView;

    public HealthyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = super.onCreateView(inflater, container, savedInstanceState);

        return rootView;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_archives;
    }

    @Override
    protected void initView(View mRootView) {

    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void initListener() {

    }

}

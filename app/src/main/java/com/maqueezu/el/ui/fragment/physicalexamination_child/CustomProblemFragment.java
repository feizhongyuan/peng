package com.maqueezu.el.ui.fragment.physicalexamination_child;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maqueezu.el.R;
import com.maqueezu.utils.ui.base.BaseFragment;

/**
 * 1V1定制沟通模块
 */
public class CustomProblemFragment extends BaseFragment {


    public CustomProblemFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mRootView = super.onCreateView(inflater, container, savedInstanceState);
        return mRootView;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_custom_problem;
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

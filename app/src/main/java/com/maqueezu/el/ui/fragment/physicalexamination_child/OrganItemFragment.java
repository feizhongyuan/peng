package com.maqueezu.el.ui.fragment.physicalexamination_child;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maqueezu.el.R;
import com.maqueezu.utils.ui.base.BaseFragment;


public class OrganItemFragment extends BaseFragment {


    private View rootView;

    public OrganItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_organ_item;
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

package com.maqueezu.el.ui.fragment.physicalexamination_child;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.utils.ui.base.BaseFragment;

/**
 * 体检机构详情-体检机构
 */
public class OrganItemFragment extends BaseFragment {


    private View rootView;

    public OrganItemFragment() {
        // Required empty public constructor
    }

    public static OrganItemFragment newInstance(AdvertBean.DataBean.AdvListBean data) {
        OrganItemFragment organItemFragment = new OrganItemFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",data);
        organItemFragment.setArguments(bundle);
        return organItemFragment;
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

package com.maqueezu.el.ui.fragment.physicalexamination_child;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maqueezu.el.R;
import com.maqueezu.utils.ui.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * 体检评价展示列表
 */
public class SetMealEvaluateFragment extends BaseFragment {


    private RecyclerView mRecycler_Evaluate;//评价条目
    private SmartRefreshLayout mSmart_refresh_layout;//刷新

    public SetMealEvaluateFragment() {
        // Required empty public constructor
    }

    public static SetMealEvaluateFragment newInstance() {
        SetMealEvaluateFragment setMealEvaluateFragment = new SetMealEvaluateFragment();
        Bundle bundle = new Bundle();
        setMealEvaluateFragment.setArguments(bundle);
        return setMealEvaluateFragment;
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
        return R.layout.fragment_set_meal_evaluate;
    }

    @Override
    protected void initView(View mRootView) {

        mRecycler_Evaluate = (RecyclerView) mRootView.findViewById(R.id.mRecycler_Evaluate);
        mRecycler_Evaluate.setLayoutManager(new LinearLayoutManager(getContext()));
        mSmart_refresh_layout = (SmartRefreshLayout) mRootView.findViewById(R.id.mSmart_refresh_layout);
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void initListener() {

    }

}

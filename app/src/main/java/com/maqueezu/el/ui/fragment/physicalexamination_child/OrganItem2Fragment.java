package com.maqueezu.el.ui.fragment.physicalexamination_child;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.maqueezu.el.R;
import com.maqueezu.el.ui.adapter.FragmentAdapter;
import com.maqueezu.utils.ui.base.BaseFragment;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 体检机构详情-机构套餐
 */
public class OrganItem2Fragment extends BaseFragment {


    private TabLayout tab_organ;
    private ViewPager mViewPager_organ;
    private AutoRelativeLayout rl_base_1;

    private List<Fragment> fragments;
    private String[] tabs = {"全部", "体检大类1", "体检大类2", "体检大类3",};

    public OrganItem2Fragment() {
        // Required empty public constructor
    }

    public static OrganItem2Fragment newInstance() {
        OrganItem2Fragment organItem2Fragment = new OrganItem2Fragment();
        Bundle bundle = new Bundle();
        organItem2Fragment.setArguments(bundle);
        return organItem2Fragment;
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
        return R.layout.fragment_organ_item2;
    }

    @Override
    protected void initView(View mRootView) {

        tab_organ = (TabLayout) mRootView.findViewById(R.id.tab_organ);

        mViewPager_organ = (ViewPager) mRootView.findViewById(R.id.mViewPager_organ);
        rl_base_1 = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_base_1);
    }

    @Override
    protected void initData(Bundle arguments) {
        fragments = new ArrayList<>();
        for (int i = 0; i < tabs.length; i++) {
            OrganItem2ListFragment fragment = OrganItem2ListFragment.newInstance();
            fragments.add(fragment);
        }
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragments, tabs);
        mViewPager_organ.setAdapter(fragmentAdapter);
        tab_organ.setupWithViewPager(mViewPager_organ);

//          事件分发
        rl_base_1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager_organ.dispatchTouchEvent(event);
            }
        });
    }

    @Override
    protected void initListener() {

    }

}

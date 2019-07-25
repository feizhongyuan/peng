package com.maqueezu.el.ui.fragment.physicalexamination_child;


import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.maqueezu.el.ui.adapter.SingleProjectAdatper;
import com.maqueezu.utils.ui.base.BaseFragment;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 体检卡内-已有套餐项目展示
 */
public class PhysicalExaminationCardSwitchFragment extends BaseFragment implements AdapterView.OnItemClickListener {


    private TextView tv_useCard_title;

    private List<String> list;
    private AutoRelativeLayout rl_single_1;
    private RecyclerView mRecycler_Cart_setMeal;

    public PhysicalExaminationCardSwitchFragment() {
    }

    public static PhysicalExaminationCardSwitchFragment newInstance(String name) {
        PhysicalExaminationCardSwitchFragment physicalExaminationCardSwitchFragment = new PhysicalExaminationCardSwitchFragment();
        Bundle bundle = new Bundle();
//        bundle.putSerializable("data",data);
        bundle.putString("name", name);
        physicalExaminationCardSwitchFragment.setArguments(bundle);
        return physicalExaminationCardSwitchFragment;
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
        return R.layout.fragment_physical_examination_card_switch;
    }

    @Override
    protected void initView(View mRootView) {
        tv_useCard_title = mRootView.findViewById(R.id.tv_useCard_title);
        rl_single_1 = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_single_1);
        mRecycler_Cart_setMeal = (RecyclerView) mRootView.findViewById(R.id.mRecycler_Cart_setMeal);
        mRecycler_Cart_setMeal.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData(Bundle arguments) {
        Bundle arguments1 = getArguments();
        String name = arguments1.getString("name");
        tv_useCard_title.setText(name);


        //        设置套餐内容
        list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add("AA项目");
        }

        SingleProjectAdatper adatper = new SingleProjectAdatper(getContext(), list, this);
        mRecycler_Cart_setMeal.setAdapter(adatper);
        mRecycler_Cart_setMeal.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mActivity, "条目"+(position +1), Toast.LENGTH_SHORT).show();
    }
}

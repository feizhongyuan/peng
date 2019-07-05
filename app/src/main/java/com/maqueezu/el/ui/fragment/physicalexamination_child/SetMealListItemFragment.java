package com.maqueezu.el.ui.fragment.physicalexamination_child;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.ui.activity.child.physicalexamination_child.SetMealItemActivity;
import com.maqueezu.el.ui.adapter.SetMealListAdapter;
import com.maqueezu.utils.ui.base.BaseFragment;

/**
 * 体检-套餐列表
 */
public class SetMealListItemFragment extends BaseFragment implements AdapterView.OnItemClickListener{


    private View rootView;
    private RecyclerView mRecycler_SetMealList;
    private AdvertBean.DataBean dataBean;

    public SetMealListItemFragment() {
        // Required empty public constructor
    }

    public static SetMealListItemFragment newInstance(AdvertBean.DataBean data) {
        SetMealListItemFragment setMealListItemFragment = new SetMealListItemFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",data);
        setMealListItemFragment.setArguments(bundle);
        return setMealListItemFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        initView(rootView);

        return rootView;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_set_meal_list_item;
    }

    @Override
    protected void initView(View mRootView) {

        mRecycler_SetMealList = (RecyclerView) rootView.findViewById(R.id.mRecycler_SetMealList);
        mRecycler_SetMealList.setLayoutManager(new GridLayoutManager(mActivity,2));
//       设置默认分割线
        mRecycler_SetMealList.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.VERTICAL));
        mRecycler_SetMealList.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.HORIZONTAL));
    }

    @Override
    protected void initData(Bundle arguments) {
        dataBean = (AdvertBean.DataBean) arguments.getSerializable("data");

        mRecycler_SetMealList.setAdapter(new SetMealListAdapter(mActivity,dataBean.getAdvList(),this));
    }


    @Override
    protected void initListener() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mActivity, ""+(position+1), Toast.LENGTH_SHORT).show();
        AdvertBean.DataBean.AdvListBean advListBean = dataBean.getAdvList().get(position);
        Intent intent = new Intent(mActivity, SetMealItemActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("date",advListBean);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

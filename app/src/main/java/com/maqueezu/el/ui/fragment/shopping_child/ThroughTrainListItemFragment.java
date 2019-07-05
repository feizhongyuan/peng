package com.maqueezu.el.ui.fragment.shopping_child;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.maqueezu.el.ui.activity.child.physicalexamination_child.OrganItemActivity;
import com.maqueezu.el.ui.activity.child.shopping_child.GoodsDetailActivity;
import com.maqueezu.el.ui.adapter.ThroughTrainListAdapter;
import com.maqueezu.utils.ui.base.BaseFragment;

/**
 * Fragment商品展示（价格 | 销量）
 */
public class ThroughTrainListItemFragment extends BaseFragment implements AdapterView.OnItemClickListener {


    private RecyclerView mRecycler_ThroughTrainList;
    private AdvertBean.DataBean data;
    private AdvertBean.DataBean dataBean;

    public ThroughTrainListItemFragment() {
        // Required empty public constructor
    }

    public static ThroughTrainListItemFragment newInstance(AdvertBean.DataBean data) {
        ThroughTrainListItemFragment throughTrainListItemFragment = new ThroughTrainListItemFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        throughTrainListItemFragment.setArguments(bundle);
        return throughTrainListItemFragment;
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
        return R.layout.fragment_through_train_list_item;
    }

    @Override
    protected void initView(View mRootView) {

        mRecycler_ThroughTrainList = (RecyclerView) mRootView.findViewById(R.id.mRecycler_ThroughTrainList);
        mRecycler_ThroughTrainList.setLayoutManager(new GridLayoutManager(mActivity,2));
//       设置默认分割线
        mRecycler_ThroughTrainList.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.HORIZONTAL));
        mRecycler_ThroughTrainList.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void initData(Bundle arguments) {
        dataBean = (AdvertBean.DataBean) arguments.getSerializable("data");

        mRecycler_ThroughTrainList.setAdapter(new ThroughTrainListAdapter(mActivity, dataBean.getAdvList(),this));

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mActivity, "点击条目--"+(position+1), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(mActivity, GoodsDetailActivity.class);
        intent.putExtra("date",dataBean.getAdvList().get(position));
        startActivity(intent);
    }
}

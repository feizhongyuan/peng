package com.maqueezu.el.ui.fragment.physicalexamination_child;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.maqueezu.el.R;
import com.maqueezu.el.ui.view.doublelistview.Bean;
import com.maqueezu.el.ui.view.doublelistview.LeftListAdapter;
import com.maqueezu.el.ui.view.doublelistview.PinnedHeaderListView;
import com.maqueezu.el.ui.view.doublelistview.RightListAdapter;
import com.maqueezu.utils.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 体检机构详情-机构套餐
 */
public class OrganItem2Fragment extends BaseFragment {


    private TabLayout tab_organ;
    private ListView left_listview;
    private PinnedHeaderListView pinnedListView;

    private String[] leftStr = {"全部", "体检大类1", "体检大类2", "体检大类3", "体检大类4", "体检大类5"};
    private boolean[] flagArray = {true, false, false, false, false, false, false, false, false};
    private boolean isScroll = true;
    private LeftListAdapter leftListAdapter;
    private List<Bean> list;
    private RightListAdapter rightListAdapter;

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

        left_listview = (ListView) mRootView.findViewById(R.id.left_listview);
        pinnedListView = (PinnedHeaderListView) mRootView.findViewById(R.id.pinnedListView);
    }

    @Override
    protected void initData(Bundle arguments) {
        list = new ArrayList<>();
        List<Bean.DataBean> dataBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Bean.DataBean dataBean = new Bean.DataBean("套餐名称套餐名称套餐名称套餐名称套餐名称","¥888","");
            dataBeans.add(dataBean);
        }

        for (int i = 0; i < leftStr.length; i++) {
            Bean bean = new Bean(leftStr[i],dataBeans);
            list.add(bean);
        }

        leftListAdapter = new LeftListAdapter(getContext(),leftStr,flagArray);

        left_listview.setAdapter(leftListAdapter);

        rightListAdapter = new RightListAdapter(getContext(),leftStr, list);

        pinnedListView.setAdapter(rightListAdapter);
    }

    @Override
    protected void initListener() {
//        左侧列表监听
        left_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                isScroll = false;
                for (int i = 0; i < leftStr.length; i++) {
                    if (i == position){
                        flagArray[i] = true;
                    }else {
                        flagArray[i] = false;
                    }
                }
                leftListAdapter.notifyDataSetChanged();
//                联动右侧列表
                int rightSection = 0;
                for (int i = 0; i < position; i++) {
                    rightSection += rightListAdapter.getCountForSection(i) + 1;
                }
                pinnedListView.setSelection(rightSection);
            }
        });

//        右侧列表监听
        pinnedListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState){
//                    当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
//                        滚动到底部
                        if (pinnedListView.getLastVisiblePosition() == (pinnedListView.getCount()-1)){
                            left_listview.setSelection(ListView.FOCUS_DOWN);
                        }
//                        滚动到顶部
                        if (pinnedListView.getFirstVisiblePosition() == 0){
                            left_listview.setSelection(0);
                        }
                        break;

                    default:
                        break;
                }
            }

            int x = 0;
            int y = 0;
            int z = 0;
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (isScroll){
                    for (int i = 0; i < list.size(); i++) {
                        if (i == rightListAdapter.getSectionForPosition(pinnedListView.getFirstVisiblePosition())){
                            flagArray[i] = true;
                            x = i;
                        }else {
                            flagArray[i] = false;
                        }
                    }
                    if (x != y){
                        leftListAdapter.notifyDataSetChanged();
                        y = x;
                        if (y == left_listview.getLastVisiblePosition()){
                            left_listview.setSelection(z);
                        }
                        if (x == left_listview.getFirstVisiblePosition()){
                            left_listview.setSelection(z);
                        }
                        if (firstVisibleItem + totalItemCount == totalItemCount -1){
                            left_listview.setSelection(ListView.FOCUS_DOWN);
                        }
                    }
                }else {
                    isScroll = true;
                }

            }
        });
    }

}

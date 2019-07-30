package com.maqueezu.el.ui.activity.child.shopping_child;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.GoodsCatBean;
import com.maqueezu.el.ui.adapter.GoodsSortLeftListAdapter;
import com.maqueezu.utils.ui.base.OtherBaseActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类
 */
public class GoodsSortActivity extends OtherBaseActivity {

    private RecyclerView mRecycler_goodsSort;
    private FrameLayout fl_fragment;
    private Context mContext;
//    private GoodsCatBean goodsCatBean;

    private List<String> titles;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_sort;
    }

    @Override
    protected void initView() {

        mRecycler_goodsSort = (RecyclerView) findViewById(R.id.mRecycler_goodsSort);
        fl_fragment = (FrameLayout) findViewById(R.id.fl_fragment);
        mRecycler_goodsSort.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycler_goodsSort.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void initData() {
//        Intent intent = getIntent();
//        String name = intent.getStringExtra("name");
//        goodsCatBean = (GoodsCatBean) intent.getSerializableExtra("data");

        titles = new ArrayList<>();
        titles.add("一级分类1");
        titles.add("一级分类2");
        titles.add("一级分类3");
        titles.add("一级分类4");
        titles.add("一级分类5");
        GoodsSortLeftListAdapter adapter = new GoodsSortLeftListAdapter(mContext, titles, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        mRecycler_goodsSort.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }
}

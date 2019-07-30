package com.maqueezu.el.ui.activity.child.healthy_child;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.utils.ui.base.OtherBaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 切换档案页
 */
public class SwitchAchivesActivity extends OtherBaseActivity {

    private RecyclerView mRecycler_switchArchives;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_switch_achives;
    }

    @Override
    protected void initView() {

        mRecycler_switchArchives = (RecyclerView) findViewById(R.id.mRecycler_switchArchives);
        mRecycler_switchArchives.setLayoutManager(new LinearLayoutManager(this));
        mRecycler_switchArchives.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void initData() {
//        TODO 添加数据
        List<AdvertBean.DataBean.AdvListBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AdvertBean.DataBean.AdvListBean advListBean = new AdvertBean.DataBean.AdvListBean();
            advListBean.setUrl("https://www.maqueezu.com/statics/attachment/adv/2019/5/31/19//37436920.jpg");
            advListBean.setAname("姓名"+(i+1));
            list.add(advListBean);
        }


//        mRecycler_switchArchives.setAdapter();

    }

    @Override
    protected void initListener() {

    }
}

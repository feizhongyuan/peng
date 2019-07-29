package com.maqueezu.el.ui.activity.child.physicalexamination_child;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.ui.activity.child.home_child.AddressActivity;
import com.maqueezu.el.ui.adapter.OrganAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 体检机构
 */
public class PhysicalOrganActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text;
    private TextView mText_Addr;
    private AutoRelativeLayout rl_statusbar;
    private RecyclerView mRecycle_Organ;
    private SmartRefreshLayout mSmart_refresh_layout;

    private AdvertBean.DataBean dataBean;
    private OrganAdapter organAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_organ);

        initView();
        initData();
        initListener();
    }

    private void initView() {

        title_back_image = (ImageView) findViewById(R.id.title_back_image);
        title_back_image.setOnClickListener(this);
        back_layout = (AutoLinearLayout) findViewById(R.id.back_layout);
        back_layout.setOnClickListener(this);
        title_text = (TextView) findViewById(R.id.title_text);
        title_text.setOnClickListener(this);
        mText_Addr = (TextView) findViewById(R.id.mText_Addr);
        mText_Addr.setOnClickListener(this);
        rl_statusbar = (AutoRelativeLayout) findViewById(R.id.rl_statusbar);
        rl_statusbar.setOnClickListener(this);
        mRecycle_Organ = (RecyclerView) findViewById(R.id.mRecycle_Organ);
        mRecycle_Organ.setOnClickListener(this);

        mRecycle_Organ.setLayoutManager(new LinearLayoutManager(this));
        mSmart_refresh_layout = (SmartRefreshLayout) findViewById(R.id.mSmart_refresh_layout);
    }

    private void initData() {

        Intent intent = getIntent();
        dataBean = (AdvertBean.DataBean) intent.getSerializableExtra("data");

        //        TODO 搜索未配数据传空值展示列表
        if (dataBean == null){
            dataBean = new AdvertBean.DataBean();
        }

        title_text.setText("体检机构");

        organAdapter = new OrganAdapter(this, dataBean.getAdvList(), this);
        mRecycle_Organ.setAdapter(organAdapter);
    }

    private void initListener() {
        if (dataBean.getAdvList() == null){
            mSmart_refresh_layout.setVisibility(View.GONE);
        }else {
//        下拉刷新
            mSmart_refresh_layout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    List<AdvertBean.DataBean.AdvListBean> advListBeans = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        AdvertBean.DataBean.AdvListBean advListBean = new AdvertBean.DataBean.AdvListBean();
                        advListBean.setAname("标题标题标题标题标题标题标题标题" + i);
                        int i1 = new Random().nextInt(1000);
                        advListBean.setAid(i1 + i);
                        advListBean.setAtturl(String.valueOf(R.drawable.ic_launcher));
                        advListBeans.add(advListBean);
                    }

                    if (advListBeans != null) {
                        organAdapter.refresh(advListBeans);
                        refreshLayout.finishRefresh(2000);
                    } else {
                        refreshLayout.finishRefresh(false);
                    }
                }
            });

//        上拉加载
            mSmart_refresh_layout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    List<AdvertBean.DataBean.AdvListBean> advListBeans = new ArrayList<>();
                    for (int i = 0; i < 6; i++) {
                        AdvertBean.DataBean.AdvListBean advListBean = new AdvertBean.DataBean.AdvListBean();
                        advListBean.setAname("标题标题标题标题标题标题标题标题" + i);
                        int i1 = new Random().nextInt(1000);
                        advListBean.setAid(i1 + i);
                        advListBean.setAtturl(String.valueOf(R.drawable.ic_launcher));
                        advListBeans.add(advListBean);
                    }

                    if (advListBeans != null) {
                        organAdapter.addList(advListBeans);
                        refreshLayout.finishLoadMore(2000);
                    } else {
                        refreshLayout.finishLoadMore(false);
                    }
                }
            });
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
                this.finish();
                break;
            case R.id.mText_Addr://区域选择
                Intent intent = new Intent(this, AddressActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "点击条目--" + (position + 1), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, OrganItemActivity.class);
        intent.putExtra("date", dataBean.getAdvList().get(position));
        startActivity(intent);

    }
}

package com.maqueezu.el.ui.activity.child.physicalexamination_child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.ui.adapter.SetMealListAdapter;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 专家定制详情页
 */
public class CustomExpertDetailsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;
    private TextView name;
    private TextView tv_name;
    private AutoLinearLayout ll_name;
    private TextView sex;
    private TextView tv_sex;
    private AutoRelativeLayout ll_sex;
    private TextView telPhone;
    private TextView tv_telPhone;
    private AutoLinearLayout ll_telPhone;
    private TextView age;
    private TextView tv_age;
    private AutoLinearLayout ll_age;
    private TextView city;
    private TextView tv_city;
    private AutoRelativeLayout rl_city;
    private AutoRelativeLayout rl_base_1;
    private TextView dingzhigaiyao;
    private AutoRelativeLayout rl_base_2;
    private TextView tuijiantaocan;
    private RecyclerView mRecycler_RecommendSetmeal;
    private NestedScrollView mNestedScrollView;

    private List<AdvertBean.DataBean.AdvListBean> advListBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_expert_details);

        initView();
        initData();
        initListener();
    }

    private void initView() {

        title_back_image = (ImageView) findViewById(R.id.title_back_image);
        title_back_image.setOnClickListener(this);
        back_layout = (AutoLinearLayout) findViewById(R.id.back_layout);
        back_layout.setOnClickListener(this);
        title_text_buttn = (TextView) findViewById(R.id.title_text_buttn);
        title_text_buttn.setOnClickListener(this);
        title_buttn_layout = (AutoLinearLayout) findViewById(R.id.title_buttn_layout);
        title_buttn_layout.setOnClickListener(this);
        title_text = (TextView) findViewById(R.id.title_text);
        title_text.setOnClickListener(this);
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setOnClickListener(this);
        rl_statusbar = (AutoRelativeLayout) findViewById(R.id.rl_statusbar);
        rl_statusbar.setOnClickListener(this);
        name = (TextView) findViewById(R.id.name);
        name.setOnClickListener(this);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_name.setOnClickListener(this);
        ll_name = (AutoLinearLayout) findViewById(R.id.ll_name);
        ll_name.setOnClickListener(this);
        sex = (TextView) findViewById(R.id.sex);
        sex.setOnClickListener(this);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_sex.setOnClickListener(this);
        ll_sex = (AutoRelativeLayout) findViewById(R.id.ll_sex);
        ll_sex.setOnClickListener(this);
        telPhone = (TextView) findViewById(R.id.telPhone);
        telPhone.setOnClickListener(this);
        tv_telPhone = (TextView) findViewById(R.id.tv_telPhone);
        tv_telPhone.setOnClickListener(this);
        ll_telPhone = (AutoLinearLayout) findViewById(R.id.ll_telPhone);
        ll_telPhone.setOnClickListener(this);
        age = (TextView) findViewById(R.id.age);
        age.setOnClickListener(this);
        tv_age = (TextView) findViewById(R.id.tv_age);
        tv_age.setOnClickListener(this);
        ll_age = (AutoLinearLayout) findViewById(R.id.ll_age);
        ll_age.setOnClickListener(this);
        city = (TextView) findViewById(R.id.city);
        city.setOnClickListener(this);
        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_city.setOnClickListener(this);
        rl_city = (AutoRelativeLayout) findViewById(R.id.rl_city);
        rl_city.setOnClickListener(this);
        rl_base_1 = (AutoRelativeLayout) findViewById(R.id.rl_base_1);
        rl_base_1.setOnClickListener(this);
        dingzhigaiyao = (TextView) findViewById(R.id.dingzhigaiyao);
        dingzhigaiyao.setOnClickListener(this);
        rl_base_2 = (AutoRelativeLayout) findViewById(R.id.rl_base_2);
        rl_base_2.setOnClickListener(this);
        tuijiantaocan = (TextView) findViewById(R.id.tuijiantaocan);
        tuijiantaocan.setOnClickListener(this);
        mRecycler_RecommendSetmeal = (RecyclerView) findViewById(R.id.mRecycler_RecommendSetmeal);
        mRecycler_RecommendSetmeal.setLayoutManager(new LinearLayoutManager(this));
        mNestedScrollView = (NestedScrollView) findViewById(R.id.mNestedScrollView);

//      设置NestedSrcollView获取焦点
        mNestedScrollView.setFocusable(true);
        mNestedScrollView.setFocusableInTouchMode(true);
        mNestedScrollView.requestFocus();
    }

    private void initData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
//        Serializable userInfo = intent.getSerializableExtra("UserInfo");
        title_text.setText(title);

        tv_name.setText("哈哈");
        tv_sex.setText("男");
        tv_telPhone.setText("12345678901");
        tv_age.setText("27");

        advListBeans = new ArrayList<>();
        advListBeans.add(new AdvertBean.DataBean.AdvListBean());
        advListBeans.add(new AdvertBean.DataBean.AdvListBean());

        SetMealListAdapter setMealListAdapter = new SetMealListAdapter(this, advListBeans, this);
        mRecycler_RecommendSetmeal.setAdapter(setMealListAdapter);

    }

    private void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
            case R.id.title_back_image:
                finish();
                break;

            default:
                break;
        }
    }

    //    条目监听
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

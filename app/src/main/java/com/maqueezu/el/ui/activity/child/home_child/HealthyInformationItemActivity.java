package com.maqueezu.el.ui.activity.child.home_child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.GoodsCatBean;
import com.maqueezu.el.ui.adapter.HealthyInformationAdapter;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import static com.maqueezu.el.MyApplication.getContext;

/**
 * 健康资讯详情页
 */
public class HealthyInformationItemActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private TextView tv_information_item_title;//文章标题
    private ImageView img_healthyNumber_img;//健康号头像
    private TextView tv_healthyNumber_title;//健康号标题
    private TextView tv_healthyNumber_time;//健康号发布时间
    private TextView tv_healthyNumber_guanzhu;//关注健康号
    private AutoRelativeLayout rl_healthyNumber;//健康号模块
    private ImageView img_information_item_img;//内容详情图
    private TextView tv_information_item_content;//内容详情
    private AutoRelativeLayout rl_content;//内容模块
    private TextView wenzhangpinglun;
    private TextView tv_wenzhangpinglun_sum;//文章评论人数
    private RecyclerView mRecycler_comment;//评论区详情
    private AutoRelativeLayout rl_pinglun;//评论区模块
    private ImageView img_information_back;//返回
    private EditText et_information_pinglun;//个人评论
    private ImageView img_information_message;//消息
    private ImageView img_information_collection;//收藏
    private ImageView img_information_share;//分享
    private AutoLinearLayout ll_bottom;//底部栏模块

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_information_item);

        initView();
        initData();
        initListener();
    }

    private void initView() {

        tv_information_item_title = (TextView) findViewById(R.id.tv_information_item_title);
        tv_information_item_title.setOnClickListener(this);
        img_healthyNumber_img = (ImageView) findViewById(R.id.img_healthyNumber_img);
        img_healthyNumber_img.setOnClickListener(this);
        tv_healthyNumber_title = (TextView) findViewById(R.id.tv_healthyNumber_title);
        tv_healthyNumber_title.setOnClickListener(this);
        tv_healthyNumber_time = (TextView) findViewById(R.id.tv_healthyNumber_time);
        tv_healthyNumber_time.setOnClickListener(this);
        tv_healthyNumber_guanzhu = (TextView) findViewById(R.id.tv_healthyNumber_guanzhu);
        tv_healthyNumber_guanzhu.setOnClickListener(this);
        rl_healthyNumber = (AutoRelativeLayout) findViewById(R.id.rl_healthyNumber);
        img_information_item_img = (ImageView) findViewById(R.id.img_information_item_img);
        img_information_item_img.setOnClickListener(this);
        tv_information_item_content = (TextView) findViewById(R.id.tv_information_item_content);
        tv_information_item_content.setOnClickListener(this);
        rl_content = (AutoRelativeLayout) findViewById(R.id.rl_content);
        wenzhangpinglun = (TextView) findViewById(R.id.wenzhangpinglun);
        wenzhangpinglun.setOnClickListener(this);
        tv_wenzhangpinglun_sum = (TextView) findViewById(R.id.tv_wenzhangpinglun_sum);
        tv_wenzhangpinglun_sum.setOnClickListener(this);
        mRecycler_comment = (RecyclerView) findViewById(R.id.mRecycler_comment);
        rl_pinglun = (AutoRelativeLayout) findViewById(R.id.rl_pinglun);
        img_information_back = (ImageView) findViewById(R.id.img_information_back);
        img_information_back.setOnClickListener(this);
        et_information_pinglun = (EditText) findViewById(R.id.et_information_pinglun);
        et_information_pinglun.setOnClickListener(this);
        img_information_message = (ImageView) findViewById(R.id.img_information_message);
        img_information_message.setOnClickListener(this);
        img_information_collection = (ImageView) findViewById(R.id.img_information_collection);
        img_information_collection.setOnClickListener(this);
        img_information_share = (ImageView) findViewById(R.id.img_information_share);
        img_information_share.setOnClickListener(this);
        ll_bottom = (AutoLinearLayout) findViewById(R.id.ll_bottom);

        mRecycler_comment.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        Intent intent = getIntent();
        GoodsCatBean goodsCatBean = (GoodsCatBean) intent.getSerializableExtra("goods");

        HealthyInformationAdapter healthyInformationAdapter = new HealthyInformationAdapter(this, goodsCatBean.getData(),this);
        mRecycler_comment.setAdapter(healthyInformationAdapter);
        mRecycler_comment.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));



    }

    private void initListener() {

    }

    private void submit() {
        // validate
        String pinglun = et_information_pinglun.getText().toString().trim();
        if (TextUtils.isEmpty(pinglun)) {
            Toast.makeText(this, "内容不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_information_back:
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "点击第" + (position + 1) + "条", Toast.LENGTH_SHORT).show();
    }
}

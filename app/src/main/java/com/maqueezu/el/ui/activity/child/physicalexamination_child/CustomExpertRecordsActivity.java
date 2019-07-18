package com.maqueezu.el.ui.activity.child.physicalexamination_child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.el.ui.adapter.SingleProjectAdatper;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 专家定制记录页
 */
public class CustomExpertRecordsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;
    private RecyclerView mRecycler_CustomExpertRecords;

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_expert_records);

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
        mRecycler_CustomExpertRecords = (RecyclerView) findViewById(R.id.mRecycler_CustomExpertRecords);

        mRecycler_CustomExpertRecords.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        title_text.setText(title);

        list = new ArrayList<>();
        list.add("定制记录1");

        SingleProjectAdatper adatper = new SingleProjectAdatper(this,list,this);
        mRecycler_CustomExpertRecords.setAdapter(adatper);

    }

    private void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_layout:
            case R.id.title_back_image:
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,CustomExpertDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title",getResources().getString(R.string.name_zhuanjiadingzhixiangqing));
//        TODO 传入定制的信息
//        bundle.putSerializable("UserInfo",);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

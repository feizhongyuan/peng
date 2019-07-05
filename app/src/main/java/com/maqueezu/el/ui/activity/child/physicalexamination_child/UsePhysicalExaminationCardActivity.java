package com.maqueezu.el.ui.activity.child.physicalexamination_child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.MainActivity;
import com.maqueezu.el.R;
import com.maqueezu.el.ui.adapter.SingleProjectAdatper;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 体检卡使用
 */
public class UsePhysicalExaminationCardActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;
    private TextView tv_useCard_tijianjigou;
    private TextView tv_useCard_taocan;
    private TextView tv_useCard_heji_numSum;
    private TextView tv_useCard_heji;
    private AutoRelativeLayout rl_base_1;
    private TextView tv_useCard_waike_sum;
    private AutoRelativeLayout rl_single_1;
    private RecyclerView mRecycler_waike;
    private AutoRelativeLayout rl_base_2;
    private TextView tv_useCard_neike_sum;
    private AutoRelativeLayout rl_single_2;
    private RecyclerView mRecycler_neike;
    private AutoRelativeLayout rl_base_3;
    private Button bt_recommend;
    private Button bt_tiaozhengtijianxiangmu;
    private Button bt_kankanqitajigou;

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_physical_examination_card);

        initView();
        initDate();
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
        tv_useCard_tijianjigou = (TextView) findViewById(R.id.tv_useCard_tijianjigou);
        tv_useCard_tijianjigou.setOnClickListener(this);
        tv_useCard_taocan = (TextView) findViewById(R.id.tv_useCard_taocan);
        tv_useCard_taocan.setOnClickListener(this);
        tv_useCard_heji_numSum = (TextView) findViewById(R.id.tv_useCard_heji_numSum);
        tv_useCard_heji_numSum.setOnClickListener(this);
        tv_useCard_heji = (TextView) findViewById(R.id.tv_useCard_heji);
        tv_useCard_heji.setOnClickListener(this);
        rl_base_1 = (AutoRelativeLayout) findViewById(R.id.rl_base_1);
        rl_base_1.setOnClickListener(this);
        tv_useCard_waike_sum = (TextView) findViewById(R.id.tv_useCard_waike_sum);
        tv_useCard_waike_sum.setOnClickListener(this);
        rl_single_1 = (AutoRelativeLayout) findViewById(R.id.rl_single_1);
        rl_single_1.setOnClickListener(this);
        mRecycler_waike = (RecyclerView) findViewById(R.id.mRecycler_waike);
        mRecycler_waike.setOnClickListener(this);
        rl_base_2 = (AutoRelativeLayout) findViewById(R.id.rl_base_2);
        rl_base_2.setOnClickListener(this);
        tv_useCard_neike_sum = (TextView) findViewById(R.id.tv_useCard_neike_sum);
        tv_useCard_neike_sum.setOnClickListener(this);
        rl_single_2 = (AutoRelativeLayout) findViewById(R.id.rl_single_2);
        rl_single_2.setOnClickListener(this);
        mRecycler_neike = (RecyclerView) findViewById(R.id.mRecycler_neike);
        mRecycler_neike.setOnClickListener(this);
        rl_base_3 = (AutoRelativeLayout) findViewById(R.id.rl_base_3);
        rl_base_3.setOnClickListener(this);
        bt_recommend = (Button) findViewById(R.id.bt_recommend);
        bt_recommend.setOnClickListener(this);
        bt_tiaozhengtijianxiangmu = (Button) findViewById(R.id.bt_tiaozhengtijianxiangmu);
        bt_tiaozhengtijianxiangmu.setOnClickListener(this);
        bt_kankanqitajigou = (Button) findViewById(R.id.bt_kankanqitajigou);
        bt_kankanqitajigou.setOnClickListener(this);

        mRecycler_waike.setLayoutManager(new LinearLayoutManager(this));
        mRecycler_neike.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initDate() {
        title_text.setText(R.string.name_tijiankashiyong);

        list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add("AA项目");
        }

        SingleProjectAdatper adatper = new SingleProjectAdatper(this,list,this);
        mRecycler_waike.setAdapter(adatper);
        mRecycler_waike.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecycler_neike.setAdapter(adatper);
        mRecycler_neike.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
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
            case R.id.bt_recommend://按照推荐项目体检

                break;
            case R.id.bt_tiaozhengtijianxiangmu://调整体检项目
                Intent intent = new Intent(this,AdjustmentProjectActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_kankanqitajigou://看看其他机构

                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, ""+(position + 1), Toast.LENGTH_SHORT).show();
    }
}

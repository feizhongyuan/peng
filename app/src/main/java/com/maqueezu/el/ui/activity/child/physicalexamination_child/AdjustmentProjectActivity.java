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

import com.maqueezu.el.R;
import com.maqueezu.el.ui.adapter.SingleProjectAdatper;
import com.maqueezu.el.ui.adapter.SingleProjectAdatper2;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 调整体检项目
 */
public class AdjustmentProjectActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;
    private TextView tv_adjustment_tijianjigou;
    private TextView tv_adjustment_taocan;
    private TextView tv_adjustment_heji_numSum;
    private TextView tv_adjustment_heji;
    private AutoRelativeLayout rl_base_1;
    private TextView tv_adjustment_yixuan_sum;
    private AutoRelativeLayout rl_single_1;
    private RecyclerView mRecycler_yixuanxiangmu;
    private AutoRelativeLayout rl_base_2;
    private TextView tv_adjustment_kexuan_sum;
    private AutoRelativeLayout rl_single_2;
    private RecyclerView mRecycler_kexuanxiangmu;
    private AutoRelativeLayout rl_base_3;
    private Button bt_querentijiao;

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjustment_project);

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
        tv_adjustment_tijianjigou = (TextView) findViewById(R.id.tv_adjustment_tijianjigou);
        tv_adjustment_tijianjigou.setOnClickListener(this);
        tv_adjustment_taocan = (TextView) findViewById(R.id.tv_adjustment_taocan);
        tv_adjustment_taocan.setOnClickListener(this);
        tv_adjustment_heji_numSum = (TextView) findViewById(R.id.tv_adjustment_heji_numSum);
        tv_adjustment_heji_numSum.setOnClickListener(this);
        tv_adjustment_heji = (TextView) findViewById(R.id.tv_adjustment_heji);
        tv_adjustment_heji.setOnClickListener(this);
        rl_base_1 = (AutoRelativeLayout) findViewById(R.id.rl_base_1);
        rl_base_1.setOnClickListener(this);
        tv_adjustment_yixuan_sum = (TextView) findViewById(R.id.tv_adjustment_yixuan_sum);
        tv_adjustment_yixuan_sum.setOnClickListener(this);
        rl_single_1 = (AutoRelativeLayout) findViewById(R.id.rl_single_1);
        rl_single_1.setOnClickListener(this);
        mRecycler_yixuanxiangmu = (RecyclerView) findViewById(R.id.mRecycler_yixuanxiangmu);
        mRecycler_yixuanxiangmu.setOnClickListener(this);
        rl_base_2 = (AutoRelativeLayout) findViewById(R.id.rl_base_2);
        rl_base_2.setOnClickListener(this);
        tv_adjustment_kexuan_sum = (TextView) findViewById(R.id.tv_adjustment_kexuan_sum);
        tv_adjustment_kexuan_sum.setOnClickListener(this);
        rl_single_2 = (AutoRelativeLayout) findViewById(R.id.rl_single_2);
        rl_single_2.setOnClickListener(this);
        mRecycler_kexuanxiangmu = (RecyclerView) findViewById(R.id.mRecycler_kexuanxiangmu);
        mRecycler_kexuanxiangmu.setOnClickListener(this);
        rl_base_3 = (AutoRelativeLayout) findViewById(R.id.rl_base_3);
        rl_base_3.setOnClickListener(this);
        bt_querentijiao = (Button) findViewById(R.id.bt_querentijiao);
        bt_querentijiao.setOnClickListener(this);

        mRecycler_yixuanxiangmu.setLayoutManager(new LinearLayoutManager(this));
        mRecycler_kexuanxiangmu.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initDate() {
        title_text.setText(R.string.name_tiaozhengxiangmu);

        list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add("AA项目");
        }

        SingleProjectAdatper2 adatper = new SingleProjectAdatper2(this,list,this);
        mRecycler_yixuanxiangmu.setAdapter(adatper);
        mRecycler_yixuanxiangmu.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecycler_kexuanxiangmu.setAdapter(adatper);
        mRecycler_kexuanxiangmu.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
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
            case R.id.bt_querentijiao://确认提交
                Intent intent = new Intent(this,SubmitOrderActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, ""+(position+1), Toast.LENGTH_SHORT).show();
    }
}

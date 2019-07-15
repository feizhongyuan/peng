package com.maqueezu.el.ui.activity.child.physicalexamination_child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.ui.activity.child.physicalexamination_child.SetMealListActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 体检套餐分类 （弃用）
 */
public class PhysicalSetMealActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private AutoRelativeLayout rl_statusbar;
    private ImageView img_taocan_1;
    private TextView tv_taocan_1;
    private AutoRelativeLayout rl_taocan_1;
    private ImageView img_taocan_2;
    private TextView tv_taocan_2;
    private AutoRelativeLayout rl_taocan_2;
    private ImageView img_taocan_3;
    private TextView tv_taocan_3;
    private AutoRelativeLayout rl_taocan_3;
    private ImageView img_taocan_4;
    private TextView tv_taocan_4;
    private AutoRelativeLayout rl_taocan_4;
    private AdvertBean.DataBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_set_meal);
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
        rl_statusbar = (AutoRelativeLayout) findViewById(R.id.rl_statusbar);
        rl_statusbar.setOnClickListener(this);
        img_taocan_1 = (ImageView) findViewById(R.id.img_taocan_1);
        img_taocan_1.setOnClickListener(this);
        tv_taocan_1 = (TextView) findViewById(R.id.tv_taocan_1);
        tv_taocan_1.setOnClickListener(this);
        rl_taocan_1 = (AutoRelativeLayout) findViewById(R.id.rl_taocan_1);
        rl_taocan_1.setOnClickListener(this);
        img_taocan_2 = (ImageView) findViewById(R.id.img_taocan_2);
        img_taocan_2.setOnClickListener(this);
        tv_taocan_2 = (TextView) findViewById(R.id.tv_taocan_2);
        tv_taocan_2.setOnClickListener(this);
        rl_taocan_2 = (AutoRelativeLayout) findViewById(R.id.rl_taocan_2);
        rl_taocan_2.setOnClickListener(this);
        img_taocan_3 = (ImageView) findViewById(R.id.img_taocan_3);
        img_taocan_3.setOnClickListener(this);
        tv_taocan_3 = (TextView) findViewById(R.id.tv_taocan_3);
        tv_taocan_3.setOnClickListener(this);
        rl_taocan_3 = (AutoRelativeLayout) findViewById(R.id.rl_taocan_3);
        rl_taocan_3.setOnClickListener(this);
        img_taocan_4 = (ImageView) findViewById(R.id.img_taocan_4);
        img_taocan_4.setOnClickListener(this);
        tv_taocan_4 = (TextView) findViewById(R.id.tv_taocan_4);
        tv_taocan_4.setOnClickListener(this);
        rl_taocan_4 = (AutoRelativeLayout) findViewById(R.id.rl_taocan_4);
        rl_taocan_4.setOnClickListener(this);
    }

    private void initDate() {
        title_text.setText("套餐分类");
        Intent intent = getIntent();
        data = (AdvertBean.DataBean) intent.getSerializableExtra("data");
    }

    private void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
            case R.id.title_back_image:
                this.finish();
                break;
            case R.id.rl_taocan_1:
            case R.id.img_taocan_1:
            case R.id.tv_taocan_1:
                String taocan1 = tv_taocan_1.getText().toString();
                Intent intent = new Intent(this,SetMealListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",taocan1);
                bundle.putSerializable("data",data);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.rl_taocan_2:
            case R.id.img_taocan_2:
            case R.id.tv_taocan_2:
                String taocan2 = tv_taocan_2.getText().toString();
                Intent intent2 = new Intent(this,SetMealListActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("name",taocan2);
                bundle2.putSerializable("data",data);
                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;
            case R.id.rl_taocan_3:
            case R.id.img_taocan_3:
            case R.id.tv_taocan_3:
                String taocan3 = tv_taocan_3.getText().toString();
                Intent intent3 = new Intent(this,SetMealListActivity.class);
                Bundle bundle3 = new Bundle();
                bundle3.putString("name",taocan3);
                bundle3.putSerializable("data",data);
                intent3.putExtras(bundle3);
                startActivity(intent3);
                break;
            case R.id.rl_taocan_4:
            case R.id.img_taocan_4:
            case R.id.tv_taocan_4:
                String taocan4 = tv_taocan_4.getText().toString();
                Intent intent4 = new Intent(this,SetMealListActivity.class);
                Bundle bundle4 = new Bundle();
                bundle4.putString("name",taocan4);
                bundle4.putSerializable("data",data);
                intent4.putExtras(bundle4);
                startActivity(intent4);
                break;
        }
    }
}

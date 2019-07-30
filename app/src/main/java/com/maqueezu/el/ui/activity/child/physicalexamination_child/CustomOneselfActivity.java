package com.maqueezu.el.ui.activity.child.physicalexamination_child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.el.ui.fragment.PhysicalExaminationFragment;
import com.maqueezu.utils.ui.base.OtherBaseActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * DIY定制
 */
public class CustomOneselfActivity extends OtherBaseActivity implements View.OnClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;
    private TextView tv_TabGroup;//标签组
    private ImageView label_img1;//标签1图
    private TextView label_tv1;//标签1标题
    private AutoRelativeLayout rl_lable1;//标签1模块
    private ImageView label_img2;
    private TextView label_tv2;
    private AutoRelativeLayout rl_lable2;//标签2模块
    private ImageView label_img3;
    private TextView label_tv3;
    private AutoRelativeLayout rl_lable3;//标签3模块


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom_oneself;
    }

    @Override
    protected void initView() {

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
        tv_TabGroup = (TextView) findViewById(R.id.tv_TabGroup);
        tv_TabGroup.setOnClickListener(this);
        label_img1 = (ImageView) findViewById(R.id.label_img1);
        label_img1.setOnClickListener(this);
        label_tv1 = (TextView) findViewById(R.id.label_tv1);
        label_tv1.setOnClickListener(this);
        rl_lable1 = (AutoRelativeLayout) findViewById(R.id.rl_lable1);
        rl_lable1.setOnClickListener(this);
        label_img2 = (ImageView) findViewById(R.id.label_img2);
        label_img2.setOnClickListener(this);
        label_tv2 = (TextView) findViewById(R.id.label_tv2);
        label_tv2.setOnClickListener(this);
        rl_lable2 = (AutoRelativeLayout) findViewById(R.id.rl_lable2);
        rl_lable2.setOnClickListener(this);
        label_img3 = (ImageView) findViewById(R.id.label_img3);
        label_img3.setOnClickListener(this);
        label_tv3 = (TextView) findViewById(R.id.label_tv3);
        label_tv3.setOnClickListener(this);
        rl_lable3 = (AutoRelativeLayout) findViewById(R.id.rl_lable3);
        rl_lable3.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

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
}

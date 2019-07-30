package com.maqueezu.el.ui.activity.child.healthy_child;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.utils.ui.base.OtherBaseActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 查看申请人信息页
 */
public class SeeApplyInformationActivity extends OtherBaseActivity implements View.OnClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;
    private TextView touxiang;
    private ImageView mImage_head;
    private AutoRelativeLayout rl_touxiang;
    private TextView name;
    private TextView mTv_name;
    private AutoRelativeLayout rl_name;
    private TextView sex;
    private TextView mTv_sex;
    private AutoRelativeLayout rl_sex;
    private TextView dateOfBirth;
    private TextView mTv_dateOfBirth;
    private AutoRelativeLayout rl_dateOfBirth;
    private TextView age;
    private TextView mTv_age;
    private AutoRelativeLayout rl_age;
    private TextView phone;
    private TextView mTv_phone;
    private AutoRelativeLayout rl_phone;
    private TextView idCardNumber;
    private TextView mTv_idCardNumber;
    private AutoRelativeLayout rl_idCardNumber;
    private TextView marriage;
    private TextView mTv_marriage;
    private AutoRelativeLayout rl_marriage;
    private TextView fertility;
    private TextView mTv_fertility;
    private AutoRelativeLayout rl_fertility;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_see_apply_information;
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
        touxiang = (TextView) findViewById(R.id.touxiang);
        touxiang.setOnClickListener(this);
        mImage_head = (ImageView) findViewById(R.id.mImage_head);
        mImage_head.setOnClickListener(this);
        rl_touxiang = (AutoRelativeLayout) findViewById(R.id.rl_touxiang);
        rl_touxiang.setOnClickListener(this);
        name = (TextView) findViewById(R.id.name);
        name.setOnClickListener(this);
        mTv_name = (TextView) findViewById(R.id.mTv_name);
        mTv_name.setOnClickListener(this);
        rl_name = (AutoRelativeLayout) findViewById(R.id.rl_name);
        rl_name.setOnClickListener(this);
        sex = (TextView) findViewById(R.id.sex);
        sex.setOnClickListener(this);
        mTv_sex = (TextView) findViewById(R.id.mTv_sex);
        mTv_sex.setOnClickListener(this);
        rl_sex = (AutoRelativeLayout) findViewById(R.id.rl_sex);
        rl_sex.setOnClickListener(this);
        dateOfBirth = (TextView) findViewById(R.id.dateOfBirth);
        dateOfBirth.setOnClickListener(this);
        mTv_dateOfBirth = (TextView) findViewById(R.id.mTv_dateOfBirth);
        mTv_dateOfBirth.setOnClickListener(this);
        rl_dateOfBirth = (AutoRelativeLayout) findViewById(R.id.rl_dateOfBirth);
        rl_dateOfBirth.setOnClickListener(this);
        age = (TextView) findViewById(R.id.age);
        age.setOnClickListener(this);
        mTv_age = (TextView) findViewById(R.id.mTv_age);
        mTv_age.setOnClickListener(this);
        rl_age = (AutoRelativeLayout) findViewById(R.id.rl_age);
        rl_age.setOnClickListener(this);
        phone = (TextView) findViewById(R.id.phone);
        phone.setOnClickListener(this);
        mTv_phone = (TextView) findViewById(R.id.mTv_phone);
        mTv_phone.setOnClickListener(this);
        rl_phone = (AutoRelativeLayout) findViewById(R.id.rl_phone);
        rl_phone.setOnClickListener(this);
        idCardNumber = (TextView) findViewById(R.id.idCardNumber);
        idCardNumber.setOnClickListener(this);
        mTv_idCardNumber = (TextView) findViewById(R.id.mTv_idCardNumber);
        mTv_idCardNumber.setOnClickListener(this);
        rl_idCardNumber = (AutoRelativeLayout) findViewById(R.id.rl_idCardNumber);
        rl_idCardNumber.setOnClickListener(this);
        marriage = (TextView) findViewById(R.id.marriage);
        marriage.setOnClickListener(this);
        mTv_marriage = (TextView) findViewById(R.id.mTv_marriage);
        mTv_marriage.setOnClickListener(this);
        rl_marriage = (AutoRelativeLayout) findViewById(R.id.rl_marriage);
        rl_marriage.setOnClickListener(this);
        fertility = (TextView) findViewById(R.id.fertility);
        fertility.setOnClickListener(this);
        mTv_fertility = (TextView) findViewById(R.id.mTv_fertility);
        mTv_fertility.setOnClickListener(this);
        rl_fertility = (AutoRelativeLayout) findViewById(R.id.rl_fertility);
        rl_fertility.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        title_text.setText(R.string.name_shenqingrenxinxi);
    }

    @Override
    protected void initListener() {

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
}

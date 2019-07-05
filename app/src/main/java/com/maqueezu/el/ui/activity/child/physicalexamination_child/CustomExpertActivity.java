package com.maqueezu.el.ui.activity.child.physicalexamination_child;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 专家定制
 */
public class CustomExpertActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;
    private TextView name;
    private EditText et_name;
    private AutoLinearLayout ll_name;
    private TextView sex;
    private TextView tv_sex;
    private AutoRelativeLayout ll_sex;
    private TextView telPhone;
    private EditText et_telPhone;
    private AutoLinearLayout ll_telPhone;
    private TextView age;
    private EditText et_age;
    private AutoLinearLayout ll_age;
    private AutoRelativeLayout rl_base_1;
    private TextView zhuyi1;
    private TextView zhuyi2;
    private TextView zhuyi3;
    private AutoRelativeLayout rl_base_2;
    private Button bt_zhuanjiadingzhi_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_expert);

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
        name = (TextView) findViewById(R.id.name);
        name.setOnClickListener(this);
        et_name = (EditText) findViewById(R.id.et_name);
        et_name.setOnClickListener(this);
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
        et_telPhone = (EditText) findViewById(R.id.et_telPhone);
        et_telPhone.setOnClickListener(this);
        ll_telPhone = (AutoLinearLayout) findViewById(R.id.ll_telPhone);
        ll_telPhone.setOnClickListener(this);
        age = (TextView) findViewById(R.id.age);
        age.setOnClickListener(this);
        et_age = (EditText) findViewById(R.id.et_age);
        et_age.setOnClickListener(this);
        ll_age = (AutoLinearLayout) findViewById(R.id.ll_age);
        ll_age.setOnClickListener(this);
        rl_base_1 = (AutoRelativeLayout) findViewById(R.id.rl_base_1);
        rl_base_1.setOnClickListener(this);
        zhuyi1 = (TextView) findViewById(R.id.zhuyi1);
        zhuyi1.setOnClickListener(this);
        zhuyi2 = (TextView) findViewById(R.id.zhuyi2);
        zhuyi2.setOnClickListener(this);
        zhuyi3 = (TextView) findViewById(R.id.zhuyi3);
        zhuyi3.setOnClickListener(this);
        rl_base_2 = (AutoRelativeLayout) findViewById(R.id.rl_base_2);
        rl_base_2.setOnClickListener(this);
        bt_zhuanjiadingzhi_submit = (Button) findViewById(R.id.bt_zhuanjiadingzhi_submit);
        bt_zhuanjiadingzhi_submit.setOnClickListener(this);
    }

    private void initDate() {
        title_text.setText(R.string.name_zhuanjiadingzhi);
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
            case R.id.bt_zhuanjiadingzhi_submit:

                break;
            case R.id.sex://性别
            case R.id.ll_sex:
            case R.id.tv_sex:

                break;
            default:
                break;
        }
    }

    private void submit() {
        // validate
        String name = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "name不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String telPhone = et_telPhone.getText().toString().trim();
        if (TextUtils.isEmpty(telPhone)) {
            Toast.makeText(this, "telPhone不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String age = et_age.getText().toString().trim();
        if (TextUtils.isEmpty(age)) {
            Toast.makeText(this, "age不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

    }
}

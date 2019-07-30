package com.maqueezu.el.ui.activity.child.healthy_child;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.maqueezu.utils.ui.base.OtherBaseActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 健康-申请信息处理页
 */
public class ApplyInformationProcessActivity extends OtherBaseActivity implements View.OnClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;
    private ImageView mImage_head;//申请人头像
    private TextView mTv_Apply_name;//姓名
    private TextView mTv_Apply_age;//年龄
    private ImageView mImage_personInformation;//申请人详情箭头
    private AutoRelativeLayout rl_base_1;//申请人模块
    private TextView guanliandangan;//关联档案
    private TextView mTv_archiveName;//档案名称
    private ImageView mImage_archiveInformation;//关联档案详情箭头
    private AutoRelativeLayout rl_base_2;//关联档案模块
    private TextView fujiaxinxi;//附加信息
    private EditText mEt_Additional;//附加信息框
    private ImageView mImage_telPhone;//电话
    private AutoRelativeLayout rl_base_3;//附加模块
    private TextView laiyuan;//来源
    private TextView mTv_source;//来源信息展示
    private AutoRelativeLayout rl_base_4;//来源模块
    private TextView mTv_Refuse;//拒绝
    private TextView mTv_Agree;//同意
    private AutoLinearLayout ll_base_5;//选择模块

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_information_process;
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
        mImage_head = (ImageView) findViewById(R.id.mImage_head);
        mImage_head.setOnClickListener(this);
        mTv_Apply_name = (TextView) findViewById(R.id.mTv_Apply_name);
        mTv_Apply_name.setOnClickListener(this);
        mTv_Apply_age = (TextView) findViewById(R.id.mTv_Apply_age);
        mTv_Apply_age.setOnClickListener(this);
        mImage_personInformation = (ImageView) findViewById(R.id.mImage_personInformation);
        mImage_personInformation.setOnClickListener(this);
        rl_base_1 = (AutoRelativeLayout) findViewById(R.id.rl_base_1);
        rl_base_1.setOnClickListener(this);
        guanliandangan = (TextView) findViewById(R.id.guanliandangan);
        guanliandangan.setOnClickListener(this);
        mImage_archiveInformation = (ImageView) findViewById(R.id.mImage_archiveInformation);
        mImage_archiveInformation.setOnClickListener(this);
        rl_base_2 = (AutoRelativeLayout) findViewById(R.id.rl_base_2);
        rl_base_2.setOnClickListener(this);
        fujiaxinxi = (TextView) findViewById(R.id.fujiaxinxi);
        fujiaxinxi.setOnClickListener(this);
        mEt_Additional = (EditText) findViewById(R.id.mEt_Additional);
        mEt_Additional.setOnClickListener(this);
        rl_base_3 = (AutoRelativeLayout) findViewById(R.id.rl_base_3);
        rl_base_3.setOnClickListener(this);
        laiyuan = (TextView) findViewById(R.id.laiyuan);
        laiyuan.setOnClickListener(this);
        mTv_source = (TextView) findViewById(R.id.mTv_source);
        mTv_source.setOnClickListener(this);
        rl_base_4 = (AutoRelativeLayout) findViewById(R.id.rl_base_4);
        rl_base_4.setOnClickListener(this);
        mTv_Refuse = (TextView) findViewById(R.id.mTv_Refuse);
        mTv_Refuse.setOnClickListener(this);
        mTv_Agree = (TextView) findViewById(R.id.mTv_Agree);
        mTv_Agree.setOnClickListener(this);
        ll_base_5 = (AutoLinearLayout) findViewById(R.id.ll_base_5);
        ll_base_5.setOnClickListener(this);
        mTv_archiveName = (TextView) findViewById(R.id.mTv_archiveName);
        mTv_archiveName.setOnClickListener(this);
        mImage_telPhone = (ImageView) findViewById(R.id.mImage_telPhone);
        mImage_telPhone.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        title_text.setText(R.string.name_shenqingxinxichuli);

        mTv_source.setText("创建档案");

    }

    private void submit() {
        // validate
        String Additional = mEt_Additional.getText().toString().trim();
        if (TextUtils.isEmpty(Additional)) {
            Toast.makeText(this, "Additional不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
            case R.id.title_back_image:
                finish();
                break;
            case R.id.rl_base_1://查看申请人信息
            case R.id.mImage_head:
            case R.id.mTv_Apply_name:
            case R.id.mTv_Apply_age:
            case R.id.mImage_personInformation:
                Intent intent = new Intent(this,SeeApplyInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_base_2://查看关联档案信息
            case R.id.guanliandangan:
            case R.id.mTv_archiveName:
            case R.id.mImage_archiveInformation:
                Intent intent1 = new Intent(this,SeeArchiveInformationActivity.class);
                startActivity(intent1);
                break;
            case R.id.mImage_telPhone://电话
                // 用intent启动拨打电话
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "10086"));
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
                break;
            case R.id.mTv_Refuse://拒绝

                break;
            case R.id.mTv_Agree://同意

                break;
            default:
                break;
        }
    }
}

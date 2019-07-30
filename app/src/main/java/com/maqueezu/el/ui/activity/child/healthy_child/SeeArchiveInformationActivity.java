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
 * 查看关联档案信息页
 */
public class SeeArchiveInformationActivity extends OtherBaseActivity implements View.OnClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;//标题栏
    private TextView touxiang;
    private ImageView mImage_head;
    private AutoRelativeLayout rl_touxiang;//头像
    private TextView name;
    private TextView mTv_name;
    private AutoRelativeLayout rl_name;//姓名
    private TextView sex;
    private TextView mTv_sex;
    private AutoRelativeLayout rl_sex;//性别
    private TextView dateOfBirth;
    private TextView mTv_dateOfBirth;
    private AutoRelativeLayout rl_dateOfBirth;//出生日期
    private TextView age;
    private TextView mTv_age;
    private AutoRelativeLayout rl_age;//年龄
    private View notView;
    private TextView maritalStatus;
    private TextView mTv_maritalStatus;
    private AutoRelativeLayout rl_maritalStatus;//婚姻状况
    private TextView nativePlace;
    private TextView mTv_nativePlace;
    private AutoRelativeLayout rl_nativePlace;//籍贯
    private TextView profession;
    private TextView mTv_profession;
    private AutoRelativeLayout rl_profession;//职业
    private TextView bloodType;
    private TextView mTv_bloodType;
    private AutoRelativeLayout rl_bloodType;//血型
    private TextView degreeOfEducation;
    private TextView mTv_degreeOfEducation;
    private AutoRelativeLayout rl_degreeOfEducation;//文化程度
    private TextView idCardNumber;
    private TextView mTv_idCardNumber;
    private AutoRelativeLayout rl_idCardNumber;//身份证号
    private TextView telPhone;
    private TextView mTv_telPhone;
    private AutoRelativeLayout rl_telPhone;//电话
    private TextView pastHistory;
    private TextView mTv_pastHistory;
    private AutoRelativeLayout rl_pastHistory;//既往病史
    private TextView onMedication;
    private TextView mTv_onMedication;
    private AutoRelativeLayout rl_onMedication;//在服药物
    private TextView historyOfAllergy;
    private TextView mTv_historyOfAllergy;
    private AutoRelativeLayout rl_historyOfAllergy;//过敏史

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_see_archive_information;
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
        notView = (View) findViewById(R.id.notView);
        notView.setOnClickListener(this);
        maritalStatus = (TextView) findViewById(R.id.maritalStatus);
        maritalStatus.setOnClickListener(this);
        mTv_maritalStatus = (TextView) findViewById(R.id.mTv_maritalStatus);
        mTv_maritalStatus.setOnClickListener(this);
        rl_maritalStatus = (AutoRelativeLayout) findViewById(R.id.rl_maritalStatus);
        rl_maritalStatus.setOnClickListener(this);
        nativePlace = (TextView) findViewById(R.id.nativePlace);
        nativePlace.setOnClickListener(this);
        mTv_nativePlace = (TextView) findViewById(R.id.mTv_nativePlace);
        mTv_nativePlace.setOnClickListener(this);
        rl_nativePlace = (AutoRelativeLayout) findViewById(R.id.rl_nativePlace);
        rl_nativePlace.setOnClickListener(this);
        profession = (TextView) findViewById(R.id.profession);
        profession.setOnClickListener(this);
        mTv_profession = (TextView) findViewById(R.id.mTv_profession);
        mTv_profession.setOnClickListener(this);
        rl_profession = (AutoRelativeLayout) findViewById(R.id.rl_profession);
        rl_profession.setOnClickListener(this);
        bloodType = (TextView) findViewById(R.id.bloodType);
        bloodType.setOnClickListener(this);
        mTv_bloodType = (TextView) findViewById(R.id.mTv_bloodType);
        mTv_bloodType.setOnClickListener(this);
        rl_bloodType = (AutoRelativeLayout) findViewById(R.id.rl_bloodType);
        rl_bloodType.setOnClickListener(this);
        degreeOfEducation = (TextView) findViewById(R.id.degreeOfEducation);
        degreeOfEducation.setOnClickListener(this);
        mTv_degreeOfEducation = (TextView) findViewById(R.id.mTv_degreeOfEducation);
        mTv_degreeOfEducation.setOnClickListener(this);
        rl_degreeOfEducation = (AutoRelativeLayout) findViewById(R.id.rl_degreeOfEducation);
        rl_degreeOfEducation.setOnClickListener(this);
        idCardNumber = (TextView) findViewById(R.id.idCardNumber);
        idCardNumber.setOnClickListener(this);
        mTv_idCardNumber = (TextView) findViewById(R.id.mTv_idCardNumber);
        mTv_idCardNumber.setOnClickListener(this);
        rl_idCardNumber = (AutoRelativeLayout) findViewById(R.id.rl_idCardNumber);
        rl_idCardNumber.setOnClickListener(this);
        telPhone = (TextView) findViewById(R.id.telPhone);
        telPhone.setOnClickListener(this);
        mTv_telPhone = (TextView) findViewById(R.id.mTv_telPhone);
        mTv_telPhone.setOnClickListener(this);
        rl_telPhone = (AutoRelativeLayout) findViewById(R.id.rl_telPhone);
        rl_telPhone.setOnClickListener(this);
        pastHistory = (TextView) findViewById(R.id.pastHistory);
        pastHistory.setOnClickListener(this);
        mTv_pastHistory = (TextView) findViewById(R.id.mTv_pastHistory);
        mTv_pastHistory.setOnClickListener(this);
        rl_pastHistory = (AutoRelativeLayout) findViewById(R.id.rl_pastHistory);
        rl_pastHistory.setOnClickListener(this);
        onMedication = (TextView) findViewById(R.id.onMedication);
        onMedication.setOnClickListener(this);
        mTv_onMedication = (TextView) findViewById(R.id.mTv_onMedication);
        mTv_onMedication.setOnClickListener(this);
        rl_onMedication = (AutoRelativeLayout) findViewById(R.id.rl_onMedication);
        rl_onMedication.setOnClickListener(this);
        historyOfAllergy = (TextView) findViewById(R.id.historyOfAllergy);
        historyOfAllergy.setOnClickListener(this);
        mTv_historyOfAllergy = (TextView) findViewById(R.id.mTv_historyOfAllergy);
        mTv_historyOfAllergy.setOnClickListener(this);
        rl_historyOfAllergy = (AutoRelativeLayout) findViewById(R.id.rl_historyOfAllergy);
        rl_historyOfAllergy.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        title_text.setText(R.string.name_guanliandanganxinxi);

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

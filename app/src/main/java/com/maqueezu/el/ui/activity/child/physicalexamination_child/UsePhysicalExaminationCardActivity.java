package com.maqueezu.el.ui.activity.child.physicalexamination_child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.maqueezu.el.R;
import com.maqueezu.el.ui.adapter.FragmentAdapter2;
import com.maqueezu.el.ui.adapter.SingleProjectAdatper;
import com.maqueezu.el.ui.fragment.physicalexamination_child.PhysicalExaminationCardSwitchFragment;
import com.maqueezu.el.ui.view.NoScrollViewPager;
import com.maqueezu.utils.ui.base.OtherBaseActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 体检卡使用
 */
public class UsePhysicalExaminationCardActivity extends OtherBaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    private List<String> list;
    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;
    private AutoRelativeLayout rl_base_1;
    private TextView tv_useCard_title;//体检卡-套餐名称
    private ImageView img_useCard_left;//左箭头查看套餐
    private ImageView img_useCard_right;//右箭头查看套餐
    private AutoRelativeLayout rl_setmeal_1;
    private NoScrollViewPager mViewPager_SetMeal;//套餐切换
    private AutoRelativeLayout rl_base_2;
    private Button bt_recommend;//推荐项目
    private Button bt_tiaozhengtijianxiangmu;//调整项目
    private Button bt_kankanqitajigou;//看看其他机构
    private AutoLinearLayout ll_base_1;
    private ImageView img_Cart_backGround;//卡背景
    private ImageView img_Cart_logo;//卡logo
    private TextView tv_Cart_status;//卡状态
    private TextView tv_Card_type;//卡类型
    private TextView tv_Card_displayNumber;//卡序列号
    private TextView tv_Cart_enjoymentOfService;//查看尊享服务


    private List<Fragment> fragments;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_use_physical_examination_card;
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
        rl_base_1 = (AutoRelativeLayout) findViewById(R.id.rl_base_1);
        rl_base_1.setOnClickListener(this);
        rl_base_2 = (AutoRelativeLayout) findViewById(R.id.rl_base_2);
        rl_base_2.setOnClickListener(this);
        bt_recommend = (Button) findViewById(R.id.bt_recommend);
        bt_recommend.setOnClickListener(this);
        bt_tiaozhengtijianxiangmu = (Button) findViewById(R.id.bt_tiaozhengtijianxiangmu);
        bt_tiaozhengtijianxiangmu.setOnClickListener(this);
        bt_kankanqitajigou = (Button) findViewById(R.id.bt_kankanqitajigou);
        bt_kankanqitajigou.setOnClickListener(this);
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
        rl_base_1 = (AutoRelativeLayout) findViewById(R.id.rl_base_1);
        rl_base_1.setOnClickListener(this);
        tv_useCard_title = (TextView) findViewById(R.id.tv_useCard_title);
        tv_useCard_title.setOnClickListener(this);
        img_useCard_left = (ImageView) findViewById(R.id.img_useCard_left);
        img_useCard_left.setOnClickListener(this);
        img_useCard_right = (ImageView) findViewById(R.id.img_useCard_right);
        img_useCard_right.setOnClickListener(this);
        rl_setmeal_1 = (AutoRelativeLayout) findViewById(R.id.rl_setmeal_1);
        rl_setmeal_1.setOnClickListener(this);
        mViewPager_SetMeal = (NoScrollViewPager) findViewById(R.id.mViewPager_SetMeal);
        rl_base_2 = (AutoRelativeLayout) findViewById(R.id.rl_base_2);
        rl_base_2.setOnClickListener(this);
        bt_recommend = (Button) findViewById(R.id.bt_recommend);
        bt_recommend.setOnClickListener(this);
        bt_tiaozhengtijianxiangmu = (Button) findViewById(R.id.bt_tiaozhengtijianxiangmu);
        bt_tiaozhengtijianxiangmu.setOnClickListener(this);
        bt_kankanqitajigou = (Button) findViewById(R.id.bt_kankanqitajigou);
        bt_kankanqitajigou.setOnClickListener(this);
        ll_base_1 = (AutoLinearLayout) findViewById(R.id.ll_base_1);
        ll_base_1.setOnClickListener(this);
        img_Cart_backGround = (ImageView) findViewById(R.id.img_Cart_backGround);
        img_Cart_backGround.setOnClickListener(this);
        img_Cart_logo = (ImageView) findViewById(R.id.img_Cart_logo);
        img_Cart_logo.setOnClickListener(this);
        tv_Cart_status = (TextView) findViewById(R.id.tv_Cart_status);
        tv_Cart_status.setOnClickListener(this);
        tv_Card_type = (TextView) findViewById(R.id.tv_Card_type);
        tv_Card_type.setOnClickListener(this);
        tv_Card_displayNumber = (TextView) findViewById(R.id.tv_Card_displayNumber);
        tv_Card_displayNumber.setOnClickListener(this);
        tv_Cart_enjoymentOfService = (TextView) findViewById(R.id.tv_Cart_enjoymentOfService);
        tv_Cart_enjoymentOfService.setOnClickListener(this);

        img_useCard_left.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        title_text.setText(R.string.name_tijiankashiyong);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String cardImg = intent.getStringExtra("cardImg");

        tv_useCard_title.setText(name);
        Glide.with(this).load(cardImg).into(img_Cart_backGround);

        fragments = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            PhysicalExaminationCardSwitchFragment fragment = PhysicalExaminationCardSwitchFragment.newInstance("套餐"+(i+1));
            fragments.add(fragment);
        }

        FragmentAdapter2 adapter = new FragmentAdapter2(getSupportFragmentManager(), fragments);
        mViewPager_SetMeal.setAdapter(adapter);
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
            case R.id.bt_recommend://按照推荐项目体检

                break;
            case R.id.bt_tiaozhengtijianxiangmu://调整体检项目
                Intent intent = new Intent(this, AdjustmentProjectActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_kankanqitajigou://看看其他机构

                break;
            case R.id.img_useCard_left:
                if (mViewPager_SetMeal.getCurrentItem() >= 0){
                    mViewPager_SetMeal.setCurrentItem(mViewPager_SetMeal.getCurrentItem() -1 , true);
                }

                if (mViewPager_SetMeal.getCurrentItem() == 0){
                    img_useCard_left.setVisibility(View.GONE);
                    img_useCard_right.setVisibility(View.VISIBLE);
                }else if (mViewPager_SetMeal.getCurrentItem() == fragments.size() -1){
                    img_useCard_right.setVisibility(View.GONE);
                    img_useCard_left.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.img_useCard_right:
                if (mViewPager_SetMeal.getCurrentItem() <= fragments.size() -1){
                    mViewPager_SetMeal.setCurrentItem(mViewPager_SetMeal.getCurrentItem() + 1, true);
                    img_useCard_left.setVisibility(View.VISIBLE);
                }

                if (mViewPager_SetMeal.getCurrentItem() == 0){
                    img_useCard_left.setVisibility(View.GONE);
                    img_useCard_right.setVisibility(View.VISIBLE);
                }else if (mViewPager_SetMeal.getCurrentItem() == fragments.size() -1){
                    img_useCard_right.setVisibility(View.GONE);
                    img_useCard_left.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "" + (position + 1), Toast.LENGTH_SHORT).show();
    }
}

package com.maqueezu.el.ui.activity.child.physicalexamination_child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.el.ui.adapter.FragmentAdapter;
import com.maqueezu.el.ui.adapter.FragmentAdapter2;
import com.maqueezu.el.ui.fragment.physicalexamination_child.CustomProblemFragment;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class CustomOneToOneActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;
    private ViewPager mViewPager_OneToOne;//Viewpager展示
    private Button bt_next;//下一步
    private TextView tv_jiankangzhuangkuang;
    private AutoRelativeLayout rl_jiankangzhuangkuang;
    private TextView tv_jibingshi;
    private AutoRelativeLayout rl_jibingshi;
    private TextView tv_shenghuoxiguan;
    private AutoRelativeLayout rl_shenghuoxiguan;
    private AutoLinearLayout ll_base_1;

    private List<Fragment> fragments;
    private int currIndex = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_one_to_one);

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
        mViewPager_OneToOne = (ViewPager) findViewById(R.id.mViewPager_OneToOne);
        mViewPager_OneToOne.setOnClickListener(this);
        bt_next = (Button) findViewById(R.id.bt_next);
        bt_next.setOnClickListener(this);
        tv_jiankangzhuangkuang = (TextView) findViewById(R.id.tv_jiankangzhuangkuang);
        tv_jiankangzhuangkuang.setOnClickListener(this);
        rl_jiankangzhuangkuang = (AutoRelativeLayout) findViewById(R.id.rl_jiankangzhuangkuang);
        rl_jiankangzhuangkuang.setOnClickListener(this);
        tv_jibingshi = (TextView) findViewById(R.id.tv_jibingshi);
        tv_jibingshi.setOnClickListener(this);
        rl_jibingshi = (AutoRelativeLayout) findViewById(R.id.rl_jibingshi);
        rl_jibingshi.setOnClickListener(this);
        tv_shenghuoxiguan = (TextView) findViewById(R.id.tv_shenghuoxiguan);
        tv_shenghuoxiguan.setOnClickListener(this);
        rl_shenghuoxiguan = (AutoRelativeLayout) findViewById(R.id.rl_shenghuoxiguan);
        rl_shenghuoxiguan.setOnClickListener(this);
        ll_base_1 = (AutoLinearLayout) findViewById(R.id.ll_base_1);
        ll_base_1.setOnClickListener(this);

    }

    private void initData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        title_text.setText(title);

        fragments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CustomProblemFragment fragment = CustomProblemFragment.newInstance("item"+i);
            fragments.add(fragment);
        }


        FragmentAdapter2 adapter = new FragmentAdapter2(getSupportFragmentManager(), fragments);
        mViewPager_OneToOne.setAdapter(adapter);

        mViewPager_OneToOne.setOffscreenPageLimit(2);
        mViewPager_OneToOne.setCurrentItem(0);
    }


    private void initListener() {
//        bt_next.setBackgroundColor(R.color.def_title_text_color);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
            case R.id.title_back_image:
                finish();
                break;
            case R.id.bt_next:

                Intent intent = new Intent(this, CustomExpertActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_jiankangzhuangkuang:
            case R.id.tv_jiankangzhuangkuang:
                mViewPager_OneToOne.setCurrentItem(0);
                break;
            case R.id.rl_jibingshi:
            case R.id.tv_jibingshi:
                mViewPager_OneToOne.setCurrentItem(1);
                break;
            case R.id.rl_shenghuoxiguan:
            case R.id.tv_shenghuoxiguan:
                mViewPager_OneToOne.setCurrentItem(2);
                break;

            default:
                break;
        }
    }

    /**
     * 头标点击监听
     * @author weizhi
     * @version 1.0
     */
    public class MyOnClickListener implements View.OnClickListener{
        private int index = 0 ;
        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mViewPager_OneToOne.setCurrentItem(index);
        }
    }

    /**
     * 页卡切换监听
     * @author weizhi
     * @version 1.0
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageSelected(int position) {
            switch (position){
                //当前为页卡1
                case 0:
                    //从页卡1跳转转到页卡2
                    if(currIndex == 1){
                        resetTextViewTextColor();
                        tv_jibingshi.setTextColor(getResources().getColor(R.color.white));
                    }else if(currIndex == 2){//从页卡1跳转转到页卡3
                        resetTextViewTextColor();
                        tv_shenghuoxiguan.setTextColor(getResources().getColor(R.color.white));
                    }
                    break;

                //当前为页卡2
                case 1:
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        resetTextViewTextColor();
                        tv_jiankangzhuangkuang.setTextColor(getResources().getColor(R.color.black));
                    } else if (currIndex == 2) { //从页卡1跳转转到页卡2
                        resetTextViewTextColor();
                        tv_shenghuoxiguan.setTextColor(getResources().getColor(R.color.white));
                    }
                    break;

                //当前为页卡3
                case 2:
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        resetTextViewTextColor();
                        tv_jiankangzhuangkuang.setTextColor(getResources().getColor(R.color.black));
                    } else if (currIndex == 1) {//从页卡1跳转转到页卡2
                        resetTextViewTextColor();
                        tv_jibingshi.setTextColor(getResources().getColor(R.color.white));
                    }
                    break;
                default:
                    break;
            }
            currIndex = position;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * 将顶部文字恢复默认值
     */
    private void resetTextViewTextColor(){

        tv_jiankangzhuangkuang.setTextColor(getResources().getColor(R.color.black));
        tv_jibingshi.setTextColor(getResources().getColor(R.color.white));
        tv_shenghuoxiguan.setTextColor(getResources().getColor(R.color.white));
    }

}

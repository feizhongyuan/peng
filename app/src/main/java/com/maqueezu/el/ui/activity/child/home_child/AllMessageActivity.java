package com.maqueezu.el.ui.activity.child.home_child;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

public class AllMessageActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;
    private ImageView img_guanfang_img;
    private TextView tv_guanfangxiaoxi;
    private TextView tv_guanfangxiaoxi_time;
    private TextView tv_guanfangxiaoxi_details;
    private AutoRelativeLayout rl_guanfangxiaoxi;
    private ImageView img_zhuanghu_img;
    private TextView tv_zhaunghutongzhi;
    private TextView tv_zhaunghutongzhi_time;
    private TextView tv_zhaunghutongzhi_details;
    private AutoRelativeLayout rl_zhanghutongzhi;
    private ImageView img_fuwu_img;
    private TextView tv_fuwutongzhi;
    private TextView tv_fuwutongzhi_time;
    private TextView tv_fuwutongzhi_details;
    private AutoRelativeLayout rl_fuwutongzhi;
    private ImageView img_wuliu_img;
    private TextView tv_wuliuzhushou;
    private TextView tv_wuliuzhushou_time;
    private TextView tv_wuliuzhushou_details;
    private AutoRelativeLayout rl_wuliuzhushou;
    private TextView hudongxiaoxi;
    private ImageView img_pingtai_img;
    private TextView tv_pingtaikefu;
    private TextView tv_pingtaikefu_time;
    private TextView tv_pingtaikefu_details;
    private AutoRelativeLayout rl_pingtaikefu;
    private ImageView img_yixue_img;
    private TextView tv_yixuekefu;
    private TextView tv_yixuekefu_time;
    private TextView tv_yixuekefu_details;
    private AutoRelativeLayout rl_yixuekefu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_message);

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
        img_guanfang_img = (ImageView) findViewById(R.id.img_guanfang_img);
        img_guanfang_img.setOnClickListener(this);
        tv_guanfangxiaoxi = (TextView) findViewById(R.id.tv_guanfangxiaoxi);
        tv_guanfangxiaoxi.setOnClickListener(this);
        tv_guanfangxiaoxi_time = (TextView) findViewById(R.id.tv_guanfangxiaoxi_time);
        tv_guanfangxiaoxi_time.setOnClickListener(this);
        tv_guanfangxiaoxi_details = (TextView) findViewById(R.id.tv_guanfangxiaoxi_details);
        tv_guanfangxiaoxi_details.setOnClickListener(this);
        rl_guanfangxiaoxi = (AutoRelativeLayout) findViewById(R.id.rl_guanfangxiaoxi);
        rl_guanfangxiaoxi.setOnClickListener(this);
        img_zhuanghu_img = (ImageView) findViewById(R.id.img_zhuanghu_img);
        img_zhuanghu_img.setOnClickListener(this);
        tv_zhaunghutongzhi = (TextView) findViewById(R.id.tv_zhaunghutongzhi);
        tv_zhaunghutongzhi.setOnClickListener(this);
        tv_zhaunghutongzhi_time = (TextView) findViewById(R.id.tv_zhaunghutongzhi_time);
        tv_zhaunghutongzhi_time.setOnClickListener(this);
        tv_zhaunghutongzhi_details = (TextView) findViewById(R.id.tv_zhaunghutongzhi_details);
        tv_zhaunghutongzhi_details.setOnClickListener(this);
        rl_zhanghutongzhi = (AutoRelativeLayout) findViewById(R.id.rl_zhanghutongzhi);
        rl_zhanghutongzhi.setOnClickListener(this);
        img_fuwu_img = (ImageView) findViewById(R.id.img_fuwu_img);
        img_fuwu_img.setOnClickListener(this);
        tv_fuwutongzhi = (TextView) findViewById(R.id.tv_fuwutongzhi);
        tv_fuwutongzhi.setOnClickListener(this);
        tv_fuwutongzhi_time = (TextView) findViewById(R.id.tv_fuwutongzhi_time);
        tv_fuwutongzhi_time.setOnClickListener(this);
        tv_fuwutongzhi_details = (TextView) findViewById(R.id.tv_fuwutongzhi_details);
        tv_fuwutongzhi_details.setOnClickListener(this);
        rl_fuwutongzhi = (AutoRelativeLayout) findViewById(R.id.rl_fuwutongzhi);
        rl_fuwutongzhi.setOnClickListener(this);
        img_wuliu_img = (ImageView) findViewById(R.id.img_wuliu_img);
        img_wuliu_img.setOnClickListener(this);
        tv_wuliuzhushou = (TextView) findViewById(R.id.tv_wuliuzhushou);
        tv_wuliuzhushou.setOnClickListener(this);
        tv_wuliuzhushou_time = (TextView) findViewById(R.id.tv_wuliuzhushou_time);
        tv_wuliuzhushou_time.setOnClickListener(this);
        tv_wuliuzhushou_details = (TextView) findViewById(R.id.tv_wuliuzhushou_details);
        tv_wuliuzhushou_details.setOnClickListener(this);
        rl_wuliuzhushou = (AutoRelativeLayout) findViewById(R.id.rl_wuliuzhushou);
        rl_wuliuzhushou.setOnClickListener(this);
        hudongxiaoxi = (TextView) findViewById(R.id.hudongxiaoxi);
        hudongxiaoxi.setOnClickListener(this);
        img_pingtai_img = (ImageView) findViewById(R.id.img_pingtai_img);
        img_pingtai_img.setOnClickListener(this);
        tv_pingtaikefu = (TextView) findViewById(R.id.tv_pingtaikefu);
        tv_pingtaikefu.setOnClickListener(this);
        tv_pingtaikefu_time = (TextView) findViewById(R.id.tv_pingtaikefu_time);
        tv_pingtaikefu_time.setOnClickListener(this);
        tv_pingtaikefu_details = (TextView) findViewById(R.id.tv_pingtaikefu_details);
        tv_pingtaikefu_details.setOnClickListener(this);
        rl_pingtaikefu = (AutoRelativeLayout) findViewById(R.id.rl_pingtaikefu);
        rl_pingtaikefu.setOnClickListener(this);
        img_yixue_img = (ImageView) findViewById(R.id.img_yixue_img);
        img_yixue_img.setOnClickListener(this);
        tv_yixuekefu = (TextView) findViewById(R.id.tv_yixuekefu);
        tv_yixuekefu.setOnClickListener(this);
        tv_yixuekefu_time = (TextView) findViewById(R.id.tv_yixuekefu_time);
        tv_yixuekefu_time.setOnClickListener(this);
        tv_yixuekefu_details = (TextView) findViewById(R.id.tv_yixuekefu_details);
        tv_yixuekefu_details.setOnClickListener(this);
        rl_yixuekefu = (AutoRelativeLayout) findViewById(R.id.rl_yixuekefu);
        rl_yixuekefu.setOnClickListener(this);
    }

    private void initDate() {
        title_text.setText(R.string.name_xiaoxi);
    }

    private void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_layout:
            case R.id.title_back_image:
                finish();
                break;
        }
    }
}

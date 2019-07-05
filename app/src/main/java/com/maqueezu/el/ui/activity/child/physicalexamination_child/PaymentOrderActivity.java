package com.maqueezu.el.ui.activity.child.physicalexamination_child;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 支付订单
 */
public class PaymentOrderActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private AutoRelativeLayout rl_statusbar;
    private TextView tv_paymentOrder_number;
    private TextView tv_paymentStatus;
    private ImageView img_paymentOrder_img;
    private TextView tv_paymentOrder_title;
    private TextView tv_paymentOrder_price;
    private TextView tv_paymentOrder_count;
    private AutoRelativeLayout rl_base_1;
    private TextView dingdanbianma;
    private TextView tv_paymentOrder_number1;
    private TextView xiadanshijian;
    private TextView tv_paymentOrder_time;
    private AutoRelativeLayout rl_base_2;
    private TextView tv_paymentOrder_price1;
    private AutoRelativeLayout rl_base_3;
    private TextView youhui;
    private TextView tv_youhuijine;
    private AutoRelativeLayout rl_base_4;
    private TextView maquebi;
    private AutoRelativeLayout rl_base_5;
    private TextView tv_paymentOrder_price2;
    private AutoRelativeLayout rl_base_6;
    private TextView tv_yuezhifu;
    private ImageView img_yuezhifu;
    private AutoRelativeLayout rl_zhifu_1;
    private TextView tv_weixinzhifu;
    private ImageView img_weixinzhifu;
    private AutoRelativeLayout rl_zhifu_2;
    private TextView tv_zhifubaozhifu;
    private ImageView img_zhifubaozhifu;
    private AutoRelativeLayout rl_zhifu_3;
    private TextView tv_paymentOrder_price3;
    private TextView xufukuan;
    private TextView tv_zhifu;
    private AutoRelativeLayout rl_zhifu;
    private AutoLinearLayout ll_base_1;
    private TextView tv_fuzhidanhao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_order);

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
        tv_paymentOrder_number = (TextView) findViewById(R.id.tv_paymentOrder_number);
        tv_paymentOrder_number.setOnClickListener(this);
        tv_paymentStatus = (TextView) findViewById(R.id.tv_paymentStatus);
        tv_paymentStatus.setOnClickListener(this);
        img_paymentOrder_img = (ImageView) findViewById(R.id.img_paymentOrder_img);
        img_paymentOrder_img.setOnClickListener(this);
        tv_paymentOrder_title = (TextView) findViewById(R.id.tv_paymentOrder_title);
        tv_paymentOrder_title.setOnClickListener(this);
        tv_paymentOrder_price = (TextView) findViewById(R.id.tv_paymentOrder_price);
        tv_paymentOrder_price.setOnClickListener(this);
        tv_paymentOrder_count = (TextView) findViewById(R.id.tv_paymentOrder_count);
        tv_paymentOrder_count.setOnClickListener(this);
        rl_base_1 = (AutoRelativeLayout) findViewById(R.id.rl_base_1);
        rl_base_1.setOnClickListener(this);
        dingdanbianma = (TextView) findViewById(R.id.dingdanbianma);
        dingdanbianma.setOnClickListener(this);
        tv_paymentOrder_number1 = (TextView) findViewById(R.id.tv_paymentOrder_number1);
        tv_paymentOrder_number1.setOnClickListener(this);
        xiadanshijian = (TextView) findViewById(R.id.xiadanshijian);
        xiadanshijian.setOnClickListener(this);
        tv_paymentOrder_time = (TextView) findViewById(R.id.tv_paymentOrder_time);
        tv_paymentOrder_time.setOnClickListener(this);
        rl_base_2 = (AutoRelativeLayout) findViewById(R.id.rl_base_2);
        rl_base_2.setOnClickListener(this);
        tv_paymentOrder_price1 = (TextView) findViewById(R.id.tv_paymentOrder_price1);
        tv_paymentOrder_price1.setOnClickListener(this);
        rl_base_3 = (AutoRelativeLayout) findViewById(R.id.rl_base_3);
        rl_base_3.setOnClickListener(this);
        youhui = (TextView) findViewById(R.id.youhui);
        youhui.setOnClickListener(this);
        tv_youhuijine = (TextView) findViewById(R.id.tv_youhuijine);
        tv_youhuijine.setOnClickListener(this);
        rl_base_4 = (AutoRelativeLayout) findViewById(R.id.rl_base_4);
        rl_base_4.setOnClickListener(this);
        maquebi = (TextView) findViewById(R.id.maquebi);
        maquebi.setOnClickListener(this);
        rl_base_5 = (AutoRelativeLayout) findViewById(R.id.rl_base_5);
        rl_base_5.setOnClickListener(this);
        tv_paymentOrder_price2 = (TextView) findViewById(R.id.tv_paymentOrder_price2);
        tv_paymentOrder_price2.setOnClickListener(this);
        rl_base_6 = (AutoRelativeLayout) findViewById(R.id.rl_base_6);
        rl_base_6.setOnClickListener(this);
        tv_yuezhifu = (TextView) findViewById(R.id.tv_yuezhifu);
        tv_yuezhifu.setOnClickListener(this);
        img_yuezhifu = (ImageView) findViewById(R.id.img_yuezhifu);
        img_yuezhifu.setOnClickListener(this);
        rl_zhifu_1 = (AutoRelativeLayout) findViewById(R.id.rl_zhifu_1);
        rl_zhifu_1.setOnClickListener(this);
        tv_weixinzhifu = (TextView) findViewById(R.id.tv_weixinzhifu);
        tv_weixinzhifu.setOnClickListener(this);
        img_weixinzhifu = (ImageView) findViewById(R.id.img_weixinzhifu);
        img_weixinzhifu.setOnClickListener(this);
        rl_zhifu_2 = (AutoRelativeLayout) findViewById(R.id.rl_zhifu_2);
        rl_zhifu_2.setOnClickListener(this);
        tv_zhifubaozhifu = (TextView) findViewById(R.id.tv_zhifubaozhifu);
        tv_zhifubaozhifu.setOnClickListener(this);
        img_zhifubaozhifu = (ImageView) findViewById(R.id.img_zhifubaozhifu);
        img_zhifubaozhifu.setOnClickListener(this);
        rl_zhifu_3 = (AutoRelativeLayout) findViewById(R.id.rl_zhifu_3);
        rl_zhifu_3.setOnClickListener(this);
        tv_paymentOrder_price3 = (TextView) findViewById(R.id.tv_paymentOrder_price3);
        tv_paymentOrder_price3.setOnClickListener(this);
        xufukuan = (TextView) findViewById(R.id.xufukuan);
        xufukuan.setOnClickListener(this);
        tv_zhifu = (TextView) findViewById(R.id.tv_zhifu);
        tv_zhifu.setOnClickListener(this);
        rl_zhifu = (AutoRelativeLayout) findViewById(R.id.rl_zhifu);
        rl_zhifu.setOnClickListener(this);
        ll_base_1 = (AutoLinearLayout) findViewById(R.id.ll_base_1);
        ll_base_1.setOnClickListener(this);
        tv_fuzhidanhao = (TextView) findViewById(R.id.tv_fuzhidanhao);
        tv_fuzhidanhao.setOnClickListener(this);
    }

    private void initDate() {

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
            case R.id.tv_fuzhidanhao:
                String orderNumber = tv_paymentOrder_number1.getText().toString();
                copy(this,orderNumber);
                break;

        }
    }

    public static void copy(Context context,String s) {
// 得到剪贴板管理器
        ClipboardManager cbm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cbm.setText(s.trim());
        Toast.makeText(context, "复制成功", Toast.LENGTH_SHORT).show();
    }

}

package com.maqueezu.el.ui.activity.child.physicalexamination_child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.utils.ui.base.OtherBaseActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 提交订单
 */
public class SubmitOrderActivity extends OtherBaseActivity implements View.OnClickListener{

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private AutoRelativeLayout rl_statusbar;
    private ImageView img_submitOrder_img;
    private TextView tv_submitOrder_title;
    private TextView tv_submitOrder_price;
    private TextView tv_submitOrder_count;
    private AutoRelativeLayout rl_base_1;
    private TextView tv_taocaozongjia_price;
    private AutoRelativeLayout rl_base_2;
    private TextView youhui;
    private TextView tv_youhuijuan;
    private AutoRelativeLayout rl_base_3;
    private TextView maquebi;
    private AutoRelativeLayout rl_base_4;
    private TextView tv_OrderAmount;
    private AutoRelativeLayout rl_base_5;
    private TextView heji;
    private TextView tv_OrderAmount1;
    private TextView tv_tijiaodingdan;
    private AutoRelativeLayout rl_tijiaodingdan;
    private AdvertBean.DataBean.AdvListBean advListBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_submit_order;
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
        rl_statusbar = (AutoRelativeLayout) findViewById(R.id.rl_statusbar);
        rl_statusbar.setOnClickListener(this);
        img_submitOrder_img = (ImageView) findViewById(R.id.img_submitOrder_img);
        img_submitOrder_img.setOnClickListener(this);
        tv_submitOrder_title = (TextView) findViewById(R.id.tv_submitOrder_title);
        tv_submitOrder_title.setOnClickListener(this);
        tv_submitOrder_price = (TextView) findViewById(R.id.tv_submitOrder_price);
        tv_submitOrder_price.setOnClickListener(this);
        tv_submitOrder_count = (TextView) findViewById(R.id.tv_submitOrder_count);
        tv_submitOrder_count.setOnClickListener(this);
        rl_base_1 = (AutoRelativeLayout) findViewById(R.id.rl_base_1);
        rl_base_1.setOnClickListener(this);
        tv_taocaozongjia_price = (TextView) findViewById(R.id.tv_taocaozongjia_price);
        tv_taocaozongjia_price.setOnClickListener(this);
        rl_base_2 = (AutoRelativeLayout) findViewById(R.id.rl_base_2);
        rl_base_2.setOnClickListener(this);
        youhui = (TextView) findViewById(R.id.youhui);
        youhui.setOnClickListener(this);
        tv_youhuijuan = (TextView) findViewById(R.id.tv_youhuijuan);
        tv_youhuijuan.setOnClickListener(this);
        rl_base_3 = (AutoRelativeLayout) findViewById(R.id.rl_base_3);
        rl_base_3.setOnClickListener(this);
        maquebi = (TextView) findViewById(R.id.maquebi);
        maquebi.setOnClickListener(this);
        rl_base_4 = (AutoRelativeLayout) findViewById(R.id.rl_base_4);
        rl_base_4.setOnClickListener(this);
        tv_OrderAmount = (TextView) findViewById(R.id.tv_OrderAmount);
        tv_OrderAmount.setOnClickListener(this);
        rl_base_5 = (AutoRelativeLayout) findViewById(R.id.rl_base_5);
        rl_base_5.setOnClickListener(this);
        heji = (TextView) findViewById(R.id.heji);
        heji.setOnClickListener(this);
        tv_OrderAmount1 = (TextView) findViewById(R.id.tv_OrderAmount1);
        tv_OrderAmount1.setOnClickListener(this);
        tv_tijiaodingdan = (TextView) findViewById(R.id.tv_tijiaodingdan);
        tv_tijiaodingdan.setOnClickListener(this);
        rl_tijiaodingdan = (AutoRelativeLayout) findViewById(R.id.rl_tijiaodingdan);
        rl_tijiaodingdan.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        advListBean = (AdvertBean.DataBean.AdvListBean) intent.getSerializableExtra("date");
        title_text.setText(R.string.name_tijiaodingdan);

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
            case R.id.rl_tijiaodingdan:
            case R.id.tv_tijiaodingdan:
                Intent intent = new Intent(this,PaymentOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("date",advListBean);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}

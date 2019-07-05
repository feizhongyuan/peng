package com.maqueezu.el.ui.activity.child.shopping_child;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 商品提交订单页
 */
public class GoodsSubmitOrderActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;
    private ImageView tishitu;
    private TextView dizhitishi1;
    private TextView dizhitishi2;
    private TextView dizhitishi3;
    private AutoRelativeLayout rl_dizhitishi;
    private ImageView img_add;
    private AutoLinearLayout ll_base_1;
    private RecyclerView mRecycler_goodsOrder;
    private AutoRelativeLayout rl_base_2;
    private TextView tv_taocaozongjia_price;
    private AutoRelativeLayout rl_base_3;
    private TextView youhui;
    private TextView tv_youhuijuan;
    private AutoRelativeLayout rl_base_4;
    private TextView maquebi;
    private AutoRelativeLayout rl_base_5;
    private TextView tv_OrderAmount;
    private AutoRelativeLayout rl_base_6;
    private TextView tv_OrderAmount1;
    private TextView heji;
    private TextView tv_tijiaodingdan;
    private AutoRelativeLayout rl_tijiaodingdan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_submit_order);

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
        tishitu = (ImageView) findViewById(R.id.tishitu);
        tishitu.setOnClickListener(this);
        dizhitishi1 = (TextView) findViewById(R.id.dizhitishi1);
        dizhitishi1.setOnClickListener(this);
        dizhitishi2 = (TextView) findViewById(R.id.dizhitishi2);
        dizhitishi2.setOnClickListener(this);
        dizhitishi3 = (TextView) findViewById(R.id.dizhitishi3);
        dizhitishi3.setOnClickListener(this);
        rl_dizhitishi = (AutoRelativeLayout) findViewById(R.id.rl_dizhitishi);
        rl_dizhitishi.setOnClickListener(this);
        img_add = (ImageView) findViewById(R.id.img_add);
        img_add.setOnClickListener(this);
        ll_base_1 = (AutoLinearLayout) findViewById(R.id.ll_base_1);
        ll_base_1.setOnClickListener(this);
        mRecycler_goodsOrder = (RecyclerView) findViewById(R.id.mRecycler_goodsOrder);
        rl_base_2 = (AutoRelativeLayout) findViewById(R.id.rl_base_2);
        rl_base_2.setOnClickListener(this);
        tv_taocaozongjia_price = (TextView) findViewById(R.id.tv_taocaozongjia_price);
        tv_taocaozongjia_price.setOnClickListener(this);
        rl_base_3 = (AutoRelativeLayout) findViewById(R.id.rl_base_3);
        rl_base_3.setOnClickListener(this);
        youhui = (TextView) findViewById(R.id.youhui);
        youhui.setOnClickListener(this);
        tv_youhuijuan = (TextView) findViewById(R.id.tv_youhuijuan);
        tv_youhuijuan.setOnClickListener(this);
        rl_base_4 = (AutoRelativeLayout) findViewById(R.id.rl_base_4);
        rl_base_4.setOnClickListener(this);
        maquebi = (TextView) findViewById(R.id.maquebi);
        maquebi.setOnClickListener(this);
        rl_base_5 = (AutoRelativeLayout) findViewById(R.id.rl_base_5);
        rl_base_5.setOnClickListener(this);
        tv_OrderAmount = (TextView) findViewById(R.id.tv_OrderAmount);
        tv_OrderAmount.setOnClickListener(this);
        rl_base_6 = (AutoRelativeLayout) findViewById(R.id.rl_base_6);
        rl_base_6.setOnClickListener(this);
        tv_OrderAmount1 = (TextView) findViewById(R.id.tv_OrderAmount1);
        tv_OrderAmount1.setOnClickListener(this);
        heji = (TextView) findViewById(R.id.heji);
        heji.setOnClickListener(this);
        tv_tijiaodingdan = (TextView) findViewById(R.id.tv_tijiaodingdan);
        tv_tijiaodingdan.setOnClickListener(this);
        rl_tijiaodingdan = (AutoRelativeLayout) findViewById(R.id.rl_tijiaodingdan);
        rl_tijiaodingdan.setOnClickListener(this);

        mRecycler_goodsOrder.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initDate() {
        title_text.setText(R.string.name_tijiaodingdan);

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
            case R.id.img_add:
            case R.id.tishitu:
            case R.id.dizhitishi1:
            case R.id.dizhitishi2:
            case R.id.dizhitishi3:
            case R.id.rl_dizhitishi:
                Toast.makeText(this, "添加地址", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}

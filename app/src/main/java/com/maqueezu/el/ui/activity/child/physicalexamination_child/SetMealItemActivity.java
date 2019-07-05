package com.maqueezu.el.ui.activity.child.physicalexamination_child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.ui.activity.child.physicalexamination_child.SubmitOrderActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 套餐详情
 */
public class SetMealItemActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView img_setmeal_item_tu;
    private ImageView img_setmeal_item_back;
    private ImageView img_setmeal_item_share;
    private TextView tv_setmeal_item_title;
    private TextView tv_setmeal_item_title1;
    private TextView tv_setmeal_item_price;
    private TextView tv_setmeal_item_sum;
    private TextView tv_setmeal_item_note;
    private AutoRelativeLayout rl_base_1;
    private ImageView img_setmeal_item_xiangqing;
    private AutoRelativeLayout rl_base_xiangqing;
    private AutoRelativeLayout rl_base_taocanpingjia;
    private ImageView img_setmeal_item_jigou;
    private TextView tv_setmeal_item_jigou;
    private AutoLinearLayout rL_base_jigou;
    private ImageView img_setmeal_item_kefu;
    private TextView tv_setmeal_item_kefu;
    private AutoLinearLayout rL_base_kefu;
    private TextView tv_setmeal_item_buy;
    private AutoRelativeLayout rl_base_buy;
    private AdvertBean.DataBean.AdvListBean advListBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_meal_item);

        initView();
        initDate();
        initListener();
    }

    private void initView() {

        img_setmeal_item_tu = (ImageView) findViewById(R.id.img_setmeal_item_tu);
        img_setmeal_item_tu.setOnClickListener(this);
        img_setmeal_item_back = (ImageView) findViewById(R.id.img_setmeal_item_back);
        img_setmeal_item_back.setOnClickListener(this);
        img_setmeal_item_share = (ImageView) findViewById(R.id.img_setmeal_item_share);
        img_setmeal_item_share.setOnClickListener(this);
        tv_setmeal_item_title = (TextView) findViewById(R.id.tv_setmeal_item_title);
        tv_setmeal_item_title.setOnClickListener(this);
        tv_setmeal_item_title1 = (TextView) findViewById(R.id.tv_setmeal_item_title1);
        tv_setmeal_item_title1.setOnClickListener(this);
        tv_setmeal_item_price = (TextView) findViewById(R.id.tv_setmeal_item_price);
        tv_setmeal_item_price.setOnClickListener(this);
        tv_setmeal_item_sum = (TextView) findViewById(R.id.tv_setmeal_item_sum);
        tv_setmeal_item_sum.setOnClickListener(this);
        tv_setmeal_item_note = (TextView) findViewById(R.id.tv_setmeal_item_note);
        tv_setmeal_item_note.setOnClickListener(this);
        rl_base_1 = (AutoRelativeLayout) findViewById(R.id.rl_base_1);
        rl_base_1.setOnClickListener(this);
        img_setmeal_item_xiangqing = (ImageView) findViewById(R.id.img_setmeal_item_xiangqing);
        img_setmeal_item_xiangqing.setOnClickListener(this);
        rl_base_xiangqing = (AutoRelativeLayout) findViewById(R.id.rl_base_xiangqing);
        rl_base_xiangqing.setOnClickListener(this);
        rl_base_taocanpingjia = (AutoRelativeLayout) findViewById(R.id.rl_base_taocanpingjia);
        rl_base_taocanpingjia.setOnClickListener(this);
        img_setmeal_item_jigou = (ImageView) findViewById(R.id.img_setmeal_item_jigou);
        img_setmeal_item_jigou.setOnClickListener(this);
        tv_setmeal_item_jigou = (TextView) findViewById(R.id.tv_setmeal_item_jigou);
        tv_setmeal_item_jigou.setOnClickListener(this);
        rL_base_jigou = (AutoLinearLayout) findViewById(R.id.rL_base_jigou);
        rL_base_jigou.setOnClickListener(this);
        img_setmeal_item_kefu = (ImageView) findViewById(R.id.img_setmeal_item_kefu);
        img_setmeal_item_kefu.setOnClickListener(this);
        tv_setmeal_item_kefu = (TextView) findViewById(R.id.tv_setmeal_item_kefu);
        tv_setmeal_item_kefu.setOnClickListener(this);
        rL_base_kefu = (AutoLinearLayout) findViewById(R.id.rL_base_kefu);
        rL_base_kefu.setOnClickListener(this);
        tv_setmeal_item_buy = (TextView) findViewById(R.id.tv_setmeal_item_buy);
        tv_setmeal_item_buy.setOnClickListener(this);
        rl_base_buy = (AutoRelativeLayout) findViewById(R.id.rl_base_buy);
        rl_base_buy.setOnClickListener(this);
    }

    private void initDate() {
        Intent intent = getIntent();
        advListBean = (AdvertBean.DataBean.AdvListBean) intent.getSerializableExtra("date");

        Glide.with(this).load(advListBean.getAtturl()).into(img_setmeal_item_tu);
        Glide.with(this).load(advListBean.getAtturl()).into(img_setmeal_item_xiangqing);
        tv_setmeal_item_title.setText(advListBean.getAname());
        tv_setmeal_item_title1.setText(advListBean.getAname());
        tv_setmeal_item_price.setText("￥"+ advListBean.getAid());
        tv_setmeal_item_sum.setText("已售 "+ advListBean.getAcid());
        tv_setmeal_item_note.setText(advListBean.getAtturl());
    }

    private void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_setmeal_item_back://返回
                finish();
                break;
            case R.id.img_setmeal_item_share://分享
                break;
            case R.id.rl_base_taocanpingjia://评价
                break;
            case R.id.rL_base_jigou://机构
            case R.id.img_setmeal_item_jigou:
            case R.id.tv_setmeal_item_jigou:
                break;
            case R.id.rL_base_kefu://客服
            case R.id.img_setmeal_item_kefu:
            case R.id.tv_setmeal_item_kefu:
                break;
            case R.id.rl_base_buy://立即购买
            case R.id.tv_setmeal_item_buy:
                Intent intent = new Intent(this,SubmitOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("date",advListBean);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}

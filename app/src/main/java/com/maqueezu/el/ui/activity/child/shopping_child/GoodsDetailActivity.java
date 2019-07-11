package com.maqueezu.el.ui.activity.child.shopping_child;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 商品详情页
 */
public class GoodsDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_goodsDetail_item_tu;//详情展示图
    private TextView tv_goodsDetail_item_title;//商品名称
    private TextView tv_goodsDetail_item_title1;//副标题
    private TextView tv_goodsDetail_item_price;//当前价格（红色）
    private TextView tv_goodsDetail_item_express;//快递费
    private TextView tv_goodsDetail_item_market;//市场价字符
    private TextView tv_goodsDetail_item_marketPrice;//市场价格
    private TextView tv_goodsDetail_item_sum;//已售数量
    private TextView tv_goodsDetail_item_note;//店铺名称
    private TextView tv_goodsDetail_item_select;//规格
    private ImageView img_goodsDetail_item_select;//规格详情跳转
    private AutoRelativeLayout rl_base_attribute; //规格属性模块
    private AutoRelativeLayout rl_base_1;//第一模块
    private ImageView img_goodsDetail_item_xiangqing;//详情图展示
    private AutoRelativeLayout rl_base_xiangqing;//第二模块详情图
    private TextView wenxintishi;//温馨提示字符
    private TextView wenxintishi_xiangqing;//温馨提示详情
    private AutoRelativeLayout rl_toolTips;//温馨提示模块
    private TextView tv_shangpinpingjia;//商品评价字符
    private TextView tv_shangpinpingjialv;//商品评价率
    private AutoRelativeLayout rl_base_shangpinpingjia;//商品评价模块
    private ImageView img_goodsDetail_item_kefu;
    private TextView tv_goodsDetail_item_kefu;
    private AutoLinearLayout rL_base_kefu;//客服
    private ImageView img_goodsDetail_item_shoucang;
    private TextView tv_goodsDetail_item_shoucang;
    private AutoLinearLayout rL_base_shoucang;//收藏
    private ImageView img_goodsDetail_item_gouwuche;
    private TextView tv_goodsDetail_item_gouwuche;
    private AutoLinearLayout rL_base_gouwuche;//购物车
    private TextView tv_goodsDetail_item_addShoppingCart;
    private AutoRelativeLayout rl_base_addShoppingCart;//加入购物车
    private TextView tv_goodsDetail_item_buy;
    private AutoRelativeLayout rl_base_buy;//立即购买
    private ImageView img_goodsDetail_item_back;//返回
    private ImageView img_goodsDetail_item_share;//分享


    private AdvertBean.DataBean.AdvListBean advListBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);

        initView();
        initDate();
        initListener();
    }

    private void initView() {
        img_goodsDetail_item_tu = (ImageView) findViewById(R.id.img_goodsDetail_item_tu);
        img_goodsDetail_item_tu.setOnClickListener(this);
        tv_goodsDetail_item_title = (TextView) findViewById(R.id.tv_goodsDetail_item_title);
        tv_goodsDetail_item_title.setOnClickListener(this);
        tv_goodsDetail_item_title1 = (TextView) findViewById(R.id.tv_goodsDetail_item_title1);
        tv_goodsDetail_item_title1.setOnClickListener(this);
        tv_goodsDetail_item_price = (TextView) findViewById(R.id.tv_goodsDetail_item_price);
        tv_goodsDetail_item_price.setOnClickListener(this);
        tv_goodsDetail_item_express = (TextView) findViewById(R.id.tv_goodsDetail_item_express);
        tv_goodsDetail_item_express.setOnClickListener(this);
        tv_goodsDetail_item_market = (TextView) findViewById(R.id.tv_goodsDetail_item_market);
        tv_goodsDetail_item_market.setOnClickListener(this);
        tv_goodsDetail_item_marketPrice = (TextView) findViewById(R.id.tv_goodsDetail_item_marketPrice);
        tv_goodsDetail_item_marketPrice.setOnClickListener(this);
        tv_goodsDetail_item_sum = (TextView) findViewById(R.id.tv_goodsDetail_item_sum);
        tv_goodsDetail_item_sum.setOnClickListener(this);
        tv_goodsDetail_item_note = (TextView) findViewById(R.id.tv_goodsDetail_item_note);
        tv_goodsDetail_item_note.setOnClickListener(this);
        tv_goodsDetail_item_select = (TextView) findViewById(R.id.tv_goodsDetail_item_select);
        tv_goodsDetail_item_select.setOnClickListener(this);
        img_goodsDetail_item_select = (ImageView) findViewById(R.id.img_goodsDetail_item_select);
        img_goodsDetail_item_select.setOnClickListener(this);
        rl_base_1 = (AutoRelativeLayout) findViewById(R.id.rl_base_1);
        rl_base_1.setOnClickListener(this);
        img_goodsDetail_item_xiangqing = (ImageView) findViewById(R.id.img_goodsDetail_item_xiangqing);
        img_goodsDetail_item_xiangqing.setOnClickListener(this);
        rl_base_xiangqing = (AutoRelativeLayout) findViewById(R.id.rl_base_xiangqing);
        rl_base_xiangqing.setOnClickListener(this);
        wenxintishi = (TextView) findViewById(R.id.wenxintishi);
        wenxintishi.setOnClickListener(this);
        rl_toolTips = (AutoRelativeLayout) findViewById(R.id.rl_toolTips);
        rl_toolTips.setOnClickListener(this);
        rl_base_shangpinpingjia = (AutoRelativeLayout) findViewById(R.id.rl_base_shangpinpingjia);
        rl_base_shangpinpingjia.setOnClickListener(this);
        img_goodsDetail_item_kefu = (ImageView) findViewById(R.id.img_goodsDetail_item_kefu);
        img_goodsDetail_item_kefu.setOnClickListener(this);
        tv_goodsDetail_item_kefu = (TextView) findViewById(R.id.tv_goodsDetail_item_kefu);
        tv_goodsDetail_item_kefu.setOnClickListener(this);
        rL_base_kefu = (AutoLinearLayout) findViewById(R.id.rL_base_kefu);
        rL_base_kefu.setOnClickListener(this);
        img_goodsDetail_item_shoucang = (ImageView) findViewById(R.id.img_goodsDetail_item_shoucang);
        img_goodsDetail_item_shoucang.setOnClickListener(this);
        tv_goodsDetail_item_shoucang = (TextView) findViewById(R.id.tv_goodsDetail_item_shoucang);
        tv_goodsDetail_item_shoucang.setOnClickListener(this);
        rL_base_shoucang = (AutoLinearLayout) findViewById(R.id.rL_base_shoucang);
        rL_base_shoucang.setOnClickListener(this);
        img_goodsDetail_item_gouwuche = (ImageView) findViewById(R.id.img_goodsDetail_item_gouwuche);
        img_goodsDetail_item_gouwuche.setOnClickListener(this);
        tv_goodsDetail_item_gouwuche = (TextView) findViewById(R.id.tv_goodsDetail_item_gouwuche);
        tv_goodsDetail_item_gouwuche.setOnClickListener(this);
        rL_base_gouwuche = (AutoLinearLayout) findViewById(R.id.rL_base_gouwuche);
        rL_base_gouwuche.setOnClickListener(this);
        tv_goodsDetail_item_addShoppingCart = (TextView) findViewById(R.id.tv_goodsDetail_item_addShoppingCart);
        tv_goodsDetail_item_addShoppingCart.setOnClickListener(this);
        rl_base_addShoppingCart = (AutoRelativeLayout) findViewById(R.id.rl_base_addShoppingCart);
        rl_base_addShoppingCart.setOnClickListener(this);
        tv_goodsDetail_item_buy = (TextView) findViewById(R.id.tv_goodsDetail_item_buy);
        tv_goodsDetail_item_buy.setOnClickListener(this);
        rl_base_buy = (AutoRelativeLayout) findViewById(R.id.rl_base_buy);
        rl_base_buy.setOnClickListener(this);
        img_goodsDetail_item_back = (ImageView) findViewById(R.id.img_goodsDetail_item_back);
        img_goodsDetail_item_back.setOnClickListener(this);
        img_goodsDetail_item_share = (ImageView) findViewById(R.id.img_goodsDetail_item_share);
        img_goodsDetail_item_share.setOnClickListener(this);
        wenxintishi_xiangqing = (TextView) findViewById(R.id.wenxintishi_xiangqing);
        wenxintishi_xiangqing.setOnClickListener(this);
        tv_shangpinpingjia = (TextView) findViewById(R.id.tv_shangpinpingjia);
        tv_shangpinpingjia.setOnClickListener(this);
        tv_shangpinpingjialv = (TextView) findViewById(R.id.tv_shangpinpingjialv);
        tv_shangpinpingjialv.setOnClickListener(this);
        rl_base_attribute = (AutoRelativeLayout) findViewById(R.id.rl_base_attribute);
        rl_base_attribute.setOnClickListener(this);
    }

    private void initDate() {
        Intent intent = getIntent();
        advListBean = (AdvertBean.DataBean.AdvListBean) intent.getSerializableExtra("date");

        Glide.with(this).load(advListBean.getAtturl()).into(img_goodsDetail_item_tu);
        Glide.with(this).load(advListBean.getAtturl()).into(img_goodsDetail_item_xiangqing);
        tv_goodsDetail_item_title.setText(advListBean.getAname());
        tv_goodsDetail_item_title1.setText(advListBean.getAname());
        tv_goodsDetail_item_price.setText("￥" + advListBean.getAid());
        tv_goodsDetail_item_marketPrice.setText("￥" + advListBean.getAid());
        tv_goodsDetail_item_express.setText("快递：0.00");
        tv_goodsDetail_item_sum.setText("已售 " + advListBean.getAcid());
        tv_goodsDetail_item_note.setText(advListBean.getAtturl());
    }

    private void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_goodsDetail_item_back:
                finish();
                break;
            case R.id.img_goodsDetail_item_share:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_base_attribute:
            case R.id.tv_goodsDetail_item_select:
            case R.id.img_goodsDetail_item_select:
                Toast.makeText(this, "规格选项", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_base_shangpinpingjia:
            case R.id.tv_shangpinpingjia:
            case R.id.tv_shangpinpingjialv:
                Toast.makeText(this, "商品评价", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rL_base_kefu:
            case R.id.img_goodsDetail_item_kefu:
            case R.id.tv_goodsDetail_item_kefu:
                Toast.makeText(this, "客服", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rL_base_shoucang:
            case R.id.img_goodsDetail_item_shoucang:
            case R.id.tv_goodsDetail_item_shoucang:
                Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rL_base_gouwuche:
            case R.id.img_goodsDetail_item_gouwuche:
            case R.id.tv_goodsDetail_item_gouwuche:
                Bundle bundle = new Bundle();
                bundle.putString("name", "购物车");
                bundle.putSerializable("advListBean", advListBean);
                multiplexIntent(this, bundle, ShoppingCartActivity.class);
                Toast.makeText(this, "购物车", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_base_addShoppingCart:
            case R.id.tv_goodsDetail_item_addShoppingCart:
                Bundle bundle1 = new Bundle();
                bundle1.putString("name", "购物车");
                bundle1.putSerializable("advListBean", advListBean);
                multiplexIntent(this, bundle1, ShoppingCartActivity.class);
                Toast.makeText(this, "加入购物车", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_base_buy:
            case R.id.tv_goodsDetail_item_buy:
                Toast.makeText(this, "立即购买", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,GoodsSubmitOrderActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }
    }

    private void multiplexIntent(Context context, Bundle bundle, Class cla) {
        Intent intent = new Intent(context,cla);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

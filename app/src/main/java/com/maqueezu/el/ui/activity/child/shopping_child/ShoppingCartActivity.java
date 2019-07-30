package com.maqueezu.el.ui.activity.child.shopping_child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.pojo.ShoppingCartBean;
import com.maqueezu.el.ui.adapter.ShoppingCartAdater;
import com.maqueezu.utils.ui.base.OtherBaseActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import ren.widget.refresh.SwipeMenuRefreshView;

/**
 * 购物车跳转页
 */
public class ShoppingCartActivity extends OtherBaseActivity implements View.OnClickListener, ShoppingCartAdater.CheckListener, ShoppingCartAdater.OnClickListenerModel {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;
    private LinearLayout ll_select_all;

    private Button btn_order;
    private AutoRelativeLayout rl_total_price;
    private AutoRelativeLayout rl_base_1;
    private ImageView img_no_contant;
    private AutoRelativeLayout rl_no_contant;
    private ListView mListView_shopping_cart;
    private TextView heji;
    private CheckBox cb_select_all;//全选
    private TextView tv_total_price;//总价
    private TextView tv_total_count;//总数量
    private SwipeMenuRefreshView mSwipeMenuRefresh;//侧滑控件

    private List<ShoppingCartBean> shoppingCartBeanList;
    private ShoppingCartAdater adater;
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopping_cart;
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
        ll_select_all = (LinearLayout) findViewById(R.id.ll_select_all);
        ll_select_all.setOnClickListener(this);
        btn_order = (Button) findViewById(R.id.btn_order);
        btn_order.setOnClickListener(this);
        tv_total_price = (TextView) findViewById(R.id.tv_total_price);
        tv_total_price.setOnClickListener(this);
        rl_total_price = (AutoRelativeLayout) findViewById(R.id.rl_total_price);
        rl_total_price.setOnClickListener(this);
        rl_base_1 = (AutoRelativeLayout) findViewById(R.id.rl_base_1);
        rl_base_1.setOnClickListener(this);
        img_no_contant = (ImageView) findViewById(R.id.img_no_contant);
        img_no_contant.setOnClickListener(this);
        rl_no_contant = (AutoRelativeLayout) findViewById(R.id.rl_no_contant);
        rl_no_contant.setOnClickListener(this);
        mListView_shopping_cart = (ListView) findViewById(R.id.mListView_shopping_cart);
        heji = (TextView) findViewById(R.id.heji);
        heji.setOnClickListener(this);
        tv_total_count = (TextView) findViewById(R.id.tv_total_count);
        tv_total_count.setOnClickListener(this);
        cb_select_all = (CheckBox) findViewById(R.id.cb_select_all);
        cb_select_all.setOnClickListener(this);
        mSwipeMenuRefresh = (SwipeMenuRefreshView) findViewById(R.id.mSwipeMenuRefresh);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        title_text.setText(name);

        AdvertBean.DataBean dataBean = (AdvertBean.DataBean) intent.getSerializableExtra("data");//商城模块传递的类
        AdvertBean.DataBean.AdvListBean advListBean = (AdvertBean.DataBean.AdvListBean) intent.getSerializableExtra("advListBean");//商城详情页传递的类

        shoppingCartBeanList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ShoppingCartBean shoppingCartBean = new ShoppingCartBean(0, "商品名称"+(i+1), "规格一", 1, 111.00, 1);
            shoppingCartBeanList.add(shoppingCartBean);
        }
        adater = new ShoppingCartAdater(this, shoppingCartBeanList);
        mListView_shopping_cart.setAdapter(adater);

        adater.setCheckListener(this);
        adater.setOnClickListenerModel(this);
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
            case R.id.cb_select_all:
                addElection();
                break;
            case R.id.btn_order://提交订单
                lementOnder();
                break;
            default:
                break;
        }
    }

    //    全选
    private void addElection() {
        if (shoppingCartBeanList.size() != 0) {
            if (cb_select_all.isChecked()) {
                for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                    shoppingCartBeanList.get(i).setChoosed(true);
                }
                adater.notifyDataSetChanged();
            } else {
                for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                    shoppingCartBeanList.get(i).setChoosed(false);
                }
                adater.notifyDataSetChanged();
            }
        }
        statistics();
    }

    @Override
    public void checkGroup(int position, boolean isChecked) {
        shoppingCartBeanList.get(position).setChoosed(isChecked);
        if (isAllCheck()) {
            cb_select_all.setChecked(true);
        } else {
            cb_select_all.setChecked(false);
            adater.notifyDataSetChanged();
            statistics();
        }
    }

    /**
     * 增加
     *
     * @param position 组元素位置
     * @param view     用于展示变化后数量的View
     * @param isFlang  子元素选中与否
     */
    @Override
    public void onItemIncreaseClick(int position, View view, boolean isFlang) {
        ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = shoppingCartBean.getCount();
        currentCount++;
        shoppingCartBean.setCount(currentCount);
        ((TextView) view).setText(currentCount + "");
        adater.notifyDataSetChanged();
        statistics();
    }

    /**
     * 删减
     *
     * @param position 组元素位置
     * @param view     用于展示变化后数量的View
     * @param isFlang  子元素选中与否
     */
    @Override
    public void onItemReduceClick(int position, View view, boolean isFlang) {
        ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = shoppingCartBean.getCount();
        if (currentCount == 1) {
            return;
        }
        currentCount--;
        shoppingCartBean.setCount(currentCount);
        ((TextView) view).setText(currentCount + "");
        adater.notifyDataSetChanged();
        statistics();
    }

    @Override
    public void onItemCountClick(View view, int index, int position, int num) {

    }

    /**
     * 删除
     *
     * @param position
     */
    @Override
    public void childDelete(int position) {
        shoppingCartBeanList.remove(position);
        adater.notifyDataSetChanged();
        statistics();
    }

    /**
     * 收藏
     *
     * @param position
     */
    @Override
    public void childCollection(int position) {
//        添加到收藏页
    }

    /**
     * 遍历list集合
     *
     * @return
     */
    private boolean isAllCheck() {

        for (ShoppingCartBean group : shoppingCartBeanList) {
            if (!group.isChoosed()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 统计操作
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作
     * 3.给底部的textView进行数据填充
     */
    public void statistics() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
            ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(i);
            if (shoppingCartBean.isChoosed()) {
                totalCount += shoppingCartBean.getCount();
                totalPrice += shoppingCartBean.getPrice() * shoppingCartBean.getCount();
            }
        }
        tv_total_price.setText("¥" + totalPrice);
        tv_total_count.setText("已选" + totalCount + "件");
    }

    /**
     * 结算订单、支付
     */
    private void lementOnder() {
        //选中的需要提交的商品清单
        for (ShoppingCartBean bean : shoppingCartBeanList) {
            boolean choosed = bean.isChoosed();
            if (choosed) {
                String shoppingName = bean.getShoppingName();
                int count = bean.getCount();
                double price = bean.getPrice();
                int size = bean.getDressSize();
                String attribute = bean.getAttribute();
                int id = bean.getId();
                Log.d("TAG", id + "----id---" + shoppingName + "---" + count + "---" + price + "--size----" + size + "--attr---" + attribute);
            }
        }
        Toast.makeText(this, "总价：" + totalPrice + "元", Toast.LENGTH_SHORT).show();

        //跳转到支付界面

        Intent intent = new Intent(this, GoodsSubmitOrderActivity.class);
        intent.putExtra("price", tv_total_price.getText().toString());
        startActivity(intent);

    }
}

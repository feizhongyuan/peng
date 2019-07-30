package com.maqueezu.el.ui.activity.child.physicalexamination_child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.ShoppingCartBean;
import com.maqueezu.el.ui.adapter.SingleProjectAdatper2;
import com.maqueezu.utils.ui.base.OtherBaseActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 调整体检项目
 */
public class AdjustmentProjectActivity extends OtherBaseActivity implements View.OnClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;
    private TextView tv_adjustment_tijianjigou;//体检机构
    private TextView tv_adjustment_taocan;//体检套餐
    private TextView tv_adjustment_heji_numSum;//总价合计
    private TextView tv_adjustment_heji;//合计字符
    private TextView tv_adjustment_count;//项目个数
    private AutoRelativeLayout rl_base_1;
    private TextView tv_adjustment_yixuan_sum;//已选项目总价
    private AutoRelativeLayout rl_single_1;
    private RecyclerView mRecycler_yixuanxiangmu;//已选项目列表
    private AutoRelativeLayout rl_base_2;
    private TextView tv_adjustment_kexuan_sum;//可选项目总价
    private AutoRelativeLayout rl_single_2;
    private RecyclerView mRecycler_kexuanxiangmu;//可选项目列表
    private AutoRelativeLayout rl_base_3;
    private Button bt_querentijiao;//确认提交

    private List<ShoppingCartBean> shoppingCartBeanList;
    private SingleProjectAdatper2 yixuanAdaper;
    private SingleProjectAdatper2 kexuanAdapter;

    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private List<ShoppingCartBean> kexuanList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_adjustment_project;
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
        tv_adjustment_tijianjigou = (TextView) findViewById(R.id.tv_adjustment_tijianjigou);
        tv_adjustment_tijianjigou.setOnClickListener(this);
        tv_adjustment_taocan = (TextView) findViewById(R.id.tv_adjustment_taocan);
        tv_adjustment_taocan.setOnClickListener(this);
        tv_adjustment_heji_numSum = (TextView) findViewById(R.id.tv_adjustment_heji_numSum);
        tv_adjustment_heji_numSum.setOnClickListener(this);
        tv_adjustment_heji = (TextView) findViewById(R.id.tv_adjustment_heji);
        tv_adjustment_heji.setOnClickListener(this);
        rl_base_1 = (AutoRelativeLayout) findViewById(R.id.rl_base_1);
        rl_base_1.setOnClickListener(this);
        tv_adjustment_yixuan_sum = (TextView) findViewById(R.id.tv_adjustment_yixuan_sum);
        tv_adjustment_yixuan_sum.setOnClickListener(this);
        rl_single_1 = (AutoRelativeLayout) findViewById(R.id.rl_single_1);
        rl_single_1.setOnClickListener(this);
        mRecycler_yixuanxiangmu = (RecyclerView) findViewById(R.id.mRecycler_yixuanxiangmu);
        mRecycler_yixuanxiangmu.setOnClickListener(this);
        rl_base_2 = (AutoRelativeLayout) findViewById(R.id.rl_base_2);
        rl_base_2.setOnClickListener(this);
        tv_adjustment_kexuan_sum = (TextView) findViewById(R.id.tv_adjustment_kexuan_sum);
        tv_adjustment_kexuan_sum.setOnClickListener(this);
        rl_single_2 = (AutoRelativeLayout) findViewById(R.id.rl_single_2);
        rl_single_2.setOnClickListener(this);
        mRecycler_kexuanxiangmu = (RecyclerView) findViewById(R.id.mRecycler_kexuanxiangmu);
        mRecycler_kexuanxiangmu.setOnClickListener(this);
        rl_base_3 = (AutoRelativeLayout) findViewById(R.id.rl_base_3);
        rl_base_3.setOnClickListener(this);
        bt_querentijiao = (Button) findViewById(R.id.bt_querentijiao);
        bt_querentijiao.setOnClickListener(this);

        mRecycler_yixuanxiangmu.setLayoutManager(new LinearLayoutManager(this));
        mRecycler_kexuanxiangmu.setLayoutManager(new LinearLayoutManager(this));
        tv_adjustment_count = (TextView) findViewById(R.id.tv_adjustment_count);
        tv_adjustment_count.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        title_text.setText(R.string.name_tiaozhengxiangmu);

        shoppingCartBeanList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ShoppingCartBean shoppingCartBean = new ShoppingCartBean(0, "AA项目"+i, "", 1, 100.00, 1);
            shoppingCartBeanList.add(shoppingCartBean);
        }

        kexuanList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ShoppingCartBean shoppingCartBean = new ShoppingCartBean(0, "AA项目"+i, "", 1, 100.00, 1);
            kexuanList.add(shoppingCartBean);
        }

//        已选项目
        yixuanAdaper = new SingleProjectAdatper2(this, shoppingCartBeanList, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AdjustmentProjectActivity.this, ""+shoppingCartBeanList.get(position).getShoppingName(), Toast.LENGTH_SHORT).show();
            }
        });

//        可选项目
        kexuanAdapter = new SingleProjectAdatper2(this, kexuanList, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AdjustmentProjectActivity.this, ""+shoppingCartBeanList.get(position).getShoppingName(), Toast.LENGTH_SHORT).show();
            }
        });

        mRecycler_yixuanxiangmu.setAdapter(yixuanAdaper);
        mRecycler_yixuanxiangmu.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mRecycler_kexuanxiangmu.setAdapter(kexuanAdapter);
        mRecycler_kexuanxiangmu.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void initListener() {
        yixuanAdaper.setCheckListener(new SingleProjectAdatper2.CheckListener() {
            @Override
            public void checkGroup(int position, boolean isChecked) {
                shoppingCartBeanList.get(position).setChoosed(isChecked);
//                if (isAllCheck()) {
//                    cb_select_all.setChecked(true);
//                } else {
//                    cb_select_all.setChecked(false);
                    yixuanAdaper.notifyDataSetChanged();
                    statistics();
//                }
            }
        });

        kexuanAdapter.setCheckListener(new SingleProjectAdatper2.CheckListener() {
            @Override
            public void checkGroup(int position, boolean isChecked) {
                kexuanList.get(position).setChoosed(isChecked);
//                if (isAllCheck()) {
//                    cb_select_all.setChecked(true);
//                } else {
//                    cb_select_all.setChecked(false);
                kexuanAdapter.notifyDataSetChanged();
                statisticsKeXuan();
//                }
            }
        });



    }

    private void statisticsKeXuan() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < kexuanList.size(); i++) {
            ShoppingCartBean shoppingCartBean = kexuanList.get(i);
            if (shoppingCartBean.isChoosed()) {
                totalCount++;
                totalPrice += shoppingCartBean.getPrice() * shoppingCartBean.getCount();
            }
        }
        tv_adjustment_kexuan_sum.setText("¥" + totalPrice);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
            case R.id.title_back_image:
                finish();
                break;
            case R.id.bt_querentijiao://确认提交
                Intent intent = new Intent(this, SubmitOrderActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            default:
                break;
        }
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
                totalCount++;
                totalPrice += shoppingCartBean.getPrice() * shoppingCartBean.getCount();
            }
        }
        tv_adjustment_heji_numSum.setText("¥" + totalPrice);
        tv_adjustment_count.setText(totalCount+"");
        tv_adjustment_yixuan_sum.setText("¥" + totalPrice);
    }

}

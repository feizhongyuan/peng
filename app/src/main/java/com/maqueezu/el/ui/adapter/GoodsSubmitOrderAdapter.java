package com.maqueezu.el.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.GoodsCatBean;
import com.maqueezu.utils.ui.recyclerView.adapter.BaseRecyclerAdapter;
import com.maqueezu.utils.ui.recyclerView.adapter.MyViewHolder;

import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/7/8 9:57
 *
 * 商品提交订单适配
 */

public class GoodsSubmitOrderAdapter extends BaseRecyclerAdapter<String> {
    public GoodsSubmitOrderAdapter(Context context, List<String> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        Holder holder = new Holder(LayoutInflater.from(context).inflate(R.layout.item_submitorder_list, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);

        Holder holder = (Holder) viewHolder;
        holder.tv_submitOrder_title.setText("商品名称");
        holder.tv_submitOrder_attribute.setText("规格1");
        holder.tv_submitOrder_price.setText("¥1111.00");
        holder.tv_submitOrder_count.setText("1");
        holder.tv_submitOrder_express.setText("0.00");
        holder.img_submitOrder_img.setBackgroundResource(R.mipmap.ic_launcher);

    }

    public class Holder extends MyViewHolder{

        private final ImageView img_submitOrder_img;//图片
        private final TextView tv_submitOrder_title;//标题
        private final TextView tv_submitOrder_attribute;//属性
        private final TextView tv_submitOrder_price;//价格
        private final TextView tv_submitOrder_count;//数量
        private final TextView tv_submitOrder_express;//快递费

        public Holder(View view) {
            super(view);
            img_submitOrder_img = (ImageView) view.findViewById(R.id.img_submitOrder_img);
            tv_submitOrder_title = (TextView) view.findViewById(R.id.tv_submitOrder_title);
            tv_submitOrder_attribute = (TextView) view.findViewById(R.id.tv_submitOrder_attribute);
            tv_submitOrder_price = (TextView) view.findViewById(R.id.tv_submitOrder_price);
            tv_submitOrder_count = (TextView) view.findViewById(R.id.tv_submitOrder_count);
            tv_submitOrder_express = (TextView) view.findViewById(R.id.tv_submitOrder_express);
        }
    }
}

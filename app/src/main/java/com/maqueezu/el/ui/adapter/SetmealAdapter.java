package com.maqueezu.el.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.pojo.LoadModel;
import com.maqueezu.utils.ui.recyclerView.adapter.BaseRecyclerAdapter;
import com.maqueezu.utils.ui.recyclerView.adapter.MyViewHolder;

import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/6/17 13:41
 */

public class SetmealAdapter extends BaseRecyclerAdapter<AdvertBean.DataBean.AdvListBean> {
    public SetmealAdapter(Context context, List<AdvertBean.DataBean.AdvListBean> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        Holder holder = new Holder(LayoutInflater.from(context).inflate(R.layout.item_healthygoodsgridview, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        Holder holder = (Holder) viewHolder;
        Glide.with(context).load(list.get(position).getAtturl()).into(holder.img_jiankangshangpin);
        holder.tv_goodsName.setText(list.get(position).getAname());
        holder.tv_goodsPrice.setText(list.get(position).getAid()+"");
    }

    public class Holder extends MyViewHolder {

        private final ImageView img_jiankangshangpin;
        private final TextView tv_goodsName;
        private final TextView tv_goodsPrice;

        public Holder(View view) {
            super(view);
            img_jiankangshangpin = (ImageView) view.findViewById(R.id.jiankangshangpin_img);
            tv_goodsName = (TextView) view.findViewById(R.id.tv_goodsName);
            tv_goodsPrice = (TextView) view.findViewById(R.id.tv_goodsPrice);
        }
    }
}

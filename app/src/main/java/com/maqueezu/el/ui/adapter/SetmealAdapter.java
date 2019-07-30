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
import com.maqueezu.el.pojo.GoodsCatBean;
import com.maqueezu.el.pojo.LoadModel;
import com.maqueezu.utils.ui.recyclerView.adapter.BaseRecyclerAdapter;
import com.maqueezu.utils.ui.recyclerView.adapter.MyViewHolder;

import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/6/17 13:41
 *
 * 套餐适配
 */

public class SetmealAdapter extends BaseRecyclerAdapter<AdvertBean.DataBean.AdvListBean> {
    public SetmealAdapter(Context context, List<AdvertBean.DataBean.AdvListBean> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        MyViewHolder holder = null;
//        if (viewType == 0){
//            View inflate = LayoutInflater.from(context).inflate(R.layout.item_healthygoodsgridview_frist, null);
//            holder = new FirstHolder(inflate);
//        }else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_healthygoodsgridview, null);
            holder = new Holder(inflate);
//        }
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
//        if (viewHolder instanceof Holder){
            Holder holder = (Holder) viewHolder;
            Glide.with(context).load(list.get(position).getAtturl()).into(holder.img_jiankangshangpin);
            holder.tv_goodsName.setText(list.get(position).getAname());
            holder.tv_goodsPrice.setText("¥"+list.get(position).getAid());
//        }else if (viewHolder instanceof FirstHolder){
//            FirstHolder holder = (FirstHolder) viewHolder;
//            Glide.with(context).load(list.get(0).getAtturl()).into(holder.mImg_xianshicuxiao);
//        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return 0;
        }else {
            return 1;
        }
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

    public class FirstHolder extends MyViewHolder {

        private final ImageView mImg_xianshicuxiao;

        public FirstHolder(View itemView) {
            super(itemView);
            mImg_xianshicuxiao = itemView.findViewById(R.id.mImg_xianshicuxiao);
        }
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void addList(List<AdvertBean.DataBean.AdvListBean> addMessageList) {
        //增加数据
        int position = list.size();
        list.addAll(position, addMessageList);
        notifyItemInserted(position);
    }

    public void refresh(List<AdvertBean.DataBean.AdvListBean> newList) {
        //刷新数据
        list.removeAll(list);
        list.addAll(newList);
        notifyDataSetChanged();
    }
}

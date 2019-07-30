package com.maqueezu.el.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.utils.ui.recyclerView.adapter.BaseRecyclerAdapter;
import com.maqueezu.utils.ui.recyclerView.adapter.MyViewHolder;

import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/7/30 18:07
 */

public class RecommendListAdapter extends BaseRecyclerAdapter<AdvertBean.DataBean.AdvListBean> {
    public RecommendListAdapter(Context context, List<AdvertBean.DataBean.AdvListBean> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        Holder holder = new Holder(LayoutInflater.from(context).inflate(R.layout.item_recommendlist, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        Holder holder = (Holder) viewHolder;
        String aname = list.get(position).getAname();
        holder.tv_taocan_title.setText("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        holder.tv_taocan_price.setText("￥888");
        holder.tv_taocan_jigou.setText("XXXX体检机构");
        holder.img_taocan_tu.setBackgroundResource(R.drawable.ic_launcher);
    }

    public class Holder extends MyViewHolder{

        private final ImageView img_taocan_tu;
        private final TextView tv_taocan_title;
        private final TextView tv_taocan_price;
        private final TextView tv_taocan_jigou;

        public Holder(View view) {
            super(view);
            img_taocan_tu = (ImageView) view.findViewById(R.id.img_taocan_tu);
            tv_taocan_title = (TextView) view.findViewById(R.id.tv_taocan_title);
            tv_taocan_price = (TextView) view.findViewById(R.id.tv_taocan_price);
            tv_taocan_jigou = (TextView) view.findViewById(R.id.tv_taocan_jigou);
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

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
 * Created by Date 2019/7/1 10:08
 */

public class ThroughTrainListAdapter extends BaseRecyclerAdapter<AdvertBean.DataBean.AdvListBean> {
    public ThroughTrainListAdapter(Context context, List<AdvertBean.DataBean.AdvListBean> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        Holder holder = new Holder(LayoutInflater.from(context).inflate(R.layout.item_throughtrainlist, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        Holder holder = (Holder) viewHolder;
        String aname = list.get(position).getAname();
        holder.tv_shangpin_title.setText("商品名称商品名称商品名称商品名称商品名称商品名称商品名称");
        holder.tv_shangpin_price.setText("￥888");
        holder.img_shangpin_tu.setBackgroundResource(R.drawable.ic_launcher);
    }

    public class Holder extends MyViewHolder {

        private final ImageView img_shangpin_tu;
        private final TextView tv_shangpin_title;
        private final TextView tv_shangpin_price;

        public Holder(View view) {
            super(view);
            img_shangpin_tu = (ImageView) view.findViewById(R.id.img_shangpin_tu);
            tv_shangpin_title = (TextView) view.findViewById(R.id.tv_shangpin_title);
            tv_shangpin_price = (TextView) view.findViewById(R.id.tv_shangpin_price);
        }
    }
}

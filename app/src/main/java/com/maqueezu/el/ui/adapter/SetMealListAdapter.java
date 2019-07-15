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
 * Created by Date 2019/6/26 15:10
 *
 * 套餐列表适配
 */

public class SetMealListAdapter extends BaseRecyclerAdapter<AdvertBean.DataBean.AdvListBean> {

    public SetMealListAdapter(Context context, List<AdvertBean.DataBean.AdvListBean> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        Holder holder = new Holder(LayoutInflater.from(context).inflate(R.layout.item_setmeallist, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        Holder holder = (Holder) viewHolder;
        String aname = list.get(position).getAname();
        holder.tv_taocan_title.setText("套餐AAAAAAAAAAAAAAAAAAA");
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
}

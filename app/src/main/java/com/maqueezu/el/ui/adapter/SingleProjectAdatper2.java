package com.maqueezu.el.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.utils.ui.recyclerView.adapter.BaseRecyclerAdapter;
import com.maqueezu.utils.ui.recyclerView.adapter.MyViewHolder;

import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/6/28 14:54
 */

public class SingleProjectAdatper2 extends BaseRecyclerAdapter<String> {

    public SingleProjectAdatper2(Context context, List<String> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
       Holder holder = new Holder(LayoutInflater.from(context).inflate(R.layout.item_singleproject2, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        Holder holder = (Holder) viewHolder;
        holder.tv_singleProject_name.setText("AA项目");
        holder.tv_singleProject_name.setText("￥100.00");

    }

    public class Holder extends MyViewHolder{

        private final TextView tv_singleProject_name;
        private final TextView tv_singleProject_price;

        public Holder(View itemView) {
            super(itemView);
            tv_singleProject_name = (TextView) itemView.findViewById(R.id.tv_singleProject_name);
            tv_singleProject_price = (TextView) itemView.findViewById(R.id.tv_singleProject_price);
        }
    }
}

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
 * Created by Date 2019/7/16 14:36
 */

public class SearchRecordAdapter extends BaseRecyclerAdapter<String> {
    public SearchRecordAdapter(Context context, List<String> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        Holder holder = new Holder(LayoutInflater.from(context).inflate(R.layout.item_search_record,null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);

        Holder holder = (Holder) viewHolder;
        holder.tv_search.setText(list.get(position));
    }

    public class Holder extends MyViewHolder{

        private final TextView tv_search;

        public Holder(View itemView) {
            super(itemView);
            tv_search = itemView.findViewById(R.id.tv_search);
        }
    }



}

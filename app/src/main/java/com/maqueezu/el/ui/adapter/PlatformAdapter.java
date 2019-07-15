package com.maqueezu.el.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.GoodsCatBean;
import com.maqueezu.utils.ui.recyclerView.adapter.BaseRecyclerAdapter;
import com.maqueezu.utils.ui.recyclerView.adapter.MyViewHolder;

import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/7/15 15:38
 */

public class PlatformAdapter extends BaseRecyclerAdapter<Integer> {
    public PlatformAdapter(Context context, List<Integer> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        Holder holder = new Holder(LayoutInflater.from(context).inflate(R.layout.item_platform_tuijian,null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);

        Holder holder = (Holder) viewHolder;
        holder.img_platform.setBackgroundResource(list.get(position));

    }

    public class Holder extends MyViewHolder{

        private final ImageView img_platform;

        public Holder(View itemView) {
            super(itemView);
            img_platform = (ImageView) itemView.findViewById(R.id.img_platform);
        }

    }

}

package com.maqueezu.el.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.ui.activity.child.healthy_child.ApplyInformationProcessActivity;
import com.maqueezu.utils.ui.recyclerView.adapter.BaseRecyclerAdapter;
import com.maqueezu.utils.ui.recyclerView.adapter.MyViewHolder;

import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/7/29 17:50
 */

public class RelationApplyAdapter extends BaseRecyclerAdapter<AdvertBean.DataBean.AdvListBean> {
    public RelationApplyAdapter(Context context, List<AdvertBean.DataBean.AdvListBean> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        Holder holder = new Holder(LayoutInflater.from(context).inflate(R.layout.item_relation_apply, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        Holder holder = (Holder) viewHolder;
        holder.mImage_head.setBackgroundResource(R.mipmap.touxiang_nan);
        holder.mTv_name.setText(list.get(position).getAname());
        holder.mTv_age.setText(list.get(position).getCname());
        holder.mTv_relationArchives.setText(list.get(position).getAid()+"XXX");
        holder.mTv_source.setText(list.get(position).getDisabled());

        holder.mTv_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                申请信息处理
                Intent intent = new Intent(context,ApplyInformationProcessActivity.class);
                context.startActivity(intent);

            }
        });
    }

    public class Holder extends MyViewHolder{

        private final ImageView mImage_head;
        private final TextView mTv_name;
        private final TextView mTv_age;
        private final TextView mTv_source;
        private final TextView mTv_relationArchives;
        private final TextView mTv_agree;

        public Holder(View itemView) {
            super(itemView);
            mImage_head = itemView.findViewById(R.id.mImage_head);
            mTv_name = itemView.findViewById(R.id.mTv_name);
            mTv_age = itemView.findViewById(R.id.mTv_age);
            mTv_source = itemView.findViewById(R.id.mTv_source);
            mTv_relationArchives = itemView.findViewById(R.id.mTv_relationArchives);
            mTv_agree = itemView.findViewById(R.id.mTv_agree);

        }
    }
}

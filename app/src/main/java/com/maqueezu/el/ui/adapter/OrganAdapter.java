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
 * Created by Date 2019/6/26 16:15
 */

public class OrganAdapter extends BaseRecyclerAdapter<AdvertBean.DataBean.AdvListBean>{

    public OrganAdapter(Context context, List<AdvertBean.DataBean.AdvListBean> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        Holder holder = new Holder(LayoutInflater.from(context).inflate(R.layout.item_organ, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        Holder holder = (Holder) viewHolder;
        holder.img_organ_telphone.setBackgroundResource(R.drawable.ic_launcher);
        holder.tv_organ_title.setText("陕西省中医医院");
        holder.tv_organ_biaoqian1.setVisibility(View.VISIBLE);
        holder.tv_organ_biaoqian2.setVisibility(View.VISIBLE);
        holder.tv_organ_address.setText("陕西省西安市莲湖区西华门大街2-4号");
        holder.tv_organ_time.setText("周一至周日 08:00—17:30");
        holder.tv_organ_distance.setText("4.9km");
    }

    public class Holder extends MyViewHolder{

        private final TextView tv_organ_title;
        private final TextView tv_organ_biaoqian1;
        private final TextView tv_organ_biaoqian2;
        private final TextView tv_organ_address;
        private final TextView tv_organ_time;
        private final TextView tv_organ_distance;
        private final ImageView img_organ_telphone;

        public Holder(View itemView) {
            super(itemView);
            tv_organ_title = (TextView) itemView.findViewById(R.id.tv_organ_title);
            tv_organ_biaoqian1 = (TextView) itemView.findViewById(R.id.tv_organ_biaoqian1);
            tv_organ_biaoqian2 = (TextView) itemView.findViewById(R.id.tv_organ_biaoqian2);
            tv_organ_address = (TextView) itemView.findViewById(R.id.tv_organ_address);
            tv_organ_time = (TextView) itemView.findViewById(R.id.tv_organ_time);
            tv_organ_distance = (TextView) itemView.findViewById(R.id.tv_organ_distance);
            img_organ_telphone = (ImageView) itemView.findViewById(R.id.img_organ_telphone);

        }

    }
}

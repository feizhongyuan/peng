package com.maqueezu.el.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.GoodsCatBean;
import com.maqueezu.el.ui.fragment.home_child.HealthyInformationFragment;
import com.maqueezu.utils.ui.recyclerView.adapter.BaseRecyclerAdapter;
import com.maqueezu.utils.ui.recyclerView.adapter.MyViewHolder;

import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/6/26 9:54
 */

public class HealthyInformationAdapter extends BaseRecyclerAdapter<GoodsCatBean.DataBean> {

    public HealthyInformationAdapter(Context context, List<GoodsCatBean.DataBean> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        Holder holder = new Holder(LayoutInflater.from(context).inflate(R.layout.healthy_information_item2,null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);

        Holder holder = (Holder) viewHolder;
        holder.information_title.setText(list.get(position).getName());
        holder.information_author.setText(list.get(position).getLevel()+"作者");
        holder.information_yueduliang.setText(list.get(position).getLevel()+"阅读量");
//        Glide.with(context).load(list.get(position).getImage()).into(holder.information_img1);
//        Glide.with(context).load(list.get(position).getImage()).into(holder.information_delete);
        holder.information_img1.setBackgroundResource(R.drawable.ic_launcher);

    }

    public class Holder extends MyViewHolder {

        private final ImageView information_img1;
        private final ImageView information_delete;
        private final TextView information_title;
        private final TextView information_author;
        private final TextView information_yueduliang;
        private final TextView information_zhiding;

        public Holder(View view) {
            super(view);
            information_img1 = (ImageView) view.findViewById(R.id.information_img1);
            information_delete = (ImageView) view.findViewById(R.id.information_delete);
            information_title = (TextView) view.findViewById(R.id.information_title);
            information_author = (TextView) view.findViewById(R.id.information_author);
            information_yueduliang = (TextView) view.findViewById(R.id.information_yueduliang);
            information_zhiding = (TextView) view.findViewById(R.id.information_zhiding);

        }
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void addList(List<GoodsCatBean.DataBean> addMessageList) {
        //增加数据
        int position = list.size();
        list.addAll(position, addMessageList);
        notifyItemInserted(position);
    }

    public void refresh(List<GoodsCatBean.DataBean> newList) {
        //刷新数据
        list.removeAll(list);
        list.addAll(newList);
        notifyDataSetChanged();
    }
}

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
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(context);

        MyViewHolder holder = null;
        if (viewType == 2){
            View inflate = from.inflate(R.layout.healthy_information_item1, parent, false);
            holder = new MultiHolder(inflate);
        }else {
            View inflate = from.inflate(R.layout.healthy_information_item2, null);
            holder = new SingleHolder(inflate);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);

        if (viewHolder instanceof SingleHolder){
            SingleHolder holder = (SingleHolder) viewHolder;
            holder.information_title.setText(list.get(position).getName());
            holder.information_author.setText(list.get(position).getLevel()+"作者");
            holder.information_yueduliang.setText(list.get(position).getLevel()+"阅读量");
//        Glide.with(context).load(list.get(position).getImage()).into(holder.information_img1);
//        Glide.with(context).load(list.get(position).getImage()).into(holder.information_delete);
            holder.information_img1.setBackgroundResource(R.drawable.ic_launcher);
        }else if (viewHolder instanceof MultiHolder){
            MultiHolder holder = (MultiHolder) viewHolder;
//            holder.information_title.setText();
        }


    }

//    设置不同的条目类型
    @Override
    public int getItemViewType(int position) {
        if (list.size() > 0) {
            return 1;
        } else {
            return 2;
        }
    }

//    单图展示
    public class SingleHolder extends MyViewHolder {

        private final ImageView information_img1;
        private final ImageView information_delete;
        private final TextView information_title;
        private final TextView information_author;
        private final TextView information_yueduliang;
        private final TextView information_zhiding;

        public SingleHolder(View view) {
            super(view);
            information_img1 = (ImageView) view.findViewById(R.id.information_img1);
            information_delete = (ImageView) view.findViewById(R.id.information_delete);
            information_title = (TextView) view.findViewById(R.id.information_title);
            information_author = (TextView) view.findViewById(R.id.information_author);
            information_yueduliang = (TextView) view.findViewById(R.id.information_yueduliang);
            information_zhiding = (TextView) view.findViewById(R.id.information_zhiding);

        }
    }

//    多图展示
    public class MultiHolder extends MyViewHolder{

    private ImageView information_delete,information_img1,information_img2,information_img3,
                    information_small_icon;
    private TextView information_title,tv_zhiding,tv_author,tv_yueduliang;

    public MultiHolder(View itemView) {
            super(itemView);
        information_delete = itemView.findViewById(R.id.information_delete);
        information_img1 = itemView.findViewById(R.id.information_img1);
        information_img2 = itemView.findViewById(R.id.information_img2);
        information_img3 = itemView.findViewById(R.id.information_img3);
        information_small_icon = itemView.findViewById(R.id.information_small_icon);
        information_title = itemView.findViewById(R.id.information_title);
        tv_zhiding = itemView.findViewById(R.id.tv_zhiding);
        tv_author = itemView.findViewById(R.id.tv_author);
        tv_yueduliang = itemView.findViewById(R.id.tv_yueduliang);
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

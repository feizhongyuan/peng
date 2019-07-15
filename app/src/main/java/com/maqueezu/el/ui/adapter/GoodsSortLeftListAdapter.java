package com.maqueezu.el.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.utils.ui.recyclerView.adapter.BaseRecyclerAdapter;
import com.maqueezu.utils.ui.recyclerView.adapter.MyViewHolder;

import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/7/1 11:41
 *
 * 双列表-左列表适配
 */

public class GoodsSortLeftListAdapter extends BaseRecyclerAdapter<String> {

    private List<String> list;
    private LeftListener leftListener;

    private int checkedPosition;

//    设置选中位置
    public void setCheckedPosition(int checkedPosition) {
        this.checkedPosition = checkedPosition;
        notifyDataSetChanged();
    }
    public GoodsSortLeftListAdapter(Context context, List<String> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        Holder holder = new Holder(LayoutInflater.from(context).inflate(R.layout.item_leftlist, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        Holder holder = (Holder) viewHolder;
        holder.tv_left.setText(list.get(position));
        if (position == checkedPosition){
            holder.mView.setBackgroundColor(Color.parseColor("#f3f3f3"));
            holder.tv_left.setTextColor(Color.parseColor("#0068cf"));
        }else {
            holder.mView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.tv_left.setTextColor(Color.parseColor("#1e1d1d"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

        @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public class Holder extends MyViewHolder{

        private final TextView tv_left;
        private View mView;

        public Holder(View view) {
            super(view);
            this.mView = itemView;
            tv_left = (TextView)view.findViewById(R.id.tv_left);
        }
    }



    public interface LeftListener {
        void onItemClick(int id, int position);
    }
}

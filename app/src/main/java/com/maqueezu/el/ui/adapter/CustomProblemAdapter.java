package com.maqueezu.el.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.utils.tools.ToastUtil;
import com.maqueezu.utils.ui.recyclerView.adapter.BaseRecyclerAdapter;
import com.maqueezu.utils.ui.recyclerView.adapter.MyViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/7/19 10:16
 */

public class CustomProblemAdapter extends BaseRecyclerAdapter<AdvertBean.DataBean.AdvListBean> {

    private List<String> results = new ArrayList<>();
//    private int layoutPosition;//记录当前点击位置
    private OnResultListener listener;

    public CustomProblemAdapter(Context context, List<AdvertBean.DataBean.AdvListBean> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        Holder holder = new Holder(LayoutInflater.from(context).inflate(R.layout.item_custom_problem, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        Holder holder = (Holder) viewHolder;
        holder.tv_title.setText(list.get(position).getAname());
        holder.mRb_one.setText(list.get(position).getAtturl());
        holder.mRb_two.setText(list.get(position).getUrl());

//        holder.itemView.setTag(position);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //获取当前点击的位置
//                layoutPosition = holder.getLayoutPosition();
//                notifyDataSetChanged();
//                listener.onItemClick(holder.itemView, (int) holder.itemView.getTag());
//            }
//        });
//        if (position == layoutPosition){
//            holder.mRadioGroup_customProblem.clearCheck();
//        }

        holder.mRadioGroup_customProblem.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String result = null;

                if (checkedId == holder.mRb_one.getId()){
                    Log.e("Radio","One---"+holder.mRb_one.getText().toString());
                    result = holder.mRb_one.getText().toString();
                }else if (checkedId == holder.mRb_two.getId()){
                    Log.e("Radio","Two---"+holder.mRb_two.getText().toString());
                    result = holder.mRb_two.getText().toString();
                }
                results.add(result);
            }
        });

        Log.e("RadioGroup","results---"+results.size());
    }

    class Holder extends MyViewHolder{

        private final TextView tv_title;
        private final RadioGroup mRadioGroup_customProblem;
        private final RadioButton mRb_one,mRb_two;

        public Holder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            mRadioGroup_customProblem = itemView.findViewById(R.id.mRadioGroup_CustomProblem);
            mRb_one = itemView.findViewById(R.id.mRb_one);
            mRb_two = itemView.findViewById(R.id.mRb_two);

        }
    }

    public void onItemClickListener(OnResultListener listener) {
        this.listener = listener;
    }

    public interface OnResultListener {
        void onResultData(View view, List<String> results);
    }



    //下面两个方法提供给页面刷新和加载时调用
    public void addList(List<AdvertBean.DataBean.AdvListBean> addMessageList) {
        //增加数据
        int position = list.size();
        list.addAll(position, addMessageList);
        notifyItemInserted(position);
    }

    public void refresh(List<AdvertBean.DataBean.AdvListBean> newList) {
        //刷新数据
        list.removeAll(list);
        list.addAll(newList);
        notifyDataSetChanged();
    }

}

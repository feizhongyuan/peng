package com.maqueezu.el.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.utils.ui.recyclerView.adapter.BaseRecyclerAdapter;
import com.maqueezu.utils.ui.recyclerView.adapter.MyViewHolder;

import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/7/23 15:38
 */

public class SetMealEvaluateAdapter extends BaseRecyclerAdapter<AdvertBean.DataBean> {
    public SetMealEvaluateAdapter(Context context, List<AdvertBean.DataBean> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        return super.onCreateViewHolder(arg0, arg1);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
    }


}

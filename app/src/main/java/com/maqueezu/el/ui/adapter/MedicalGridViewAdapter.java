package com.maqueezu.el.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;


import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/6/14 10:32
 */

public class MedicalGridViewAdapter extends BaseAdapter{
    Context context;
    AdvertBean.DataBean dataBean;

    public MedicalGridViewAdapter(Context context, AdvertBean.DataBean dataBean) {
        this.context = context;
        this.dataBean = dataBean;
    }

    @Override
    public int getCount() {
        return dataBean.getAdvList().size();
    }

    @Override
    public Object getItem(int position) {
        return dataBean.getAdvList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_medicalgridview, null);
        ImageView yiliaofuwu_img = (ImageView) convertView.findViewById(R.id.yiliaofuwu_img);
        List<AdvertBean.DataBean.AdvListBean> advList = dataBean.getAdvList();
        Glide.with(context).load(advList.get(position).getAtturl()).into(yiliaofuwu_img);
        return convertView;
    }
}

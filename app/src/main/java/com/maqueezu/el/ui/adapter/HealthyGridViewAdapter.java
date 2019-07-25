package com.maqueezu.el.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;

import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/6/14 11:29
 * 首页更改，暂时不用
 */

public class HealthyGridViewAdapter extends BaseAdapter{
    Context context;
    AdvertBean.DataBean dataBean;

    public HealthyGridViewAdapter(Context context, AdvertBean.DataBean dataBean) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.item_healthygoodsgridview, null);
        ImageView jiankangshangpin_img = (ImageView) convertView.findViewById(R.id.jiankangshangpin_img);
        TextView tv_goodsName = (TextView) convertView.findViewById(R.id.tv_goodsName);
        TextView tv_goodsPrice = (TextView) convertView.findViewById(R.id.tv_goodsPrice);
        List<AdvertBean.DataBean.AdvListBean> advList = dataBean.getAdvList();
        Glide.with(context).load(advList.get(position).getAtturl()).into(jiankangshangpin_img);
        tv_goodsName.setText(advList.get(position).getAname());
        tv_goodsPrice.setText(advList.get(position).getAid()+"");
        return convertView;
    }
}

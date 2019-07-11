package com.maqueezu.el.ui.view.doublelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.R;

import java.util.List;


/**
 * Created by fei .
 * Created by Date 2019/7/9 17:17
 */

public class RightListAdapter extends SectionedBaseAdapter {

    private Context mContext;
    private String[] leftStr;
    private List<Bean> list;

    public RightListAdapter(Context mContext, String[] leftStr, List<Bean> list) {
        this.mContext = mContext;
        this.leftStr = leftStr;
        this.list = list;
    }

    @Override
    public Object getItem(int section, int position) {
        return list.get(section).getDataList().get(position);
    }

    @Override
    public long getItemId(int section, int position) {
        return position;
    }

    @Override
    public int getSectionCount() {
        return leftStr.length;
    }

    @Override
    public int getCountForSection(int section) {
        return list.size();
    }

    @Override
    public View getItemView(final int section, final int position, View convertView, ViewGroup parent) {
        RelativeLayout layout = null;
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (RelativeLayout) inflator.inflate(R.layout.right_list_item, null);
        } else {
            layout = (RelativeLayout) convertView;
        }
        String name = null,price = null;
        if (section <= list.size() - 1){
            if (position <= list.get(section).getDataList().size() - 1){
                name = list.get(section).getDataList().get(position).getName();
                price = list.get(section).getDataList().get(position).getPrice();
            }
        }
        
        ((TextView) layout.findViewById(R.id.textItem_title)).setText(name);
        ((TextView) layout.findViewById(R.id.textItem_price)).setText(price);
        ((ImageView) layout.findViewById(R.id.imageItem)).setBackgroundResource(R.mipmap.ic_launcher);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String name = list.get(section).getDataList().get(position).getName();
                Toast.makeText(mContext,name , Toast.LENGTH_SHORT).show();
            }
        });
        return layout;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        LinearLayout layout = null;
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflator.inflate(R.layout.header_item, null);
        } else {
            layout = (LinearLayout) convertView;
        }
        layout.setClickable(false);
        ((TextView) layout.findViewById(R.id.textItem)).setText(leftStr[section]);
        return layout;
    }
}

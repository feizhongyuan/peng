package com.maqueezu.el.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maqueezu.utils.tools.LogUtil;

import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/7/15 18:02
 */

public class PhysicalExaminationCardAdapter extends PagerAdapter {
    private List<View> mList;
    private LayoutInflater layoutInflater;

    public PhysicalExaminationCardAdapter(Context context,List<View> mList) {
        super();
        this.mList = mList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

//    页面宽度所占ViewPager测量宽度的权重比例，默认为1
//    @Override
//    public float getPageWidth(int position) {
//        return 1;
//    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        ((ViewPager)container).removeView(view);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mList.get(position));
        return mList.get(position);

    }

    public int indexView(View view){
        int index = -1;
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i) != null && mList.get(i).equals(view)){
                index = i;
                break;
            }
        }
        return index;
    }
}

package com.maqueezu.el.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.maqueezu.el.ui.activity.child.physicalexamination_child.UsePhysicalExaminationCardActivity;
import com.maqueezu.el.ui.webview.NewWebFragment;
import com.maqueezu.utils.ui.FragmentContainerActivity;
import com.maqueezu.utils.ui.web.WebFragment;

import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/7/15 18:02
 */

public class PhysicalExaminationCardAdapter extends PagerAdapter {
    private List<View> mList;
    private List<String> imgs;
    private LayoutInflater layoutInflater;
    private Context context;

    public PhysicalExaminationCardAdapter(Context context,List<View> mList,List<String> imgs) {
        super();
        this.mList = mList;
        this.imgs = imgs;
        this.context = context;
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
        // 为每页添加点击监听(手动设置监听个数)
       mList.get(position).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Bundle bundle = new Bundle();
               bundle.putString("name","体检卡"+(position+1));
               bundle.putString("cardImg",imgs.get(position));
               multiplexIntent(context,bundle,UsePhysicalExaminationCardActivity.class);
               Toast.makeText(context, "体检卡"+(position+1), Toast.LENGTH_SHORT).show();
           }
       });

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

    private void multiplexIntent(Context context,Bundle bundle,Class cla){
        Intent intent = new Intent(context, cla);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}

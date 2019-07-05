package com.maqueezu.el.ui.test;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.el.ui.adapter.FragmentAdapter;
import com.maqueezu.el.ui.fragment.HealthyFragment;
import com.maqueezu.el.ui.fragment.HomeFragment;
import com.maqueezu.el.ui.fragment.MyFragment;
import com.maqueezu.el.ui.fragment.PhysicalExaminationFragment;

import java.util.ArrayList;
import java.util.List;

public class Test2Activity extends AppCompatActivity {

    private ViewPager view_pager;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        initView();
    }

    private void initView() {
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        tab = (TabLayout) findViewById(R.id.tab);
//      定义tab数据
        String[] tabtitle = getResources().getStringArray(R.array.tab_titles);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new PhysicalExaminationFragment());
        fragments.add(new HomeFragment());
        fragments.add(new HealthyFragment());
        fragments.add(new MyFragment());
//      viewpager加载适配器
        view_pager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),fragments,tabtitle));
//        viewpager事件
        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                  选中
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                  未选中
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
//                  再次选中
            }
        });


//        tablayout关联viewpager
        tab.setupWithViewPager(view_pager);

        Drawable drawable = null;
        for (int i = 0; i < tab.getTabCount(); i++) {
            TabLayout.Tab tabAt = tab.getTabAt(i);
            switch (i){
                case 0:
                    drawable = getResources().getDrawable(R.drawable.tab_home_selector);
                    break;
                case 1:
                    drawable = getResources().getDrawable(R.drawable.tab_physical_selector);
                    break;
                case 2:
                    drawable = getResources().getDrawable(R.drawable.tab_shoppingmall_selector);
                    break;
                case 3:
                    drawable = getResources().getDrawable(R.drawable.tab_healthy_selector);
                    break;
                case 4:
                    drawable = getResources().getDrawable(R.drawable.tab_mine_selector);
                    break;
            }
            tabAt.setIcon(drawable);
        }

//        tab.addTab(tab.newTab().setText(R.string.fragmentone_name).setIcon(R.drawable.tab_home_selector));
//        tab.addTab(tab.newTab().setText(R.string.fragmenttwo_name).setIcon(R.drawable.tab_physical_selector));
//        tab.addTab(tab.newTab().setText(R.string.fragmentthree_name).setIcon(R.drawable.tab_shoppingmall_selector));
//        tab.addTab(tab.newTab().setText(R.string.fragmentfour_name).setIcon(R.drawable.tab_healthy_selector));
//        tab.addTab(tab.newTab().setText(R.string.fragmentfive_name).setIcon(R.drawable.tab_mine_selector));


//        Tab添加自定义View
//        tab.getTabAt(0).setCustomView(getTabView(getResources().getString(R.string.fragmentone_name),
//                R.drawable.tab_home_selector));
//        tab.getTabAt(1).setCustomView(getTabView(getResources().getString(R.string.fragmenttwo_name),
//                R.drawable.tab_physical_selector));
//        tab.getTabAt(2).setCustomView(getTabView(getResources().getString(R.string.fragmentthree_name),
//                R.drawable.tab_shoppingmall_selector));
//        tab.getTabAt(3).setCustomView(getTabView(getResources().getString(R.string.fragmentfour_name),
//                R.drawable.tab_healthy_selector));
//        tab.getTabAt(4).setCustomView(getTabView(getResources().getString(R.string.fragmentfive_name),
//                R.drawable.tab_mine_selector));


    }

    // Tab自定义view
    public View getTabView(String title, int image_src) {
        View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_tab_item, null);
        ImageView imageView = (ImageView) v.findViewById(R.id.tab_image);
        imageView.setImageResource(image_src);
        TextView textView =(TextView) v.findViewById(R.id.tab_title);
        textView.setText(title);
        return v;
    }

}

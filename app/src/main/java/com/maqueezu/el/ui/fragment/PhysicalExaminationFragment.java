package com.maqueezu.el.ui.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.ui.activity.child.physicalexamination_child.AddPhysicalExaminationCardActivity;
import com.maqueezu.el.ui.activity.child.physicalexamination_child.CustomExpertActivity;
import com.maqueezu.el.ui.activity.child.physicalexamination_child.CustomOneselfActivity;
import com.maqueezu.el.ui.activity.child.physicalexamination_child.PhysicalOrganActivity;
import com.maqueezu.el.ui.activity.child.physicalexamination_child.RecommendSetmealActivity;
import com.maqueezu.el.ui.activity.child.physicalexamination_child.SetMealListActivity;
import com.maqueezu.el.ui.adapter.MedicalGridViewAdapter;
import com.maqueezu.el.ui.adapter.PhysicalExaminationCardAdapter;
import com.maqueezu.el.ui.adapter.PlatformAdapter;
import com.maqueezu.el.ui.adapter.SetmealAdapter;
import com.maqueezu.utils.ui.base.BaseFragment;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 体检模块
 */
public class PhysicalExaminationFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {


    private View rootView;
    private ImageView img_physical;//体检图
    private TextView tv_pingtaituijian_title;//平台推荐标题
    private AutoRelativeLayout rl_pingtaituijian;//平台推荐框
    //    private ImageView img_tijiantaocan;//体检套餐图
//    private TextView tv_tijiantaocao;//体检套餐标题
//    private ImageView img_tijianjigou;//体检机构图
//    private TextView tv_tijianjigou;//体检机构图
    private AutoRelativeLayout rl_base_1;//1、平台推荐模块
    private AutoRelativeLayout rl_pingtaituijian_item;//平台推荐模块
    private TextView tv_tijianka_title;//体检卡标题
    private TextView mTv_tijianka_lijibangka;//体检卡-立即绑卡
    private AutoRelativeLayout rl_tijianka;//体检卡框
    //    private GridView mGridView_tijianka;//体检卡展示
    private AutoRelativeLayout rl_base_2;//2、体检卡模块
    private TextView tv_dingzhitijian_title;//定制体检标题
    private TextView tv_tijianka_count;//体检卡数量
    private ViewPager mViewPager_tijianka;//体检卡展示
    private AutoRelativeLayout rl_dingzhitijian;//定制体检框
    private ImageView img_zijitu;//自己定制图
    private TextView tv_zijiDIY;//DIY
    private TextView tv_zijidingzhi;//自己定制长标题
    private AutoRelativeLayout rl_zijidingzhi;//自己定制框
    private ImageView img_zhuanjiatu;//专家定制图
    private ImageView img_zhuanjiaVIP;//专家定制VIP
    private TextView tv_1dui1;//专家定制1对1
    private TextView tv_zhuanjiadingzhi;//专家定制长标题
    private TextView tv_dingzhijilu;//专家定制记录
    private AutoRelativeLayout rl_zhuanjia;//专家定制框
    private AutoLinearLayout rl_dingzhi;//定制框（自己定制和专家定制）
    private AutoRelativeLayout rl_base_3;//3、定制模块
    private TextView mTv_gengduo_zhenxuantaocan;//甄选套餐-更多
    private AutoRelativeLayout rl_zhenxuantaocan;//甄选套餐框
    private AutoRelativeLayout rl_base_4;//4、甄选套餐模块
    private RecyclerView mRecycler_zhenxuantaocan;//甄选套餐展示
    private RecyclerView mRecycler_platform;// 平台推荐更改


    private AdvertBean.DataBean data;

    public PhysicalExaminationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = super.onCreateView(inflater, container, savedInstanceState);

        EventBus.getDefault().register(this);
        initView(rootView);
        return rootView;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_physical_examination;
    }

    @Override
    protected void initView(View mRootView) {

        img_physical = (ImageView) rootView.findViewById(R.id.img_physical);
        img_physical.setOnClickListener(this);
        tv_pingtaituijian_title = (TextView) rootView.findViewById(R.id.tv_pingtaituijian_title);
        tv_pingtaituijian_title.setOnClickListener(this);
        rl_pingtaituijian = (AutoRelativeLayout) rootView.findViewById(R.id.rl_pingtaituijian);
//        img_tijiantaocan = (ImageView) rootView.findViewById(R.id.img_tijiantaocan);
//        img_tijiantaocan.setOnClickListener(this);
//        tv_tijiantaocao = (TextView) rootView.findViewById(R.id.tv_tijiantaocao);
//        tv_tijiantaocao.setOnClickListener(this);
//        img_tijianjigou = (ImageView) rootView.findViewById(R.id.img_tijianjigou);
//        img_tijianjigou.setOnClickListener(this);
//        tv_tijianjigou = (TextView) rootView.findViewById(R.id.tv_tijianjigou);
//        tv_tijianjigou.setOnClickListener(this);
        rl_base_1 = (AutoRelativeLayout) rootView.findViewById(R.id.rl_base_1);
        tv_tijianka_title = (TextView) rootView.findViewById(R.id.tv_tijianka_title);
        mTv_tijianka_lijibangka = (TextView) rootView.findViewById(R.id.mTv_tijianka_lijibangka);
        mTv_tijianka_lijibangka.setOnClickListener(this);
        rl_tijianka = (AutoRelativeLayout) rootView.findViewById(R.id.rl_tijianka);
//        mGridView_tijianka = (GridView) rootView.findViewById(R.id.mGridView_tijianka);
        rl_base_2 = (AutoRelativeLayout) rootView.findViewById(R.id.rl_base_2);
        tv_dingzhitijian_title = (TextView) rootView.findViewById(R.id.tv_dingzhitijian_title);
        tv_dingzhitijian_title.setOnClickListener(this);
        rl_dingzhitijian = (AutoRelativeLayout) rootView.findViewById(R.id.rl_dingzhitijian);
        img_zijitu = (ImageView) rootView.findViewById(R.id.img_zijitu);
        img_zijitu.setOnClickListener(this);
        tv_zijiDIY = (TextView) rootView.findViewById(R.id.tv_zijiDIY);
        tv_zijiDIY.setOnClickListener(this);
        tv_zijidingzhi = (TextView) rootView.findViewById(R.id.tv_zijidingzhi);
        tv_zijidingzhi.setOnClickListener(this);
        rl_zijidingzhi = (AutoRelativeLayout) rootView.findViewById(R.id.rl_zijidingzhi);
        img_zhuanjiatu = (ImageView) rootView.findViewById(R.id.img_zhuanjiatu);
        img_zhuanjiatu.setOnClickListener(this);
        img_zhuanjiaVIP = (ImageView) rootView.findViewById(R.id.img_zhuanjiaVIP);
        img_zhuanjiaVIP.setOnClickListener(this);
        tv_1dui1 = (TextView) rootView.findViewById(R.id.tv_1dui1);
        tv_1dui1.setOnClickListener(this);
        tv_zhuanjiadingzhi = (TextView) rootView.findViewById(R.id.tv_zhuanjiadingzhi);
        tv_zhuanjiadingzhi.setOnClickListener(this);
        rl_zhuanjia = (AutoRelativeLayout) rootView.findViewById(R.id.rl_zhuanjia);
        rl_dingzhi = (AutoLinearLayout) rootView.findViewById(R.id.rl_dingzhi);
        rl_base_3 = (AutoRelativeLayout) rootView.findViewById(R.id.rl_base_3);
        mTv_gengduo_zhenxuantaocan = (TextView) rootView.findViewById(R.id.mTv_gengduo_zhenxuantaocan);
        mTv_gengduo_zhenxuantaocan.setOnClickListener(this);
        rl_zhenxuantaocan = (AutoRelativeLayout) rootView.findViewById(R.id.rl_zhenxuantaocan);
        rl_base_4 = (AutoRelativeLayout) rootView.findViewById(R.id.rl_base_4);
        mRecycler_zhenxuantaocan = (RecyclerView) rootView.findViewById(R.id.mRecycler_zhenxuantaocan);
        mRecycler_zhenxuantaocan.setOnClickListener(this);

        mRecycler_zhenxuantaocan.setLayoutManager(new GridLayoutManager(getContext(), 3));

        mRecycler_platform = (RecyclerView) rootView.findViewById(R.id.mRecycler_platform);
        mRecycler_platform.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rl_pingtaituijian_item = (AutoRelativeLayout) rootView.findViewById(R.id.rl_pingtaituijian_item);
        rl_pingtaituijian_item.setOnClickListener(this);
        tv_tijianka_count = (TextView) rootView.findViewById(R.id.tv_tijianka_count);
        tv_tijianka_count.setOnClickListener(this);
        tv_dingzhijilu = (TextView) rootView.findViewById(R.id.tv_dingzhijilu);
        tv_dingzhijilu.setOnClickListener(this);
        mViewPager_tijianka = (ViewPager) rootView.findViewById(R.id.mViewPager_tijianka);
        mViewPager_tijianka.setOnClickListener(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initData(Bundle arguments) {
//        设置平台推荐数据
        initPlatform();

        List<View> list = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundResource(R.drawable.ic_launcher);
            list.add(imageView);
        }

        PhysicalExaminationCardAdapter adapter = new PhysicalExaminationCardAdapter(getContext(),list);
        mViewPager_tijianka.setAdapter(adapter);

        mViewPager_tijianka.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_tijianka_count.setText("("+(position+1)+"/"+list.size()+")");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                状态变更
            }
        });

//        事件分发
//        mViewPager_tijianka.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return mViewPager_tijianka.dispatchTouchEvent(event);
//            }
//        });


    }

    private void initPlatform() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            list.add(R.mipmap.touxiang_ertong);
        }
        list.add(7, R.mipmap.touxiang_laonian);

        PlatformAdapter adapter = new PlatformAdapter(getContext(), list, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 7) {//体检机构 固定位置
                    Toast.makeText(mActivity, "体检机构---id" + position, Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(mActivity, PhysicalOrganActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable("data", data);
                    intent1.putExtras(bundle1);
                    startActivity(intent1);
                } else {//其他推荐 前7个有运营配置
                    Toast.makeText(mActivity, list.get(position) + "----id" + position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mActivity, SetMealListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", data);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        mRecycler_platform.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
//        mGridView_tijianka.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String aname = data.getAdvList().get(position).getAname();
//                ToastUtil.showToast(getContext(), (position + 1) + ":" + aname);
//            }
//        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent1(AdvertBean advert) {
        getAdvert(advert);
    }

    private void getAdvert(AdvertBean advert) {
        //        广告获取
        data = advert.getData();
        MedicalGridViewAdapter medicalGridViewAdapter = new MedicalGridViewAdapter(getContext(), this.data);
//        setGridView(mGridView_tijianka, this.data, medicalGridViewAdapter, 300);
//        medicalGridViewAdapter.notifyDataSetChanged();

        mRecycler_zhenxuantaocan.setAdapter(new SetmealAdapter(getContext(), data.getAdvList(), PhysicalExaminationFragment.this));

    }

    /**
     * 设置GirdView参数，绑定数据
     *
     * @param
     */
    private void setGridView(GridView gridView, AdvertBean.DataBean dataBean, BaseAdapter adapter, int length) {
        int size = dataBean.getAdvList().size();
//        int length = 300;//设置item长度
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        gridView.setHorizontalSpacing(5); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数

        gridView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_physical://首张Image

                break;
//            case R.id.img_tijiantaocan://体检套餐
//                Intent intent = new Intent(mActivity, PhysicalSetMealActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("data", data);
//                intent.putExtras(bundle);
//                startActivity(intent);
//                break;
//            case R.id.img_tijianjigou://体检机构
//                Intent intent1 = new Intent(mActivity, PhysicalOrganActivity.class);
//                Bundle bundle1 = new Bundle();
//                bundle1.putSerializable("data", data);
//                intent1.putExtras(bundle1);
//                startActivity(intent1);
//                break;
            case R.id.mTv_tijianka_lijibangka://立即绑卡
                multiplexIntent(mActivity, AddPhysicalExaminationCardActivity.class);
                break;
            case R.id.rl_zijidingzhi://自己定制
            case R.id.img_zijitu:
            case R.id.tv_zijiDIY:
            case R.id.tv_zijidingzhi:
                multiplexIntent(mActivity, CustomOneselfActivity.class);
                break;
            case R.id.rl_zhuanjia://专家定制
            case R.id.img_zhuanjiatu:
            case R.id.img_zhuanjiaVIP:
            case R.id.tv_zhuanjiadingzhi:
            case R.id.tv_1dui1:
                multiplexIntent(mActivity, CustomExpertActivity.class);
                break;
            case R.id.mTv_gengduo_zhenxuantaocan://甄选套餐-更多
                Intent intent2 = new Intent(mActivity, RecommendSetmealActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("data", data);
                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "点击第" + (position + 1) + "条", Toast.LENGTH_SHORT).show();
    }

    private void multiplexIntent(Context context, Class cla) {
        Intent intent = new Intent(context, cla);
        startActivity(intent);
    }

}

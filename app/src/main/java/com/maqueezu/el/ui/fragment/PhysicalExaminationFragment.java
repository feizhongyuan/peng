package com.maqueezu.el.ui.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.pojo.GoodsCatBean;
import com.maqueezu.el.ui.activity.child.physicalexamination_child.AddPhysicalExaminationCardActivity;
import com.maqueezu.el.ui.activity.child.physicalexamination_child.CustomExpertActivity;
import com.maqueezu.el.ui.activity.child.physicalexamination_child.CustomExpertRecordsActivity;
import com.maqueezu.el.ui.activity.child.physicalexamination_child.CustomOneToOneActivity;
import com.maqueezu.el.ui.activity.child.physicalexamination_child.CustomOneselfActivity;
import com.maqueezu.el.ui.activity.child.physicalexamination_child.PhysicalOrganActivity;
import com.maqueezu.el.ui.activity.child.physicalexamination_child.RecommendSetmealActivity;
import com.maqueezu.el.ui.activity.child.physicalexamination_child.SetMealListActivity;
import com.maqueezu.el.ui.adapter.MedicalGridViewAdapter;
import com.maqueezu.el.ui.adapter.PhysicalExaminationCardAdapter;
import com.maqueezu.el.ui.adapter.PlatformAdapter;
import com.maqueezu.el.ui.adapter.SetmealAdapter;
import com.maqueezu.el.ui.webview.NewWebFragment;
import com.maqueezu.utils.ui.FragmentContainerActivity;
import com.maqueezu.utils.ui.base.BaseFragment;
import com.maqueezu.utils.ui.web.WebFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 体检模块
 */
public class PhysicalExaminationFragment extends BaseFragment implements View.OnClickListener,
        AdapterView.OnItemClickListener, OnBannerListener, ViewPager.PageTransformer {


    private View rootView;
    private Banner mBanner_PhysicalExamination;//体检轮播图
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
    private SmartRefreshLayout mSmart_refresh_layout;//上拉加载


    private AdvertBean.DataBean data;
    private PhysicalExaminationCardAdapter physicalExaminationCardAdapter;
    private List<View> list;
    private SetmealAdapter setmealAdapter;

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

    private void initBanner() {
        List<String> list_title = new ArrayList<>();
        List<String> list_path = new ArrayList<>();
        for (int i = 0; i < data.getAdvList().size(); i++) {
            list_title.add(data.getAdvList().get(i).getAname());
            list_path.add(data.getAdvList().get(i).getAtturl());
        }


//        mBanner_PhysicalExamination.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mBanner_PhysicalExamination.setImageLoader(new MyLoader());
        mBanner_PhysicalExamination.setBannerAnimation(Transformer.Default);
        mBanner_PhysicalExamination.setBannerTitles(list_title);
        mBanner_PhysicalExamination.setDelayTime(3000);
        mBanner_PhysicalExamination.isAutoPlay(true);
        mBanner_PhysicalExamination.setIndicatorGravity(BannerConfig.CENTER);
        mBanner_PhysicalExamination.setImages(list_path)
                .setOnBannerListener(this)
                .start();
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_physical_examination;
    }

    @Override
    protected void initView(View mRootView) {

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
        mBanner_PhysicalExamination = (Banner) rootView.findViewById(R.id.mBanner_PhysicalExamination);
        mBanner_PhysicalExamination.setOnClickListener(this);
        mSmart_refresh_layout = (SmartRefreshLayout) rootView.findViewById(R.id.mSmart_refresh_layout);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initData(Bundle arguments) {
//        设置平台推荐数据
        initPlatform();

        list = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundResource(R.drawable.ic_launcher);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            list.add(imageView);
        }

        initPhysicalExaminationCard();



    }

    //    体检卡
    private void initPhysicalExaminationCard() {
        int itemWidth = (getResources().getDisplayMetrics().widthPixels) / 3;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mViewPager_tijianka
                .getLayoutParams();
        layoutParams.leftMargin = itemWidth / 2;
        layoutParams.rightMargin = itemWidth / 2;
        mViewPager_tijianka.setLayoutParams(layoutParams);
        mViewPager_tijianka.setPageMargin(getResources().getDimensionPixelSize(R.dimen.dp_5));
        mViewPager_tijianka.setOffscreenPageLimit(3);
        mViewPager_tijianka.setPageTransformer(true, this);

        physicalExaminationCardAdapter = new PhysicalExaminationCardAdapter(getContext(), list);
        mViewPager_tijianka.setAdapter(physicalExaminationCardAdapter);

        ((ViewGroup) mViewPager_tijianka.getParent()).setOnTouchListener(new View.OnTouchListener() {
            float x;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    x = event.getX();
                }
                //如果是点击事件，那么需要处理下，判断是否点在左右两边
                if (event.getAction() == MotionEvent.ACTION_UP && Math.abs(event.getX() - x) < 20) {
                    View view = viewOfClickOnScreen(event);
                    if (view != null) {
                        // int index = mViewPager.indexOfChild(view);
                        int index = physicalExaminationCardAdapter.indexView(view);
                        if (index != mViewPager_tijianka.getCurrentItem()) {
                            mViewPager_tijianka.setCurrentItem(index);
                            return true;
                        }
                    }
                }
                return mViewPager_tijianka.dispatchTouchEvent(event);
            }
        });
    }

    //    平台推荐
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
        mViewPager_tijianka.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_tijianka_count.setText("(" + (position + 1) + "/" + list.size() + ")");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                状态变更
            }
        });

//        将父类的touch事件分发至ViewPager
        rl_base_2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager_tijianka.dispatchTouchEvent(event);
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent1(AdvertBean advert) {
        getAdvert(advert);
    }

    private void getAdvert(AdvertBean advert) {
        //        广告获取
        data = advert.getData();

        initBanner();
        MedicalGridViewAdapter medicalGridViewAdapter = new MedicalGridViewAdapter(getContext(), this.data);
//        setGridView(mGridView_tijianka, this.data, medicalGridViewAdapter, 300);
//        medicalGridViewAdapter.notifyDataSetChanged();

        setmealAdapter = new SetmealAdapter(getContext(), data.getAdvList(), PhysicalExaminationFragment.this);
        mRecycler_zhenxuantaocan.setAdapter(setmealAdapter);

//        下拉刷新
        mSmart_refresh_layout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                List<AdvertBean.DataBean.AdvListBean> advListBeans = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    AdvertBean.DataBean.AdvListBean advListBean = new AdvertBean.DataBean.AdvListBean();
                    advListBean.setAname("标题标题标题标题标题标题标题标题"+i);
                    int i1 = new Random().nextInt(1000);
                    advListBean.setAid(i1+i);
                    advListBean.setAtturl(String.valueOf(R.drawable.ic_launcher));
                    advListBeans.add(advListBean);
                }

                if (advListBeans != null){
                    setmealAdapter.refresh(advListBeans);
                    refreshLayout.finishRefresh(2000);
                }else {
                    refreshLayout.finishRefresh(false);
                }
            }
        });

//        上拉加载
        mSmart_refresh_layout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                List<AdvertBean.DataBean.AdvListBean> advListBeans = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    AdvertBean.DataBean.AdvListBean advListBean = new AdvertBean.DataBean.AdvListBean();
                    advListBean.setAname("标题标题标题标题标题标题标题标题"+i);
                    int i1 = new Random().nextInt(1000);
                    advListBean.setAid(i1+i);
                    advListBean.setAtturl(String.valueOf(R.drawable.ic_launcher));
                    advListBeans.add(advListBean);
                }
                if (advListBeans != null){
                    setmealAdapter.addList(advListBeans);
                    refreshLayout.finishLoadMore(2000);
                }else {
                    refreshLayout.finishLoadMore(false);
                }
            }
        });
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

    //    banner监听
    @Override
    public void OnBannerClick(int position) {
        Intent intent = new Intent(getContext(), FragmentContainerActivity.class);
        intent.putExtra(FragmentContainerActivity.FragmentClassName, NewWebFragment.class.getName());
//        intent.putExtra(NewWebFragment.urlKey, "file:///android_asset/222.html");
//        intent.putExtra(NewWebFragment.urlKey, "https://forum.vuejs.org/t/vue/40391");
        intent.putExtra(NewWebFragment.urlKey, "http://loadingmore.com/vue-element-admin-preview/#/login");//加载网络vue项目
        getContext().startActivity(intent);
        Toast.makeText(mActivity, (position + 1) + "---" + data.getAdvList().get(position).getAname(), Toast.LENGTH_SHORT).show();
    }

    /**
     * 判断当前点击的位置在ViewPager的哪一个View上面
     */
    private View viewOfClickOnScreen(MotionEvent ev) {
        int childCount = mViewPager_tijianka.getChildCount();
        int[] location = new int[2];
        for (int i = 0; i < childCount; i++) {
            View v = mViewPager_tijianka.getChildAt(i);
            v.getLocationOnScreen(location);

            int minX = location[0];
            int minY = mViewPager_tijianka.getTop();

            int maxX = location[0] + v.getWidth();
            int maxY = mViewPager_tijianka.getBottom();

            float x = ev.getX();
            float y = ev.getY();

            if ((x > minX && x < maxX) && (y > minY && y < maxY)) {
                return v;
            }
        }
        return null;
    }

    //    体检卡切换效果
    @Override
    public void transformPage(@NonNull View view, float position) {
        if (position < -1) {
            view.setScaleY(0.8f);
        } else if (position < 0) {
            view.setScaleY(0.2f * position + 1);
        } else if (position < 1) {
            view.setScaleY(-0.2f * position + 1);
        } else {
            view.setScaleY(0.8f);
        }
    }

    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load((String) path)
                    .into(imageView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                multiplexIntent(mActivity,null, AddPhysicalExaminationCardActivity.class);
                mActivity.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                break;
            case R.id.rl_zijidingzhi://自己定制
            case R.id.img_zijitu:
            case R.id.tv_zijiDIY:
            case R.id.tv_zijidingzhi:
                multiplexIntent(mActivity,null, CustomOneselfActivity.class);
                break;
            case R.id.rl_zhuanjia://专家定制
            case R.id.img_zhuanjiatu:
            case R.id.img_zhuanjiaVIP:
            case R.id.tv_zhuanjiadingzhi:
            case R.id.tv_1dui1:
                Bundle bundle4 = new Bundle();
                bundle4.putString("title",getResources().getString(R.string.name_yiduiyidingzhigoutong));
                multiplexIntent(mActivity,bundle4, CustomOneToOneActivity.class);
                break;
            case R.id.tv_dingzhijilu://专家定制记录
                Bundle bundle3 = new Bundle();
                bundle3.putString("title",tv_dingzhijilu.getText().toString());
                multiplexIntent(mActivity,bundle3, CustomExpertRecordsActivity.class);
                break;
            case R.id.mTv_gengduo_zhenxuantaocan://甄选套餐-更多
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("data", data);
                multiplexIntent(mActivity,bundle2,RecommendSetmealActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "点击第" + (position + 1) + "条", Toast.LENGTH_SHORT).show();
    }

    private void multiplexIntent(Context context,Bundle bundle, Class cla) {
        Intent intent = new Intent(context, cla);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

}

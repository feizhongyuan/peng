package com.maqueezu.el.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.maqueezu.el.pojo.GoodsCatBean;
import com.maqueezu.el.ui.activity.MultiplexActivity;
import com.maqueezu.el.ui.activity.child.home_child.SignInActivity;
import com.maqueezu.el.ui.activity.child.physicalexamination_child.RecommendSetmealActivity;
import com.maqueezu.el.ui.adapter.FragmentAdapter;
import com.maqueezu.el.ui.adapter.SetmealAdapter;
import com.maqueezu.el.ui.fragment.home_child.HealthyInformationFragment;
import com.maqueezu.el.ui.fragment.home_child.MedicalServiceFragment;
import com.maqueezu.el.ui.webview.NewWebFragment;
import com.maqueezu.utils.ui.FragmentContainerActivity;
import com.maqueezu.utils.ui.base.BaseFragment;
import com.maqueezu.utils.ui.custom.WaterRippleView;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 今日模块
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private View rootView;
    private WaterRippleView waterrippleView;//圆形进度条展示
    private TextView tv_jiankangxingweizhishu;//健康行为指数
    private TextView tv_jiankangdengji;//健康等级
    private TextView tv_weather_temperature;//天气-温度
    private ImageView img_weather_image;//天气-图片
    private ImageView img_signIn;//签到
    private TextView tv_guizeshuoming;//规则说明
    private ImageView img_renwutu;//任务图
    private TextView tv_renwuxiang;//任务项
    private TextView tv_renwukewancheng;//任务可完成
    private AutoRelativeLayout rl_renwu;//任务框
    private ImageView img_dingdantu;//订单图
    private TextView tv_dingdanxiang;//订单项
    private TextView tv_dingdankewancheng;//订单可完成
    private AutoRelativeLayout rl_dingdan;//订单框
    private AutoLinearLayout rl_pingjia;//任务与订单总框
    private ImageView img_lingqumaquebi;//领取麻雀币图
    private AutoRelativeLayout rl_base_1;
    private TabLayout tab_yiliaofuwu;//医疗服务标题
    private ViewPager view_pager_yiliaofuwu;//医疗服务信息展示
    //    private TextView tv_yiliaofuwu_title;
//    private AutoRelativeLayout rl_yiliaofuwu;
//    private GridView mGridView_yiliaofuwu;//医疗服务展示图
    private AutoRelativeLayout rl_base_2;
    private TextView mTv_gengduo_jiankangshangpin;
    private AutoRelativeLayout rl_jiankanglan;
    private AutoRelativeLayout rl_base_3;
    private AutoRelativeLayout rl_jiankangzixunlan;//健康资讯模块
    private TabLayout tab_toolbar_jiankangzixun;//健康资讯标题切换
    private ViewPager view_pager_jiankangzixun;//健康资讯列表切换
    private AutoRelativeLayout rl_base_4;
    private AutoRelativeLayout rl_item_waterripple;
    private AutoRelativeLayout rl_yiliaofuwu;
    private NestedScrollView mNestedScrollView;//滑动布局
    private AutoRelativeLayout rl_base_all;
    private AutoRelativeLayout rl_maquebilingqu;
    private RecyclerView mRecycler_jiankangshangpin;//健康商品推荐展示图


    private String[] arr = {"关注", "推荐", "养生", "减肥", "女性", "男性", "防癌", "慢性病", "疾病1", "疾病2", "疾病3", "疾病4",};
    private String[] project = {"医疗服务", "内外科陪护", "妇产科陪护", "就医绿色通道", "贴针灸", "医学微视"};
    private List<Fragment> fragments;
    private List<Fragment> fragmentList;
    private AdvertBean.DataBean data = null;
    private GoodsCatBean goodsCatBean = null;

    private int count;
    private int count1;
    private int sum;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //removeMessages(0);
            if (count == 0) {

                waterrippleView.setDepthRate((float) count1 / sum);
                waterrippleView.startRefresh();
                waterrippleView.startRefresh2();
            }
            if (count < count1) {
                count += 10;
                handler.sendEmptyMessage(0);
            } else if (count > count1) {
                count = count1;
            }
            tv_jiankangxingweizhishu.setText(count + "");
//      risenumber.withNumber((float) count).start();//自定义数字
        }
    };

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        EventBus.getDefault().register(this);

        initView(rootView);
        initWaterRipple();
        return rootView;
    }

    private void initWaterRipple() {
//        WaterRippleHelper waterRippleHelper = new WaterRippleHelper(getContext());
//        waterRippleHelper.showEvent(rl_item_waterripple, "waterRipple", 666, 1000);
        count1 = 666;
        sum = 1000;
        waterrippleView.startRefresh2();
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View mRootView) {
        rl_maquebilingqu = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_maquebilingqu);
        rl_maquebilingqu.setOnClickListener(this);
        waterrippleView = (WaterRippleView) mRootView.findViewById(R.id.waterrippleView);
        waterrippleView.setOnClickListener(this);
        tv_jiankangxingweizhishu = (TextView) mRootView.findViewById(R.id.tv_jiankangxingweizhishu);
        tv_jiankangxingweizhishu.setOnClickListener(this);
        tv_jiankangdengji = (TextView) mRootView.findViewById(R.id.tv_jiankangdengji);
        tv_jiankangdengji.setOnClickListener(this);
        rl_item_waterripple = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_item_waterripple);
        rl_item_waterripple.setOnClickListener(this);
        tv_weather_temperature = (TextView) mRootView.findViewById(R.id.tv_weather_temperature);
        tv_weather_temperature.setOnClickListener(this);
        img_weather_image = (ImageView) mRootView.findViewById(R.id.img_weather_image);
        img_weather_image.setOnClickListener(this);
        img_signIn = (ImageView) mRootView.findViewById(R.id.img_signIn);
        img_signIn.setOnClickListener(this);
        tv_guizeshuoming = (TextView) mRootView.findViewById(R.id.tv_guizeshuoming);
        tv_guizeshuoming.setOnClickListener(this);
        rl_base_1 = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_base_1);
        rl_base_1.setOnClickListener(this);
        img_renwutu = (ImageView) mRootView.findViewById(R.id.img_renwutu);
        img_renwutu.setOnClickListener(this);
        tv_renwukewancheng = (TextView) mRootView.findViewById(R.id.tv_renwukewancheng);
        tv_renwukewancheng.setOnClickListener(this);
        tv_renwuxiang = (TextView) mRootView.findViewById(R.id.tv_renwuxiang);
        tv_renwuxiang.setOnClickListener(this);
        rl_renwu = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_renwu);
        rl_renwu.setOnClickListener(this);
        img_dingdantu = (ImageView) mRootView.findViewById(R.id.img_dingdantu);
        img_dingdantu.setOnClickListener(this);
        tv_dingdankewancheng = (TextView) mRootView.findViewById(R.id.tv_dingdankewancheng);
        tv_dingdankewancheng.setOnClickListener(this);
        tv_dingdanxiang = (TextView) mRootView.findViewById(R.id.tv_dingdanxiang);
        tv_dingdanxiang.setOnClickListener(this);
        rl_dingdan = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_dingdan);
        rl_dingdan.setOnClickListener(this);
        rl_pingjia = (AutoLinearLayout) mRootView.findViewById(R.id.rl_pingjia);
        rl_pingjia.setOnClickListener(this);
        img_lingqumaquebi = (ImageView) mRootView.findViewById(R.id.img_lingqumaquebi);
        img_lingqumaquebi.setOnClickListener(this);
        tab_yiliaofuwu = (TabLayout) mRootView.findViewById(R.id.tab_yiliaofuwu);
        rl_yiliaofuwu = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_yiliaofuwu);
        rl_yiliaofuwu.setOnClickListener(this);
        view_pager_yiliaofuwu = (ViewPager) mRootView.findViewById(R.id.view_pager_yiliaofuwu);
        rl_base_2 = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_base_2);
        rl_base_2.setOnClickListener(this);
        mTv_gengduo_jiankangshangpin = (TextView) mRootView.findViewById(R.id.mTv_gengduo_jiankangshangpin);
        mTv_gengduo_jiankangshangpin.setOnClickListener(this);
        rl_jiankanglan = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_jiankanglan);
        rl_jiankanglan.setOnClickListener(this);
        rl_base_3 = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_base_3);
        rl_base_3.setOnClickListener(this);
        rl_jiankangzixunlan = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_jiankangzixunlan);
        rl_jiankangzixunlan.setOnClickListener(this);
        tab_toolbar_jiankangzixun = (TabLayout) mRootView.findViewById(R.id.tab_toolbar_jiankangzixun);
        view_pager_jiankangzixun = (ViewPager) mRootView.findViewById(R.id.view_pager_jiankangzixun);
        rl_base_4 = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_base_4);
        rl_base_4.setOnClickListener(this);
        rl_base_all = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_base_all);
        mNestedScrollView = (NestedScrollView) mRootView.findViewById(R.id.mNestedScrollView);
        mRecycler_jiankangshangpin = (RecyclerView) rootView.findViewById(R.id.mRecycler_jiankangshangpin);
        mRecycler_jiankangshangpin.setLayoutManager(new GridLayoutManager(getContext(),3));
    }

    @Override
    protected void initData(Bundle arguments) {

//        TextView title = (TextView)(((LinearLayout) ((LinearLayout) tab_yiliaofuwu.getChildAt(0)).getChildAt(0)).getChildAt(1));
//        title.setTextSize(12);
//        title.setTextAppearance(getActivity(), R.style.TabLayoutTextSize);

    }

    @Override
    protected void initListener() {

        tab_yiliaofuwu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                view_pager_yiliaofuwu.setCurrentItem(tab.getPosition());
                TextView title = (TextView) (((LinearLayout) ((LinearLayout) tab_yiliaofuwu.getChildAt(0)).getChildAt(tab.getPosition())).getChildAt(1));
                title.setTextAppearance(getActivity(), R.style.TabLayoutTextStyle);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                view_pager_yiliaofuwu.setCurrentItem(tab.getPosition());
                TextView title = (TextView) (((LinearLayout) ((LinearLayout) tab_yiliaofuwu.getChildAt(0)).getChildAt(tab.getPosition())).getChildAt(1));
                title.setTextAppearance(getActivity(), Typeface.NORMAL);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
        gridView.setVerticalScrollBarEnabled(false);//隐藏滚动条

        gridView.setAdapter(adapter);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent1(AdvertBean advert) {
        getAdvert(advert);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent2(GoodsCatBean goodsCatBean) {
        getGoodsCat(goodsCatBean);
    }

    private void getGoodsCat(GoodsCatBean goodsCat) {
//        获取商品分类
        goodsCatBean = goodsCat;
        Log.e("TAG", "商品分类----" + goodsCatBean.toString());

        fragments = new ArrayList<>();

        if (goodsCatBean != null) {
            Log.e("TAG", "--------获取goodsCatBean:" + goodsCatBean.toString());

            for (int i = 0; i < arr.length; i++) {
                HealthyInformationFragment healthyInformationFragment = HealthyInformationFragment.newInstance(arr[i]);
                fragments.add(healthyInformationFragment);
            }
        }
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), fragments, arr);
        view_pager_jiankangzixun.setAdapter(adapter);
        view_pager_jiankangzixun.setOffscreenPageLimit(fragments.size());
        tab_toolbar_jiankangzixun.setupWithViewPager(view_pager_jiankangzixun);

    }


    private void getAdvert(AdvertBean advert) {
//        广告获取
        data = advert.getData();
        Log.e("TAG", "广告获取--" + data.toString());

        fragmentList = new ArrayList<>();

        for (int i = 0; i < project.length; i++) {
            MedicalServiceFragment medicalServiceFragment = MedicalServiceFragment.newInstance(project[i], advert);
            fragmentList.add(medicalServiceFragment);
        }

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getFragmentManager(), fragmentList, project);
        view_pager_yiliaofuwu.setAdapter(fragmentAdapter);
        tab_yiliaofuwu.setupWithViewPager(view_pager_yiliaofuwu);

        if (data != null) {
//            健康商品数据获取并展示
            Log.e("TAG", "------获取data：" + data.toString());
            List<AdvertBean.DataBean.AdvListBean> advList = data.getAdvList();
            List<AdvertBean.DataBean.AdvListBean> listBeans = new ArrayList<>();
//            推荐只取前3个
            for (int i = 0; i < 3; i++) {
                listBeans.add(advList.get(i));
            }
            SetmealAdapter adapter = new SetmealAdapter(getContext(),listBeans,this);
            mRecycler_jiankangshangpin.setAdapter(adapter);
            adapter.notifyDataSetChanged();

//            横向列表图适配
//            MedicalGridViewAdapter medicalGridViewAdapter = new MedicalGridViewAdapter(getContext(), this.data);
//            setGridView(mGridView_yiliaofuwu, this.data, medicalGridViewAdapter, 300);
//            medicalGridViewAdapter.notifyDataSetChanged();
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
            case R.id.img_signIn://签到
                Intent intent = new Intent(mActivity, SignInActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_weather_temperature:
            case R.id.img_weather_image:
                //        TODO 加载Vue项目
                Intent intent2 = new Intent(getContext(), FragmentContainerActivity.class);
                intent2.putExtra(FragmentContainerActivity.FragmentClassName, NewWebFragment.class.getName());
//        intent.putExtra(NewWebFragment.urlKey, "file:///android_asset/222.html");
//        intent.putExtra(NewWebFragment.urlKey, "https://forum.vuejs.org/t/vue/40391");
                intent2.putExtra(NewWebFragment.urlKey, "http://loadingmore.com/vue-element-admin-preview/#/login");//加载网络vue项目
                getContext().startActivity(intent2);
                break;
//            case R.id.waterrippleView:
//
//                break;
            case R.id.tv_guizeshuoming://规则说明
                Intent intent1 = new Intent(mActivity, MultiplexActivity.class);
                Bundle bundle = new Bundle();
                intent1.putExtras(bundle);
                startActivity(intent1);
                break;
            case R.id.rl_renwu://任务项
                break;
            case R.id.rl_dingdan://订单项
                break;
            case R.id.img_lingqumaquebi://领取麻雀币
                break;
            case R.id.mTv_gengduo_jiankangshangpin://健康商品-更多
                Bundle bundle2 = new Bundle();
                bundle2.putString("title","健康商品列表");
                bundle2.putSerializable("data", data);
                multiplexIntent(mActivity,bundle2,RecommendSetmealActivity.class);
                break;
            default:
                break;

        }
    }

//    健康商品条目监听
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mActivity, (position+1)+"---"+data.getAdvList().get(position).getAname(), Toast.LENGTH_SHORT).show();
    }

    private void multiplexIntent(Context context, Bundle bundle, Class cla) {
        Intent intent = new Intent(context, cla);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}

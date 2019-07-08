package com.maqueezu.el.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.pojo.GoodsCatBean;
import com.maqueezu.el.ui.activity.MultiplexActivity;
import com.maqueezu.el.ui.activity.child.home_child.SignInActivity;
import com.maqueezu.el.ui.adapter.FragmentAdapter;
import com.maqueezu.el.ui.adapter.HealthyGridViewAdapter;
import com.maqueezu.el.ui.adapter.MedicalGridViewAdapter;
import com.maqueezu.utils.tools.ToastUtil;
import com.maqueezu.utils.ui.base.BaseFragment;
import com.maqueezu.utils.ui.custom.WaterRippleHelper;
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
public class HomeFragment extends BaseFragment implements View.OnClickListener {

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
    private TextView tv_yiliaofuwu_title;
    private AutoRelativeLayout rl_yiliaofuwu;
    private GridView mGridView_yiliaofuwu;//医疗服务展示图
    private AutoRelativeLayout rl_base_2;
    private TextView mTv_gengduo_jiankangshangpin;
    private AutoRelativeLayout rl_jiankanglan;
    private GridView mGridView_jiankangshangpin;//健康商品展示图
    private AutoRelativeLayout rl_base_3;
    private AutoRelativeLayout rl_jiankangzixunlan;//健康咨询模块
    private TabLayout tab_toolbar_jiankangzixun;
    private ViewPager view_pager_jiankangzixun;
    private AutoRelativeLayout rl_base_4;
    private AutoRelativeLayout rl_item_waterripple;


    private String[] arr = {"关注", "推荐", "养生", "减肥", "女性", "男性", "防癌", "慢性病","疾病1","疾病2","疾病3","疾病4",};
    private List<Fragment> fragments;
    private AdvertBean.DataBean data = null;
    private GoodsCatBean goodsCatBean = null;

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
        WaterRippleHelper waterRippleHelper = new WaterRippleHelper(getContext());
        waterRippleHelper.showEvent(rl_item_waterripple, "waterRipple", 666, 1000);
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View mRootView) {

        waterrippleView = (WaterRippleView) rootView.findViewById(R.id.waterrippleView);
        waterrippleView.setOnClickListener(this);
        tv_jiankangxingweizhishu = (TextView) rootView.findViewById(R.id.tv_jiankangxingweizhishu);
        tv_jiankangxingweizhishu.setOnClickListener(this);
        tv_jiankangdengji = (TextView) rootView.findViewById(R.id.tv_jiankangdengji);
        tv_jiankangdengji.setOnClickListener(this);
        tv_weather_temperature = (TextView) rootView.findViewById(R.id.tv_weather_temperature);
        tv_weather_temperature.setOnClickListener(this);
        img_weather_image = (ImageView) rootView.findViewById(R.id.img_weather_image);
        img_weather_image.setOnClickListener(this);
        img_signIn = (ImageView) rootView.findViewById(R.id.img_signIn);
        img_signIn.setOnClickListener(this);
        tv_guizeshuoming = (TextView) rootView.findViewById(R.id.tv_guizeshuoming);
        tv_guizeshuoming.setOnClickListener(this);
        img_renwutu = (ImageView) rootView.findViewById(R.id.img_renwutu);
        img_renwutu.setOnClickListener(this);
        tv_renwuxiang = (TextView) rootView.findViewById(R.id.tv_renwuxiang);
        tv_renwuxiang.setOnClickListener(this);
        tv_renwukewancheng = (TextView) rootView.findViewById(R.id.tv_renwukewancheng);
        tv_renwukewancheng.setOnClickListener(this);
        rl_renwu = (AutoRelativeLayout) rootView.findViewById(R.id.rl_renwu);
        rl_renwu.setOnClickListener(this);
        img_dingdantu = (ImageView) rootView.findViewById(R.id.img_dingdantu);
        img_dingdantu.setOnClickListener(this);
        tv_dingdanxiang = (TextView) rootView.findViewById(R.id.tv_dingdanxiang);
        tv_dingdanxiang.setOnClickListener(this);
        tv_dingdankewancheng = (TextView) rootView.findViewById(R.id.tv_dingdankewancheng);
        tv_dingdankewancheng.setOnClickListener(this);
        rl_dingdan = (AutoRelativeLayout) rootView.findViewById(R.id.rl_dingdan);
        rl_dingdan.setOnClickListener(this);
        rl_pingjia = (AutoLinearLayout) rootView.findViewById(R.id.rl_pingjia);
        img_lingqumaquebi = (ImageView) rootView.findViewById(R.id.img_lingqumaquebi);
        img_lingqumaquebi.setOnClickListener(this);
        rl_base_1 = (AutoRelativeLayout) rootView.findViewById(R.id.rl_base_1);
        tv_yiliaofuwu_title = (TextView) rootView.findViewById(R.id.tv_yiliaofuwu_title);
        tv_yiliaofuwu_title.setOnClickListener(this);
        rl_yiliaofuwu = (AutoRelativeLayout) rootView.findViewById(R.id.rl_yiliaofuwu);
        mGridView_yiliaofuwu = (GridView) rootView.findViewById(R.id.mGridView_yiliaofuwu);
        rl_base_2 = (AutoRelativeLayout) rootView.findViewById(R.id.rl_base_2);
        mTv_gengduo_jiankangshangpin = (TextView) rootView.findViewById(R.id.mTv_gengduo_jiankangshangpin);
        mTv_gengduo_jiankangshangpin.setOnClickListener(this);
        rl_jiankanglan = (AutoRelativeLayout) rootView.findViewById(R.id.rl_jiankanglan);
        mGridView_jiankangshangpin = (GridView) rootView.findViewById(R.id.mGridView_jiankangshangpin);
        rl_base_3 = (AutoRelativeLayout) rootView.findViewById(R.id.rl_base_3);
        rl_jiankangzixunlan = (AutoRelativeLayout) rootView.findViewById(R.id.rl_jiankangzixunlan);
        tab_toolbar_jiankangzixun = (TabLayout) rootView.findViewById(R.id.tab_toolbar_jiankangzixun);
        tab_toolbar_jiankangzixun.setOnClickListener(this);
        view_pager_jiankangzixun = (ViewPager) rootView.findViewById(R.id.view_pager_jiankangzixun);
        view_pager_jiankangzixun.setOnClickListener(this);
        rl_base_4 = (AutoRelativeLayout) rootView.findViewById(R.id.rl_base_4);
        rl_item_waterripple = (AutoRelativeLayout) rootView.findViewById(R.id.rl_item_waterripple);
    }

    @Override
    protected void initData(Bundle arguments) {



    }

    @Override
    protected void initListener() {
        mGridView_yiliaofuwu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aname = data.getAdvList().get(position).getAname();
                ToastUtil.showToast(getContext(), (position + 1) + ":" + aname);
            }
        });
        mGridView_jiankangshangpin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aname = data.getAdvList().get(position).getAname();
                ToastUtil.showToast(getContext(), (position + 1) + ":" + aname);
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

        if (goodsCatBean != null){
            Log.e("TAG","--------获取goodsCatBean:"+goodsCatBean.toString());

            for (int i = 0; i < arr.length; i++) {
                fragments.add(HealthyInformationFragment.newInstance(arr[i], goodsCatBean));
            }
        }
        FragmentAdapter adapter = new FragmentAdapter(getActivity().getSupportFragmentManager(), fragments, arr);
        view_pager_jiankangzixun.setAdapter(adapter);
        view_pager_jiankangzixun.setOffscreenPageLimit(fragments.size());
        tab_toolbar_jiankangzixun.setupWithViewPager(view_pager_jiankangzixun);

    }


    private void getAdvert(AdvertBean advert) {
//        广告获取
        data = advert.getData();
        Log.e("TAG","广告获取--"+data.toString());

        if (data != null){
            Log.e("TAG","------获取data："+data.toString());
            MedicalGridViewAdapter medicalGridViewAdapter = new MedicalGridViewAdapter(getContext(), this.data);
            setGridView(mGridView_yiliaofuwu, this.data, medicalGridViewAdapter, 300);
            medicalGridViewAdapter.notifyDataSetChanged();

            HealthyGridViewAdapter healthyGridViewAdapter = new HealthyGridViewAdapter(getContext(), this.data);
            setGridView(mGridView_jiankangshangpin, this.data, healthyGridViewAdapter, 150);
            healthyGridViewAdapter.notifyDataSetChanged();
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
            default:
                break;

        }
    }
}

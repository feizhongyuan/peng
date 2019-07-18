package com.maqueezu.el;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.pojo.GoodsCatBean;
import com.maqueezu.el.presenter.HomePresenter;
import com.maqueezu.el.ui.activity.SearchActivity;
import com.maqueezu.el.ui.activity.child.home_child.AddressActivity;
import com.maqueezu.el.ui.activity.child.home_child.AllMessageActivity;
import com.maqueezu.el.ui.fragment.HealthyFragment;
import com.maqueezu.el.ui.fragment.HomeFragment;
import com.maqueezu.el.ui.fragment.MyFragment;
import com.maqueezu.el.ui.fragment.PhysicalExaminationFragment;
import com.maqueezu.el.ui.fragment.ShoppingMallFragment;
import com.maqueezu.el.ui.iview.HomeContract;
import com.maqueezu.utils.tools.LogUtil;
import com.maqueezu.utils.tools.ToastUtil;
import com.maqueezu.utils.ui.base.BaseActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * App的首页
 */
public class MainActivity extends BaseActivity<HomeContract.View, HomePresenter<HomeContract.View>> implements HomeContract.View, View.OnClickListener {
    private boolean mBackKeyPressed = false;//记录是否有首次按键

    String[] permissions = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.CALL_PHONE
//            Manifest.permission.READ_CALENDAR,
//            Manifest.permission.WRITE_CALENDAR,
//            Manifest.permission.SET_ALARM,
//            Manifest.permission.VIBRATE,
//            Manifest.permission.WAKE_LOCK,
    };

    // 声明一个集合，在后面的代码中用来存储用户拒绝授权的权
    List<String> mPermissionList = new ArrayList<>();

    private AdvertBean advertBean;

    private HomeFragment homeFragment;
    private PhysicalExaminationFragment physicalExaminationFragment;
    private HealthyFragment healthyFragment;
    private MyFragment myFragment;
    private ShoppingMallFragment shoppingMallFragment;


    private boolean useThemestatusBarColor = true;
    private boolean useStatusBarColor = true;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ImageView title_back_image;//返回键
    private LinearLayout back_layout;//返回键所属布局
    private ImageView title_image_buttn2;//右侧按键2
    private ImageView title_image_buttn;//右侧按键1（消息框）
    private TextView title_text_buttn;//右侧文字标题
    private LinearLayout title_buttn_layout;//右侧所属布局
    private TextView title_text;//标题
    private RelativeLayout rl_bk;
    private TextView mText_Addr;//地理位置
    private ImageView title_shezhi_image;//设置键
    private EditText et_ProductSearch;//搜索框
    private TextView tv_xiaoxi_num;//消息个数
    private AutoLinearLayout xiaoxi_background;//消息背景


    private RadioButton tab_home;
    private RadioButton tab_tijian;
    private RadioButton tab_shangcheng;
    private RadioButton tab_jiankang;
    private RadioButton tab_mine;
    private RadioGroup tabs_rg;
    private FrameLayout fragment_container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initPermission();

        presenter.fetchData();

        int statusBarHeight = getStatusBarHeight(this);
        Log.e("TAG", "状态栏高度----" + statusBarHeight);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//build.gradle中compileSdkVersion>=23
            //    实现状态栏图标和文字颜色为暗色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        setStatusBar();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected HomePresenter<HomeContract.View> createPresenter() {
        return new HomePresenter<>();
    }

    @Override
    protected void initView() {
        title_back_image = (ImageView) findViewById(R.id.title_back_image);
        title_back_image.setOnClickListener(this);
        title_shezhi_image = (ImageView) findViewById(R.id.title_shezhi_image);
        title_shezhi_image.setOnClickListener(this);
        mText_Addr = (TextView) findViewById(R.id.mText_Addr);
        mText_Addr.setOnClickListener(this);
        back_layout = (AutoLinearLayout) findViewById(R.id.back_layout);
        back_layout.setOnClickListener(this);
        title_image_buttn2 = (ImageView) findViewById(R.id.title_image_buttn2);
        title_image_buttn2.setOnClickListener(this);
        title_image_buttn = (ImageView) findViewById(R.id.title_image_buttn);
        title_image_buttn.setOnClickListener(this);
        title_text_buttn = (TextView) findViewById(R.id.title_text_buttn);
        title_text_buttn.setOnClickListener(this);
        title_buttn_layout = (AutoLinearLayout) findViewById(R.id.title_buttn_layout);
        title_buttn_layout.setOnClickListener(this);
        title_text = (TextView) findViewById(R.id.title_text);
        title_text.setOnClickListener(this);
        et_ProductSearch = (EditText) findViewById(R.id.et_ProductSearch);
        et_ProductSearch.setOnClickListener(this);
        tv_xiaoxi_num = (TextView) findViewById(R.id.tv_xiaoxi_num);
        tv_xiaoxi_num.setOnClickListener(this);
        rl_bk = (AutoRelativeLayout) findViewById(R.id.rl_bk);
        rl_bk.setOnClickListener(this);
        xiaoxi_background = (AutoLinearLayout) findViewById(R.id.xiaoxi_background);
//        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
//        mViewPager.setOnClickListener(this);
//        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
//        mTabLayout.setOnClickListener(this);


        tab_home = (RadioButton) findViewById(R.id.tab_home);
        tab_home.setOnClickListener(this);
        tab_tijian = (RadioButton) findViewById(R.id.tab_tijian);
        tab_tijian.setOnClickListener(this);
        tab_shangcheng = (RadioButton) findViewById(R.id.tab_shangcheng);
        tab_shangcheng.setOnClickListener(this);
        tab_jiankang = (RadioButton) findViewById(R.id.tab_jiankang);
        tab_jiankang.setOnClickListener(this);
        tab_mine = (RadioButton) findViewById(R.id.tab_mine);
        tab_mine.setOnClickListener(this);
        tabs_rg = (RadioGroup) findViewById(R.id.tabs_rg);
        tabs_rg.setOnClickListener(this);
        fragment_container = (FrameLayout) findViewById(R.id.fragment_container);
        fragment_container.setOnClickListener(this);

        initFragment();
    }

    //    更改底部图标大小
    private void setTopDrawable(RadioButton radioButton, int resourceId) {
        //定义底部标签图片大小和位置
        Drawable drawable_home = getResources().getDrawable(resourceId);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_home.setBounds(0, 0, 50, 50);
        //设置图片在文字的哪个方向
        radioButton.setCompoundDrawables(null, drawable_home, null, null);
    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        physicalExaminationFragment = new PhysicalExaminationFragment();
        shoppingMallFragment = new ShoppingMallFragment();
        healthyFragment = new HealthyFragment();
        myFragment = new MyFragment();

//        //      定义tab数据
//        String[] tabtitle = getResources().getStringArray(R.array.tab_titles);
//        List<Fragment> fragments = new ArrayList<>();
//        fragments.add(homeFragment);
//        fragments.add(physicalExaminationFragment);
//        fragments.add(shoppingMallFragment);
//        fragments.add(healthyFragment);
//        fragments.add(myFragment);
//
//        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, tabtitle);
//
//
//        mViewPager.setAdapter(fragmentAdapter);
////        ViewPager的预加载解决
//        mViewPager.setOffscreenPageLimit(fragments.size());
//
//        //        tablayout关联viewpager
//        mTabLayout.setupWithViewPager(mViewPager);
//
//        for (int i = 0; i < fragmentAdapter.getCount(); i++) {
//            if (fragmentAdapter.getItem(i) == homeFragment) {
//                //        标题栏设置
//                title_text.setText(R.string.name_fragment_jinri);
//                isVisibility(title_text, View.VISIBLE);
//                title_image_buttn.setBackgroundResource(R.mipmap.xiaoxi2x);
//                title_image_buttn.setVisibility(View.VISIBLE);
//                mText_Addr.setVisibility(View.VISIBLE);
//                title_back_image.setVisibility(View.GONE);
//                title_image_buttn2.setVisibility(View.GONE);
//                et_ProductSearch.setVisibility(View.GONE);
//                title_shezhi_image.setVisibility(View.GONE);
//            } else if (fragmentAdapter.getItem(i) == physicalExaminationFragment) {
//                title_text.setText(R.string.name_fragment_tijian);
//                title_image_buttn.setBackgroundResource(R.mipmap.shezhi2x);
//                title_image_buttn.setVisibility(View.VISIBLE);
//                title_image_buttn2.setVisibility(View.GONE);
//                et_ProductSearch.setVisibility(View.GONE);
//                title_shezhi_image.setVisibility(View.GONE);
//            } else if (fragmentAdapter.getItem(i) == shoppingMallFragment) {
//                mText_Addr.setVisibility(View.GONE);
//                title_text.setVisibility(View.GONE);
//                et_ProductSearch.setVisibility(View.VISIBLE);
//                title_image_buttn.setBackgroundResource(R.mipmap.xiaoxi2x);
//                title_image_buttn.setVisibility(View.VISIBLE);
//                tv_xiaoxi_num.setVisibility(View.VISIBLE);
//                title_image_buttn2.setVisibility(View.GONE);
//                title_shezhi_image.setVisibility(View.GONE);
//            } else if (fragmentAdapter.getItem(i) == healthyFragment) {
//                title_text.setText(R.string.name_fragment_jiankang);
//                title_image_buttn.setBackgroundResource(R.mipmap.xiaoxi2x);
//                title_image_buttn.setVisibility(View.VISIBLE);
//                tv_xiaoxi_num.setVisibility(View.VISIBLE);
//                title_image_buttn2.setVisibility(View.GONE);
//                et_ProductSearch.setVisibility(View.GONE);
//                title_shezhi_image.setVisibility(View.GONE);
//            } else if (fragmentAdapter.getItem(i) == myFragment) {
//                title_shezhi_image.setVisibility(View.VISIBLE);
//                title_image_buttn.setBackgroundResource(R.mipmap.xiaoxi2x);
//                title_image_buttn.setVisibility(View.VISIBLE);
//                tv_xiaoxi_num.setVisibility(View.VISIBLE);
//                title_image_buttn2.setBackgroundResource(R.mipmap.shezhi2x);
//                title_image_buttn2.setVisibility(View.VISIBLE);
//                et_ProductSearch.setVisibility(View.GONE);
//            }
//        }
//
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//        Drawable drawable = null;
//        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
//            TabLayout.Tab tabAt = mTabLayout.getTabAt(i);
//            switch (i) {
//                case 0:
//                    drawable = getResources().getDrawable(R.drawable.tab_home_selector);
//                    break;
//                case 1:
//                    drawable = getResources().getDrawable(R.drawable.tab_physical_selector);
//                    break;
//                case 2:
//                    drawable = getResources().getDrawable(R.drawable.tab_shoppingmall_selector);
//                    break;
//                case 3:
//                    drawable = getResources().getDrawable(R.drawable.tab_healthy_selector);
//                    break;
//                case 4:
//                    drawable = getResources().getDrawable(R.drawable.tab_mine_selector);
//
//                    break;
//            }
//            tabAt.setIcon(drawable);
//        }
//
//
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, homeFragment)
                .add(R.id.fragment_container, physicalExaminationFragment)
                .add(R.id.fragment_container, shoppingMallFragment)
                .add(R.id.fragment_container, healthyFragment)
                .add(R.id.fragment_container, myFragment)
                .show(homeFragment)
                .hide(physicalExaminationFragment)
                .hide(shoppingMallFragment)
                .hide(healthyFragment)
                .hide(myFragment)
                .commitAllowingStateLoss();


    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initListener() {
        tabs_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (checkedId) {
                    case R.id.tab_home:
                        if (isInitFragment()) {
                            fragmentTransaction.show(homeFragment)
                                    .hide(physicalExaminationFragment)
                                    .hide(shoppingMallFragment)
                                    .hide(healthyFragment)
                                    .hide(myFragment)
                                    .commitAllowingStateLoss();
                            //        标题栏设置
                            title_text.setText(R.string.name_fragment_jinri);
                            title_image_buttn.setBackgroundResource(R.mipmap.xiaoxi2x);
                            isVisibility(title_text, View.VISIBLE);
                            isVisibility(title_image_buttn,View.VISIBLE);
                            isVisibility(tv_xiaoxi_num,View.VISIBLE);
                            isVisibility(mText_Addr,View.VISIBLE);
                            isVisibility(xiaoxi_background,View.VISIBLE);

                            isVisibility(title_back_image,View.GONE);
                            isVisibility(title_image_buttn2,View.GONE);
                            isVisibility(et_ProductSearch,View.GONE);
                            isVisibility(title_shezhi_image,View.GONE);
                        }
                        break;
                    case R.id.tab_tijian:
                        if (isInitFragment()) {
                            fragmentTransaction.show(physicalExaminationFragment)
                                    .hide(homeFragment)
                                    .hide(shoppingMallFragment)
                                    .hide(healthyFragment)
                                    .hide(myFragment)
                                    .commitAllowingStateLoss();
                            title_text.setText(R.string.name_fragment_tijian);
                            title_image_buttn2.setBackgroundResource(R.mipmap.shezhi2x);
                            isVisibility(title_text, View.VISIBLE);
                            isVisibility(title_image_buttn2, View.VISIBLE);
                            isVisibility(title_image_buttn, View.GONE);
                            isVisibility(et_ProductSearch, View.GONE);
                            isVisibility(tv_xiaoxi_num, View.GONE);
                            isVisibility(xiaoxi_background, View.GONE);
                            isVisibility(title_shezhi_image, View.GONE);
                            isVisibility(mText_Addr, View.GONE);
                            isVisibility(title_back_image, View.GONE);
                        }
                        break;
                    case R.id.tab_shangcheng:
                        if (isInitFragment()) {
                            fragmentTransaction.show(shoppingMallFragment)
                                    .hide(homeFragment)
                                    .hide(physicalExaminationFragment)
                                    .hide(healthyFragment)
                                    .hide(myFragment)
                                    .commitAllowingStateLoss();
                            title_image_buttn.setBackgroundResource(R.mipmap.xiaoxi2x);
                            isVisibility(et_ProductSearch,View.VISIBLE);
                            isVisibility(title_image_buttn,View.VISIBLE);
                            isVisibility(tv_xiaoxi_num,View.VISIBLE);
                            isVisibility(xiaoxi_background,View.VISIBLE);
                            isVisibility(mText_Addr,View.GONE);
                            isVisibility(title_image_buttn2,View.GONE);
                            isVisibility(title_shezhi_image,View.GONE);
                            isVisibility(title_back_image,View.GONE);
                        }
                        break;
                    case R.id.tab_jiankang:
                        if (isInitFragment()) {
                            fragmentTransaction.show(healthyFragment)
                                    .hide(homeFragment)
                                    .hide(physicalExaminationFragment)
                                    .hide(shoppingMallFragment)
                                    .hide(myFragment)
                                    .commitAllowingStateLoss();
                            title_text.setText(R.string.name_fragment_jiankang);
                            title_image_buttn.setBackgroundResource(R.mipmap.xiaoxi2x);
                            isVisibility(title_text, View.VISIBLE);
                            isVisibility(title_image_buttn, View.VISIBLE);
                            isVisibility(tv_xiaoxi_num, View.VISIBLE);
                            isVisibility(xiaoxi_background,View.VISIBLE);
                            isVisibility(title_image_buttn2, View.GONE);
                            isVisibility(et_ProductSearch, View.GONE);
                            isVisibility(title_shezhi_image, View.GONE);
                            isVisibility(title_back_image, View.GONE);
                        }
                        break;
                    case R.id.tab_mine:
                        if (isInitFragment()) {
                            fragmentTransaction.show(myFragment)
                                    .hide(homeFragment)
                                    .hide(physicalExaminationFragment)
                                    .hide(shoppingMallFragment)
                                    .hide(healthyFragment)
                                    .commitAllowingStateLoss();
                            title_image_buttn.setBackgroundResource(R.mipmap.xiaoxi2x);
                            title_image_buttn2.setBackgroundResource(R.mipmap.shezhi2x);
                            isVisibility(title_shezhi_image, View.VISIBLE);
                            isVisibility(title_image_buttn, View.VISIBLE);
                            isVisibility(tv_xiaoxi_num, View.VISIBLE);
                            isVisibility(title_image_buttn2, View.VISIBLE);
                            isVisibility(xiaoxi_background,View.VISIBLE);
                            isVisibility(title_text, View.GONE);
                            isVisibility(mText_Addr, View.GONE);
                            isVisibility(et_ProductSearch, View.GONE);
                            isVisibility(title_back_image, View.GONE);
                        }
                        break;
                    default:

                        break;
                }
            }
        });
    }

    public boolean isInitFragment() {
        if (homeFragment != null && physicalExaminationFragment != null && shoppingMallFragment != null
                && healthyFragment != null && myFragment != null) {
            return true;
        } else {
            return false;
        }
    }

    private void isVisibility(View view, int visibility) {
        view.setVisibility(visibility);
    }

    @Override
    public void getAd(AdvertBean advert) {
        advertBean = advert;
        LogUtil.print("TAG3", advertBean.toString());
        EventBus.getDefault().post(advert);
    }

    @Override
    public void getGoodsCatList(GoodsCatBean goodsCatBean) {
        Log.e("TAG4", goodsCatBean.toString());
        EventBus.getDefault().post(goodsCatBean);
    }


    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (useThemestatusBarColor) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.title_bk_color));//设置状态栏背景色
            } else {
                getWindow().setStatusBarColor(Color.TRANSPARENT);//透明
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        } else {
            Toast.makeText(this, "低于4.4的android系统版本不存在沉浸式状态栏", Toast.LENGTH_SHORT).show();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && useStatusBarColor) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public void JumpIntent(Context context, Class mClass) {
        Intent intent = new Intent(context, mClass);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mText_Addr://位置
                ToastUtil.showToast(this, "打开位置");
                Log.e("aaaa", "打开位置");
                Intent intent = new Intent(this, AddressActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                } else {
                    startActivity(intent);
                }
                break;
            case R.id.title_shezhi_image://设置
                ToastUtil.showToast(this, "打开设置");
                Log.e("aaaa", "打开设置");
                break;
            case R.id.title_image_buttn://消息
                ToastUtil.showToast(this, "打开消息");
                Log.e("aaaa", "打开消息");
                Bundle bundle = new Bundle();
                multiplexIntent(this, AllMessageActivity.class,bundle);
                break;
            case R.id.title_image_buttn2://搜索
                ToastUtil.showToast(this,"打开搜索");
                Log.e("aaaa", "打开搜索");
                Bundle bundle1 = new Bundle();
                multiplexIntent(this,SearchActivity.class,bundle1);
                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
                break;
            case R.id.et_ProductSearch:
//                submit();
                Bundle bundle2 = new Bundle();
                multiplexIntent(this,SearchActivity.class,bundle2);
                break;
            default:
                break;
        }
    }

    public void multiplexIntent(Context context,Class cla,Bundle bundle){
        Intent intent = new Intent(context,cla);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {//延时两秒，如果超出则清除第一次记录

                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        } else {
            finish();
        }
    }

    private void submit() {
        // validate
        String ProductSearch = et_ProductSearch.getText().toString().trim();
        if (TextUtils.isEmpty(ProductSearch)) {
            Toast.makeText(this, "商品名称", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

    }

    private void initPermission() {
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了
            Toast.makeText(this, "已经授权", Toast.LENGTH_LONG).show();
        } else {//请求权限方法
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(this, permissions, 2);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "权限已申请", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "权限已取消", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == 2) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    //判断是否勾选禁止后不再询问
                    boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]);
                    if (showRequestPermission) {
                        Toast.makeText(this, "权限未申请", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }


}

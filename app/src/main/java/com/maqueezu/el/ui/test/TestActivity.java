package com.maqueezu.el.ui.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.maqueezu.el.dao.DBTestModelDao;
import com.maqueezu.el.engine.DBManager;
import com.maqueezu.el.engine.NetWorkController;
import com.maqueezu.el.entity.DBTestModel;
import com.maqueezu.el.pojo.UserModel;
import com.maqueezu.el.presenter.HomePresenter;
import com.maqueezu.utils.tools.LogUtil;
import com.maqueezu.utils.tools.http.OnResultListener;
import com.maqueezu.utils.tools.preference.SettingPref;
import com.maqueezu.utils.tools.presenter.BasePresenter;
import com.maqueezu.utils.ui.FragmentContainerActivity;
import com.maqueezu.utils.ui.base.BaseActivity;
import com.maqueezu.utils.ui.web.WebFragment;
import com.maqueezu.utils.view.TitleView;

public class TestActivity extends BaseActivity {

    private TitleView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        titleView = findViewById(R.id.title_view);
        titleView.setBackButVisiable(false);

        //打开测试网页
        findViewById(R.id.web_button).setOnClickListener(v -> {
            Intent intent = new Intent(TestActivity.this, FragmentContainerActivity.class);
            intent.putExtra(FragmentContainerActivity.FragmentClassName, WebFragment.class.getName());
            intent.putExtra(WebFragment.urlKey, "file:///android_asset/test_dw.html");
            startActivity(intent);
        });


        //模拟请求
        findViewById(R.id.request_button).setOnClickListener(v -> {
            showDialog();
            NetWorkController.INSTANCE.loginTest("tang123", "tang123", new OnResultListener<UserModel>() {
                @Override
                public void onResult(UserModel userModel) {

                }

                @Override
                public void onFailse(String s) {

                }
            });
        });


        //模拟列表数据加载
        findViewById(R.id.list_button).setOnClickListener(v -> {
            Intent intent = new Intent(this, FragmentContainerActivity.class);
            intent.putExtra(FragmentContainerActivity.FragmentClassName,LoadDataFragment.class.getName());
            startActivity(intent);
        });

        /**
         *  数据库使用说明
         *
         *  在 pojo包下 按照 DBTestModel 一样的格式来创建模型，每次编译后 dao包中会自动生成数据库操作类
         *
         */
        findViewById(R.id.db_button).setOnClickListener(v -> {
            String id = "123";

            DBTestModel dbTestModel = new DBTestModel();
            dbTestModel.setId(id);
            dbTestModel.setVar("张三");

            long rowId = DBManager.getDaoSession().getDBTestModelDao().insertOrReplace(dbTestModel);
            LogUtil.print("MainActivity", "DBManager insertOrReplace rowId:" + rowId);
            Toast.makeText(this, "保存到数据库" + (rowId > -1 ? "成功" : "失败"), Toast.LENGTH_SHORT).show();

            DBTestModel model = DBManager.getDaoSession().getDBTestModelDao().queryBuilder().where(DBTestModelDao.Properties.Id.eq(id)).build()
                    .unique();
            LogUtil.print("MainActivity", "DBManager queryBuilder model:" + model);
            Toast.makeText(this, "从数据库查询" + (model == null ? "失败" : "成功"), Toast.LENGTH_SHORT).show();
        });

        //sharedPreferences 使用说明
        findViewById(R.id.sharedPreferences_button).setOnClickListener((View v) -> {
            //存储
            SettingPref.getInstance().setFirstOpen(true);
            //获取
            boolean b = SettingPref.getInstance().isFirstOpen();
            Toast.makeText(this, "从sharedPreferences查询" + (b ? "成功" : "失败"), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected BasePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}

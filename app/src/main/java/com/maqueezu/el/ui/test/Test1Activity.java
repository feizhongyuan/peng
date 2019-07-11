package com.maqueezu.el.ui.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.maqueezu.el.Constant;
import com.maqueezu.el.R;
import com.maqueezu.el.pojo.CodeBean;
import com.maqueezu.el.pojo.UserBean;
import com.maqueezu.el.pojo.UserInfoBean;
import com.maqueezu.el.presenter.MinePresenter;
import com.maqueezu.el.ui.activity.WelcomeActivity;
import com.maqueezu.el.ui.iview.IMineView;
import com.maqueezu.utils.ui.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录验证页
 */
public class Test1Activity extends BaseActivity<IMineView, MinePresenter<IMineView>> implements IMineView, View.OnClickListener {


    private SharedPreferences sharedPreferences;
    private EditText username;
    private EditText code;
    private Button login;
    private Button sendCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test1;
    }

    @Override
    protected MinePresenter<IMineView> createPresenter() {
        return new MinePresenter<>();
    }

    @Override
    protected void initView() {
        username = (EditText) findViewById(R.id.username);
        code = (EditText) findViewById(R.id.code);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        sendCode = (Button) findViewById(R.id.sendCode);
        sendCode.setOnClickListener(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Map<String,String> map = new HashMap<>();
        map.put("username",username.getText().toString().trim());
        map.put("password",Constant.HTTP.CODE);
        map.put("validcode",code.getText().toString().trim());
        map.put("valid",Constant.HTTP.VALID);
        presenter.fetchLoginCode(map);
    }
//      获取注册用户信息
    @Override
    public void getUserInfo(UserBean userBean) {
        Log.e("TAG22", userBean.getData().toString());
    }

//    获取验证码
    @Override
    public void getCode(CodeBean codeBean) {
        Log.e("TAG33", codeBean.getMessage());
        Toast.makeText(this, codeBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

//    验证码登录    获取用户详细信息
    @Override
    public void LoginCode(UserInfoBean userInfoBean) {
        Log.e("TAG44",userInfoBean.toString());
    }

    private void submit() {
        // validate
        String usernameString = username.getText().toString().trim();
        if (TextUtils.isEmpty(usernameString)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String passwordString = code.getText().toString().trim();
        if (TextUtils.isEmpty(passwordString)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        presenter.fetchUserInfo(usernameString, Constant.HTTP.CODE);

        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                submit();
                break;
            case R.id.sendCode:
                String usernameString = username.getText().toString().trim();
                if (TextUtils.isEmpty(usernameString)) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                presenter.fetchCode(usernameString, "");
                break;
        }
    }
}

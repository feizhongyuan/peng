package com.maqueezu.el.engine.imodel;

import com.maqueezu.el.pojo.CodeBean;
import com.maqueezu.el.pojo.UserBean;
import com.maqueezu.el.pojo.UserInfoBean;
import com.maqueezu.utils.tools.http.OnResultListener;

import java.util.Map;

/**
 * Created by fei .
 * Created by Date 2019/6/4 14:18
 */

public interface IMineModel {
    //    获取用户信息
    void LoadUserInfo(OnResultListener<UserBean> listener, String username,String userpassword);

//      获取验证码
    void LoadCode(OnResultListener<CodeBean> listener, String mobile, String code);

//    验证码登录
    void LoadLoginCode(OnResultListener<UserInfoBean> listener, Map<String,String> map);
}

package com.maqueezu.el.ui.iview;

import com.maqueezu.el.pojo.CodeBean;
import com.maqueezu.el.pojo.UserBean;
import com.maqueezu.el.pojo.UserInfoBean;

/**
 * Created by fei .
 * Created by Date 2019/6/4 14:14
 */

public interface IMineView {
    void getUserInfo(UserBean userBean);

    void getCode(CodeBean codeBean);

    void LoginCode(UserInfoBean userInfoBean);
}

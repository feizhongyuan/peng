package com.maqueezu.el.engine;

import android.util.Log;

import com.maqueezu.el.engine.imodel.IMineModel;
import com.maqueezu.el.pojo.CodeBean;
import com.maqueezu.el.pojo.UserBean;
import com.maqueezu.el.pojo.UserInfoBean;
import com.maqueezu.utils.tools.http.ApiFactory;
import com.maqueezu.utils.tools.http.OnResultListener;

import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fei .
 * Created by Date 2019/6/4 14:20
 */

public class MineModelImpl implements IMineModel {

    @Override
    public void LoadUserInfo(OnResultListener<UserBean> listener, String username, String userpassword) {
        Api.getUserInfo api = ApiFactory.ApiType.def.getApi(Api.getUserInfo.class);
        api.getUserInfo(username,userpassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailse(e.getMessage());
                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        listener.onResult(userBean);
                    }
                });
    }

    @Override
    public void LoadCode(OnResultListener<CodeBean> listener, String mobile, String code) {
        Api.getUserInfo api = ApiFactory.ApiType.def.getApi(Api.getUserInfo.class);
        api.getCode(mobile,code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CodeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailse(e.getMessage());
                    }

                    @Override
                    public void onNext(CodeBean codeBean) {
                        listener.onResult(codeBean);
                    }
                });
    }

    @Override
    public void LoadLoginCode(OnResultListener<UserInfoBean> listener, Map<String, String> map) {
        Api.getUserInfo api = ApiFactory.ApiType.def.getApi(Api.getUserInfo.class);
        api.LoginCode(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInfoBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailse(e.getMessage());
                    }

                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        listener.onResult(userInfoBean);
                    }
                });
    }


}

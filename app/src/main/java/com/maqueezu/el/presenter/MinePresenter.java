package com.maqueezu.el.presenter;

import com.maqueezu.el.engine.MineModelImpl;
import com.maqueezu.el.engine.imodel.IMineModel;
import com.maqueezu.el.pojo.CodeBean;
import com.maqueezu.el.pojo.UserBean;
import com.maqueezu.el.pojo.UserInfoBean;
import com.maqueezu.el.ui.iview.IMineView;
import com.maqueezu.utils.tools.LogUtil;
import com.maqueezu.utils.tools.http.OnResultListener;
import com.maqueezu.utils.tools.presenter.BasePresenter;

import java.util.Map;

/**
 * Created by fei .
 * Created by Date 2019/6/4 14:16
 */

public class MinePresenter<T extends IMineView> extends BasePresenter<T> {

    private IMineModel mineModel = new MineModelImpl();

    public MinePresenter() {
    }

    public void fetchUserInfo(String username,String userpassword){
        if (mViewRef.get() != null){
            if (mineModel != null){
                mineModel.LoadUserInfo(new OnResultListener<UserBean>() {
                    @Override
                    public void onResult(UserBean userBean) {
                        mViewRef.get().getUserInfo(userBean);
                    }

                    @Override
                    public void onFailse(String s) {
                        LogUtil.print("TAG-fail",s);
                    }
                },username,userpassword);
            }
        }
    }

    public void fetchCode(String mobile,String code){
        if (mViewRef.get() != null){
            if (mineModel != null){
                mineModel.LoadCode(new OnResultListener<CodeBean>() {
                    @Override
                    public void onResult(CodeBean codeBean) {
                        mViewRef.get().getCode(codeBean);
                    }

                    @Override
                    public void onFailse(String s) {
                        LogUtil.print("TAG-fail",s);
                    }
                },mobile,code);
            }
        }
    }

    public void fetchLoginCode(Map<String,String> map){
        if (mViewRef.get() != null){
            if (mineModel != null){
                mineModel.LoadLoginCode(new OnResultListener<UserInfoBean>() {
                    @Override
                    public void onResult(UserInfoBean userInfoBean) {
                        mViewRef.get().LoginCode(userInfoBean);
                    }

                    @Override
                    public void onFailse(String s) {
                        LogUtil.print("TAG-fail",s);
                    }
                },map);
            }
        }
    }

}

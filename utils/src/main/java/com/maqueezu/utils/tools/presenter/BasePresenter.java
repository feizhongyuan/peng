package com.maqueezu.utils.tools.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by fei .
 * Created by Date 2019/5/31 9:47
 */

public abstract class BasePresenter<T> {
//    View引用
    protected WeakReference<T> mViewRef;

    public void attachView(T view){
        mViewRef = new WeakReference<T>(view);
    }

    public void datachView(){
        mViewRef.clear();
    }

}

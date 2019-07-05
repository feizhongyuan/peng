package com.maqueezu.utils.ui.recyclerView;

import com.maqueezu.utils.tools.http.OnResultListener;
import com.maqueezu.utils.ui.recyclerView.adapter.BaseRecyclerAdapter;

import java.io.Serializable;

public abstract class RecyclerLoadListener<T,V extends Serializable> implements OnResultListener<T> {
    private RecyclerViewListFragment fragment;

    protected abstract BaseRecyclerAdapter<V> getAdapter();

    protected  abstract void onLoad(ParametersBean parametersBean, OnResultListener<T> listener);

    @Override
    public void onResult(T t) {
        RespanseModel<V> respanseModel = onRespanse(t);
        fragment.onRespance(respanseModel);
    }

    protected abstract RespanseModel<V> onRespanse(T t);

    public void setFragment(RecyclerViewListFragment fragment) {
        this.fragment = fragment;
    }
}

package com.maqueezu.utils.ui.recyclerView;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maqueezu.utils.R;
import com.maqueezu.utils.tools.LogUtil;
import com.maqueezu.utils.ui.recyclerView.adapter.BaseRecyclerAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.io.Serializable;
import java.util.List;


/**
 * Created by admin on 2019/5/8 0008.
 */

public class RecyclerViewListFragment<T,V extends Serializable> extends RecyclerBaseFragment {
     private static final String tag = "RecyclerViewListFragment";

    protected RefreshLayout refreshLayout;
    protected RecyclerView recyclerView;
    protected BaseRecyclerAdapter<V> adapter;
    protected RecyclerLoadListener<T,V> loadListener;
    private LinearLayoutManager layoutManager;
    private View rootView;

    @Override
    protected void onHanderMessage(Message msg) {
        String message = "";
        if (msg.obj != null) {
            message = msg.obj.toString().trim();
        }

        switch (msg.what) {
            case MSG_UI_START_LOADING:
                LogUtil.print(tag, "MSG_UI_START_LOADING");
                loadData(loadPage, parametersBean.getPageSize());
                break;
            case MSG_UI_LOAD_FAILE:// 加载失败
                LogUtil.print(tag, "MSG_UI_LOAD_FAILE");
                if (refreshLayout != null) {
                    if (loadPage == 1) {
                        refreshLayout.finishRefresh(false);
                    } else {
                        refreshLayout.finishLoadMore(false);
                    }
                }
                break;
            case MSG_UI_LOAD_NO_DATA:// 没有数据
                LogUtil.print(tag, "MSG_UI_LOAD_NO_DATA");
                if (refreshLayout != null) {
                    refreshLayout.finishRefresh(true);
                }
                break;
            case MSG_UI_LOAD_SUCCESS:// 加载成功有更多数据
                LogUtil.print(tag, "MSG_UI_LOAD_SUCCESS");
                if (refreshLayout != null) {
                    if (loadPage == 1) {
                        refreshLayout.finishRefresh(0,true,false);
                    } else {
                        refreshLayout.finishLoadMore(0,true,false);
                    }
                }
                break;
            case MSG_ALL_DATA_HAVE_LOADED:// 加载成功没有更多数据
                LogUtil.print(tag, "MSG_ALL_DATA_HAVE_LOADED");
                if (refreshLayout != null) {
                    if (loadPage == 1) {
                        refreshLayout.finishRefresh(0, true, true);
                    } else {
                        refreshLayout.finishLoadMore(0, true, true);
                    }
                }
                break;
            case DATABASE_LOED_SUCCESS:// 数据库获取到数据时
                LogUtil.print(tag, "DATABASE_LOED_SUCCESS");
                if (refreshLayout != null) {
                    refreshLayout.finishRefresh();
                }

            default:
                break;
        }
    }

    /**
     * 获取fragment实例，fragment_parent大于0时才会自动添加到fragment
     */
    public static <T,V extends Serializable> RecyclerViewListFragment<T,V> getInstance(FragmentManager fm, ParametersBean parametersBean) {
        RecyclerViewListFragment<T,V> fragment = null;
        if (parametersBean.getFragmentParentId() > 0) {
            fragment = (RecyclerViewListFragment) fm.findFragmentById(parametersBean.getFragmentParentId());
        }
        if (fragment == null) {
            fragment = new RecyclerViewListFragment<T,V>();
            if (parametersBean.getFragmentParentId() > 0) {
                fm.beginTransaction().replace(parametersBean.getFragmentParentId(), fragment).commitAllowingStateLoss();
            }
        }
        LogUtil.print("parametersBean", "parametersBean=" + parametersBean);
        fragment.reSetParameters(parametersBean);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);

        return rootView;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_recyclerview_list;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refresh();
    }

    @Override
    protected void initView(View mRootView) {
        super.initView(mRootView);
        refreshLayout = getView().findViewById(R.id.refreshLayout);
        recyclerView = getView().findViewById(R.id.recyclerView);
    }

    @Override
    protected void initData(Bundle arguments) {
        super.initData(arguments);

        if (layoutManager == null) {
            layoutManager = new WrapContentLinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
        } else {
            recyclerView.setLayoutManager(layoutManager);
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        refreshLayout.setOnRefreshListener(refreshlayout -> {
            refresh();
//            refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
        });
        refreshLayout.setOnLoadMoreListener(refreshlayout -> {
            loadNextPage();
//            refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
        });
    }

    public void reSetParameters(ParametersBean parametersBean) {
        this.parametersBean = parametersBean;
    }

    public void setLoadListener(RecyclerLoadListener<T,V> loadListener) {
        this.loadListener = loadListener;
        if(loadListener != null){
            loadListener.setFragment(this);
        }
    }

    /**
     * 设置布局管理器，不设默认为WrapContentLinearLayoutManager
     */
    public void setLayoutManager(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }


    protected void loadData(final int loadPage, int pageSize) {
        parametersBean.setCurPage(loadPage);
        parametersBean.setPageSize(pageSize);
        if (loadListener != null) {
            loadListener.onLoad(parametersBean, this.loadListener);
        }
    }


    public void onRespance(RespanseModel<V> model){

        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }

        if (parametersBean == null) {
            return;
        }

        if (model.isSuccess()) {
            sendMessage(MSG_UI_LOAD_FAILE, model.getMessage());
            return;
        }

        List<V> list = model.getList();
        if(parametersBean.getCurPage() == 1){
            if (list == null || list.size() < 1) {
                sendEmptyMessage(MSG_UI_LOAD_NO_DATA);
                return;
            }
        }

        BaseRecyclerAdapter<V> adapter = (BaseRecyclerAdapter) recyclerView.getAdapter();
        if(adapter == null){
            adapter = loadListener.getAdapter();
            recyclerView.setAdapter(adapter);
        }

        //记录下更新数据前的滚动值
        int scrolly = recyclerView.getScrollY();
        // 成功获取到了数据
        if (parametersBean.getCurPage() == 1) {
            adapter.update(list);
            recyclerView.getAdapter().notifyItemRangeChanged(0, recyclerView.getAdapter().getItemCount());
        } else {
            adapter.add(list);
            recyclerView.getAdapter().notifyItemRangeInserted(adapter.getItemCount(), list.size());
        }
        //恢复更新数据前的滚动值
        recyclerView.scrollTo(0, scrolly);

        if (model.getPageCount() <= parametersBean.getCurPage()) {
            sendMessage(MSG_ALL_DATA_HAVE_LOADED, "无更多数据");
        } else {
            // 加载成功且还有更多数据
            sendMessage(MSG_UI_LOAD_SUCCESS, "加载下一页");
        }
    }
}

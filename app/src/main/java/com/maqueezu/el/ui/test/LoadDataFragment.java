package com.maqueezu.el.ui.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.LoadModel;
import com.maqueezu.utils.tools.http.OnResultListener;
import com.maqueezu.utils.ui.base.BaseFragment;
import com.maqueezu.utils.ui.recyclerView.ParametersBean;
import com.maqueezu.utils.ui.recyclerView.RecyclerLoadListener;
import com.maqueezu.utils.ui.recyclerView.RecyclerViewListFragment;
import com.maqueezu.utils.ui.recyclerView.RespanseModel;
import com.maqueezu.utils.ui.recyclerView.adapter.BaseRecyclerAdapter;
import com.maqueezu.utils.ui.recyclerView.adapter.MyViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2019/5/8 0008.
 * <p>
 * <p>
 * 模拟了集合分页数据加载的情况
 */
public class LoadDataFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private RecyclerViewListFragment<LoadModel,LoadModel.Data> fragment;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);

        return rootView;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_load_data;
    }

    @Override
    protected void initView(View mRootView) {

    }

    @Override
    protected void initData(Bundle arguments) {

        ParametersBean bean = new ParametersBean();
        bean.setCurPage(1);//从第几页开始加载
        bean.setPageSize(30);//一次加载多少条数据

        fragment = RecyclerViewListFragment.getInstance(getChildFragmentManager(), bean);
        fragment.setLoadListener(new RecyclerLoadListener<LoadModel,LoadModel.Data>() {

            @Override
            public void onFailse(String s) {

            }

            @Override
            public BaseRecyclerAdapter<LoadModel.Data> getAdapter() {
                return new MyLoadDataAdapter(getActivity(), null, LoadDataFragment.this);
            }

            @Override
            public void onLoad(ParametersBean parametersBean, OnResultListener<LoadModel> listener) {
                List<LoadModel.Data> list = new ArrayList<>();
                //模拟加载数据
                for(double i = 100*parametersBean.getCurPage(); i < (100*parametersBean.getCurPage()+parametersBean.getPageSize()); i++){
                    LoadModel.Data data = new LoadModel.Data();
                    data.setStr(i+"");
                    list.add(data);
                }

                //组装模拟返回的数据，这里仅做演示，真实代码应该是最下面一行代码的写法
                LoadModel model = new LoadModel();
                //设置api返回的数据
                model.setResultData(list);
                //设置api返回的业务状态码
                model.setResultCode(1);
                //设置api返回的消息提示
                model.setMessage("请求成功");
                //设置http请求状态码
                model.setCode("200");
                //设置总共只有200条数据
                model.setTotalCount(200);
                //这里不应该手动调用，而是如下传递this后，this的onResult 会被自动调用，这里仅做模拟被调用
                listener.onResult(model);

                //=====================================================================================


                //真实的加载数据 应该如下这样
//                NetWorkController.INSTANCE.****( loadpager,pagesize,this);
            }

            @Override
            protected RespanseModel<LoadModel.Data> onRespanse(LoadModel loadModel) {
                RespanseModel<LoadModel.Data> model = new RespanseModel<>();
                model.setList(loadModel.getResultData());
                model.setMessage(loadModel.getMessage());
                model.setSuccess(true);
                model.setPageCount(10);
                return model;
            }
        });


    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(),"点击第"+position+"条",Toast.LENGTH_SHORT).show();
    }


    /**
     * 数据适配器
     */
    private class MyLoadDataAdapter extends BaseRecyclerAdapter<LoadModel.Data>{
        public MyLoadDataAdapter(Context context, List<LoadModel.Data> list, AdapterView.OnItemClickListener itemClickListener) {
            super(context, list, itemClickListener);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
            Holder holder = new Holder(LayoutInflater.from(context).inflate(R.layout.item_load_data, null));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder viewHolder, int position) {
            super.onBindViewHolder(viewHolder, position);
            Holder holder = (Holder) viewHolder;
            holder.tv.setText(list.get(position).getStr());
        }

        public class Holder extends MyViewHolder {
            public Holder(View view) {
                super(view);
                tv =  view.findViewById(R.id.tv);
            }
            TextView tv;
        }
    }
}

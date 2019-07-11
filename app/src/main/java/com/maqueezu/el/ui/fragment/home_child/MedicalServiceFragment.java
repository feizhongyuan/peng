package com.maqueezu.el.ui.fragment.home_child;


import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.ui.adapter.MedicalGridViewAdapter;
import com.maqueezu.utils.tools.ToastUtil;
import com.maqueezu.utils.ui.base.BaseFragment;

/**
 * 今日-医疗服务模块
 */
public class MedicalServiceFragment extends BaseFragment {


    private GridView mGridView_yiliaofuwu;
    private AdvertBean.DataBean data;
    private TextView tv_title;

    public MedicalServiceFragment() {
        // Required empty public constructor
    }

    public static MedicalServiceFragment newInstance(String title, AdvertBean advertBean) {
        MedicalServiceFragment medicalServiceFragment = new MedicalServiceFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putSerializable("advert", advertBean);
        medicalServiceFragment.setArguments(bundle);
        return medicalServiceFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        initView(rootView);
        return rootView;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_medical_service;
    }

    @Override
    protected void initView(View mRootView) {

        mGridView_yiliaofuwu = (GridView) mRootView.findViewById(R.id.mGridView_yiliaofuwu);

        tv_title = (TextView) mRootView.findViewById(R.id.tv_title);
    }

    @Override
    protected void initData(Bundle arguments) {
        String title = arguments.getString("title");
        AdvertBean advert = (AdvertBean) arguments.getSerializable("advert");
        data = advert.getData();

        tv_title.setText(title);

        MedicalGridViewAdapter medicalGridViewAdapter = new MedicalGridViewAdapter(getContext(), this.data);
        setGridView(mGridView_yiliaofuwu, this.data, medicalGridViewAdapter, 300);
        medicalGridViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {
        mGridView_yiliaofuwu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aname = data.getAdvList().get(position).getAname();
                ToastUtil.showToast(getContext(), (position + 1) + ":" + aname);
            }
        });
    }

    /**
     * 设置GirdView参数，绑定数据
     *
     * @param
     */
    private void setGridView(GridView gridView, AdvertBean.DataBean dataBean, BaseAdapter adapter, int length) {
        int size = dataBean.getAdvList().size();
//        int length = 300;//设置item长度
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        gridView.setHorizontalSpacing(5); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数
        gridView.setVerticalScrollBarEnabled(false);//隐藏滚动条

        gridView.setAdapter(adapter);
    }

}

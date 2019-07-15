package com.maqueezu.el.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.ShoppingCartBean;
import com.maqueezu.utils.ui.recyclerView.adapter.BaseRecyclerAdapter;
import com.maqueezu.utils.ui.recyclerView.adapter.MyViewHolder;

import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/6/28 14:54
 *
 * 调整项目适配
 */

public class SingleProjectAdatper2 extends BaseRecyclerAdapter<ShoppingCartBean> {

    //     复选框接口
    private CheckListener checkListener;
    public void setCheckListener(CheckListener checkListener) {
        this.checkListener = checkListener;
    }

    public SingleProjectAdatper2(Context context, List<ShoppingCartBean> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
       Holder holder = new Holder(LayoutInflater.from(context).inflate(R.layout.item_singleproject2, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        Holder holder = (Holder) viewHolder;
        holder.tv_singleProject_name.setText(list.get(position).getShoppingName());
        holder.tv_singleProject_price.setText("¥"+list.get(position).getPrice());

        final ShoppingCartBean shoppingCartBean = list.get(position);
        boolean choosed = shoppingCartBean.isChoosed();
        if (choosed){
            holder.cb_select.setChecked(true);
        }else{
            holder.cb_select.setChecked(false);
        }

        holder.cb_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingCartBean.setChoosed(((CheckBox) v).isChecked());
                checkListener.checkGroup(position,((CheckBox) v).isChecked());//向外暴露接口
            }
        });

    }

    public class Holder extends MyViewHolder{

        private final TextView tv_singleProject_name;
        private final TextView tv_singleProject_price;
        private final CheckBox cb_select;

        public Holder(View itemView) {
            super(itemView);
            cb_select = (CheckBox) itemView.findViewById(R.id.cb_select);
            tv_singleProject_name = (TextView) itemView.findViewById(R.id.tv_singleProject_name);
            tv_singleProject_price = (TextView) itemView.findViewById(R.id.tv_singleProject_price);
        }
    }

    /**
     * 复选框接口
     */
    public interface CheckListener {
        /**
         * 组选框状态改变触发的事件
         *
         * @param position  元素位置
         * @param isChecked 元素选中与否
         */
        void checkGroup(int position, boolean isChecked);
    }


}

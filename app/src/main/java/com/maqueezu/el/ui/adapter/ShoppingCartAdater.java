package com.maqueezu.el.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maqueezu.el.R;
import com.maqueezu.el.pojo.ShoppingCartBean;

import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/7/1 15:54
 *
 * 购物车适配
 */

public class ShoppingCartAdater extends BaseAdapter {

    private Context mContext;
    private List<ShoppingCartBean> list;
    private Button btnOrder;
    private TextView tvTotalPrice;
    private TextView tv_total_count;
    private double total_price;

    //    选择接口方法
    private OnClickListenerModel onClickListenerModel = null;
    public void setOnClickListenerModel(OnClickListenerModel onClickListenerModel) {
        this.onClickListenerModel = onClickListenerModel;
    }
//     复选框接口
    private CheckListener checkListener;
    public void setCheckListener(CheckListener checkListener) {
        this.checkListener = checkListener;
    }

    public ShoppingCartAdater(Context mContext, List<ShoppingCartBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_shoppingcart_child,parent, false);
            viewHolder = ViewHolder.newViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(list.get(position).getShoppingName());
        viewHolder.tv_price_value.setText(list.get(position).getPrice()+"");
        viewHolder.tv_edit_buy_number.setText(list.get(position).getCount()+"");
//        Glide.with(mContext).load(list.get(position).getImageUrl()).into(viewHolder.img_photo);
        viewHolder.img_photo.setBackgroundResource(R.drawable.ic_launcher);


        final ShoppingCartBean shoppingCartBean = list.get(position);
        boolean choosed = shoppingCartBean.isChoosed();
        if (choosed){
            viewHolder.cb_select.setChecked(true);
        }else{
            viewHolder.cb_select.setChecked(false);
        }

        viewHolder.cb_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingCartBean.setChoosed(((CheckBox) v).isChecked());
                checkListener.checkGroup(position,((CheckBox) v).isChecked());//向外暴露接口
            }
        });
        viewHolder.iv_edit_subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                删减数量 暴露删减接口
                onClickListenerModel.onItemReduceClick(position,viewHolder.tv_edit_buy_number,viewHolder.cb_select.isChecked());
            }
        });
        viewHolder.iv_edit_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                添加数量  暴露添加接口
                onClickListenerModel.onItemIncreaseClick(position,viewHolder.tv_edit_buy_number,viewHolder.cb_select.isChecked());
            }
        });

        return convertView;
    }


    static class ViewHolder{
        private final CheckBox cb_select;//选择
        private final ImageView img_photo;
        private final TextView tv_name;
        private final TextView guige1;//规格
        private final TextView tv_price_value;//价格
        private final ImageView iv_edit_subtract;//减少个数
        private final TextView tv_edit_buy_number;//总个数
        private final ImageView iv_edit_add;//添加个数

        public ViewHolder(View convertView){
            cb_select = convertView.findViewById(R.id.cb_select);
            img_photo = convertView.findViewById(R.id.img_photo);
            tv_name = convertView.findViewById(R.id.tv_name);
            guige1 = convertView.findViewById(R.id.guige1);
            tv_price_value = convertView.findViewById(R.id.tv_price_value);
            iv_edit_subtract = convertView.findViewById(R.id.iv_edit_subtract);
            tv_edit_buy_number = convertView.findViewById(R.id.tv_edit_buy_number);
            iv_edit_add = convertView.findViewById(R.id.iv_edit_add);
        }

        public static ViewHolder newViewHolder(View convertView){
            ViewHolder viewHolder= (ViewHolder) convertView.getTag();
            if (viewHolder==null){
                viewHolder=new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }
            return viewHolder;
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

    public interface OnClickListenerModel{
        //

        /**
         * 添加接口方法
         * @param position     元素位置
         * @param view          展示变化后数量的View
         * @param isFlang       子元素选中与否
         */
        void onItemIncreaseClick(int position, View view,boolean isFlang);

        /**
         * 删除接口方法
         * @param position      元素位置
         * @param view          展示变化后数量的View
         * @param isFlang       子元素选中与否
         */
        void onItemReduceClick(int position,View view,boolean isFlang);

        //    数量接口方法
        void onItemCountClick(View view,int index,int position,int num);

//        删除
        void childDelete(int position);
//         收藏
        void childCollection(int position);
    }


}

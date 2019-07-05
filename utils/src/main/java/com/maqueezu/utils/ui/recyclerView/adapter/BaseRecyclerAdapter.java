package com.maqueezu.utils.ui.recyclerView.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import java.io.Serializable;
import java.util.List;

public class BaseRecyclerAdapter<T extends Serializable> extends Adapter<MyViewHolder> {

	protected Context context;
	protected List<T> list;
	protected OnItemClickListener itemClickListener;
	protected AdapterView.OnItemLongClickListener itemLongClickListener;

	public BaseRecyclerAdapter(Context context, List<T> list, OnItemClickListener itemClickListener) {
		this.context = context;
		this.list = list;
		this.itemClickListener = itemClickListener;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public void update(List<T> list) {
		this.list = list;
	}

	public void add(List<T> list) {
		if (this.list == null) {
			this.list = list;
		} else {
			this.list.addAll(list);
		}
	}
	
	public T getItem(int index){
		if(list == null || list.size() <= index){
			return null;
		}
		return list.get(index);
	}

	@Override
	public int getItemCount() {
		int size = list == null ? 0 : list.size();
		return size;
	}

	public List<T> getList() {
		return list;
	}

	public void setOnItemClickListener(OnItemClickListener itemClickListener) {
		this.itemClickListener = itemClickListener;
	}
	public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener itemLongClickListener){this.itemLongClickListener = itemLongClickListener;}

	/**
	 * 这个方法会调用onBindViewHolder(final MyViewHolder viewHolder, final int position)
	 * @param viewHolder
	 * @param position
	 * @param payloads 这个参数如果不为null，说明只有指定的数据发生了变化
     */
	@Override
	public void onBindViewHolder(final MyViewHolder viewHolder, final int position, List<Object> payloads) {
		super.onBindViewHolder(viewHolder, position, payloads);
	}


	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		return null;
	}

	@Override
	public void onBindViewHolder(final MyViewHolder viewHolder,final int position) {
		if(itemClickListener != null){
			viewHolder.itemView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					itemClickListener.onItemClick(null, viewHolder.itemView, position, 0);
				}
			});
		}

		if(itemLongClickListener != null){
			viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View view) {
					itemLongClickListener.onItemLongClick(null,viewHolder.itemView,position,0);
					return true;
				}
			});
		}
	}
}

package com.maqueezu.utils.ui.recyclerView;

import com.maqueezu.utils.R;

import java.util.List;

public class ParametersBean {

	private int fragmentParentId = R.id.fragment_parent_id;
	private int curPage = 1;
	private int pageSize = 30;
	private List list;



	public int getFragmentParentId() {
		return fragmentParentId;
	}

	public void setFragmentParentId(int fragmentParentId) {
		this.fragmentParentId = fragmentParentId;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "ParametersBean{" +
				"fragmentParentId=" + fragmentParentId +
				", curPage=" + curPage +
				", pageSize=" + pageSize +
				", list=" + list +
				'}';
	}
}

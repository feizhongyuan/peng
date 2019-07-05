package com.maqueezu.utils.ui.recyclerView;

import java.io.Serializable;
import java.util.List;

/**
 * 响应结果
 */
public class RespanseModel<T> implements Serializable {
    private boolean isSuccess;
    private String message;
    private int pageCount;
    private List<T> list;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "RespanseModel{" + "isSuccess=" + isSuccess + ", message='" + message + '\'' + ", pageCount=" + pageCount + ", list=" + list + '}';
    }
}

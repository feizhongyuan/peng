package com.maqueezu.utils.tools.http;


public interface OnResultListener<T> {
    void onResult(T t);
    void onFailse(String s);
}

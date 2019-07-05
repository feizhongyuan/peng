package com.maqueezu.utils.ui.custom;

/**
 * Created by fei .
 * Created by Date 2019/6/13 11:34
 */

public interface RiseNumberBase {
    public void start();
    public RiseNumberTextView withNumber(float number);
    public RiseNumberTextView withNumber(float number, boolean flag);
    public RiseNumberTextView withNumber(int number);
    public RiseNumberTextView setDuration(long duration);
    public void setOnEnd(RiseNumberTextView.EndListener callback);
}

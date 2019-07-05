package com.maqueezu.utils.ui.web;

import android.app.Activity;

import wendu.dsbridge.DWebView;

/**
 * Created by admin on 2019/5/5 0005.
 */

public interface OnCreateJsApiListener {
    Object createJsObj(Activity activity,DWebView dWebView);
}

package com.maqueezu.el.ui.webview;

import android.app.Activity;
import android.webkit.JavascriptInterface;

import com.maqueezu.utils.tools.ToastUtil;

import wendu.dsbridge.DWebView;

/**
 * Created by fei .
 * Created by Date 2019/7/17 18:19
 */

public class JsDSBridge {
    private Activity activity;
    private DWebView webView;

    public JsDSBridge(Activity activity, DWebView webView) {
        this.activity = activity;
        this.webView = webView;
    }

    @JavascriptInterface
    public void showToast(String msg){
        ToastUtil.setToast(msg);
    }
}

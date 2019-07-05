package com.maqueezu.el.engine;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import com.maqueezu.el.Constant;
import com.maqueezu.utils.ui.FragmentContainerActivity;
import com.maqueezu.utils.ui.web.WebFragment;

import wendu.dsbridge.CompletionHandler;
import wendu.dsbridge.DWebView;


/**
 * 原生代码与js交互的桥梁
 */
public class JsApi  {
    private Activity context;
    private DWebView webView;

    public static final int getImageCode = 333;

    public JsApi(Activity context, DWebView webView) {
        this.context = context;
        this.webView = webView;
    }



    /**
     * 打开指定网页
     */
    @JavascriptInterface
    public void openWebView(Object url) {
        Intent intent = new Intent(context, FragmentContainerActivity.class);
        intent.putExtra(FragmentContainerActivity.FragmentClassName, WebFragment.class.getName());
        intent.putExtra(WebFragment.urlKey, url.toString());
        context.startActivity(intent);
    }

    /**
     * 拨打电话
     *
     * @param phoneNumber
     */
    @JavascriptInterface
    public void dial(Object phoneNumber) {
        if (context == null) {
            return;
        }
        // 用intent启动拨打电话
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber.toString()));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 无参同步调用示例
     */
    @JavascriptInterface
    public String clickTest1(Object object){
        return object.toString()+" 哈哈哈";
    }

    /**
     * 有参同步调用示例
     */
    @JavascriptInterface
    public String clickTest2(Object object){
        return object.toString()+" 哈哈哈";
    }

    /**
     * 有参异步调用示例
     */
    @JavascriptInterface
    public void clickTest3(Object object,CompletionHandler<String> handler){
        handler.complete(object.toString()+"哈哈哈哈");
    }

    /**
     * java 主动调用 js
     * @param count
     */
    private void jsTestFunct(Integer count) {
        if (webView == null) {
            return;
        }
        runJS("jsTestFunct", count + "");
    }

    /**
     * 主动调用js方法
     *
     * @param functionName
     * @param parms
     */
    public void runJS(String functionName, String... parms) {
        if (webView == null) {
            return;
        }
        try {
            final StringBuilder builder = new StringBuilder();
            builder.append("javascript:");
            builder.append(functionName);//传入Js的function名
            builder.append("(");

            if (parms != null && parms.length > 0) {
                for (int i = 0; i < parms.length; i++) {
                    String parm = parms[i];
                    if (TextUtils.isEmpty(parm)) {
                        continue;
                    }
                    builder.append("'" + parm + "'");
                    if (i < parms.length - 1) {
                        builder.append(",");
                    }
                }
            }
            builder.append(")");
            Log.e("TAG111",builder.toString());

            if (Constant.getCons().isDebug()) {
                Log.e(JsApi.class.getName(), "调用JS function:" + builder.toString());
            }

            webView.post(() -> webView.loadUrl(builder.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 在webview销毁的时候需要调用
     */
    public void onDestory() {

    }
}

package com.maqueezu.el.ui.webview;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieSyncManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.maqueezu.el.R;
import com.maqueezu.utils.BaseApplication;
import com.maqueezu.utils.tools.LogUtil;
import com.maqueezu.utils.ui.base.BaseFragment;
import com.maqueezu.utils.ui.web.OnCreateJsApiListener;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import wendu.dsbridge.DWebView;

/**
 * Web展示页
 */
public class NewWebFragment extends BaseFragment implements View.OnClickListener {


    private AutoFrameLayout web_x5_container;
    private AutoRelativeLayout rl_base_1;

    private DWebView mWebView;
    private static OnCreateJsApiListener createJsApiListener;//创建JsApi监听器
    private ValueCallback<Uri[]> x5valueCallback;
    private Object jsApi;
    private Runnable backRunnable;

    private String mIntentData;//跳转数据
    private String mIntentUrl;//跳转路径
    public static final String dataKey = "webviewDataKey";
    public static final String urlKey = "webviewurlkey";
    private static final int uploadFilesRequestCode = 0x111;//上传代码请求
    // 存储选择文件的url
    private android.webkit.ValueCallback<Uri> uploadFile;
    private android.webkit.ValueCallback<Uri[]> uploadFiles;

    private AnimationDrawable animationDrawable;
    private ImageView mImage_animation;//动画图


    public NewWebFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mRootView = super.onCreateView(inflater, container, savedInstanceState);
        initView(mRootView);
        return mRootView;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_new_web;
    }

    @Override
    protected void initView(View mRootView) {

        web_x5_container = (AutoFrameLayout) mRootView.findViewById(R.id.web_x5_container);
        rl_base_1 = (AutoRelativeLayout) mRootView.findViewById(R.id.rl_base_1);

//        设置动画
        mImage_animation = (ImageView) mRootView.findViewById(R.id.mImage_animation);
        animationDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.animation1);
        mImage_animation.setVisibility(View.VISIBLE);
        animationDrawable.setOneShot(false);
        animationDrawable.start();
    }

    @Override
    protected void initData(Bundle arguments) {
        if (getArguments() != null) {
            mIntentData = getArguments().getString(dataKey);
            mIntentUrl = getArguments().getString(urlKey);
        }
        if (TextUtils.isEmpty(mIntentData)) {
            mIntentData = getActivity().getIntent().getStringExtra(dataKey);
            mIntentUrl = getActivity().getIntent().getStringExtra(urlKey);
        }

        mWebView = new DWebView(getActivity(), null);
        try {
//            开启WebView调试
            DWebView.setWebContentsDebuggingEnabled(BaseApplication.getCons().isDebug());
        } catch (Exception e) {
            e.printStackTrace();
        }
//          将WebView添加到布局中
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        web_x5_container.addView(mWebView, layoutParams);

        initSetting();

        if (!TextUtils.isEmpty(mIntentData)) {
            mWebView.loadDataWithBaseURL(BaseApplication.getCons().getWebPageUrl(), mIntentData, "text/html; charset=UTF-8", null, null);
        } else if (!TextUtils.isEmpty(mIntentUrl)) {
            mWebView.loadUrl(mIntentUrl);
        } else {
            mWebView.loadUrl(BaseApplication.getCons().getWebPageUrl());
        }

    }

    private void initSetting() {
        WebSettings settings = mWebView.getSettings();
        settings.setAllowFileAccess(true);//允许文件访问
        settings.setSupportZoom(false);//设置缩放
        settings.setBuiltInZoomControls(true);//内置缩放控件
        settings.setUseWideViewPort(true);//设置使用宽视图窗口
        settings.setLoadWithOverviewMode(true);//设置加载概览模式
        settings.setSupportMultipleWindows(true);//设置支持多窗口

        settings.setJavaScriptEnabled(true);//启用JavaScript
        settings.setAppCacheEnabled(true);//设置启用缓存
        settings.setDatabaseEnabled(true);//设置启动数据库
        settings.setDomStorageEnabled(true);//设置启用Dom存储
        settings.setGeolocationEnabled(true);//设置启用地理位置
        settings.setLoadsImagesAutomatically(true); //支持自动加载图片
        settings.setDefaultTextEncodingName("utf-8");//设置编码格式
        settings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口

        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setAppCacheMaxSize(Long.MAX_VALUE);//设置最大应用缓存
        settings.setAppCachePath(getActivity().getDir("appcache", 0).getPath());//设置缓存路径
        settings.setDatabasePath(getActivity().getDir("databases", 0).getPath());//设置数据库路径
        settings.setGeolocationDatabasePath(getActivity().getDir("geolocation", 0).getPath());//设置地理位置数据库路径

        //        Cookie同步管理器
        CookieSyncManager.createInstance(getActivity());
        CookieSyncManager.getInstance().sync();

    }

    @Override
    protected void initListener() {

        if (createJsApiListener != null) {
            jsApi = createJsApiListener.createJsObj(getActivity(), mWebView);
            mWebView.addJavascriptObject(jsApi, "JsName");
        }

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
//                开始加载网络
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
//	        url  即将要被加载的url
//          true 当前应用程序要自己处理这个url， 返回false则不处理。 注："post"请求方式不会调用这个回调函数
                LogUtil.print("Url", "" + url);
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
//                网页加载完成回调
                mImage_animation.setVisibility(View.GONE);
                animationDrawable.stop();
                mWebView.post(new Runnable() {
                    @Override
                    public void run() {
//                        开启Js就调用
                        //                     android 调JS方式一
//                        mWebView.loadUrl("javascript:toBuy()");
//                      android 调JS方式二
//                        mWebView.evaluateJavascript("javascript:toBuy()", new ValueCallback<String>() {
//                            @Override
//                            public void onReceiveValue(String s) {
//                                LogUtil.print("Js返回结果：" + s);
//                            }
//                        });
                    }
                });
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                //                加载进度
//                progressBar.setProgress(newProgress);
//                if (newProgress == 100) {
//                    progressBar.setVisibility(View.GONE);
//
//                } else {
//                    progressBar.setVisibility(View.VISIBLE);
//                }
            }

            @Override
            public boolean onJsAlert(WebView webView, String s, String s1, JsResult jsResult) {
                return super.onJsAlert(webView, s, s1, jsResult);
            }

            @Override
            public void openFileChooser(ValueCallback<Uri> valueCallback, String s, String s1) {
//                打开文件选择器
                super.openFileChooser(valueCallback, s, s1);
                Log.i("test", "openFileChooser 3");
                NewWebFragment.this.uploadFile = uploadFile;
                openFileChooseProcess();
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
                //                显示文件选择器
                Log.i("test", "openFileChooser 5:" + valueCallback.toString());
                NewWebFragment.this.x5valueCallback = valueCallback;
                NewWebFragment.this.uploadFiles = valueCallback;
                openFileChooseProcess();
//                return super.onShowFileChooser(webView, valueCallback, fileChooserParams);
                return true;
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (getActivity() == null || getActivity().isFinishing() || mWebView == null) {
            return;
        }

        if (requestCode == uploadFilesRequestCode) {
            if (resultCode == Activity.RESULT_OK) {
                if (null != uploadFile) {
                    Uri result = data == null || resultCode != Activity.RESULT_OK ? null : data.getData();
                    uploadFile.onReceiveValue(result);
                    uploadFile = null;
                }
                if (null != uploadFiles) {
                    Uri result = data == null || resultCode != Activity.RESULT_OK ? null : data.getData();
                    uploadFiles.onReceiveValue(new Uri[]{result});
                    uploadFiles = null;
                }
                if (null != x5valueCallback) {
                    Uri result = data == null || resultCode != Activity.RESULT_OK ? null : data.getData();
                    x5valueCallback.onReceiveValue(new Uri[]{result});
                    x5valueCallback = null;
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                if (null != uploadFile) {
                    uploadFile.onReceiveValue(null);
                    uploadFile = null;
                }
                if (null != uploadFiles) {
                    uploadFiles.onReceiveValue(null);
                    uploadFiles = null;
                }
                if (null != x5valueCallback) {
                    x5valueCallback.onReceiveValue(null);
                    x5valueCallback = null;
                }
            }
            return;
        }

    }

    //    打开文件流程
    private void openFileChooseProcess() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(Intent.createChooser(intent, "test"), uploadFilesRequestCode);
    }

    //    设置JsApi监听
    public static void setCreateJsApiListener(OnCreateJsApiListener createJsApiListener) {
        NewWebFragment.createJsApiListener = createJsApiListener;
    }

    public void setBackRunnable(Runnable backRunnable) {
        this.backRunnable = backRunnable;
    }

    //    加载页面
    public void loadWeb(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        this.mIntentUrl = url;
        if (mWebView != null) {
            mWebView.loadUrl(mIntentUrl);
        }
    }

    public WebView getWebView() {
        return mWebView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.back_layout:
//            case R.id.title_back_image:
//                getActivity().finish();
//                break;
            default:
                break;
        }
    }

    //    web页返回
    @Override
    public boolean onBackPressed() {
        if (goBack()) {
            return true;
        }
        return super.onBackPressed();
    }

    public boolean canGoBack() {
        return mWebView.canGoBack();
    }

    public boolean goBack() {
        if (mWebView != null) {
            // 自己处理回退事件
            if (canGoBack()) {
                mWebView.goBack();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public void onDestroy() {
        if (mWebView != null) {
            ViewGroup parent = (ViewGroup) mWebView.getParent();
            if (parent != null) {
                parent.removeAllViews();
            }
            mWebView.removeAllViews();
            mWebView.destroy();
        }

        super.onDestroy();
    }


}

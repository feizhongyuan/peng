package com.maqueezu.utils.ui.web;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieSyncManager;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.utils.BaseApplication;
import com.maqueezu.utils.R;
import com.maqueezu.utils.tools.Utils;
import com.maqueezu.utils.ui.FragmentContainerActivity;
import com.maqueezu.utils.ui.base.BaseFragment;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;


import wendu.dsbridge.DWebView;

/**
 * android 调 Js
 * loadUrl()    获取返回值麻烦，对性能要求较低
 * evaluateJavascript() 效率高，支持4.4以上，向下兼容差
 *  一般混合使用
 *
 *  Js 调 android
 *  addJavascriptInterface() 添加对象映射 支持4.2以上简单互调
 *  WebViewClient.shouldOverrideUrlLoading() 回调拦截url，需要协议约束，不需要返回情况的互调场景
 *  WebChromeClient的onJsAlert(),onJsConfirm(),onJsPrompt() 回调拦截js对话框消息，满足大多数的互调场景
 */

public class WebFragment extends BaseFragment {

    private View test_layout;
    private EditText edit_text;
    private ProgressBar progressBar;
    private FrameLayout web_parent;

    private DWebView mWebView;
    // 存储选择文件的url
    private ValueCallback<Uri> uploadFile;
    private ValueCallback<Uri[]> uploadFiles;
    private com.tencent.smtt.sdk.ValueCallback<Uri[]> x5valueCallback;
    private static final int uploadFilesRequestCode = 0x111;//上传代码请求

    public static final String dataKey = "webviewDataKey";
    public static final String urlKey = "webviewurkkey";
    private String mIntentData;//跳转数据
    private String mIntentUrl;//跳转路径
    private OnReceivedTitleAndIconListener listener;//返回标题及图片监听
    private static OnCreateJsApiListener createJsApiListener;//创建JsApi监听器
    private Object jsApi;
    private Runnable backRunnable;
    private TextView but_load;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);

        return rootView;
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_web;
    }

    @Override
    protected void initView(View mRootView) {
        if (getArguments() != null) {
            mIntentData = getArguments().getString(dataKey);
            mIntentUrl = getArguments().getString(urlKey);
        }
        if (TextUtils.isEmpty(mIntentData)) {
            mIntentData = getActivity().getIntent().getStringExtra(dataKey);
            mIntentUrl = getActivity().getIntent().getStringExtra(urlKey);
        }


        web_parent = (FrameLayout) mRootView.findViewById(R.id.web_x5_parent);
        progressBar = (ProgressBar) mRootView.findViewById(R.id.progressBar);
        edit_text = (EditText) mRootView.findViewById(R.id.edit_text);
        test_layout = mRootView.findViewById(R.id.test_layout);
        but_load = mRootView.findViewById(R.id.but_load);

        if (!BaseApplication.getCons().isDebug()) {
            setHiddenUrl();
        }
    }

    public void setHiddenUrl() {
        if (test_layout == null) {
            return;
        }
        test_layout.setVisibility(View.GONE);
    }

    @SuppressLint("NewApi")
    @Override
    protected void initData(Bundle arguments) {
        mWebView = new DWebView(getActivity(), null);
        try {
//            开启WebView调试
            mWebView.setWebContentsDebuggingEnabled(BaseApplication.getCons().isDebug());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        将WebView添加到布局中
        web_parent.addView(mWebView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    }

    @Override
    protected void initListener() {
        but_load.setOnClickListener(v -> {
//            每次跳转开启新的Fragment
            Intent intent = new Intent(getActivity(), FragmentContainerActivity.class);
            intent.putExtra(FragmentContainerActivity.FragmentClassName, WebFragment.class.getName());
            intent.putExtra(WebFragment.urlKey, "file:///android_asset/test_dw.html");
            startActivity(intent);
        });

        /**
         * Js调android  方式二  WebViewClient.shouldOverrideUrlLoading()
         *  用来控制webView的页面显示替换
         当加载的网页需要重定向的时候就会回调这个函数告知我们应用程序是否需要接管控制网页加载，
         如果应用程序接管，并且return true意味着主程序接管网页加载，如果返回false让webview自己处理
         */

        mWebView.setWebViewClient(new com.tencent.smtt.sdk.WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                开始加载网络
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
//                网页加载完成回调
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
//	        url  即将要被加载的url
//          true 当前应用程序要自己处理这个url， 返回false则不处理。 注："post"请求方式不会调用这个回调函数
                Log.e(WebFragment.class.getName(), "url=" + url);
                if (url.startsWith("weixin://wap/pay?") || url.startsWith("http://weixin/wap/pay")) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                    return true;
                } else if (url.startsWith("alipays:") || url.startsWith("alipay")) {
                    webView.loadUrl(url);
                    return true;
                } else if (URLUtil.isHttpUrl(url) || URLUtil.isHttpsUrl(url)) {
                    if (edit_text != null) {
                        edit_text.setText(url);
                    }
                }
                return false;
            }
        });

        /**
         * Js调android  方式三
         * WebChromeClient的onJsAlert(),onJsConfirm(),onJsPrompt()
         *
         */


        mWebView.setWebChromeClient(new com.tencent.smtt.sdk.WebChromeClient() {

            @Override
            public void onProgressChanged(WebView webView, int newProgress) {
                super.onProgressChanged(webView, newProgress);
//                加载进度
                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);

                } else {
                    progressBar.setVisibility(View.VISIBLE);
                }
                if (edit_text != null) {
                    edit_text.setText(webView.getUrl());
                }
            }

            @Override
            public void onReceivedIcon(WebView webView, Bitmap bitmap) {
                super.onReceivedIcon(webView, bitmap);
                if (listener != null) {
                    listener.onReceivedIcon(webView, bitmap);
                }
            }

            @Override
            public void onReceivedTitle(WebView webView, String title) {
                super.onReceivedTitle(webView, title);
//                网页标题
                // 判断只有包含了中文才会认为是比较合适的标题，这个标题可能将会显示在错误页面之上
                if (Utils.isChineseChar(title)) {
                    if (listener != null) {
                        listener.onReceivedTitle(webView, title);
                    }
                }
            }

            //          openFileChooser  打开文件选择器
            // For Android 3.0+
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                Log.i("test", "openFileChooser 1");
                WebFragment.this.uploadFile = uploadFile;
                openFileChooseProcess();
            }

            // For Android < 3.0
            public void openFileChooser(ValueCallback<Uri> uploadMsgs) {
                Log.i("test", "openFileChooser 2");
                WebFragment.this.uploadFile = uploadFile;
                openFileChooseProcess();
            }

            // For Android  > 4.1.1
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                Log.i("test", "openFileChooser 3");
                WebFragment.this.uploadFile = uploadFile;
                openFileChooseProcess();
            }

            // For Android  >= 5.0
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams
                    fileChooserParams) {
                Log.i("test", "openFileChooser 4:" + filePathCallback.toString());
                WebFragment.this.uploadFiles = filePathCallback;
                openFileChooseProcess();
                return true;
            }

            @Override
            public boolean onShowFileChooser(WebView webView, com.tencent.smtt.sdk.ValueCallback<Uri[]> valueCallback, FileChooserParams
                    fileChooserParams) {
//                显示文件选择器

                Log.i("test", "openFileChooser 5:" + valueCallback.toString());
                WebFragment.this.x5valueCallback = valueCallback;
                openFileChooseProcess();
//                return super.onShowFileChooser(webView, valueCallback, fileChooserParams);
                return true;
            }
        });

//        设置下载监听器
        mWebView.setDownloadListener(new DownloadListener() {
            //            @Override
            public void onDownloadStart(String arg0, String arg1, String arg2, String arg3, long arg4) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("allow to download？")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "fake message: i'll download...", Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                Toast.makeText(getActivity(), "fake message: refuse download...", Toast.LENGTH_SHORT).show();
                            }
                        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                Toast.makeText(getActivity(), "fake message: refuse download...", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

//        在编辑器上添加监听器
        edit_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String url = edit_text.getText().toString();
                    if (TextUtils.isEmpty(url)) {
                        Toast.makeText(getActivity(), "请输入url", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    if (!URLUtil.isHttpUrl(url) && !URLUtil.isHttpsUrl(url)) {
                        Toast.makeText(getActivity(), "url错误", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    if (mWebView != null) {
                        mWebView.loadUrl(url);
                    }

					/* 隐藏软键盘 */
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });

//        WebSetting配置Webview属性
        WebSettings webSetting = mWebView.getSettings();
        webSetting.setAllowFileAccess(true);//允许文件访问
        webSetting.setSupportZoom(false);//设置缩放
        webSetting.setBuiltInZoomControls(true);//内置缩放控件
        webSetting.setUseWideViewPort(true);//设置使用宽视图窗口
        webSetting.setLoadWithOverviewMode(true);//设置加载概览模式
        webSetting.setSupportMultipleWindows(true);//设置支持多窗口

        webSetting.setAppCacheEnabled(true);//设置启用缓存
        webSetting.setDatabaseEnabled(true);//设置启动数据库
        webSetting.setDomStorageEnabled(true);//设置启用Dom存储
        webSetting.setJavaScriptEnabled(true);//设置启用JavaScript
        webSetting.setGeolocationEnabled(true);//设置启用地理位置
        webSetting.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSetting.setDefaultTextEncodingName("utf-8");//设置编码格式
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口


        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);//设置最大应用缓存
        webSetting.setAppCachePath(getActivity().getDir("appcache", 0).getPath());//设置缓存路径
        webSetting.setDatabasePath(getActivity().getDir("databases", 0).getPath());//设置数据库路径
        webSetting.setGeolocationDatabasePath(getActivity().getDir("geolocation", 0).getPath());//设置地理位置数据库路径
//         webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//设置页面缓存容量
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);//设置渲染优先级
//         webSetting.setPreFectch(true);

        if (!TextUtils.isEmpty(mIntentData)) {
            mWebView.loadDataWithBaseURL(BaseApplication.getCons().getWebPageUrl(), mIntentData, "text/html; charset=UTF-8", null, null);
        } else if (!TextUtils.isEmpty(mIntentUrl)) {
            mWebView.loadUrl(mIntentUrl);
        } else {
            mWebView.loadUrl(BaseApplication.getCons().getWebPageUrl());
        }
//        Cookie同步管理器
        CookieSyncManager.createInstance(getActivity());
        CookieSyncManager.getInstance().sync();

        if (createJsApiListener != null) {
            jsApi = createJsApiListener.createJsObj(getActivity(), mWebView);
            //DWebView添加本地方法的方式 适用于4.2以上系统（方便简洁）Js调android 方式一
            mWebView.addJavascriptObject(jsApi, null);
        }
    }


    private void openFileChooseProcess() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*");
        startActivityForResult(Intent.createChooser(i, "test"), uploadFilesRequestCode);
    }

    public WebView getWebView() {
        return mWebView;
    }

    public void setonBackListener(Runnable backRunnable) {
        this.backRunnable = backRunnable;
    }

    public static void setCreateJsApiListener(OnCreateJsApiListener createJsApiListener) {
        WebFragment.createJsApiListener = createJsApiListener;
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

    /**
     * 设置当获取到网页的图标与标题的监听
     *
     * @param listener
     */
    public void setOnReceivedTitleAndIconListener(OnReceivedTitleAndIconListener listener) {
        this.listener = listener;
    }


    public void loadWeb(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        this.mIntentUrl = url;
        if (mWebView != null) {
            mWebView.loadUrl(mIntentUrl);
        }
    }

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

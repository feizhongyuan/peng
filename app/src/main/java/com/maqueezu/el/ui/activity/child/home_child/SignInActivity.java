package com.maqueezu.el.ui.activity.child.home_child;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.maqueezu.el.ui.test.TestActivity;
import com.maqueezu.utils.ui.FragmentContainerActivity;
import com.maqueezu.utils.ui.web.WebFragment;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 签到页
 */
public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;
    private FrameLayout container;

    private String url = "file:///android_asset/test_dw.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initView();
        initDate();
        initListener();
    }

    private void initView() {

        title_back_image = (ImageView) findViewById(R.id.title_back_image);
        title_back_image.setOnClickListener(this);
        back_layout = (AutoLinearLayout) findViewById(R.id.back_layout);
        back_layout.setOnClickListener(this);
        title_text_buttn = (TextView) findViewById(R.id.title_text_buttn);
        title_text_buttn.setOnClickListener(this);
        title_buttn_layout = (AutoLinearLayout) findViewById(R.id.title_buttn_layout);
        title_buttn_layout.setOnClickListener(this);
        title_text = (TextView) findViewById(R.id.title_text);
        title_text.setOnClickListener(this);
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setOnClickListener(this);
        rl_statusbar = (AutoRelativeLayout) findViewById(R.id.rl_statusbar);
        rl_statusbar.setOnClickListener(this);
        container = (FrameLayout) findViewById(R.id.container);
        container.setOnClickListener(this);

        //        WebView组件生成方式有两种：xml创建(不推荐)，代码创建

        //通过代码创建
        FrameLayout parentLayout=findViewById(R.id.container);
        WebView webView = new WebView(getApplicationContext());//使用应用级别的context，避免对Activity的引用
        FrameLayout.LayoutParams layoutParams=new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        parentLayout.addView(webView,layoutParams);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

//        Intent intent = new Intent(this, FragmentContainerActivity.class);
//        intent.putExtra(FragmentContainerActivity.FragmentClassName, WebFragment.class.getName());
//        intent.putExtra(WebFragment.urlKey, "file:///android_asset/test_dw.html");
//        startActivity(intent);

//      加载方式 1.网页 2.apk包内页面 3.手机本地页面
        webView.loadUrl(url);
        if (Build.VERSION.SDK_INT <= 19){
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascipt:jsTestFunct()");
                }
            });
        }else {
            webView.evaluateJavascript("javascipt:jsTestFunct()", new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    Log.e("Value","------"+value);
                }
            });
        }
    }

    private void initDate() {

    }

    private void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_layout:
            case R.id.title_back_image:
                finish();
                break;

        }
    }
}

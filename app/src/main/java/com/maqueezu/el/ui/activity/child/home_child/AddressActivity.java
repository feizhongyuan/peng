package com.maqueezu.el.ui.activity.child.home_child;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.maqueezu.el.R;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 城市选择
 */
public class AddressActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private AutoRelativeLayout rl_statusbar;
    private TextView title_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Slide(Gravity.BOTTOM).setDuration(300));
            getWindow().setExitTransition(new Slide(Gravity.TOP).setDuration(300));
        }
        setContentView(R.layout.activity_address);
        initView();
        initDate();
    }

    private void initDate() {
        title_text.setText("选择地区");
    }

    private void initView() {
        title_back_image = (ImageView) findViewById(R.id.title_back_image);
        back_layout = (AutoLinearLayout) findViewById(R.id.back_layout);
        rl_statusbar = (AutoRelativeLayout) findViewById(R.id.rl_statusbar);
        back_layout.setOnClickListener(this);
        title_text = (TextView) findViewById(R.id.title_text);
        title_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
            case R.id.title_back_image:
                finish();
                break;
        }
    }
}

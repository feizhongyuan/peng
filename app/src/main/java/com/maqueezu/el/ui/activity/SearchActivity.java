package com.maqueezu.el.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.maqueezu.el.ui.fragment.physicalexamination_child.SetMealListItemFragment;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private EditText et_ProductSearch;
    private AutoRelativeLayout rl_statusbar;
    private TextView tv_zuijinsousuo;
    private ImageView img_recycler;
    private TextView tv_zuijinsousuo_tijiantaocan;
    private TextView tv_zuijinsousuo_jigou;
    private AutoRelativeLayout rl_base_1;
    private TextView tv_resou;
    private TextView tv_resou_tijiantaocan;
    private TextView tv_resou_jigou;
    private AutoRelativeLayout rl_base_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        title_back_image = (ImageView) findViewById(R.id.title_back_image);
        back_layout = (AutoLinearLayout) findViewById(R.id.back_layout);
        et_ProductSearch = (EditText) findViewById(R.id.et_ProductSearch);
        rl_statusbar = (AutoRelativeLayout) findViewById(R.id.rl_statusbar);
        tv_zuijinsousuo = (TextView) findViewById(R.id.tv_zuijinsousuo);
        img_recycler = (ImageView) findViewById(R.id.img_recycler);
        tv_zuijinsousuo_tijiantaocan = (TextView) findViewById(R.id.tv_zuijinsousuo_tijiantaocan);
        tv_zuijinsousuo_jigou = (TextView) findViewById(R.id.tv_zuijinsousuo_jigou);
        rl_base_1 = (AutoRelativeLayout) findViewById(R.id.rl_base_1);
        tv_resou = (TextView) findViewById(R.id.tv_resou);
        tv_resou_tijiantaocan = (TextView) findViewById(R.id.tv_resou_tijiantaocan);
        tv_resou_jigou = (TextView) findViewById(R.id.tv_resou_jigou);
        rl_base_2 = (AutoRelativeLayout) findViewById(R.id.rl_base_2);
        back_layout.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String ProductSearch = et_ProductSearch.getText().toString().trim();
        if (TextUtils.isEmpty(ProductSearch)) {
            Toast.makeText(this, "体检套餐/体检机构名称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ProductSearch.equals("体检套餐") || ProductSearch.equals("体检机构名称")){
            Intent intent = new Intent(this, SetMealListItemFragment.class);
            startActivity(intent);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_layout:
                this.finish();
                break;
            case R.id.tv_zuijinsousuo_tijiantaocan:

                break;
            case R.id.tv_resou_tijiantaocan:
                break;

            case R.id.tv_zuijinsousuo_jigou:
                break;
            case R.id.tv_resou_jigou:
                break;

            case R.id.et_ProductSearch:
                submit();
                break;
            default:
                break;
        }
    }
}

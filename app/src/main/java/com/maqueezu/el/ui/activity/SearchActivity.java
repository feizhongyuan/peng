package com.maqueezu.el.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.maqueezu.el.ui.adapter.SearchRecordAdapter;
import com.maqueezu.el.ui.fragment.physicalexamination_child.SetMealListItemFragment;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private EditText et_ProductSearch;
    private AutoRelativeLayout rl_statusbar;
    private TextView tv_zuijinsousuo;
    private ImageView img_recycler;
    private AutoRelativeLayout rl_base_1;
    private TextView tv_resou;
    private AutoRelativeLayout rl_base_2;
    private RecyclerView mRecycler_RecentSearch;
    private RecyclerView mRecycler_HotSearch;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        title_back_image = (ImageView) findViewById(R.id.title_back_image);
        back_layout = (AutoLinearLayout) findViewById(R.id.back_layout);
        et_ProductSearch = (EditText) findViewById(R.id.et_ProductSearch);
        rl_statusbar = (AutoRelativeLayout) findViewById(R.id.rl_statusbar);
        tv_zuijinsousuo = (TextView) findViewById(R.id.tv_zuijinsousuo);
        img_recycler = (ImageView) findViewById(R.id.img_recycler);
        rl_base_1 = (AutoRelativeLayout) findViewById(R.id.rl_base_1);
        tv_resou = (TextView) findViewById(R.id.tv_resou);
        rl_base_2 = (AutoRelativeLayout) findViewById(R.id.rl_base_2);
        back_layout.setOnClickListener(this);
        mRecycler_RecentSearch = (RecyclerView) findViewById(R.id.mRecycler_RecentSearch);
        mRecycler_HotSearch = (RecyclerView) findViewById(R.id.mRecycler_HotSearch);

        mRecycler_RecentSearch.setLayoutManager(new GridLayoutManager(this,4));
        mRecycler_HotSearch.setLayoutManager(new GridLayoutManager(this,4));
    }

    private void initData() {
        list = new ArrayList<>();
        list.add("体检套餐");
        list.add("机构");

        SearchRecordAdapter adapter = new SearchRecordAdapter(this, list,this);
        mRecycler_RecentSearch.setAdapter(adapter);
        mRecycler_HotSearch.setAdapter(adapter);

    }

    private void initListener() {
//        取消预设字
        et_ProductSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText editText = (EditText) v;
                if (!hasFocus){
                    editText.setHint(editText.getTag().toString());
                }else {
                    String hint=editText.getHint().toString();
                    editText.setTag(hint);//保存预设字
                    editText.setHint(null);
                }
            }
        });
    }

    private void submit() {
        // validate
        String ProductSearch = et_ProductSearch.getText().toString().trim();
        if (TextUtils.isEmpty(ProductSearch)) {
            Toast.makeText(this, "体检套餐/体检机构名称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ProductSearch.equals("体检套餐") || ProductSearch.equals("体检机构名称")) {
            Intent intent = new Intent(this, SetMealListItemFragment.class);
            startActivity(intent);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_layout:
                this.finish();
                break;
            case R.id.img_recycler://清空最近搜索
                break;
            case R.id.et_ProductSearch:
                submit();
                break;
            default:
                break;
        }
    }

//    recyclerView条目监听
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

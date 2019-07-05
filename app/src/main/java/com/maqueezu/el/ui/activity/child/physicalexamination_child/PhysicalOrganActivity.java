package com.maqueezu.el.ui.activity.child.physicalexamination_child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.maqueezu.el.pojo.AdvertBean;
import com.maqueezu.el.ui.activity.child.home_child.AddressActivity;
import com.maqueezu.el.ui.adapter.OrganAdapter;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 体检机构
 */
public class PhysicalOrganActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text;
    private TextView mText_Addr;
    private AutoRelativeLayout rl_statusbar;
    private RecyclerView mRecycle_Organ;
    private AdvertBean.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_organ);

        initView();
        initData();
        initListener();
    }

    private void initView() {

        title_back_image = (ImageView) findViewById(R.id.title_back_image);
        title_back_image.setOnClickListener(this);
        back_layout = (AutoLinearLayout) findViewById(R.id.back_layout);
        back_layout.setOnClickListener(this);
        title_text = (TextView) findViewById(R.id.title_text);
        title_text.setOnClickListener(this);
        mText_Addr = (TextView) findViewById(R.id.mText_Addr);
        mText_Addr.setOnClickListener(this);
        rl_statusbar = (AutoRelativeLayout) findViewById(R.id.rl_statusbar);
        rl_statusbar.setOnClickListener(this);
        mRecycle_Organ = (RecyclerView) findViewById(R.id.mRecycle_Organ);
        mRecycle_Organ.setOnClickListener(this);

        mRecycle_Organ.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {

        Intent intent = getIntent();
        dataBean = (AdvertBean.DataBean) intent.getSerializableExtra("data");

        title_text.setText("体检机构");

        OrganAdapter setMealListAdapter = new OrganAdapter(this, dataBean.getAdvList(),this);
        mRecycle_Organ.setAdapter(setMealListAdapter);
    }

    private void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_layout:
                this.finish();
                break;
            case R.id.mText_Addr:
                Intent intent = new Intent(this, AddressActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "点击条目--"+(position+1), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, OrganItemActivity.class);
        intent.putExtra("date",dataBean.getAdvList().get(position));
        startActivity(intent);

    }
}

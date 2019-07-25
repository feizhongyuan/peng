package com.maqueezu.el.ui.activity.child.physicalexamination_child;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maqueezu.el.R;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 添加体检卡
 */
public class AddPhysicalExaminationCardActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView title_back_image;
    private AutoLinearLayout back_layout;
    private TextView title_text_buttn;
    private AutoLinearLayout title_buttn_layout;
    private TextView title_text;
    private TextView right_text;
    private AutoRelativeLayout rl_statusbar;
    private EditText et_physicalExmination_card;
    private Button bt_nextStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_physical_examination_card);

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
        et_physicalExmination_card = (EditText) findViewById(R.id.et_physicalExmination_card);
        et_physicalExmination_card.setOnClickListener(this);
        bt_nextStep = (Button) findViewById(R.id.bt_nextStep);
        bt_nextStep.setOnClickListener(this);
    }

    private void initDate() {
        title_text.setText(R.string.name_tianjiatijianka);
        right_text.setText(R.string.name_tianjialiucheng);

    }

    private void initListener() {
        et_physicalExmination_card.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //EditText输入状态改变，Button背景颜色也改变
                if (et_physicalExmination_card.getText().toString().length() == 14) {
                    //设置selector来控制Button背景颜色
                    bt_nextStep.setBackground(ContextCompat.getDrawable(AddPhysicalExaminationCardActivity.this,
                            R.color.colorAccent));
                    bt_nextStep.setEnabled(true);
                } else {
                    bt_nextStep.setBackgroundColor(getResources().getColor(R.color.huise));
                    bt_nextStep.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_nextStep:
                submit();
                break;
            case R.id.title_back_image://返回
            case R.id.back_layout:
                finish();
                break;
            case R.id.right_text:
                Intent intent1 = new Intent(this,AddCardProcessActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name","操作流程");
                intent1.putExtras(bundle);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

    private void submit() {
        // validate
        String card = et_physicalExmination_card.getText().toString().trim();
        if (TextUtils.isEmpty(card)) {
            Toast.makeText(this, "卡号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        boolean b = checkCard(card);
        if (b){
            if (card.equals("不存在")){
                Toast.makeText(this, "很抱歉，您输入的卡号不存在！", Toast.LENGTH_SHORT).show();
            }else if (card.equals("被绑定")){
                Toast.makeText(this, "很抱歉，您输入的卡号被绑定！", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(this,UsePhysicalExaminationCardActivity.class);
                startActivity(intent);
            }
        }else {
            Toast.makeText(this, "您输入有误,请重新输入", Toast.LENGTH_SHORT).show();
        }

    }

//    卡号判断
    private boolean checkCard(String str) {

        String regexp = "^\\d{14}$";

        Pattern pattern = Pattern.compile(regexp);

        Matcher matcher = pattern.matcher(str);

        return matcher.matches();

    }
}

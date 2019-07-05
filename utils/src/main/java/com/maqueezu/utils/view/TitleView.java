package com.maqueezu.utils.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maqueezu.utils.R;

/**
 * Created by admin on 2019/5/6 0006.
 *
 * 自定义标题栏
 */
public class TitleView extends LinearLayout {
    private Activity activity;
    private String title_text;
    private View title_view;

    public TitleView(Context context) {
        super(context);
        init(context, null);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        this.activity = (Activity) context;

        setId(R.id.title_view);
        title_view = View.inflate(context, R.layout.view_title, null);
        addView(title_view);

        View backlayout = title_view.findViewById(R.id.back_layout);
        backlayout.setOnClickListener(view -> {
            Activity activity = (Activity) context;
            if (activity != null || !activity.isFinishing()) {
                activity.onBackPressed();
            }
        });

        if (attrs != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.title_attributes);
            title_text = obtainStyledAttributes.getString(R.styleable.title_attributes_title_value);
            setTitleText(title_text);
        }

        //阴影的开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(getResources().getDimension(R.dimen.title_elevation));
        }
    }


    public void setBackgroundResources( Drawable id) {
        View rl_bk = title_view.findViewById(R.id.rl_bk);
        rl_bk.setBackgroundDrawable(id);
    }


    public TextView getTitleTextView() {
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        return (TextView) title_view.findViewById(R.id.title_text);
    }

    public void setTitleText(int textId) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        TextView title_text = (TextView) title_view.findViewById(R.id.title_text);
        title_text.setText(activity.getResources().getString(textId));
    }

    public void setTitleText(String text) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        TextView title_text = (TextView) title_view.findViewById(R.id.title_text);
        title_text.setText(text);
    }

    /**
     * 设置标题颜色
     *
     * @param resid
     */
    public void setTitleTextColor(int resid) {
        TextView titleText = title_view.findViewById(R.id.title_text);
        titleText.setTextColor(getResources().getColor(resid));
    }

    public ImageView getBackImageView() {
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        ImageView title_back_image = (ImageView) title_view.findViewById(R.id.title_back_image);
        return title_back_image;
    }

    public void setBackButVisiable(boolean isVisiable) {
        View backlayout = title_view.findViewById(R.id.back_layout);
        if (isVisiable) {
            backlayout.setVisibility(View.VISIBLE);
        } else {
            backlayout.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 设置返回键图片
     *
     * @param resId
     */
    public void setBackBottonImg(int resId) {
        ImageView backImg = title_view.findViewById(R.id.title_back_image);
        backImg.setImageResource(resId);
    }


    public TextView getTextBut() {
        TextView title_text_buttn = (TextView) title_view.findViewById(R.id.title_text_buttn);
        return title_text_buttn;
    }

    public void setTextBut(String text, OnClickListener listener) {
        ImageView title_image_buttn = (ImageView) title_view.findViewById(R.id.title_image_buttn);
        TextView title_text_buttn = (TextView) title_view.findViewById(R.id.title_text_buttn);

        title_image_buttn.setVisibility(View.GONE);
        title_text_buttn.setVisibility(View.VISIBLE);

        title_text_buttn.setText(text);

        if (listener != null) {
            title_text_buttn.setOnClickListener(listener);
        }
    }

    /**
     * 设置右边按钮图片资源
     *
     * @param resId
     * @param listener
     */
    public void setRightImgRes(int resId, OnClickListener listener) {
        ImageView title_image_buttn = (ImageView) title_view.findViewById(R.id.title_image_buttn);
        TextView title_text_buttn = (TextView) title_view.findViewById(R.id.title_text_buttn);
        title_image_buttn.setVisibility(View.VISIBLE);
        title_text_buttn.setVisibility(View.GONE);
        title_image_buttn.setImageResource(resId);

        if (listener != null) {
            title_image_buttn.setOnClickListener(listener);
        }
    }

    /**
     * 设置右边按钮图片资源
     *
     * @param resId
     * @param listener
     */
    public void setRightImgResTwo(int resId, OnClickListener listener) {
        ImageView title_image_buttn = (ImageView) title_view.findViewById(R.id.title_image_buttn2);
        TextView title_text_buttn = (TextView) title_view.findViewById(R.id.title_text_buttn);
        title_image_buttn.setVisibility(View.VISIBLE);
        title_text_buttn.setVisibility(View.GONE);
        title_image_buttn.setImageResource(resId);

        if (listener != null) {
            title_image_buttn.setOnClickListener(listener);
        }
    }

    /**
     * 设置标题分界线是否显示
     *
     * @param visable
     */
    public void setLineVisable(boolean visable) {
        View line = title_view.findViewById(R.id.view_line);
        if (visable) {
            line.setVisibility(VISIBLE);
        } else {
            line.setVisibility(GONE);
        }
    }

    public void canClickRightBtn(boolean isClick) {
        ImageView title_image_buttn = (ImageView) title_view.findViewById(R.id.title_image_buttn);
        title_image_buttn.setClickable(isClick);
    }
}

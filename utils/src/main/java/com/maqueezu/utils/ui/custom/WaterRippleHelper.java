package com.maqueezu.utils.ui.custom;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.maqueezu.utils.R;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.Random;


/**
 * Created by Zhangwh on 2016/12/8 0008.
 * email:616505546@qq.com
 */

public class WaterRippleHelper {
  private WaterRippleView waterRippleView;
  private TextView tv_jiankangxingweizhishu;
  private Context context;
  private View rootView;
  private int count = 0;
  private int sum = 0;
  private int count1 =0;
//  public int[] shadowColors ={Color.argb(120,2,199,46), Color.argb(120,228,31,98), Color.argb(120,109,140,198),};
//  public int[] frontColors ={Color.argb(255,2,199,46), Color.argb(255,228,31,98), Color.argb(255,109,140,198),};
  public int[] shadowColors ={Color.argb(120,2,255,46)};
  public int[] frontColors ={Color.argb(255,2,255,46)};

  int position = 0;


//  private RiseNumberTextView risenumber;

  public WaterRippleHelper(Context context){
    this.context = context;
    rootView = View.inflate(context, R.layout.item_waterripple, null);
    waterRippleView = (WaterRippleView) rootView.findViewById(R.id.waterrippleView);
    tv_jiankangxingweizhishu = (TextView) rootView.findViewById(R.id.tv_jiankangxingweizhishu);
//    risenumber = (RiseNumberTextView) rootView.findViewById(R.id.risenumber);
  }
  Handler handler = new Handler(){
    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
        //removeMessages(0);
      if (count == 0){

        waterRippleView.setDepthRate((float)count1/sum);
        waterRippleView.startRefresh();
        waterRippleView.startRefresh2();
      }
      if(count<count1){
        count+=10;
        handler.sendEmptyMessage(0);
      }else if(count>count1) {
        count=count1;
      }
      tv_jiankangxingweizhishu.setText(count+"");
//      risenumber.withNumber((float) count).start();//自定义数字
    }
  };

  /*
  * root 根布局
  * tag 点击控件的tag
  */
  public void showEvent(final ViewGroup root, final String tag, int count1, int sum){
    this.count1 = count1;
    this.sum = sum;
    if((root.findViewWithTag(tag+"ripple")==null)) {
      seColors();
      rootView.setTag(tag+"ripple");
      rootView.setAlpha(0);
      rootView.setTranslationX(-50f);
      rootView.setTranslationY(-50f);
      rootView.setScaleX(0.1f);
      rootView.setScaleY(0.1f);
      RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
              RelativeLayout.LayoutParams.WRAP_CONTENT);
      params.leftMargin = root.findViewWithTag(tag).getLeft()+50;
//      params.addRule(RelativeLayout.CENTER_HORIZONTAL);
      params.topMargin = root.findViewWithTag(tag).getTop() + root.findViewWithTag(tag).getWidth()+20;
//      params.topMargin = R.dimen.dp_20;
      ViewCompat.animate(rootView)
          .setDuration(700)
          .setInterpolator(new DecelerateInterpolator(1.2f))
          .scaleX(1f)//缩放
          .scaleY(1f)
          .alpha(100f)//透明度
          .translationX(0)//位移
          .translationY(0)
          .start();
      root.addView(rootView, params);
      rootView.postDelayed(new Runnable() {
        @Override
        public void run() {
          handler.sendEmptyMessage(0);
        }
      },200);
    }else {
      ViewCompat.animate(root.findViewWithTag(tag+"ripple"))
          .setDuration(700)
          .setInterpolator(new DecelerateInterpolator(1.2f))
          .alpha(0f)
          .scaleX(0f)
          .scaleY(0f)
          .translationX(-50f)
          .translationY(-50f)
          .setListener(new ViewPropertyAnimatorListener() {
            @Override
            public void onAnimationStart(View view) {

            }

            @Override
            public void onAnimationEnd(View view) {
              root.removeView(root.findViewWithTag(tag+"ripple"));
            }

            @Override
            public void onAnimationCancel(View view) {

            }
          })
          .start();
    }
//    risenumber.setDuration(700);
  }

//  获取随机位置
  public int getRandomPosition(){
    Random rd = new Random();
    position = rd.nextInt(3);
    return position;
  }

  public void seColors(){
    getRandomPosition();
    waterRippleView.setFrontColor(frontColors[0]);
    waterRippleView.setShadowColor(shadowColors[0]);
//    waterRippleView.setBackGroundColor(R.color.colorPrimaryDark);
  }

}

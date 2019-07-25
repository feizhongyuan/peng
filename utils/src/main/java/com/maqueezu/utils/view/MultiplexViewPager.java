package com.maqueezu.utils.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by fei .
 * Created by Date 2019/7/24 11:43
 */

public class MultiplexViewPager extends ViewPager {

        private int startX;
        private int startY;

        public MultiplexViewPager(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
        }

        public MultiplexViewPager(Context context, AttributeSet attrs) {
            super(context, attrs);
            // TODO Auto-generated constructor stub
        }

        /**
         * 事件分发，请求父控件及祖宗控件是否拦截事件
         * 1.用户右滑，而且是第一个页面，就需要父控件拦截，显示上一个标签或者侧边栏
         * 2.用户左滑，而且是最后一个页面，就需要父控件拦截，显示下一个标签
         * 3.上下滑动，需要父控件拦截
         */
        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {

//    	//用getParent去请求，希望父控件不拦截这个OnToch事件
//        getParent().requestDisallowInterceptTouchEvent(true);

            switch (ev.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    //用getParent去请求，希望父控件不拦截这个OnToch事件
                    //这样为了保证ACTION_MOVE调用
                    getParent().requestDisallowInterceptTouchEvent(true);

                    startX = (int) ev.getRawX();
                    startY = (int) ev.getRawY();

                    break;

                case MotionEvent.ACTION_MOVE:

                    int endX=(int) ev.getRawX();
                    int endY=(int) ev.getRawY();

                    if(Math.abs(endX-startX)>Math.abs(endY-startY)){//左右滑动

                        if(endX>startX){//右滑

                            if(getCurrentItem()==0){//第一个页面，需要父控件拦截

                                getParent().requestDisallowInterceptTouchEvent(false);
                            }

                        }else{//左滑

                            if(getCurrentItem()==getAdapter().getCount()-1){//最后一个页面，需要拦截

                                getParent().requestDisallowInterceptTouchEvent(false);
                            }

                        }

                    }else{//上下滑动，需要父控件拦截
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }

                    break;

                default:
                    break;
            }




            return super.dispatchTouchEvent(ev);
        }

}

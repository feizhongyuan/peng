package com.maqueezu.utils.tools;

import android.content.Context;
import android.widget.Toast;

import com.maqueezu.utils.BaseApplication;


/**
 * Created by gaosheng on 2016/12/1.
 * 23:34
 * com.example.gaosheng.myapplication.utils
 */

public class ToastUtil {
    public static Toast toast;

    public static void setToast(String str) {

        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getContext(), str, Toast.LENGTH_SHORT);
        } else {
            toast.setText(str);
        }
        toast.show();
    }

    public static void showToast(Context context, String msg)
    {
        if (toast == null)
        {
            toast = Toast.makeText(context,msg, Toast.LENGTH_SHORT);
        }else {
            toast.setText(msg);
        }
        toast.show();
    }
}

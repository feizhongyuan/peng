package com.maqueezu.utils.tools;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.maqueezu.utils.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import okhttp3.Call;

/**
 * Created by fei .
 * Created by Date 2019/5/23 21:47
 */

public class UpdateUtil {
    private static final String TAG = "UpdateUtil";
    private static ProgressBar progressBar;
    private static Dialog dialogProgressBar;
    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dialogProgressBar.setTitle("下载中(" + msg.arg1 + "%)");
            progressBar.setProgress(msg.arg1);
            if (progressBar.getProgress() == 100) {
                dialogProgressBar.dismiss();
                handler.removeCallbacksAndMessages(null);
            }
        }
    };

    public static void checkUpdate(final Context context, final boolean isMainActivity) {

        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            Log.i(TAG, "版本号： " + info.versionName.substring(1));
            OkHttpUtils
                    .post()
                    .url("https://cloud.huadin.net:444/Service1.asmx/version_number")//
                    .addParams("version", info.versionName.substring(1))//内容
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e("UpdateUtil", "onError: " + e.toString());
                            ToastUtil.showToast(context, "网络连接错误");
                        }

                        @Override
                        public void onResponse(String response, int id) {

                            try {
                                JSONObject obj = new JSONObject(response);
                                String code = (String) obj.get("code");
                                if ("0".equals(code)) {//0为已是最新版本
                                    if(!isMainActivity){
                                        ToastUtil.showToast(context, "当前已是最新版本！");
                                    }

                                    return;
                                } else if ("2".equals(code)) {//2为发现新版本
                                    String msg = (String) obj.get("msg");
                                    String url = (String) obj.get("VarUrl");

                                    //弹出对话框
                                    showUpdateDialog(context, msg, url);
                                    //downLoad(url);
                                } else {

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void showUpdateDialog(final Context context, String msg, final String url) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("发现新版本");
        dialog.setIcon(android.R.drawable.ic_dialog_info);
        String[] strings = msg.split(";");
        String updateContent = "";
        for (int i = 0; i < strings.length; i++) {
            updateContent += strings[i] + "\r\n";
        }
        dialog.setMessage(updateContent);

        dialog.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //显示下载进度条
                showProgressBar(context);
                downLoad(context, url);
            }
        });

        dialog.setNegativeButton("稍后再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.setCancelable(false);
        dialog.show();

    }

    private static void showProgressBar(Context context) {
        dialogProgressBar = new Dialog(context);

        View view = View.inflate(context, R.layout.progress, null);
        progressBar = (ProgressBar) view.findViewById(R.id.pbar);
        progressBar.setMax(100);
        dialogProgressBar.setContentView(view);
        dialogProgressBar.show();
    }

    public static void downLoad(final Context context, String url) {
        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "ypgt.apk") {
                    @Override
                    public void inProgress(float progress, long total, int id) {

                        super.inProgress(progress, total, id);

                        Message message = Message.obtain();
                        message.arg1 = (int) (progress * 100 + 0.5f);
                        handler.sendMessage(message);
                        //
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(File file, int id) {
                        //自动安装
                        Intent intent = new Intent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(file),
                                "application/vnd.android.package-archive");
                        context.startActivity(intent);
                    }
                });
    }
}

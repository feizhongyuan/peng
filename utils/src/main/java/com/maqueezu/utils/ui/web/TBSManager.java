package com.maqueezu.utils.ui.web;

import com.maqueezu.utils.BaseApplication;
import com.maqueezu.utils.tools.LogUtil;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

/**
 * Created by admin on 2019/5/7 0007.
 */

public class TBSManager {

    private static TBSManager tbsManager;

    public static TBSManager getTbsManager(){
        if(tbsManager == null){
            synchronized (TBSManager.class){
                if(tbsManager == null){
                    tbsManager = new TBSManager();
                }
            }
        }
        return tbsManager;
    }

    private TBSManager(){
        /**
         * tbs 初始化
         */
        QbSdk.initX5Environment(BaseApplication.getAppContext(),new QbSdk.PreInitCallback(){
            @Override
            public void onCoreInitFinished() {
                LogUtil.print("TBSManager","initX5Environment onCoreInitFinished ");
            }

            @Override
            public void onViewInitFinished(boolean b) {
                LogUtil.print("TBSManager","initX5Environment onViewInitFinished "+b);
            }
        });

        /**
         * tbs 加载监听
         */
        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                LogUtil.print("TBSManager","setTbsListener onDownloadFinish "+i);
            }

            @Override
            public void onInstallFinish(int i) {
                LogUtil.print("TBSManager","setTbsListener onInstallFinish "+i);
            }

            @Override
            public void onDownloadProgress(int i) {
                LogUtil.print("TBSManager","setTbsListener onDownloadProgress "+i);
            }
        });


    }

}

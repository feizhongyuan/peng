package com.maqueezu.el.engine;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.maqueezu.el.Constant;
import com.maqueezu.el.MyApplication;
import com.maqueezu.el.dao.DaoMaster;
import com.maqueezu.el.dao.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by txl on 2017/2/17 0017.
 */
public class DBManager {
    private static DaoSession daoSession;



    /**
     * 获取数据库操作类
     * @return
     */
    public static DaoSession getDaoSession(){
        if(daoSession != null){
            return daoSession;
        }
        String dbName = MyApplication.getAppContext().getPackageName();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.getAppContext(), dbName+".db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        if(Constant.getCons().isDebug()){
            //数据库查询日志
            QueryBuilder.LOG_SQL = true;
            QueryBuilder.LOG_VALUES = true;
        }
        daoSession = daoMaster.newSession();
        return daoSession;
    }

//    /**
//     * 获取缓存字符串
//     * @param url
//     * @return
//     */
//    public static CacheModle getCacheStr(String url){
//        if(TextUtils.isEmpty(url)){
//            return null;
//        }
//        CacheModleDao dao = getDaoSession().getCacheModleDao();
//        CacheModle unique = dao.queryBuilder().where(CacheModleDao.Properties.Url.eq(url)).build().unique();
//        return unique;
//    }
//
//    /**
//     * 根据url与指定的javabean类型获取缓存在数据库中的缓存对象
//     * @param url
//     * @param tClass
//     * @param <T>
//     * @return
//     */
//    public static <T> T getCache(String url,Class<T> tClass){
//        T jsonBean = null;
//
//        LogUtils.i( "获取缓存 url="+url);
//        try{
//            CacheModleDao dao = getDaoSession().getCacheModleDao();
//            CacheModle unique = dao.queryBuilder().where(CacheModleDao.Properties.Url.eq(url)).build().unique();
//            if(unique == null || TextUtils.isEmpty(unique.getResponse())){
//                return jsonBean;
//            }
//
//            Gson gson = new Gson();
//            jsonBean = gson.fromJson(unique.getResponse(), tClass);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        LogUtils.i("获取缓存结果 jsonBean="+jsonBean);
//        return jsonBean;
//    }
//
//    /**
//     * 保存缓存数据
//     */
//    public static void saveCache(CacheModle modle){
//        if(modle == null || TextUtils.isEmpty(modle.getUrl()) || TextUtils.isEmpty(modle.getResponse())){
//            return;
//        }
//
//        LogUtils.i("保存缓存 CacheModle="+modle);
//
//        try{
//            CacheModleDao dao = getDaoSession().getCacheModleDao();
//            dao.insertOrReplace(modle);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }


//    /**
//     * 获取搜索词
//     * @param type
//     */
//    public static List<SearchItemModel> getSearchItem(int type){
//        DaoSession daoSession = getDaoSession();
//        SearchItemModelDao searchItemModelDao = daoSession.getSearchItemModelDao();
//
//        List<SearchItemModel> list = searchItemModelDao.queryBuilder().where(SearchItemModelDao.Properties.Type.eq(type)).orderDesc(SearchItemModelDao.Properties.Id).build().list();
//
//        if(list != null && list.size() > 5){
//            List<SearchItemModel> remList = list.subList(5,list.size());
//            searchItemModelDao.deleteInTx(remList);
//            list = list.subList(0,5);
//        }
//        return list;
//    }

}

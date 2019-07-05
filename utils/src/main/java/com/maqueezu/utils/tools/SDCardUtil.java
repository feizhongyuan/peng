
package com.maqueezu.utils.tools;

import android.os.Environment;
import android.os.StatFs;

import com.maqueezu.utils.BaseApplication;

import java.io.File;

/**
 * 描述: SDCard工具类
 * 
 * @author chenys
 * @since 2013-7-11 下午4:25:27
 */
public class SDCardUtil {

    /**
     * sdcard
     */
	 public static final String SDCARD_FOLDER = BaseApplication.getAppContext().getExternalFilesDir("").getAbsolutePath();
//    public static final String SDCARD_FOLDER = MyApplication.getInstance().getExternalFilesDir("").getAbsolutePath();
//    public static final String SDCARD_FOLDER = MyApplication.getInstance().getCacheDir().getAbsolutePath();
    /**
     * 应用目录
     */
    public static final String ROOT_FOLDER = SDCARD_FOLDER;

    /**
     * 缓存目录
     */
    public static final String CACHE_FOLDER = ROOT_FOLDER + "/cache/";

    /** 图片缓存目录 */
    public static final String IMAGE_CACHE_FOLDER = CACHE_FOLDER + ".image/";

    /** 截图缓存目录 */
    public static final String SCREENSHOT_IMAGE_CACHE_FOLDER = IMAGE_CACHE_FOLDER + ".screenshot/";

    /** 用户头像路径 */
    public static final String USER_HEAD_ICON = IMAGE_CACHE_FOLDER + "user_icon.png";

    /** 其他监时文件缓存目录 */
    public static final String OTHER_CACHE_FOLDER = CACHE_FOLDER + "other/";

    /** 安装包目录 */
    public static final String APP_FOLDER = ROOT_FOLDER + "apps/";
    
    /**更新包位置/*/
    public static final String updateApkFile = APP_FOLDER+"update.apk";

    /**
     * 日志目录
     */
    public static final String LOG_FOLDER = ROOT_FOLDER + "log/";

    /**
     * 配置目录
     */
    public static final String CONFIG_FOLDER = ROOT_FOLDER + "config/";

    /**
     * 游戏名单库
     */
    public static final String GAMES_LIB_FILE = CONFIG_FOLDER + "games.lib";

    /**
     * 临时文件后缀名
     */
    public static final String TEMP_FILE_EXT_NAME = ".temp";

    private SDCardUtil() {
    }

    /**
     * 判断是否存在SDCard
     * 
     * @return
     */
    public static boolean hasSDCard() {
        String status = Environment.getExternalStorageState();
        if (!status.equals(Environment.MEDIA_MOUNTED)) {
            return false;
        }
        return true;
    }

    /**
     * SDCard剩余大小
     * 
     * @return 字节
     */
    public static long getAvailableExternalMemorySize() {
        if (hasSDCard()) {
            try {
                File path = Environment.getExternalStorageDirectory();
                StatFs stat = new StatFs(path.getPath());
                long blockSize = stat.getBlockSize();
                long availableBlocks = stat.getAvailableBlocks();
                return availableBlocks * blockSize;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    /**
     * 是否有足够的空间（30M）
     * 
     * @return
     */
    public static boolean hasEnoughSpace() {
        return getAvailableExternalMemorySize() > 30 * 1024 * 1024;
    }

    /**
     * 是否有足够的空间
     * 
     * @param minSize 最小值
     * @return
     */
    public static boolean hasEnoughSpace(long minSize) {
        return getAvailableExternalMemorySize() > minSize;
    }

    /**
     * SDCard总容量大小
     * 
     * @return 字节
     */
    public static long getTotalExternalMemorySize() {
        if (hasSDCard()) {
            try {
                File path = Environment.getExternalStorageDirectory();
                StatFs stat = new StatFs(path.getPath());
                long blockSize = stat.getBlockSize();
                long totalBlocks = stat.getBlockCount();
                return totalBlocks * blockSize;

            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    /**
     * 这个是手机内存的可用空间大小
     * 
     * @return
     */
    public static long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    /**
     * 这个是手机内存的总空间大小
     * 
     * @return
     */
    public static long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

}

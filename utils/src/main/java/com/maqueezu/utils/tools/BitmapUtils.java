package com.maqueezu.utils.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.maqueezu.utils.BaseApplication;
import com.maqueezu.utils.view.MyMenuDialog;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by admin on 2019/5/5 0005.
 */

public class BitmapUtils {
    public final static int cameraRequestCode = 0x91;
    public final static int fileRequestCode = 0x92;
    private OnLoadImageListener imageListener;


    public BitmapUtils(Context context) {

    }



    public void showGetImageDialog(final Activity activity, FragmentManager fragmentManager, final int requestCode) {
        MyMenuDialog dialog = new MyMenuDialog();
        Bundle bundle = new Bundle();
        bundle.putString(MyMenuDialog.title, "选择图片来源");

        String[] items = new String[] { "从相机拍摄", "从相册获取" };
        bundle.putStringArray(MyMenuDialog.items, items);
        dialog.setArguments(bundle);
        dialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // 从相机拍摄
                    getImageByCamera(activity, requestCode);
                } else {
                    // 从相册获取
                    getImageByFile(activity, requestCode);
                }
            }
        });

        dialog.show(fragmentManager, "menudialog");
    }

    /**
     * 从相机获取
     */
    public void getImageByCamera(Activity activity, int requestCode) {
        // File dirFile = new File(SDCardUtil.IMAGE_CACHE_FOLDER);
        //
        // if (!dirFile.exists()) {
        // dirFile.mkdirs();
        // }
        //
        // File originalFile = new File(dirFile, "Camera.jpg");
        // if(originalFile.exists()){
        // originalFile.delete();
        // }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(originalFile));
        // intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        intent.putExtra("scale", true);
        // 取消人脸识别功能
        intent.putExtra("noFaceDetection", true);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 从相册获取
     *
     * @param activity
     */
    public void getImageByFile(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra("scale", true);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 从相册或相机获取返回的图片url
     *
     * @param data
     * @return
     */
    public String getImageUrlByIntent(Intent data) {
        if (data == null) {
            return "";
        }

        Uri selectedImage = data.getData();

        if (selectedImage != null) {

            // 获取到了图片
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = BaseApplication.getAppContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            return picturePath;

        } else {
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return null;
            }
            Bitmap bitmap = (Bitmap) bundle.get("data");

            if (bitmap == null) {
                return null;
            }

            File dirFile = new File(SDCardUtil.IMAGE_CACHE_FOLDER);

            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            File originalFile = new File(dirFile, "Camera.jpg");
            if (originalFile.exists()) {
                originalFile.delete();
            }

            BufferedOutputStream append = null;
            try {
                append = new BufferedOutputStream(new FileOutputStream(originalFile.getAbsolutePath()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, append);
            return originalFile.getAbsolutePath();
        }
    }

    /**
     * 根据imageview的宽度获取适配的缩略图,并返回缩略图的字节数组
     *
     * @param picturePath
     * @param bitmapByteMaxSize
     *            bitmap字节数组的最大长度 单位为kb
     */
    public void getImagePath(final String picturePath, final int bitmapByteMaxSize, final OnLoadImageListener imageListener) {
        LogUtil.print("图片地址：" + picturePath);

        if (TextUtils.isEmpty(picturePath) && imageListener != null) {
            imageListener.onFinish("未获取到照片", null);
            return;
        }

        this.imageListener = imageListener;

        new Thread(new Runnable() {
            @Override
            public void run() {

                // 缩放图片, width, height 按相同比例缩放图片
                BitmapFactory.Options options = new BitmapFactory.Options();
                // 为了节约内存我们还可以使用下面的几个字段
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                options.inPurgeable = true;
                options.inInputShareable = true;
                // options 设
                // 为true时，构造出的bitmap没有图片，只有一些长宽等配置信息，但比较快，设为false时，才有图片
                options.inJustDecodeBounds = true;
                Bitmap bitmap = BitmapFactory.decodeFile(picturePath, options);
                // float scale = options.outWidth / (float)
                // imageView.getWidth();
                float scale = calculateInSampleSize(options, 300, 300);
                if (scale <= 0) {
                    scale = 1;
                }
                options.inSampleSize = (int) scale;
                options.inJustDecodeBounds = false;

                int degree = getRotateImage(picturePath);
                bitmap = BitmapFactory.decodeFile(picturePath, options);
                if (bitmap == null) {
                    if (imageListener != null) {
                        BaseApplication.getAppContext().getTopActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageListener.onFinish("", "");
                            }
                        });
                    }
                    return;
                }

                // 如果图片角度不对，则需要对图片进行旋转
                if (degree != 0) {
                    Matrix m = new Matrix();
                    m.setRotate(degree, (float) options.outWidth / 2, (float) options.outHeight / 2);
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
                }

                LogUtil.print("缩放后的bitmap.width=" + bitmap.getWidth() + ",hight=" + bitmap.getHeight());

                // 对图片进行压缩
                final String path = compress(picturePath, 60);

                if (imageListener != null) {
                    BaseApplication.getAppContext().getTopActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageListener.onFinish("", path);
                        }
                    });
                }
            }
        }).start();
    }


    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * 从相册获取到的图片有可能角度不正确，所以这里需要计算旋转的角度，如果角度为0则不需要旋转，否则按照角度对图片进行旋转
     *
     * @return
     */
    private int getRotateImage(String picturePath) {
        ExifInterface exifInterface = null;
        try {
            exifInterface = new ExifInterface(picturePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (exifInterface == null) {
            return 0;
        }
        int tag = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
        switch (tag) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return 90;
            case ExifInterface.ORIENTATION_ROTATE_180:
                return 180;
            case ExifInterface.ORIENTATION_ROTATE_270:
                return -90;
            default:
                return 0;
        }
    }

    /**
     * 质量压缩
     *
     * @param picturePath  待压缩图片地址
     * @param quality 图片的质量,0-100,数值越小质量越差
     */
    public static String compress(String picturePath, int quality) {
        File saveFile = new File(SDCardUtil.CACHE_FOLDER+"temp.jpg");
        try {
            Bitmap originBitmap = BitmapFactory.decodeFile(picturePath);
            FileOutputStream fos = new FileOutputStream(saveFile);
            originBitmap.compress(Bitmap.CompressFormat.JPEG, quality, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return saveFile.getAbsolutePath();
    }

    /**
     * 图片加载监听器
     *
     * @author 69095
     *
     */
    public static interface OnLoadImageListener {
        void onFinish(String msg, String path);
    }
}

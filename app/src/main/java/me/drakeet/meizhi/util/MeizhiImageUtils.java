package me.drakeet.meizhi.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import me.drakeet.meizhi.R;

/**
 * TODO: 这个类需要重构，代码有点屎
 * Created by drakeet on 8/10/15.
 */
public class MeizhiImageUtils {

    public static void saveImageToSdCard(Context context, String url, String title) {
        TaskUtils.executeAsyncTask(new AsyncTask<Object, Object, Boolean>() {
                @Override protected Boolean doInBackground(Object... params) {
                    Bitmap bmp = null;
                    try {
                        bmp = Picasso.with(context).load(url).get();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (bmp == null) {
                        return false;
                    }

                    // 首先保存图片
                    File appDir = new File(Environment.getExternalStorageDirectory(), "Meizhi");
                    if (!appDir.exists()) {
                        appDir.mkdir();
                    }
                    String fileName = title.replace('/', '-') + ".jpg";
                    File file = new File(appDir, fileName);
                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }

                    // 其次把文件插入到系统图库
                    try {
                        MediaStore.Images.Media.insertImage(context.getContentResolver(),
                            file.getAbsolutePath(), fileName, null);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    // 最后通知图库更新
                    Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.parse("file://" + file.getAbsolutePath()));
                    context.sendBroadcast(scannerIntent);

                    return true;
                }

                @Override protected void onPostExecute(Boolean result) {
                    super.onPostExecute(result);
                    String msg;
                    if (result) {
                        File appDir = new File(Environment.getExternalStorageDirectory(), "Meizhi");
                        if (!appDir.exists()) {
                            appDir.mkdir();
                        }
                        msg = String.format(context.getString(R.string.picture_has_save_to),
                            appDir.getAbsolutePath());
                    } else {
                        msg = context.getString(R.string.picture_save_fail);
                    }
                    ToastUtils.showShort(msg);
                }
            });
    }
}

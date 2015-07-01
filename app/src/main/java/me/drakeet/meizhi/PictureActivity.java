package me.drakeet.meizhi;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import me.drakeet.meizhi.util.TaskUtils;
import me.drakeet.meizhi.util.ToastUtils;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PictureActivity extends ToolbarActivity {

    public static final String EXTRA_IMAGE_URL = "image_url";
    public static final String EXTRA_IMAGE_TITLE = "image_title";
    public static final String TRANSIT_PIC = "picture";

    private ImageView mImageView;
    private PhotoViewAttacher mPhotoViewAttacher;

    private String imageUrl = null;
    private String imageTitle = null;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_picture;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageView = (ImageView) findViewById(R.id.picture);
        mPhotoViewAttacher = new PhotoViewAttacher(mImageView);

        imageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        imageTitle = getIntent().getStringExtra(EXTRA_IMAGE_TITLE);

        Picasso.with(this).load(imageUrl).into(mImageView);

        setAppBarAlpha(0.7f);
        setTitle(imageTitle);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ViewCompat.setTransitionName(mImageView, TRANSIT_PIC);

        mPhotoViewAttacher.setOnViewTapListener(
                new PhotoViewAttacher.OnViewTapListener() {
                    @Override
                    public void onViewTap(View view, float v, float v1) {
                        hideOrShowToolbar();
                    }
                }
        );

        mPhotoViewAttacher.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        new AlertDialog.Builder(PictureActivity.this)
                                .setMessage("Saving to phone?")
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setPositiveButton("Saving", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        saveImageToGallery();
                                        dialog.dismiss();
                                    }
                                })
                                .show();

                        return true;
                    }
                }
        );
    }

    private void saveImageToGallery() {
        TaskUtils.executeAsyncTask(
                new AsyncTask<Object, Object, Boolean>() {
                    @Override
                    protected Boolean doInBackground(Object... params) {
                        Bitmap bmp = null;
                        try {
                            bmp = Picasso.with(PictureActivity.this).load(imageUrl).get();
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
                        String fileName = imageTitle.replace('/', '-') + ".jpg";
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
                            MediaStore.Images.Media.insertImage(PictureActivity.this.getContentResolver(),
                                    file.getAbsolutePath(), fileName, null);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        // 最后通知图库更新
                        Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                                Uri.parse("file://" + file.getAbsolutePath()));
                        sendBroadcast(scannerIntent);

                        return true;
                    }

                    @Override
                    protected void onPostExecute(Boolean result) {
                        super.onPostExecute(result);
                        String msg;
                        if (result) {
                            File appDir = new File(Environment.getExternalStorageDirectory(), "Meizhi");
                            if (!appDir.exists()) {
                                appDir.mkdir();
                            }
                            msg = "图片已保存至 " + appDir.getAbsolutePath() + " 文件夹";
                        } else {
                            msg = "图片保存失败，请再次尝试保存！";
                        }
                        ToastUtils.showShort(msg);
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_picture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}

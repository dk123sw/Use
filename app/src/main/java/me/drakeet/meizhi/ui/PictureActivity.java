package me.drakeet.meizhi.ui;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;
import me.drakeet.meizhi.R;
import me.drakeet.meizhi.ui.base.ToolbarActivity;
import me.drakeet.meizhi.util.MeizhiImageUtils;
import me.drakeet.meizhi.util.ShareUtils;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PictureActivity extends ToolbarActivity {

    public static final String EXTRA_IMAGE_URL = "image_url";
    public static final String EXTRA_IMAGE_TITLE = "image_title";
    public static final String TRANSIT_PIC = "picture";

    @Bind(R.id.picture) ImageView mImageView;

    PhotoViewAttacher mPhotoViewAttacher;
    String mImageUrl, mImageTitle;

    @Override protected int provideContentViewId() {
        return R.layout.activity_picture;
    }

    @Override public boolean canBack() {
        return true;
    }

    private void parseIntent() {
        mImageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        mImageTitle = getIntent().getStringExtra(EXTRA_IMAGE_TITLE);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        parseIntent();

        // init image view
        ViewCompat.setTransitionName(mImageView, TRANSIT_PIC);
        Picasso.with(this).load(mImageUrl).into(mImageView);

        // set up app bar
        setAppBarAlpha(0.7f);
        setTitle(mImageTitle);

        setUpPhotoAttacher();
    }

    private void setUpPhotoAttacher() {
        mPhotoViewAttacher = new PhotoViewAttacher(mImageView);
        mPhotoViewAttacher.setOnViewTapListener((view, v, v1) -> hideOrShowToolbar());
        mPhotoViewAttacher.setOnLongClickListener(v -> {
            new AlertDialog.Builder(PictureActivity.this).setMessage(
                getString(R.string.ask_saving_picture))
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {
                    dialog.dismiss();
                })
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    saveImageToGallery();
                    dialog.dismiss();
                })
                .show();
            return true;
        });
    }

    private void saveImageToGallery() {
        MeizhiImageUtils.saveImageToSdCard(this, mImageUrl, mImageTitle);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_picture, menu);
        // TODO: 把图片的一些信息，比如 who，加载到 Overflow 当中
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_share:
                ShareUtils.share(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

package me.drakeet.meizhi.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.meizhi.R;
import me.drakeet.meizhi.adapter.MeizhiListAdapter;
import me.drakeet.meizhi.api.OnMeizhiTouchListener;
import me.drakeet.meizhi.model.Meizhi;
import me.drakeet.meizhi.ui.base.SwipeRefreshBaseActivity;
import me.drakeet.meizhi.util.AlarmManagerUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends SwipeRefreshBaseActivity {

    RecyclerView mRecyclerView;
    ImageView mHackImageView;
    Handler mHandler;
    MeizhiListAdapter mMeizhiListAdapter;
    List<Meizhi> mMeizhiList;
    boolean mIsDbInited, mIsFirstTimeTouchBottom = true;
    int mPage = 1;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        mMeizhiList = new ArrayList<>();
        setUpRecyclerView();
        MobclickAgent.updateOnlineConfig(this);
        AlarmManagerUtils.register(this);

        mHackImageView = (ImageView) findViewById(R.id.hack_imageView);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHandler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        getData();
                    }
                }, 358
        );
    }

    private void setUpRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_meizhi);
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
        );
        mRecyclerView.setLayoutManager(layoutManager);
        mMeizhiListAdapter = new MeizhiListAdapter(this, mMeizhiList);
        mRecyclerView.setAdapter(mMeizhiListAdapter);
        mRecyclerView.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView rv, int dx, int dy) {
                        if (!mSwipeRefreshLayout.isRefreshing() && layoutManager.findLastCompletelyVisibleItemPositions(new int[2])[1] >= mMeizhiListAdapter.getItemCount() - 4) {
                            if (!mIsFirstTimeTouchBottom) {
                                mSwipeRefreshLayout.setRefreshing(true);
                                mPage += 20;
                                getData();
                            } else {
                                mIsFirstTimeTouchBottom = false;
                            }
                        }
                    }
                }
        );

        mMeizhiListAdapter.setOnMeizhiTouchListener(
                new OnMeizhiTouchListener() {
                    @Override
                    public void onTouch(View v, final View meizhiView, View card, final Meizhi meizhi) {
                        if (meizhi == null)
                            return;
                        if (v == meizhiView) {
                            Picasso.with(MainActivity.this).load(meizhi.url).into(
                                    mHackImageView, new Callback() {
                                        @Override
                                        public void onSuccess() {
                                            Intent i = new Intent(
                                                    MainActivity.this,
                                                    PictureActivity.class
                                            );
                                            i.putExtra(PictureActivity.EXTRA_IMAGE_URL, meizhi.url);
                                            i.putExtra(
                                                    PictureActivity.EXTRA_IMAGE_TITLE,
                                                    meizhi.desc
                                            );

                                            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                                                    .makeSceneTransitionAnimation(
                                                            MainActivity.this,
                                                            meizhiView,
                                                            PictureActivity.TRANSIT_PIC
                                                    );
                                            ActivityCompat.startActivity(
                                                    MainActivity.this, i, optionsCompat.toBundle()
                                            );
                                        }

                                        @Override
                                        public void onError() {

                                        }
                                    }
                            );
                        } else if (v == card) {
                            Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                            intent.putExtra("meizhi", meizhi);
                            startActivity(intent);
                        }
                    }
                }
        );
    }

    private void getData(boolean addFromDb) {
        setRefreshing(true);
        sDrakeet.getMeizhiData(mPage)
                .observeOn(AndroidSchedulers.mainThread())
                .map(meizhiData -> meizhiData.results)
                .flatMap(Observable::from)
                .toSortedList((meizhi1, meizhi2) -> meizhi2.updatedAt.compareTo(meizhi1.updatedAt))
                .subscribe(meizhis -> {
                               mMeizhiList.addAll(meizhis);
                               mMeizhiListAdapter.notifyDataSetChanged();
                               setRefreshing(false);
                           });
    }

    private void getData() {
        getData(true);
    }

    @Override
    public void onToolbarClick() {
                super.onToolbarClick();
        mRecyclerView.smoothScrollToPosition(0);
    }

    public void onFab(View v) {
        mRecyclerView.smoothScrollToPosition(0);
        requestDataRefresh();
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        getData(/* add from db */ false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Uri uri = Uri.parse("http://drakeet.me");
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
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

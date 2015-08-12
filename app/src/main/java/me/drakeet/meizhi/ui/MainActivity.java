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

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.meizhi.R;
import me.drakeet.meizhi.adapter.MeizhiListAdapter;
import me.drakeet.meizhi.listener.OnMeizhiTouchListener;
import me.drakeet.meizhi.model.Meizhi;
import me.drakeet.meizhi.ui.base.SwipeRefreshBaseActivity;
import me.drakeet.meizhi.util.AlarmManagerUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends SwipeRefreshBaseActivity {

    RecyclerView mRecyclerView;
    Handler mHandler;
    MeizhiListAdapter mMeizhiListAdapter;
    List<Meizhi> mMeizhiList;
    boolean mIsFirstTimeTouchBottom = true;
    int mPage = 1;

    @Override protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        mMeizhiList = new ArrayList<>();
        setUpRecyclerView();
        MobclickAgent.updateOnlineConfig(this);
        AlarmManagerUtils.register(this);
    }

    @Override protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHandler.postDelayed(this::getData, 358);
    }

    private void setUpRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_meizhi);
        final StaggeredGridLayoutManager layoutManager =
            new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mMeizhiListAdapter = new MeizhiListAdapter(this, mMeizhiList);
        mRecyclerView.setAdapter(mMeizhiListAdapter);

        mRecyclerView.addOnScrollListener(getScrollToBottomListener(layoutManager));
        mMeizhiListAdapter.setOnMeizhiTouchListener(getOnMeizhiTouchListener());
    }

    private void getData(boolean addFromDb) {
        setRefreshing(true);
        sDrakeet.getMeizhiData(mPage)
            .map(meizhiData -> meizhiData.results)
            .flatMap(Observable::from)
            .toSortedList((meizhi1, meizhi2) -> meizhi2.updatedAt.compareTo(meizhi1.updatedAt))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(meizhis -> {
                    mMeizhiList.addAll(meizhis);
                    mMeizhiListAdapter.notifyDataSetChanged();
                    setRefreshing(false);
                }, Throwable::printStackTrace);
    }

    RecyclerView.OnScrollListener getScrollToBottomListener(
        StaggeredGridLayoutManager layoutManager) {
        return new RecyclerView.OnScrollListener() {
            @Override public void onScrolled(RecyclerView rv, int dx, int dy) {
                boolean isBottom =
                    layoutManager.findLastCompletelyVisibleItemPositions(new int[2])[1]
                        >= mMeizhiListAdapter.getItemCount() - 4;
                if (!mSwipeRefreshLayout.isRefreshing() && isBottom) {
                    if (!mIsFirstTimeTouchBottom) {
                        mSwipeRefreshLayout.setRefreshing(true);
                        mPage += 1;
                        getData();
                    } else {
                        mIsFirstTimeTouchBottom = false;
                    }
                }
            }
        };
    }

    private OnMeizhiTouchListener getOnMeizhiTouchListener() {
        return (v, meizhiView, card, meizhi) -> {
            if (meizhi == null) return;
            if (v == meizhiView) {
                Picasso.with(this).load(meizhi.url).fetch(new Callback() {
                                                              @Override public void onSuccess() {
                                                                  startPictureActivity(meizhi,
                                                                      meizhiView);
                                                              }

                                                              @Override public void onError() {
                                                              }
                                                          });
            } else if (v == card) {
                // TODO: start Ganhuo activity!!!
                Intent intent = new Intent(this, GankActivity.class);
                startActivity(intent);
            }
        };
    }

    private void getData() {
        getData(true);
    }

    private void startPictureActivity(Meizhi meizhi, View transitView) {
        Intent i = new Intent(MainActivity.this, PictureActivity.class);
        i.putExtra(PictureActivity.EXTRA_IMAGE_URL, meizhi.url);
        i.putExtra(PictureActivity.EXTRA_IMAGE_TITLE, meizhi.desc);

        ActivityOptionsCompat optionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, transitView,
                PictureActivity.TRANSIT_PIC);
        ActivityCompat.startActivity(MainActivity.this, i, optionsCompat.toBundle());
    }

    @Override public void onToolbarClick() {
        mRecyclerView.smoothScrollToPosition(0);
    }

    public void onFab(View v) {
        requestDataRefresh();
        mRecyclerView.smoothScrollToPosition(0);
    }

    @Override public void requestDataRefresh() {
        super.requestDataRefresh();
        mMeizhiList.clear();
        getData(/* add from db */ false);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            Uri uri = Uri.parse(getString(R.string.blog_drakeet));
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
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
}

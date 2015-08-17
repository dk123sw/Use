package me.drakeet.meizhi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;
import java.util.List;
import me.drakeet.meizhi.R;
import me.drakeet.meizhi.adapter.MeizhiListAdapter;
import me.drakeet.meizhi.data.MeizhiData;
import me.drakeet.meizhi.data.休息视频Data;
import me.drakeet.meizhi.face.OnMeizhiTouchListener;
import me.drakeet.meizhi.model.Meizhi;
import me.drakeet.meizhi.ui.base.SwipeRefreshBaseActivity;
import me.drakeet.meizhi.util.AlarmManagerUtils;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends SwipeRefreshBaseActivity {

    @Bind(R.id.rv_meizhi) RecyclerView mRecyclerView;

    MeizhiListAdapter mMeizhiListAdapter;
    List<Meizhi> mMeizhiList;
    boolean mIsFirstTimeTouchBottom = true;
    int mPage = 1;

    @Override protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mMeizhiList = new ArrayList<>();
        setUpRecyclerView();
        MobclickAgent.updateOnlineConfig(this);
        AlarmManagerUtils.register(this);
    }

    @Override protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        new Handler().postDelayed(() -> setRefreshing(true), 358);
        getData();
    }

    private void setUpRecyclerView() {
        final StaggeredGridLayoutManager layoutManager =
            new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mMeizhiListAdapter = new MeizhiListAdapter(this, mMeizhiList);
        mRecyclerView.setAdapter(mMeizhiListAdapter);

        mRecyclerView.addOnScrollListener(getScrollToBottomListener(layoutManager));
        mMeizhiListAdapter.setOnMeizhiTouchListener(getOnMeizhiTouchListener());
    }

    private void getData(boolean addFromDb) {
        Subscription s = Observable.zip(sDrakeet.getMeizhiData(mPage), sDrakeet.get休息视频Data(mPage),
            (meizhi, love) -> createMeizhiDataWith休息视频Desc(meizhi, love))
            .map(meizhiData -> meizhiData.results)
            .flatMap(Observable::from)
            .toSortedList((meizhi1, meizhi2) -> meizhi2.publishedAt.compareTo(meizhi1.publishedAt))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(meizhis -> {
                saveMeizhis(meizhis);
                mMeizhiList.addAll(meizhis);
                mMeizhiListAdapter.notifyDataSetChanged();
                setRefreshing(false);
            }, Throwable::printStackTrace);
        addSubscription(s);
    }

    private void saveMeizhis(List<Meizhi> meizhis) {

    }

    private MeizhiData createMeizhiDataWith休息视频Desc(MeizhiData mzData, 休息视频Data love) {
        for (int i = 0; i < mzData.results.size(); i++) {
            Meizhi m = mzData.results.get(i);
            m.desc = m.desc + " " + love.results.get(i).desc;
        }
        return mzData;
    }

    private void getData() {
        getData(/* addFromDb */true);
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
                    }
                    else {
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
                    @Override public void onSuccess() {startPictureActivity(meizhi, meizhiView);}

                    @Override public void onError() {}
                });
            }
            else if (v == card) {
                Intent intent = new Intent(this, GankActivity.class);
                intent.putExtra(GankActivity.EXTRA_GANK_DATE, meizhi.publishedAt);
                startActivity(intent);
            }
        };
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

    @Override public void onToolbarClick() {mRecyclerView.smoothScrollToPosition(0);}

    @OnClick(R.id.main_fab) public void onFab(View v) {
        setRefreshing(true);
        mRecyclerView.smoothScrollToPosition(0);
        requestDataRefresh();
    }

    @Override public void requestDataRefresh() {
        super.requestDataRefresh();
        //mMeizhiList.clear();
        //mPage = 1;
        //getData(/* add from db */ false);
        setRefreshing(false);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            case R.id.action_login:
                loginGitHub();
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

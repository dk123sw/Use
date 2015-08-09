package me.drakeet.meizhi.ui.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import me.drakeet.meizhi.R;
import me.drakeet.meizhi.widget.MultiSwipeRefreshLayout;


/**
 * Created by drakeet on 1/3/15.
 */
public abstract class SwipeRefreshBaseActivity extends ToolbarActivity {

    public MultiSwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        trySetupSwipeRefresh();
    }

    void trySetupSwipeRefresh() {
        mSwipeRefreshLayout = (MultiSwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setColorSchemeResources(
                    R.color.refresh_progress_3,
                    R.color.refresh_progress_2,
                    R.color.refresh_progress_1
            );
            mSwipeRefreshLayout.setOnRefreshListener(
                    new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            requestDataRefresh();
                        }
                    }
            );
        }
    }

    public void requestDataRefresh() {}

    public void setRefreshing(boolean refreshing) {
        if (mSwipeRefreshLayout == null) {
            return;
        }
        if (!refreshing) {
            // 防止刷新消失太快，让子弹飞一会儿
            mSwipeRefreshLayout.postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }, 1000
            );
        } else {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }

    public void setProgressViewOffset(boolean scale, int start, int end) {
        mSwipeRefreshLayout.setProgressViewOffset(scale, start, end);
    }

    public void setSwipeableChildren(
            MultiSwipeRefreshLayout.CanChildScrollUpCallback canChildScrollUpCallback) {
        mSwipeRefreshLayout.setCanChildScrollUpCallback(
                canChildScrollUpCallback
        );
    }
}

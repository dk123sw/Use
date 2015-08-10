package me.drakeet.meizhi.listener;

import android.support.v7.widget.RecyclerView;

/**
 * Created by drakeet on 8/10/15.
 */
public abstract class OnRVScrollToBottomListener extends RecyclerView.OnScrollListener {

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }

    public void onScrollToBottom() {

    }
}

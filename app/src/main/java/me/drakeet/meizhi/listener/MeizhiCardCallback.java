package me.drakeet.meizhi.listener;

import android.view.View;

import com.squareup.picasso.Callback;

/**
 * Created by drakeet on 8/10/15.
 */
public class MeizhiCardCallback implements Callback {

    View mView;

    public MeizhiCardCallback(View view) {
        mView = view;
    }

    @Override
    public void onSuccess() {
        if (!mView.isShown()) mView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError() {

    }
}

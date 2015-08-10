package me.drakeet.meizhi.listener;

import android.view.View;

import me.drakeet.meizhi.model.Meizhi;

/**
 * 邪恶的 class 名。。。。
 * Created by drakeet on 7/1/15.
 */
public interface OnMeizhiTouchListener {
    void onTouch(View v, View meizhiView, View card, Meizhi meizhi);
}

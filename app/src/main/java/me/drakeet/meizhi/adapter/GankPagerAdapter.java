package me.drakeet.meizhi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import me.drakeet.meizhi.DrakeetFactory;
import me.drakeet.meizhi.ui.GankFragment;

/**
 * Created by drakeet on 8/11/15.
 */
public class GankPagerAdapter extends FragmentPagerAdapter {

    public GankPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override public Fragment getItem(int position) {
        return GankFragment.newInstance(position + 1);
    }

    @Override public int getCount() {
        return DrakeetFactory.gankSize;
    }
}
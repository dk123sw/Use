package me.drakeet.meizhi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.Calendar;
import java.util.Date;
import me.drakeet.meizhi.DrakeetFactory;
import me.drakeet.meizhi.ui.GankFragment;

/**
 * Created by drakeet on 8/11/15.
 */
public class GankPagerAdapter extends FragmentPagerAdapter {

    Date mDate;

    public GankPagerAdapter(FragmentManager fm, Date date) {
        super(fm);
        mDate = date;
    }

    @Override public Fragment getItem(int position) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        calendar.add(Calendar.DATE, -position);
        return GankFragment.newInstance(calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override public int getCount() {
        return DrakeetFactory.gankSize;
    }
}
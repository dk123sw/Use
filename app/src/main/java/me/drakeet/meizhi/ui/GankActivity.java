package me.drakeet.meizhi.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import me.drakeet.meizhi.R;
import me.drakeet.meizhi.adapter.GankPagerAdapter;
import me.drakeet.meizhi.ui.base.SwipeRefreshBaseActivity;

public class GankActivity extends SwipeRefreshBaseActivity {

    GankPagerAdapter mPagerAdapter;
    ViewPager mViewPager;
    TabLayout mTabLayout;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_gank;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPagerAdapter = new GankPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);

        initTabLayout();
    }

    private void initTabLayout() {
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
            mTabLayout.addTab(mTabLayout.newTab());
        }
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gank, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

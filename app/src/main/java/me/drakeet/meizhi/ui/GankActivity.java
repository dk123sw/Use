package me.drakeet.meizhi.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Date;
import me.drakeet.meizhi.R;
import me.drakeet.meizhi.adapter.GankPagerAdapter;
import me.drakeet.meizhi.ui.base.ToolbarActivity;

public class GankActivity extends ToolbarActivity {

    public static final String EXTRA_GANK_DATE = "gank_date";

    GankPagerAdapter mPagerAdapter;
    ViewPager mViewPager;
    TabLayout mTabLayout;

    @Override protected int getLayoutResource() {
        return R.layout.activity_gank;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Date gankDate = (Date) getIntent().getSerializableExtra(EXTRA_GANK_DATE);
        mPagerAdapter = new GankPagerAdapter(getSupportFragmentManager(), gankDate);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        initTabLayout();
    }

    private void initTabLayout() {
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
            mTabLayout.addTab(mTabLayout.newTab());
        }
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gank, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

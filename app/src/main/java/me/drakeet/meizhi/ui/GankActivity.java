package me.drakeet.meizhi.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.umeng.analytics.MobclickAgent;
import java.util.Date;
import me.drakeet.meizhi.R;
import me.drakeet.meizhi.adapter.GankPagerAdapter;
import me.drakeet.meizhi.event.LoveBus;
import me.drakeet.meizhi.event.OnKeyBackClickEvent;
import me.drakeet.meizhi.ui.base.ToolbarActivity;

public class GankActivity extends ToolbarActivity {

    public static final String EXTRA_GANK_DATE = "gank_date";

    @Bind(R.id.pager) ViewPager mViewPager;
    @Bind(R.id.tabLayout) TabLayout mTabLayout;

    GankPagerAdapter mPagerAdapter;

    @Override protected int getLayoutResource() {
        return R.layout.activity_gank;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Date gankDate = (Date) getIntent().getSerializableExtra(EXTRA_GANK_DATE);
        mPagerAdapter = new GankPagerAdapter(getSupportFragmentManager(), gankDate);
        mViewPager.setAdapter(mPagerAdapter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        initTabLayout();
    }

    private void initTabLayout() {
        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
            mTabLayout.addTab(mTabLayout.newTab());
        }
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                LoveBus.getLovelySeat().post(new OnKeyBackClickEvent());
        }
        return super.onKeyDown(keyCode, event);
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

    @Override public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        LoveBus.getLovelySeat().register(this);
    }

    @Override public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        LoveBus.getLovelySeat().unregister(this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

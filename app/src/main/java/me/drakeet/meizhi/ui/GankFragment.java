package me.drakeet.meizhi.ui;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.List;
import me.drakeet.meizhi.R;
import me.drakeet.meizhi.adapter.GankListAdapter;
import me.drakeet.meizhi.data.GankData;
import me.drakeet.meizhi.model.Gank;
import me.drakeet.meizhi.ui.base.BaseActivity;
import me.drakeet.meizhi.widget.GoodAppBarLayout;
import me.drakeet.meizhi.widget.VideoImageView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by drakeet on 8/11/15.
 */
public class GankFragment extends Fragment {

    private static final String ARG_YEAR = "year";
    private static final String ARG_MONTH = "month";
    private static final String ARG_DAY = "day";

    @Bind(R.id.rv_gank) RecyclerView mRecyclerView;
    @Bind(R.id.stub_empty_view) View mEmptyViewStub;
    @Bind(R.id.iv_video) VideoImageView mVideoImageView;
    @Bind(R.id.header_appbar) GoodAppBarLayout mAppBarLayout;

    List<Gank> mGankList;
    GankListAdapter mAdapter;
    Subscription mSubscription;
    int mYear, mMonth, mDay;
    private CoordinatorLayout.LayoutParams mAppBarLayoutParams;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static GankFragment newInstance(int year, int month, int day) {
        GankFragment fragment = new GankFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_YEAR, year);
        args.putInt(ARG_MONTH, month);
        args.putInt(ARG_DAY, day);
        fragment.setArguments(args);
        return fragment;
    }

    public GankFragment() {
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGankList = new ArrayList<>();
        mAdapter = new GankListAdapter(mGankList);
        parseArguments();
        setRetainInstance(true);
    }

    private void parseArguments() {
        Bundle bundle = getArguments();
        mYear = bundle.getInt(ARG_YEAR);
        mMonth = bundle.getInt(ARG_MONTH);
        mDay = bundle.getInt(ARG_DAY);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gank, container, false);
        ButterKnife.bind(this, rootView);
        initRecyclerView();
        return rootView;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(String.format("%s/%s/%s", mYear, mMonth, mDay));
        getData();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getData() {
        mSubscription = BaseActivity.sDrakeet.getGankData(mYear, mMonth, mDay)
            .observeOn(AndroidSchedulers.mainThread())
            .map(data -> data.results)
            .map(this::addAllResults)
            .subscribe(list -> {
                if (list.isEmpty()) { showEmptyView(); }
                else { mAdapter.notifyDataSetChanged(); }
            }, Throwable::printStackTrace);
    }

    private void showEmptyView() {mEmptyViewStub.setVisibility(View.VISIBLE);}

    private List<Gank> addAllResults(GankData.Result results) {
        if (results.androidList != null) mGankList.addAll(results.androidList);
        if (results.iOSList != null) mGankList.addAll(results.iOSList);
        if (results.拓展资源List != null) mGankList.addAll(results.拓展资源List);
        if (results.瞎推荐List != null) mGankList.addAll(results.瞎推荐List);
        return mGankList;
    }

    @OnClick(R.id.header_appbar) void onPlayVideo() {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    private void setVideoViewPosition(Configuration newConfig) {
        switch (newConfig.orientation) {
            case Configuration.ORIENTATION_LANDSCAPE: {
                mRecyclerView.setVisibility(View.GONE);
                mAppBarLayoutParams =
                    (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
                CoordinatorLayout.LayoutParams params =
                    new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT,
                        CoordinatorLayout.LayoutParams.MATCH_PARENT);
                mAppBarLayout.setLayoutParams(params);
                break;
            }
            case Configuration.ORIENTATION_UNDEFINED:
            case Configuration.ORIENTATION_PORTRAIT:
            default: {
                mRecyclerView.setVisibility(View.VISIBLE);
                if (mAppBarLayoutParams != null) mAppBarLayout.setLayoutParams(mAppBarLayoutParams);
                break;
            }
        }
    }

    @Override public void onConfigurationChanged(Configuration newConfig) {
        setVideoViewPosition(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override public void onDestroy() {
        super.onDestroy();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }
}

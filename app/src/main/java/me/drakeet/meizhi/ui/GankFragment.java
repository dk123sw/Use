package me.drakeet.meizhi.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import me.drakeet.meizhi.R;
import me.drakeet.meizhi.adapter.GankListAdapter;
import me.drakeet.meizhi.data.GankData;
import me.drakeet.meizhi.model.Gank;
import me.drakeet.meizhi.ui.base.BaseActivity;
import me.drakeet.meizhi.widget.GoodAppBarLayout;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by drakeet on 8/11/15.
 */
public class GankFragment extends Fragment {

    private static final String ARG_YEAR = "year";
    private static final String ARG_MONTH = "month";
    private static final String ARG_DAY = "day";

    RecyclerView mRecyclerView;
    View mEmptyViewStub;
    List<Gank> mGankList;
    GankListAdapter mAdapter;
    GoodAppBarLayout mAppBarLayout;
    Subscription mSubscription;
    int mYear, mMonth, mDay;

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
        mAppBarLayout = (GoodAppBarLayout) rootView.findViewById(R.id.header_appbar);
        mEmptyViewStub = rootView.findViewById(R.id.stub_empty_view);
        initRecyclerView(rootView);
        return rootView;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
        mAppBarLayout.notifyAddOffsetListener();
        getActivity().setTitle(String.format("%s/%s/%s", mYear, mMonth, mDay));
    }

    private void initRecyclerView(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_gank);
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

    private void showEmptyView() {
        mEmptyViewStub.setVisibility(View.VISIBLE);
    }

    private List<Gank> addAllResults(GankData.Result results) {
        if (results.androidList != null) mGankList.addAll(results.androidList);
        if (results.iOSList != null) mGankList.addAll(results.iOSList);
        if (results.拓展资源List != null) mGankList.addAll(results.拓展资源List);
        if (results.瞎推荐List != null) mGankList.addAll(results.瞎推荐List);
        return mGankList;
    }

    @Override public void onDestroy() {
        super.onDestroy();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }
}

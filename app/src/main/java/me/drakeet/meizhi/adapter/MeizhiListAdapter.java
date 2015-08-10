package me.drakeet.meizhi.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import me.drakeet.meizhi.R;
import me.drakeet.meizhi.listener.OnMeizhiTouchListener;
import me.drakeet.meizhi.model.Meizhi;
import me.drakeet.meizhi.widget.RatioImageView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by drakeet on 6/20/15.
 */
public class MeizhiListAdapter extends RecyclerView.Adapter<MeizhiListAdapter.ViewHolder> {

    public static final String TAG = "MeizhiListAdapter";

    private List<Meizhi> mList;
    private Context mContext;
    private OnMeizhiTouchListener mOnMeizhiTouchListener;

    public MeizhiListAdapter(Context context, List<Meizhi> meizhiList) {
        mList = meizhiList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meizhi, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.card.setVisibility(View.GONE);
        Meizhi meizhi = mList.get(position);
        viewHolder.meizhi = meizhi;
        viewHolder.titleView.setText(meizhi.desc);
        viewHolder.card.setTag(meizhi.desc);
        //viewHolder.meizhiView.setOriginalSize(meizhi.imageWidth, meizhi.imageHeight);

        getBitmapObservable(meizhi.url)
                  .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                  .subscribe(
                          bitmap -> {
                              viewHolder.meizhiView.setOriginalSize(
                                      bitmap.getWidth(), bitmap.getHeight()
                              );
                              //viewHolder.meizhiView.setImageBitmap(bitmap);
                              Picasso.with(mContext)
                                     .load(meizhi.url)
                                     .resize(bitmap.getWidth()/3, bitmap.getHeight()/3)
                                     .into(viewHolder.meizhiView);
                              viewHolder.card.setVisibility(View.VISIBLE);
                          }, throwable -> Log.e(TAG, throwable.getMessage())
                  );

    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        //if (holder.meizhiView != null) holder.meizhiView.setImageBitmap(null);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnMeizhiTouchListener(OnMeizhiTouchListener onMeizhiTouchListener) {this.mOnMeizhiTouchListener = onMeizhiTouchListener;}

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Meizhi meizhi;
        RatioImageView meizhiView;
        TextView titleView;
        View card;

        public ViewHolder(View itemView) {
            super(itemView);
            card = itemView;
            meizhiView = (RatioImageView) itemView.findViewById(R.id.iv_meizhi);
            titleView = (TextView) itemView.findViewById(R.id.tv_title);
            meizhiView.setOnClickListener(this);
            card.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnMeizhiTouchListener.onTouch(v, meizhiView, card, meizhi);
        }
    }

    Observable<Bitmap> getBitmapObservable(String url) {
        return Observable.defer(
                () -> {
                    try {
                        return Observable.just(Picasso.with(mContext).load(url).get());
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
        );
    }

}

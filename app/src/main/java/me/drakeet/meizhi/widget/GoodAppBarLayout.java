package me.drakeet.meizhi.widget;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;

/**
 * Created by drakeet on 8/11/15.
 */
public class GoodAppBarLayout extends AppBarLayout {

    public int offset;
    OnOffsetChangedListener mOnOffsetChangedListener;

    public GoodAppBarLayout(Context context) {
        this(context, null);

    }

    public GoodAppBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mOnOffsetChangedListener = (appBarLayout, i) -> GoodAppBarLayout.this.offset = i;
    }

    public void notifyRemoveOffsetListener() {
        this.removeOnOffsetChangedListener(mOnOffsetChangedListener);
    }

    public void notifyAddOffsetListener() {
        this.addOnOffsetChangedListener(mOnOffsetChangedListener);
    }

}

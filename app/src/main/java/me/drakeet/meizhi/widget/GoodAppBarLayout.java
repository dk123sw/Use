/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.drakeet.meizhi.widget;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;

/**
 * A good boy! AppBarLayout is bad boy!
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
    }


    public void notifyRemoveOffsetListener() {
        this.removeOnOffsetChangedListener(mOnOffsetChangedListener);
    }


    public void notifyAddOffsetListener() {
        this.addOnOffsetChangedListener(mOnOffsetChangedListener);
    }
}

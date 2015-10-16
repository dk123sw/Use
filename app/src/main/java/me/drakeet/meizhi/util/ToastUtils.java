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

package me.drakeet.meizhi.util;

import android.widget.Toast;
import me.drakeet.meizhi.App;

/**
 * Created by drakeet on 9/27/14.
 */
public class ToastUtils {

    private ToastUtils() {
    }


    public static void showShort(int resId) {
        Toast.makeText(App.sContext, resId, Toast.LENGTH_SHORT).show();
    }


    public static void showShort(String message) {
        Toast.makeText(App.sContext, message, Toast.LENGTH_SHORT).show();
    }


    public static void showLong(int resId) {
        Toast.makeText(App.sContext, resId, Toast.LENGTH_LONG).show();
    }


    public static void showLong(String message) {
        Toast.makeText(App.sContext, message, Toast.LENGTH_LONG).show();
    }


    public static void showLongX2(String message) {
        showLong(message);
        showLong(message);
    }


    public static void showLongX2(int resId) {
        showLong(resId);
        showLong(resId);
    }


    public static void showLongX3(int resId) {
        showLong(resId);
        showLong(resId);
        showLong(resId);
    }


    public static void showLongX3(String message) {
        showLong(message);
        showLong(message);
        showLong(message);
    }
}

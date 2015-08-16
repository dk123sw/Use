package me.drakeet.meizhi.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by drakeet on 8/16/15.
 */
public class Once {

    private SharedPreferences mSharedPreferences;

    public Once(Context context) {
        mSharedPreferences = context.getSharedPreferences("once", Context.MODE_PRIVATE);
    }

    public void show(String tagKey, OnceCallback callback) {
        boolean isSecondTime = mSharedPreferences.getBoolean(tagKey, false);
        if (!isSecondTime) {
            callback.onOnce();
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putBoolean(tagKey, true);
            editor.apply();
        }
    }

    public interface OnceCallback {
        void onOnce();
    }
}

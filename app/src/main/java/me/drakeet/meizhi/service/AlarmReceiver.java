package me.drakeet.meizhi.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import me.drakeet.meizhi.ui.MainActivity;
import me.drakeet.meizhi.R;
import me.drakeet.meizhi.util.HeadsUpUtils;

/**
 * Created by drakeet on 7/1/15.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override public void onReceive(Context context, Intent intent) {
        HeadsUpUtils.show(context, MainActivity.class, context.getString(R.string.headsup_title),
            context.getString(R.string.headsup_content), R.mipmap.ic_meizhi_150602,
            R.mipmap.ic_female, 123123);
    }
}
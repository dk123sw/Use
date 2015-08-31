
/*
 * Copyright (C) 2015 Drakeet
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

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.Date;

import me.drakeet.meizhi.service.AlarmReceiver;

/**
 * Created by drakeet on 7/1/15.
 */
public class AlarmManagerUtils {

    public static void register(Context context) {

        Calendar tomorrow = Calendar.getInstance();
        tomorrow.setTime(DateUtils.getNextdayDate(new Date()));

        tomorrow.set(Calendar.HOUR_OF_DAY, 12);
        tomorrow.set(Calendar.MINUTE, 38);
        tomorrow.set(Calendar.SECOND, 0);

        Intent intent = new Intent("me.drakeet.meizhi.alarm");
        intent.setClass(context, AlarmReceiver.class);

        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);

        am.set(AlarmManager.RTC_WAKEUP, tomorrow.getTimeInMillis(), pi);
    }
}

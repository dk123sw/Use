package me.drakeet.meizhi.util;

import android.content.Context;
import android.content.Intent;
import me.drakeet.meizhi.R;

/**
 * Created by drakeet on 8/17/15.
 */
public class ShareUtils {

    public static void share(Context context) {
        share(context, context.getString(R.string.share_text));
    }

    public static void share(Context context, String extraText) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.action_share));
        intent.putExtra(Intent.EXTRA_TEXT, extraText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(
            Intent.createChooser(intent, context.getString(R.string.action_share)));
    }
}

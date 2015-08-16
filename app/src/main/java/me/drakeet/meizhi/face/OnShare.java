package me.drakeet.meizhi.face;

import android.content.Context;
import android.content.Intent;
import me.drakeet.meizhi.R;

/**
 * lol
 * Created by drakeet on 8/16/15.
 */
public interface OnShare {

    default void onClickShare(Context context) {
        onClickShare(context, context.getString(R.string.share_text));
    }

    default void onClickShare(Context context, String extraText) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.action_share));
        intent.putExtra(Intent.EXTRA_TEXT, extraText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(
            Intent.createChooser(intent, context.getString(R.string.action_share)));
    }
}

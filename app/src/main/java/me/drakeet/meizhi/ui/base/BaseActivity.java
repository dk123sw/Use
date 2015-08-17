package me.drakeet.meizhi.ui.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import me.drakeet.meizhi.Drakeet;
import me.drakeet.meizhi.DrakeetFactory;
import me.drakeet.meizhi.R;
import me.drakeet.meizhi.ui.WebActivity;
import me.drakeet.meizhi.util.Once;
import me.drakeet.meizhi.util.ToastUtils;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by drakeet on 8/9/15.
 */
public class BaseActivity extends AppCompatActivity {

    public static final Drakeet sDrakeet = DrakeetFactory.getSingleton();

    private CompositeSubscription mCompositeSubscription;

    public CompositeSubscription getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        return this.mCompositeSubscription;
    }

    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        this.mCompositeSubscription.add(s);
    }

    protected void loginGitHub() {
        new Once(this).show(R.string.action_login_github, () -> {
            ToastUtils.showLongLong(getString(R.string.tip_login_github));
        });
        String url = getString(R.string.url_login_github);
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra(WebActivity.EXTRA_URL, url);
        intent.putExtra(WebActivity.EXTRA_TITLE, getString(R.string.action_login_github));
        startActivity(intent);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }
}

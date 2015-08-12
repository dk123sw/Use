package me.drakeet.meizhi.ui.base;

import android.support.v7.app.AppCompatActivity;

import me.drakeet.meizhi.Drakeet;
import me.drakeet.meizhi.DrakeetFactory;
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

    @Override protected void onDestroy() {
        super.onDestroy();
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }
}

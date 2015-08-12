package me.drakeet.meizhi;

import android.app.Application;
import android.content.Context;

import com.activeandroid.ActiveAndroid;

/**
 * Created by drakeet on 6/21/15.
 */
public class App extends Application {

    public static Context sContext;

    @Override public void onCreate() {
        super.onCreate();
        sContext = this;
        ActiveAndroid.initialize(this);
    }

    @Override public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}

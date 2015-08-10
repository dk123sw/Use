package me.drakeet.meizhi.ui.base;

import android.support.v7.app.AppCompatActivity;

import me.drakeet.meizhi.Drakeet;
import me.drakeet.meizhi.DrakeetFactory;

/**
 * Created by drakeet on 8/9/15.
 */
public class BaseActivity extends AppCompatActivity {
    public static final Drakeet sDrakeet = DrakeetFactory.getSingleton();
}

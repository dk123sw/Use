package me.drakeet.meizhi.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.umeng.analytics.MobclickAgent;

import me.drakeet.meizhi.R;
import me.drakeet.meizhi.model.Meizhi;

public class WebViewActivity extends AppCompatActivity {

    private NumberProgressBar mProgressbar;
    private Context mContext;
    private WebView mWebView;
    private Meizhi meizhi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        meizhi = (Meizhi) getIntent().getSerializableExtra("meizhi");
        if (meizhi == null) {
            Toast.makeText(mContext, "Oh No.", Toast.LENGTH_SHORT).show();
            finish();
        }
        setContentView(R.layout.activity_webview);
        mWebView = (WebView) findViewById(R.id.webview);
        mProgressbar = (NumberProgressBar) findViewById(R.id.progressbar);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChrome());
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.loadUrl("http://gank.io/" + meizhi.getMid());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null)
            mWebView.destroy();
    }

    @Override
    protected void onPause() {
        if (mWebView != null)
            mWebView.onPause();
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWebView != null)
            mWebView.onResume();
        MobclickAgent.onResume(this);
    }

    private class WebChrome extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mProgressbar.setProgress(newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
    }

}

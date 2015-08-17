package me.drakeet.meizhi.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.umeng.analytics.MobclickAgent;
import me.drakeet.meizhi.R;
import me.drakeet.meizhi.ui.base.ToolbarActivity;

public class WebActivity extends ToolbarActivity {

    public static final String EXTRA_URL = "extra_url";
    public static final String EXTRA_TITLE = "extra_title";

    @Bind(R.id.progressbar) NumberProgressBar mProgressbar;
    @Bind(R.id.webView) WebView mWebView;
    @Bind(R.id.tv_title) TextView mTitleView;

    Context mContext;
    String mUrl, mTitle;

    @Override protected int provideContentViewId() {
        return R.layout.activity_web;
    }

    @Override public boolean canBack() {
        return true;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mContext = this;
        mUrl = getIntent().getStringExtra(EXTRA_URL);
        mTitle = getIntent().getStringExtra(EXTRA_TITLE);
        if (mTitle != null) setTitle(mTitle);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        mWebView.setWebChromeClient(new ChromeClient());
        mWebView.setWebViewClient(new LoveClient());

        mWebView.loadUrl(mUrl);

        mTitleView.postDelayed(() -> mTitleView.setSelected(true), 1380);
    }

    @Override public void setTitle(CharSequence title) {
        super.setTitle(title);
        mTitleView.setText(title);
    }

    @Override public void setTitle(int titleId) {
        super.setTitle(titleId);
        mTitleView.setText(titleId);
    }

    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                    }
                    else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) mWebView.destroy();
        ButterKnife.unbind(this);
    }

    @Override protected void onPause() {
        if (mWebView != null) mWebView.onPause();
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override protected void onResume() {
        super.onResume();
        if (mWebView != null) mWebView.onResume();
        MobclickAgent.onResume(this);
    }

    private class ChromeClient extends WebChromeClient {

        @Override public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mProgressbar.setProgress(newProgress);
        }

        @Override public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setTitle(title);
        }
    }

    private class LoveClient extends WebViewClient {

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null) view.loadUrl(url);
            return true;
        }
    }
}

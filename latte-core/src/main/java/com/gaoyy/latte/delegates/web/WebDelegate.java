package com.gaoyy.latte.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.gaoyy.latte.delegates.LatteDelegate;
import com.gaoyy.latte.delegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by gaoyy on 2018/1/21 0021.
 */

public abstract class WebDelegate extends LatteDelegate
{
    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAvailable = false;

    public WebDelegate()
    {

    }

    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());

    }

    private void initWebView()
    {
        if (mWebView != null)
        {
            mWebView.removeAllViews();
            mWebView.destroy();
        }
        else
        {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null)
            {
                //通过new出来方式的webview，比在xml中Webview更加能避免内存泄漏
                final WeakReference<WebView> webViewWeakReference = new WeakReference<WebView>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                mWebView.addJavascriptInterface(LatteWebInterface.create(this), "latte");
                mIsWebViewAvailable = true;
            }
            else
            {
                throw new NullPointerException("Initializer is null!");
            }
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (mWebView != null)
        {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (mWebView != null)
        {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        mIsWebViewAvailable = false;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (mWebView != null)
        {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}

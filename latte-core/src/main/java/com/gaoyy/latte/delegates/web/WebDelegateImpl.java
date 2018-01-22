package com.gaoyy.latte.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gaoyy.latte.delegates.web.chromeclient.WebChromeClientImpl;
import com.gaoyy.latte.delegates.web.client.WebViewClientImpl;
import com.gaoyy.latte.delegates.web.route.RouteKeys;
import com.gaoyy.latte.delegates.web.route.Router;

/**
 * Created by gaoyy on 2018/1/21 0021.
 */

public class WebDelegateImpl extends WebDelegate
{
    private IPageLoadListener mIPageLoadListener = null;

    public static WebDelegateImpl create(String url)
    {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    public void setPageLoadListener(IPageLoadListener listener)
    {
        this.mIPageLoadListener = listener;
    }


    @Override
    public IWebViewInitializer setInitializer()
    {
        return this;
    }

    @Override
    public Object setLayout()
    {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        if (getUrl() != null)
        {
            //用原生的方式模拟Web跳转并进行页面加载
            Router.getInstance().loadPage(this, getUrl());
        }
    }


    @Override
    public WebView initWebView(WebView webView)
    {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient()
    {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        client.setPageLoadListener(mIPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient()
    {
        return new WebChromeClientImpl();
    }
}

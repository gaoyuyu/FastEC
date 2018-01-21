package com.gaoyy.latte.delegates.web.chromeclient;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by gaoyy on 2018/1/21 0021.
 */

public class WebChromeClientImpl extends WebChromeClient
{
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result)
    {
        return super.onJsAlert(view, url, message, result);
    }
}

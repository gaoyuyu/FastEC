package com.gaoyy.fastec.example;

import android.app.Application;

import com.gaoyy.latte.app.Latte;
import com.gaoyy.latte.ec.icon.FontEcModule;
import com.gaoyy.latte.net.interceptors.DebugInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by gaoyy on 2018/1/10 0010.
 */

public class ExampleApp extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1/")
                .withLoaderDelayed(5000)
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
    }
}

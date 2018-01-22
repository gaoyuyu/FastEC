package com.gaoyy.fastec.example;

import android.app.Application;

import com.gaoyy.latte.app.Latte;
import com.gaoyy.fastec.example.event.TestEvent;
import com.gaoyy.latte.ec.database.DatabaseManager;
import com.gaoyy.latte.ec.icon.FontEcModule;
import com.gaoyy.latte.net.interceptors.DebugInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by gaoyy on 2018/1/14.
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
                .withLoaderDelayed(1000)
                .withJavascriptInterface("latte")
                .withWebEvent("test",new TestEvent())
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .configure();

        // 测试数据库 initStetho();
        DatabaseManager.getInstance().init(this);
    }
}

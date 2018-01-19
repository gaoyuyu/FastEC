package com.gaoyy.latte.app;

import android.content.Context;
import android.os.Handler;


/**
 * Created by gaoyy on 2018/1/14.
 */

public final class Latte
{

    /**
     * 初始化
     *
     * @param context
     * @return
     */
    public static Configurator init(Context context)
    {
        Configurator.getInstance().getLatteConfigs().put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    /**
     * 获取Configurator单例
     *
     * @return
     */
    public static Configurator getConfigurator()
    {
        return Configurator.getInstance();
    }

    /**
     * 获取配置项
     *
     * @param key not null
     * @param <T>
     * @return
     */
    public static <T> T getConfiguration(Object key)
    {
        return getConfigurator().getConfiguration(key);
    }

    /**
     * 获取全局Applcaition context
     *
     * @return
     */
    public static Context getApplicationContext()
    {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    /**
     * 获取全局handler
     *
     * @return
     */
    public static Handler getHandler()
    {
        return getConfiguration(ConfigKeys.HANDLER);
    }

}

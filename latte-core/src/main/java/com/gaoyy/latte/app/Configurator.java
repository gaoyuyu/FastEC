package com.gaoyy.latte.app;


import android.app.Activity;
import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by gaoyy on 2018/1/14.
 */

public class Configurator
{
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private static final Handler HANDLER = new Handler();

    private Configurator()
    {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        //构造方法实例化handler
        LATTE_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    /**
     * 静态内部类的单例
     *
     * @return
     */
    public static Configurator getInstance()
    {
        return Holder.INSTANCE;
    }

    final HashMap<Object, Object> getLatteConfigs()
    {
        return LATTE_CONFIGS;
    }

    private static class Holder
    {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure()
    {
        //初始化字体图标
        initIcons();
        //设置CONFIG_READY为true
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }

    /**
     * 设置host
     *
     * @param host
     * @return
     */
    public final Configurator withApiHost(String host)
    {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    /**
     * 设置loading延时
     *
     * @param delayed
     * @return
     */
    public final Configurator withLoaderDelayed(long delayed)
    {
        LATTE_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    /**
     * 设置okhttp的拦截器【single】
     *
     * @param interceptor
     * @return
     */
    public final Configurator withInterceptor(Interceptor interceptor)
    {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 设置okhttp的拦截器【多个】
     *
     * @param interceptors
     * @return
     */
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors)
    {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    private void initIcons()
    {
        if (ICONS.size() > 0)
        {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++)
            {
                initializer.with(ICONS.get(i));
            }
        }
    }

    /**
     * 设置微信APP ID
     *
     * @param appId
     * @return
     */
    public final Configurator withWeChatAppId(String appId)
    {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId);
        return this;
    }

    /**
     * 设置微信APP Secret
     *
     * @param appSecret
     * @return
     */
    public final Configurator withWeChatAppSecret(String appSecret)
    {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }

    /**
     * 设置宿主Activity
     *
     * @param activity
     * @return
     */
    public final Configurator withActivity(Activity activity)
    {
        LATTE_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }

    /**
     * 设置字体库
     *
     * @param descriptor
     * @return
     */
    public final Configurator withIcon(IconFontDescriptor descriptor)
    {
        ICONS.add(descriptor);
        return this;
    }

    /**
     * 检查配置项，CONFIG_READY为false时报运行时异常
     */
    private void checkConfiguration()
    {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady)
        {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    /**
     * 根据key获取配置项
     *
     * @param key
     * @param <T>
     * @return
     */
    final <T> T getConfiguration(Object key)
    {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null)
        {
            throw new NullPointerException(key.toString() + "IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }
}

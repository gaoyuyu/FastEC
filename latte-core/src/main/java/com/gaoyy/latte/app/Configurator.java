package com.gaoyy.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by gaoyy on 2018/1/10 0010.
 */

public final class Configurator
{

    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();
    private final static ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator()
    {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    public static Configurator getInstance()
    {
        return Holder.INSTANCE;
    }

    //单例-静态内部类的写法
    private static class Holder
    {
        private static final Configurator INSTANCE = new Configurator();
    }

    final HashMap<String, Object> getLatteConfigs()
    {
        return LATTE_CONFIGS;
    }

    public final void configure()
    {
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public final Configurator withApiHost(String host)
    {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    private void initIcons()
    {
        if (ICONS.size() > 0)
        {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++)
            {
                initializer.with(ICONS.get(1));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor)
    {
        ICONS.add(descriptor);
        return this;
    }

    /**
     * 检查配置项是否完成
     */
    private void checkConfiguration()
    {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady)
        {
            throw new RuntimeException("Configuration is not ready,call configure!");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfigurations(Enum<ConfigType> key)
    {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }

}

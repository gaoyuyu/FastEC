package com.gaoyy.latte.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by gaoyy on 2018/1/10 0010.
 */

public class Latte
{
    public static Configurator init(Context context)
    {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static HashMap<String, Object> getConfigurations()
    {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplicationContext()
    {
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }

}

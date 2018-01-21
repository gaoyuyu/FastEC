package com.gaoyy.latte.delegates.web.event;


import com.gaoyy.latte.util.log.LatteLogger;

/**
 * Created by gaoyy
 */

public class UndefineEvent extends Event
{
    @Override
    public String execute(String params)
    {
        LatteLogger.e("UndefineEvent", params);
        return null;
    }
}

package com.gaoyy.latte.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by gaoyy on 2018/1/10 0010.
 */

public class FontEcModule implements IconFontDescriptor
{
    @Override
    public String ttfFileName()
    {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters()
    {
        return EcIcons.values();
    }
}

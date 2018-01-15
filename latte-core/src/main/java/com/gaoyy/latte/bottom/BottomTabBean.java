package com.gaoyy.latte.bottom;

/**
 * Created by gaoyy on 2018/1/15 0015.
 */

public class BottomTabBean
{
    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title)
    {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon()
    {
        return ICON;
    }

    public CharSequence getTitle()
    {
        return TITLE;
    }
}

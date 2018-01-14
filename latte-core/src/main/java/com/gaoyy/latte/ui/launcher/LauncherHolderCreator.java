package com.gaoyy.latte.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by gaoyy on 2017/7/31.
 */

public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder>
{
    @Override
    public LauncherHolder createHolder()
    {
        return new LauncherHolder();
    }
}

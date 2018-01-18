package com.gaoyy.latte.delegates;

/**
 * Created by gaoyy on 2018/1/14.
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate
{

    public <T extends LatteDelegate> T getParentDelegate()
    {
        return (T) getParentFragment();
    }

}

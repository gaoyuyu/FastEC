package com.gaoyy.fastec.example;

import com.gaoyy.latte.activities.ProxyActivity;
import com.gaoyy.latte.delegates.LatteDelegate;
import com.gaoyy.latte.ec.launcher.LauncherScrollDelegate;

public class ExampleActivity extends ProxyActivity
{

    @Override
    public LatteDelegate setRootDelegate()
    {
        return new LauncherScrollDelegate();
    }
}

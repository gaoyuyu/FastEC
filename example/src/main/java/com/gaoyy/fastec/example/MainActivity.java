package com.gaoyy.fastec.example;

import com.gaoyy.latte.activities.ProxyActivity;
import com.gaoyy.latte.delegates.LatteDelegate;

public class MainActivity extends ProxyActivity
{

    @Override
    public LatteDelegate setRootDelegate()
    {
        return new ExampleDelegate();
    }
}

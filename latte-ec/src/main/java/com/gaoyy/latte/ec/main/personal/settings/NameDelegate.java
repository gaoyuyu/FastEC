package com.gaoyy.latte.ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.gaoyy.latte.delegates.LatteDelegate;
import com.gaoyy.latte.ec.R;

/**
 * Created by gaoyy on 2018/1/24 0024.
 */

public class NameDelegate extends LatteDelegate
{
    @Override
    public Object setLayout()
    {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {

    }
}

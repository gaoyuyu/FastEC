package com.gaoyy.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.gaoyy.latte.app.Latte;
import com.gaoyy.latte.delegates.LatteDelegate;

/**
 * Created by gaoyy on 2018/1/13 0013.
 */

public class ExampleDelegate extends LatteDelegate
{
    @Override
    public Object setLayout()
    {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        Toast.makeText(Latte.getApplicationContext(),"exasdasdasd",Toast.LENGTH_SHORT).show();
    }


}

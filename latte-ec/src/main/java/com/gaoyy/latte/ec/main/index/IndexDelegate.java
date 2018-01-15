package com.gaoyy.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.gaoyy.latte.bottom.BottomItemDelegate;
import com.gaoyy.latte.ec.R;

/**
 * Created by gaoyy on 2018/1/15 0015.
 */

public class IndexDelegate extends BottomItemDelegate
{
    @Override
    public Object setLayout()
    {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {

    }
}

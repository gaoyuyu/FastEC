package com.gaoyy.latte.ui.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by gaoyy on 2018/1/17 0017.
 */

public class BaseDecoration extends DividerItemDecoration
{

    private BaseDecoration(@ColorInt int color, int size)
    {
        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static BaseDecoration create(@ColorInt int color, int size)
    {
        return new BaseDecoration(color, size);
    }
}

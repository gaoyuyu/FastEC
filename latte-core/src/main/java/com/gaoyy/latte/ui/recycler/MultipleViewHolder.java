package com.gaoyy.latte.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by gaoyy on 2018/1/17 0017.
 */

public class MultipleViewHolder extends BaseViewHolder
{
    private MultipleViewHolder(View view)
    {
        super(view);
    }

    public static MultipleViewHolder create(View view)
    {
        return new MultipleViewHolder(view);
    }

}

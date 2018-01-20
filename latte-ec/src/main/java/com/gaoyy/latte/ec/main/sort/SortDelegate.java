package com.gaoyy.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.gaoyy.latte.bottom.BottomItemDelegate;
import com.gaoyy.latte.ec.R;
import com.gaoyy.latte.ec.main.sort.content.ContentDelegate;
import com.gaoyy.latte.ec.main.sort.list.VerticalListDelegate;

/**
 * Created by gaoyy on 2018/1/15 0015.
 */

public class SortDelegate extends BottomItemDelegate
{
    @Override
    public Object setLayout()
    {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState)
    {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate verticalListDelegate = new VerticalListDelegate();
        loadRootFragment(R.id.vertical_list_container,verticalListDelegate);
        //默认显示第一个分类
        replaceLoadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1),false);
    }
}

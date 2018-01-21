package com.gaoyy.latte.ec.main;

import android.graphics.Color;

import com.gaoyy.latte.bottom.BaseBottomDelegate;
import com.gaoyy.latte.bottom.BottomItemDelegate;
import com.gaoyy.latte.bottom.BottomTabBean;
import com.gaoyy.latte.bottom.ItemBuilder;
import com.gaoyy.latte.ec.main.discover.DiscoverDelegate;
import com.gaoyy.latte.ec.main.index.IndexDelegate;
import com.gaoyy.latte.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by gaoyy on 2018/1/15 0015.
 */

public class EcBottomDelegate extends BaseBottomDelegate
{

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder)
    {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate()
    {
        return 0;
    }

    @Override
    public int setClickedColor()
    {
        return Color.parseColor("#ffff8800");
    }
}

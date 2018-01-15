package com.gaoyy.latte.bottom;

import java.util.LinkedHashMap;

/**
 * Created by gaoyy on 2018/1/15 0015.
 */

public final class ItemBuilder
{
    private LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    //静态工厂
    static ItemBuilder builder()
    {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean bean, BottomItemDelegate delegate)
    {
        ITEMS.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemDelegate> items)
    {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean, BottomItemDelegate> build()
    {
        return ITEMS;
    }


}

package com.gaoyy.latte.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.gaoyy.latte.R;
import com.gaoyy.latte.R2;
import com.gaoyy.latte.delegates.LatteDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by gaoyy on 2018/1/15 0015.
 */

public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener
{
    private ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    private ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();
    //使用LinkedHashMap保证有序，HashMap为无序
    private LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    //当前的ftem
    private int mCurrentDelegate = 0;
    //首次进入的第一个item
    private int mIndexDelegate = 0;
    //tab选中的颜色
    private int mClickedColor = Color.RED;

    /**
     * 设置数据
     *
     * @param builder
     * @return
     */
    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);


    /**
     * 设置初始进入的item
     *
     * @return
     */
    public abstract int setIndexDelegate();

    /**
     * 设置选中的颜色
     *
     * @return
     */
    @ColorInt
    public abstract int setClickedColor();


    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null;

    @Override
    public Object setLayout()
    {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mIndexDelegate = setIndexDelegate();
        if (setClickedColor() != 0)
        {
            mClickedColor = setClickedColor();
        }

        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);

        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS.entrySet())
        {
            final BottomTabBean bean = item.getKey();
            final BottomItemDelegate delegate = item.getValue();
            TAB_BEANS.add(bean);
            ITEM_DELEGATES.add(delegate);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++)
        {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);

            //设置每个item的点击事件
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            item.setTag(i);
            item.setOnClickListener(this);

            //初始化数据
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);

            final BottomTabBean bean = TAB_BEANS.get(i);
            itemIcon.setText(bean.getIcon());
            itemTitle.setText(bean.getTitle());

            //默认选中，设置点击后的颜色
            if (i == mIndexDelegate)
            {
                itemIcon.setTextColor(mClickedColor);
                itemTitle.setTextColor(mClickedColor);
            }

        }
        final SupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new SupportFragment[size]);
        loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);
    }


    private void resetColor()
    {
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++)
        {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemIcon.setTextColor(Color.GRAY);
            itemTitle.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View view)
    {
        final int tag = (int) view.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) view;
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemIcon.setTextColor(mClickedColor);
        itemTitle.setTextColor(mClickedColor);

        showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));
        mCurrentDelegate = tag;


    }
}





















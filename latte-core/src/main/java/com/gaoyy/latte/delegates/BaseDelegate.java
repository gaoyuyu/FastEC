package com.gaoyy.latte.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaoyy.latte.activities.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by gaoyy on 2018/1/14.
 */

public abstract class BaseDelegate extends SwipeBackFragment
{

    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder = null;

    public abstract Object setLayout();

    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View rootView;
        if (setLayout() instanceof Integer)
        {
            rootView = inflater.inflate((int) setLayout(), container, false);
        }
        else if (setLayout() instanceof View)
        {
            rootView = (View) setLayout();
        }
        else
        {
            throw new ClassCastException("type of setLayout() must be int or View!");
        }
        mUnbinder = ButterKnife.bind(this, rootView);
        onBindView(savedInstanceState, rootView);

        return rootView;

    }

    public final ProxyActivity getProxyActivity()
    {
        return (ProxyActivity) _mActivity;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        if (mUnbinder != null)
        {
            mUnbinder.unbind();
        }
    }
}

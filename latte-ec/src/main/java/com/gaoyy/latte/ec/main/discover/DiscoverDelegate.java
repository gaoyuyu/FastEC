package com.gaoyy.latte.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.gaoyy.latte.bottom.BottomItemDelegate;
import com.gaoyy.latte.delegates.web.WebDelegateImpl;
import com.gaoyy.latte.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by gaoyy on 2018/1/21 0021.
 */

public class DiscoverDelegate extends BottomItemDelegate
{
    @Override
    public Object setLayout()
    {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState)
    {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl delegate = WebDelegateImpl.create("index.html");
        delegate.setTopDelegate(this.getParentDelegate());
        loadRootFragment(R.id.web_discovery_container, delegate);
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator()
    {
        return new DefaultHorizontalAnimator();
    }
}

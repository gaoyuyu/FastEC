package com.gaoyy.latte.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.gaoyy.latte.R;
import com.gaoyy.latte.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by gaoyy on 2018/1/10 0010.
 */

public abstract class ProxyActivity extends SupportActivity
{
    public abstract LatteDelegate setRootDelegate();

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState)
    {
        super.onCreate(savedInstanceState, persistentState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState)
    {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);

        setContentView(container);
        if (savedInstanceState == null)
        {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}

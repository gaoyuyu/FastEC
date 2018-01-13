package com.gaoyy.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.gaoyy.latte.delegates.LatteDelegate;
import com.gaoyy.latte.ec.R;
import com.gaoyy.latte.ec.R2;
import com.gaoyy.latte.ui.launcher.ScrollLauncherTag;
import com.gaoyy.latte.util.storage.LattePreference;
import com.gaoyy.latte.util.timer.BaseTimerTask;
import com.gaoyy.latte.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by gaoyy on 2018/1/13 0013.
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener
{

    private Timer mTimer = null;
    private int mCount = 5;

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView()
    {
        if (mTimer != null)
        {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer()
    {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public Object setLayout()
    {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        initTimer();
    }

    /**
     * 判断是否显示滑动启动页
     */
    private void checkIsShowScroll()
    {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name()))
        {
            start(new LauncherScrollDelegate(), SINGLETASK);
        }
        else
        {
            //检查用户是否登录
        }
    }

    @Override
    public void onTimer()
    {
        getProxyActivity().runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                if (mTvTimer != null)
                {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0)
                    {
                        if (mTimer != null)
                        {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}

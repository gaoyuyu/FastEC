package com.gaoyy.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.gaoyy.latte.app.AccountManager;
import com.gaoyy.latte.app.IUserChecker;
import com.gaoyy.latte.delegates.LatteDelegate;
import com.gaoyy.latte.ec.R;
import com.gaoyy.latte.ui.launcher.ILauncherListener;
import com.gaoyy.latte.ui.launcher.LauncherHolderCreator;
import com.gaoyy.latte.ui.launcher.OnLauncherFinishTag;
import com.gaoyy.latte.ui.launcher.ScrollLauncherTag;
import com.gaoyy.latte.util.storage.LattePreference;

import java.util.ArrayList;

/**
 * Created by gaoyy on 2018/1/13 0013.
 */
public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener
{

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mILauncherListener = null;


    private void initBanner()
    {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner.setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_forcus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener)
        {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout()
    {
        mConvenientBanner = new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        initBanner();
    }

    @Override
    public void onItemClick(int position)
    {
        if (position == INTEGERS.size() - 1)
        {
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检查用户是否登录
            AccountManager.checkAccount(new IUserChecker()
            {
                @Override
                public void onSignIn()
                {
                    if (mILauncherListener != null)
                    {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn()
                {
                    if (mILauncherListener != null)
                    {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }
}

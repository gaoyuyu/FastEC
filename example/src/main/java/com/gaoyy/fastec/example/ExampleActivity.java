package com.gaoyy.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.gaoyy.latte.activities.ProxyActivity;
import com.gaoyy.latte.app.Latte;
import com.gaoyy.latte.delegates.LatteDelegate;
import com.gaoyy.latte.ec.launcher.LauncherDelegate;
import com.gaoyy.latte.ec.main.EcBottomDelegate;
import com.gaoyy.latte.ec.sign.ISignListener;
import com.gaoyy.latte.ec.sign.SignInDelegate;
import com.gaoyy.latte.ui.launcher.ILauncherListener;
import com.gaoyy.latte.ui.launcher.OnLauncherFinishTag;

import qiu.niorgai.StatusBarCompat;

public class ExampleActivity extends ProxyActivity implements
        ISignListener, ILauncherListener
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this,true);
    }

    @Override
    public LatteDelegate setRootDelegate()
    {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess()
    {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess()
    {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag)
    {
        switch (tag)
        {
            case SIGNED:
//                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
//                Toast.makeText(this, "启动结束，用户没有登录", Toast.LENGTH_LONG).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}

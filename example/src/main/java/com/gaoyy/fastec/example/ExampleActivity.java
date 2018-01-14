package com.gaoyy.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.gaoyy.latte.activities.ProxyActivity;
import com.gaoyy.latte.delegates.LatteDelegate;
import com.gaoyy.latte.ec.launcher.LauncherDelegate;
import com.gaoyy.latte.ec.sign.ISignListener;
import com.gaoyy.latte.ec.sign.SignInDelegate;
import com.gaoyy.latte.ui.launcher.ILauncherListener;
import com.gaoyy.latte.ui.launcher.OnLauncherFinishTag;

public class ExampleActivity extends ProxyActivity implements ISignListener, ILauncherListener
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.hide();
        }

    }

    @Override
    public LatteDelegate setRootDelegate()
    {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess()
    {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess()
    {
        Toast.makeText(this, "注册并登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag)
    {
        switch (tag)
        {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();
                start(new ExampleDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                start(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}

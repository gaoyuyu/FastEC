package com.gaoyy.latte.app;

import com.gaoyy.latte.util.storage.LattePreference;

/**
 * Created by gaoyy on 2018/1/14 0014.
 */

public class AccountManager
{
    private enum SignTag
    {
        SIGN_TAG
    }

    public static void setSignState(boolean state)
    {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    public static boolean isSignIn()
    {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker)
    {
        if (isSignIn())
        {
            checker.onSignIn();
        }
        else
        {
            checker.onNotSignIn();
        }
    }

}

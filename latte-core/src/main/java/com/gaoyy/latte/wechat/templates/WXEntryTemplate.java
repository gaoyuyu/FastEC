package com.gaoyy.latte.wechat.templates;


import com.gaoyy.latte.wechat.BaseWXEntryActivity;
import com.gaoyy.latte.wechat.LatteWeChat;

public class WXEntryTemplate extends BaseWXEntryActivity
{

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}

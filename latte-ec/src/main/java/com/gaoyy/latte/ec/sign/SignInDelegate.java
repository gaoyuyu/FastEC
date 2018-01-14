package com.gaoyy.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.gaoyy.latte.delegates.LatteDelegate;
import com.gaoyy.latte.ec.R;
import com.gaoyy.latte.ec.R2;
import com.gaoyy.latte.net.RestClient;
import com.gaoyy.latte.net.callback.ISuccess;
import com.gaoyy.latte.util.log.LatteLogger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 * Created by gaoyy on 2018/1/13 0013.
 */
public class SignInDelegate extends LatteDelegate
{

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        if (activity instanceof ISignListener)
        {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn()
    {
        if (checkForm())
        {
            RestClient.builder()
                    .url("http://192.168.1.101/RestServer/data/user_profile.json")
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess()
                    {
                        @Override
                        public void onSuccess(String response)
                        {
                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response, mISignListener);
                        }
                    })
                    .build()
                    .post();
        }
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat()
    {
//        LatteWeChat
//                .getInstance()
//                .onSignSuccess(new IWeChatSignInCallback()
//                {
//                    @Override
//                    public void onSignInSuccess(String userInfo)
//                    {
//                        Toast.makeText(getContext(), userInfo, Toast.LENGTH_LONG).show();
//                    }
//                })
//                .signIn();
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink()
    {
        getSupportDelegate().start(new SignUpDelegate());
    }

    private boolean checkForm()
    {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        }
        else
        {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6)
        {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        }
        else
        {
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout()
    {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView)
    {

    }
}

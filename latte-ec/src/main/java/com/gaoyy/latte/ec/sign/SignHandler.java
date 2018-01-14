package com.gaoyy.latte.ec.sign;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gaoyy.latte.app.AccountManager;
import com.gaoyy.latte.ec.database.DatabaseManager;
import com.gaoyy.latte.ec.database.UserProfile;

/**
 * Created by 傅令杰 on 2017/4/22
 */

public class SignHandler
{

    /**
     * 登录
     * @param response
     * @param signListener
     */
    public static void onSignIn(String response, ISignListener signListener)
    {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }


    /**
     * 注册
     * @param response
     * @param signListener
     */
    public static void onSignUp(String response, ISignListener signListener)
    {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }
}

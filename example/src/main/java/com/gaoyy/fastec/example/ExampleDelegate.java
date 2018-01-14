package com.gaoyy.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.gaoyy.latte.delegates.LatteDelegate;
import com.gaoyy.latte.net.RestClient;
import com.gaoyy.latte.net.callback.IError;
import com.gaoyy.latte.net.callback.IFailure;
import com.gaoyy.latte.net.callback.ISuccess;

/**
 * Created by gaoyy on 2018/1/14.
 */

public class ExampleDelegate extends LatteDelegate
{
    @Override
    public Object setLayout()
    {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {

        //testRestClient();

    }

    private void testRestClient()
    {
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess()
                {
                    @Override
                    public void onSuccess(String response)
                    {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure()
                {
                    @Override
                    public void onFailure()
                    {

                    }
                })
                .error(new IError()
                {
                    @Override
                    public void onError(int code, String msg)
                    {

                    }
                })
                .build()
                .get();
    }
}

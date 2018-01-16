package com.gaoyy.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;

import com.gaoyy.latte.app.Latte;
import com.gaoyy.latte.net.RestClient;
import com.gaoyy.latte.net.callback.ISuccess;

/**
 * Created by gaoyy on 2018/1/16 0016.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener
{
    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout refreshLayout)
    {
        this.REFRESH_LAYOUT = refreshLayout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }


    public void refresh()
    {
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    public void firstPage()
    {
        RestClient.builder()
                .url("http://192.168.1.101/RestServer/api/index.php")
                .success(new ISuccess()
                {
                    @Override
                    public void onSuccess(String response)
                    {

                    }
                })
                .build()
                .get();
    }


    @Override
    public void onRefresh()
    {
        refresh();
    }
}

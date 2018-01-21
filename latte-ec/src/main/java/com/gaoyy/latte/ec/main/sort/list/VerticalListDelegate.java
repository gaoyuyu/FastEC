package com.gaoyy.latte.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gaoyy.latte.delegates.LatteDelegate;
import com.gaoyy.latte.ec.R;
import com.gaoyy.latte.ec.R2;
import com.gaoyy.latte.ec.main.sort.SortDelegate;
import com.gaoyy.latte.net.RestClient;
import com.gaoyy.latte.net.callback.ISuccess;
import com.gaoyy.latte.ui.loader.LoaderStyle;
import com.gaoyy.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by gaoyy on 2018/1/20 0020.
 */

public class VerticalListDelegate extends LatteDelegate
{

    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout()
    {
        return R.layout.delegate_vertical_list;
    }

    private void initRecyclerView()
    {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState)
    {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("http://192.168.1.101/RestServer/api/sort_list.php")
                .loader(getContext(), LoaderStyle.BallGridPulseIndicator)
                .success(new ISuccess()
                {
                    @Override
                    public void onSuccess(String response)
                    {
                        final ArrayList<MultipleItemEntity> data = new VerticalListDataConverter().setJsonData(response).convert();

                        final SortDelegate delegate = getParentDelegate();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, delegate);

                        mRecyclerView.setAdapter(adapter);

                    }
                })
                .build()
                .get();
    }
}

package com.gaoyy.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.gaoyy.latte.app.Latte;
import com.gaoyy.latte.bottom.BottomItemDelegate;
import com.gaoyy.latte.ec.R;
import com.gaoyy.latte.ec.R2;
import com.gaoyy.latte.net.RestClient;
import com.gaoyy.latte.net.callback.ISuccess;
import com.gaoyy.latte.ui.recycler.MultipleFields;
import com.gaoyy.latte.ui.recycler.MultipleItemEntity;
import com.gaoyy.latte.ui.refresh.RefreshHandler;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by gaoyy on 2018/1/15 0015.
 */

public class IndexDelegate extends BottomItemDelegate
{


    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mRefreshHandler = null;

    private void initRefreshLayout()
    {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    @Override
    public Object setLayout()
    {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        mRefreshHandler = new RefreshHandler(mRefreshLayout);
        mRefreshHandler.firstPage();

        RestClient.builder()
                .url("http://192.168.1.101/RestServer/api/index.php")
                .success(new ISuccess()
                {
                    @Override
                    public void onSuccess(String response)
                    {
                        IndexDataConverter converter = new IndexDataConverter();
                        converter.setJsonData(response);

                        ArrayList<MultipleItemEntity> data = converter.convert();
                        String a = (String) data.get(1).getField(MultipleFields.IMAGE_URL);
                        Toast.makeText(Latte.getApplicationContext(),a,Toast.LENGTH_SHORT).show();

                    }
                })
                .build()
                .get();

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState)
    {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();

    }
}

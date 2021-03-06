package com.gaoyy.latte.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.gaoyy.latte.delegates.LatteDelegate;
import com.gaoyy.latte.ec.R;
import com.gaoyy.latte.ec.R2;
import com.gaoyy.latte.net.RestClient;
import com.gaoyy.latte.net.callback.ISuccess;
import com.gaoyy.latte.util.log.LatteLogger;

import java.util.List;

import butterknife.BindView;

/**
 * Created by gaoyy on 2018/1/20 0020.
 */

public class ContentDelegate extends LatteDelegate
{

    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    private List<SectionBean> mData = null;


    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView = null;

    public static ContentDelegate newInstance(int contentId)
    {
        LatteLogger.d("ContentDelegate newInstance");
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        LatteLogger.d("ContentDelegate onCreate");
        final Bundle args = getArguments();
        if (args != null)
        {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    @Override
    public Object setLayout()
    {
        return R.layout.delegate_list_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {

        final StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        initData();
    }

    private void initData()
    {
        RestClient.builder()
                .url("http://192.168.1.101/RestServer/api/sort_content_list.php?contentId=" + mContentId)
                .success(new ISuccess()
                {
                    @Override
                    public void onSuccess(String response)
                    {
                        mData = new SectionDataConverter().convert(response);
                        final SectionAdapter sectionAdapter =
                                new SectionAdapter(R.layout.item_section_content,
                                        R.layout.item_section_header, mData);
                        mRecyclerView.setAdapter(sectionAdapter);
                    }
                })
                .build()
                .get();
    }
}

package com.gaoyy.latte.net.download;

import android.os.AsyncTask;

import com.gaoyy.latte.net.RestCreator;
import com.gaoyy.latte.net.callback.IError;
import com.gaoyy.latte.net.callback.IFailure;
import com.gaoyy.latte.net.callback.IRequest;
import com.gaoyy.latte.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gaoyy on 2017/7/31.
 */

public class DownloadHandler
{

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;

    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    public DownloadHandler(String url,
                           IRequest iRequest,
                           String downloadDir,
                           String extension,
                           String name,
                           ISuccess iSuccess,
                           IFailure iFailure,
                           IError iError)
    {
        this.URL = url;
        this.REQUEST = iRequest;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = iSuccess;
        this.FAILURE = iFailure;
        this.ERROR = iError;
    }

    public final void handleDownload()
    {

        if (REQUEST != null)
        {
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>()
                {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
                    {

                        if (response.isSuccessful())
                        {

                            final ResponseBody responseBody = response.body();

                            final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, responseBody, NAME);

                            //这里一定要注意判断，否则文件下载不全
                            if (task.isCancelled())
                            {
                                if (REQUEST != null)
                                {
                                    REQUEST.onRequestEnd();
                                }
                            }

                        }
                        else
                        {
                            if (ERROR != null)
                            {
                                ERROR.onError(response.code(), response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t)
                    {

                        if (FAILURE != null)
                        {
                            FAILURE.onFailure();
                        }
                    }
                });

    }

}

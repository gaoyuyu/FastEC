package com.gaoyy.latte.ui.camera;

import android.net.Uri;

/**
 * 存储一些中间值
 * Created by gaoyy on 2018/1/24 0024.
 */

public class CameraImageBean
{
    private Uri mPath = null;

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance()
    {
        return INSTANCE;
    }

    public Uri getPath()
    {
        return mPath;
    }

    public void setPath(Uri mPath)
    {
        this.mPath = mPath;
    }
}

package com.gaoyy.latte.ui.camera;

import android.net.Uri;

import com.gaoyy.latte.delegates.PermissionCheckerDelegate;
import com.gaoyy.latte.util.file.FileUtil;

/**
 * 照片调用类
 * Created by gaoyy on 2018/1/24 0024.
 */

public class LatteCamera
{
    public static Uri createCropFile()
    {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate)
    {
        new CameraHandler(delegate).beginCameraDialog();
    }
}

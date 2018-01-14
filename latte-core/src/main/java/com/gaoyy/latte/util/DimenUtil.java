package com.gaoyy.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.gaoyy.latte.app.Latte;

/**
 * Created by gaoyy on 2017/7/31.
 */

public class DimenUtil {

    public static int getScreenWidth(){
        final Resources resources= Latte.getApplicationContext().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources= Latte.getApplicationContext().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return dm.heightPixels;
    }

}

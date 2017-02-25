package com.task.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.task.App;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.LOLLIPOP;

/**
 * Created by AhmedEltaher on 05/12/16.
 */

public class ResourcesUtil {
    private static Context context = App.getContext();
    private static Resources.Theme theme = App.getContext().getTheme();

    public static Drawable getDrawableById(int resId) {
        return SDK_INT >= LOLLIPOP ? context.getResources().getDrawable(resId, theme) :
            context.getResources().getDrawable(resId);
    }
    public static String getString(int resId) {
        return SDK_INT >= LOLLIPOP ? context.getResources().getString(resId) :
                context.getResources().getString(resId);
    }
}

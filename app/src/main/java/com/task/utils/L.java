package com.task.utils;

import com.orhanobut.logger.Logger;
import com.task.BuildConfig;

/**
 * Created by AhmedEltaher on 01/01/17.
 */

public class L {
    public static void d(String tag, String massage) {
        if (BuildConfig.DEBUG) {
            Logger.d(tag, massage);
        }
    }

    public static void i(String tag, String massage) {
        if (BuildConfig.DEBUG) {
            Logger.i(tag, massage);
        }
    }

    public static void v(String tag, String massage) {
        if (BuildConfig.DEBUG) {
            Logger.v(tag, massage);
        }
    }

    public static void e(String tag, String massage) {
        if (BuildConfig.DEBUG) {
            Logger.e(tag, massage);
        }
    }

    public static void json(String tag, String massage) {
        if (BuildConfig.DEBUG) {
            Logger.i(tag);
            Logger.json(massage);
        }
    }
}

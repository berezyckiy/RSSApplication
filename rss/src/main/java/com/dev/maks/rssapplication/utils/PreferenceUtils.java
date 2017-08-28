package com.dev.maks.rssapplication.utils;

import android.content.Context;

import com.orhanobut.hawk.Hawk;

import java.util.Date;

/**
 * Created by berezyckiy on 28.08.2017.
 */

public class PreferenceUtils {

    private static final String PREF_KEY_UPDATE_DATE = "updateDate";


    public static void saveUpdateDate(Context context) {
        Hawk.init(context).build();
        Hawk.put(PREF_KEY_UPDATE_DATE, new Date().toString());
    }

    public static String getUpdateDate(Context context) {
        Hawk.init(context).build();
        return Hawk.get(PREF_KEY_UPDATE_DATE, "Not found");
    }
}

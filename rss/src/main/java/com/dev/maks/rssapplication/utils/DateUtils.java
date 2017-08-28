package com.dev.maks.rssapplication.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Класс для работы с датами
 */

public class DateUtils {

    private static final String APPLICATION_TAG = "RssApplication";

    public static Date getParsedDate(String date) {
        Date tmpDate = null;
        try {
            tmpDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US).parse(date);
        } catch (ParseException e) {
            Log.e(APPLICATION_TAG, e.getMessage(), e);
        }
        return tmpDate;
    }
}

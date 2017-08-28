package com.dev.maks.rssapplication.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.dev.maks.rssapplication.MainActivity;
import com.dev.maks.rssapplication.R;
import com.dev.maks.rssapplication.utils.PreferenceUtils;

/**
 * Implementation of App Widget functionality.
 */
public class SimpleNewsWidget extends AppWidgetProvider {

    private static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                        int appWidgetId) {
        String dateUpdate = PreferenceUtils.getUpdateDate(context);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.simple_news_widget);
        Intent launchMain = new Intent(context, MainActivity.class);
        PendingIntent pendingMainIntent = PendingIntent.getActivity(context, 0, launchMain, 0);
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingMainIntent);
        views.setTextViewText(R.id.appwidget_text, dateUpdate);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}


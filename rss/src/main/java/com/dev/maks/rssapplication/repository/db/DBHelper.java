package com.dev.maks.rssapplication.repository.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Класс для инициализации базы данных
 */

class DBHelper extends SQLiteOpenHelper {

    private static final String DB_CREATE_LIVE_NEWS_TABLE = "create table liveNews" +
            "(id integer primary key autoincrement, title text, date real, content text);";
    private static final String DB_CREATE_ANALYTICS_NEWS_TABLE = "create table analyticsNews" +
            "(id integer primary key autoincrement, title text, date real, content text);";

    DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_LIVE_NEWS_TABLE);
        db.execSQL(DB_CREATE_ANALYTICS_NEWS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

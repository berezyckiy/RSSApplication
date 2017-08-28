package com.dev.maks.rssapplication.repository.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.dev.maks.rssapplication.model.Item;
import com.dev.maks.rssapplication.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Класс реализаущий интерфейс {@link NewsRepository}
 * Содержит свои методы:
 * <li>{@link #sortNewsListByDate(List)}</li> сортирует список новостей по датам
 * <li>{@link #deleteOddNews(String)}</li> если количество одной из таблиц превышает 100 записей,
 * метод удаляет лишние-старые записи
 */

public class DB implements NewsRepository {

    private static final String LIVE_NEWS_TABLE_NAME = "liveNews";
    private static final String ANALYTICS_NEWS_TABLE_NAME = "analyticsNews";
    private static final int MAX_COUNT = 100;

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    public void open(Context context) {
        dbHelper = new DBHelper(context, "myDB", null, 1);
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public void close() {
        if (dbHelper != null) {
            dbHelper.close();
        }
    }

    @Override
    public void addNews(Item news, String tableName) {
        Cursor cursor = database.query(tableName, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(cursor.getColumnIndex("title")).equals(news.getTitle())) {
                    return;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        ContentValues cv = new ContentValues();
        cv.put("title", news.getTitle());
        cv.put("date", news.getPubDate());
        cv.put("content", news.getDescription());
        database.insert(tableName, null, cv);
    }

    @Override
    public List<Item> getNews(String tableName) {
        deleteOddNews(LIVE_NEWS_TABLE_NAME);
        deleteOddNews(ANALYTICS_NEWS_TABLE_NAME);

        List<Item> liveNews = new ArrayList<>();
        Item news;
        Cursor cursor = database.query(tableName, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                news = new Item();
                news.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                news.setPubDate(cursor.getString(cursor.getColumnIndex("date")));
                news.setDescription(cursor.getString(cursor.getColumnIndex("content")));
                liveNews.add(news);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return sortNewsListByDate(liveNews);
    }

    private List<Item> sortNewsListByDate(List<Item> newsList) {
        List<Item> tmpList = new ArrayList<>();
        tmpList.addAll(newsList);
        Item news;

        for (int i = 0; i < newsList.size(); i++) {
            for (int k = i + 1; k < newsList.size(); k++) {

                Date firstDate = DateUtils.getParsedDate(tmpList.get(i).getPubDate());
                Date secondDate = DateUtils.getParsedDate(tmpList.get(k).getPubDate());

                if (firstDate != null && secondDate != null && firstDate.before(secondDate)) {
                    news = tmpList.get(i);
                    tmpList.set(i, tmpList.get(k));
                    tmpList.set(k, news);
                }

            }
        }

        return tmpList;
    }

    private void deleteOddNews(String tableName) {
        long count = DatabaseUtils.queryNumEntries(dbHelper.getReadableDatabase(), tableName);

        if (count > MAX_COUNT) {
            Cursor cursor = database.query(tableName, null, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    String rowId = cursor.getString(cursor.getColumnIndex("id"));
                    database.delete(tableName, "id" + "=?", new String[]{rowId});
                }
                while (cursor.moveToNext() && DatabaseUtils.queryNumEntries(dbHelper.
                        getReadableDatabase(), tableName) > MAX_COUNT);
            }
            cursor.close();
        }
    }
}

package com.dev.maks.rssapplication.repository.db;

import android.content.Context;

import com.dev.maks.rssapplication.model.Item;

import java.util.List;

/**
 * Интерфейс предоставляющий методы для работы с хранилищем
 */

public interface NewsRepository {

    void open(Context context);

    void close();

    void addNews(Item news, String tableName);

    List<Item> getNews(String tableName);
}

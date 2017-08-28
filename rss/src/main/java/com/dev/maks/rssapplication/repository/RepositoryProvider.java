package com.dev.maks.rssapplication.repository;


import com.dev.maks.rssapplication.repository.db.DB;
import com.dev.maks.rssapplication.repository.db.NewsRepository;

/**
 * Класс предоставляющий реализацию интерфейса {@link NewsRepository}
 */

public class RepositoryProvider {

    private static NewsRepository newsRepository;

    public static NewsRepository provideNewsRepository() {
        if (newsRepository == null) {
            newsRepository = new DB();
        }
        return newsRepository;
    }

    public static void init() {
        newsRepository = new DB();
    }
}

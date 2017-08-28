package com.dev.maks.rssapplication.connection;

import com.dev.maks.rssapplication.model.Rss;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Интерфейс для взаимойдествия с сервером, описывает методы:
 * <li>{@link #getNews(String)} для загрузки новостей с учётом переданного параметра</li>
 */

public interface SpotFxBrokerAPI {

    @GET("{newsType}")
    Call<Rss> getNews(@Path("newsType") String newsType);
}

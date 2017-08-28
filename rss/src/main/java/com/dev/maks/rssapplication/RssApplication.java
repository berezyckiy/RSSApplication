package com.dev.maks.rssapplication;

import android.app.Application;

import com.dev.maks.rssapplication.connection.SpotFxBrokerAPI;
import com.dev.maks.rssapplication.repository.RepositoryProvider;
import com.orhanobut.hawk.Hawk;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Класс, предоставляющий доступ к реализации интерфейса {@link SpotFxBrokerAPI}
 */

public class RssApplication extends Application {

    private static final String BASE_URL = "https://widgets.spotfxbroker.com:8088/";

    private SpotFxBrokerAPI spotFxBrokerAPI;

    @Override
    public void onCreate() {
        super.onCreate();

        RepositoryProvider.init();
        buildRequest();
    }

    public SpotFxBrokerAPI getAPI() {
        if (spotFxBrokerAPI == null) {
            buildRequest();
        }
        return spotFxBrokerAPI;
    }

    private void buildRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        spotFxBrokerAPI = retrofit.create(SpotFxBrokerAPI.class);
    }
}

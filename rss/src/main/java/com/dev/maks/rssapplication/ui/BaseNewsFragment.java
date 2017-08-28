package com.dev.maks.rssapplication.ui;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RemoteViews;

import com.dev.maks.rssapplication.R;
import com.dev.maks.rssapplication.RssApplication;
import com.dev.maks.rssapplication.model.Item;
import com.dev.maks.rssapplication.model.Rss;
import com.dev.maks.rssapplication.repository.RepositoryProvider;
import com.dev.maks.rssapplication.utils.PreferenceUtils;
import com.dev.maks.rssapplication.widget.SimpleNewsWidget;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Класс, описывающий базовый фрагмент отображающий новости, предоставляет методы с реализацией:
 * <li>{@link #downloadAndSaveNews(String, RecyclerAdapter, String)}</li> метод для загрузки данных
 * из интернета, и в случае успешного получения данных - их сохранения
 * <li>{@link #showContentDialog(Item)}</li> метод для отображения диалога с содержанием новости
 * <li>{@link #loadNews(RecyclerAdapter, String)}</li> метод загружающий данные из базы данных
 * в адаптер списка
 * Метод для реализации наследникам {@link #iniRecyclerNews()} для инициализации списка новостей
 * Класс-слушатель нажатия на элемент списка {@link OnItemClickListener} открывающий диалог
 */

public abstract class BaseNewsFragment extends Fragment {

    private static final String APPLICATION_TAG = "RssApplication";

    public void downloadAndSaveNews(String newsType, final RecyclerAdapter adapter, final String tableName) {
        ((RssApplication) getActivity().getApplication()).getAPI().getNews(newsType).enqueue(new Callback<Rss>() {
            @Override
            public void onResponse(Call<Rss> call, Response<Rss> response) {
                for (Item news : response.body().getChannel().getItems()) {
                    RepositoryProvider.provideNewsRepository().addNews(news, tableName);
                }
                saveDateAndRefreshWidget();
                loadNews(adapter, tableName);
            }

            @Override
            public void onFailure(Call<Rss> call, Throwable t) {
                Log.e(APPLICATION_TAG, t.getMessage());
                loadNews(adapter, tableName);
            }
        });
    }

    public void showContentDialog(Item news) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        NewsContentDialog contentDialog = NewsContentDialog.newInstance(news.getTitle(),
                news.getDescription());
        String dialogTag = "dialog";
        contentDialog.show(ft, dialogTag);
    }

    private void loadNews(RecyclerAdapter adapter, String tableName) {
        List<Item> newsList = RepositoryProvider.provideNewsRepository().getNews(tableName);
        adapter.setData(newsList);
    }

    private void saveDateAndRefreshWidget() {
        PreferenceUtils.saveUpdateDate(getContext());

        RemoteViews remoteViews = new RemoteViews(getContext().getPackageName(), R.layout.simple_news_widget);
        ComponentName thisWidget = new ComponentName( getContext(), SimpleNewsWidget.class );
        remoteViews.setTextViewText(R.id.appwidget_text, PreferenceUtils.getUpdateDate(getContext()));
        AppWidgetManager.getInstance( getContext()).updateAppWidget( thisWidget, remoteViews );
    }

    public abstract void iniRecyclerNews();

    class OnItemClickListener implements OnNewsClickListener {

        @Override
        public void onItemClick(Item news) {
            showContentDialog(news);
        }
    }
}

package com.dev.maks.rssapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.maks.rssapplication.R;

/**
 * Класс, представляющий фрагмент Forex Analytics News
 */

public class AnalyticsNewsFragment extends BaseNewsFragment {

    private static final String ANALYTICS_NEWS_TYPE = "GetAnalyticsRss";
    private static final String ANALYTICS_NEWS_TABLE_NAME = "analyticsNews";

    private RecyclerAdapter analyticsNewsAdapter;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_analytics_news, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        iniRecyclerNews();
        downloadAndSaveNews(ANALYTICS_NEWS_TYPE, analyticsNewsAdapter, ANALYTICS_NEWS_TABLE_NAME);
    }

    @Override
    public void iniRecyclerNews() {
        RecyclerView analyticsNewsRecycler = (RecyclerView) rootView.findViewById(R.id.analyticsNewsRecycler);

        analyticsNewsAdapter = new RecyclerAdapter(new OnItemClickListener());

        analyticsNewsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        analyticsNewsRecycler.setAdapter(analyticsNewsAdapter);
    }
}

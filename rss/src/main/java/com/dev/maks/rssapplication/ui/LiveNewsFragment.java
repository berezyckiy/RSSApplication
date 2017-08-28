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
 * Класс, представляющий фрагмент Forex Live News
 */

public class LiveNewsFragment extends BaseNewsFragment {

    private static final String LIVE_NEWS_TYPE = "GetLiveNewsRss";
    private static final String LIVE_NEWS_TABLE_NAME = "liveNews";

    private RecyclerAdapter liveNewsAdapter;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_live_news, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        iniRecyclerNews();
        downloadAndSaveNews(LIVE_NEWS_TYPE, liveNewsAdapter, LIVE_NEWS_TABLE_NAME);
    }

    @Override
    public void iniRecyclerNews() {
        RecyclerView liveNewsRecycler = (RecyclerView) rootView.findViewById(R.id.liveNewsRecycler);

        liveNewsAdapter = new RecyclerAdapter(new OnItemClickListener());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        liveNewsRecycler.setLayoutManager(layoutManager);
        liveNewsRecycler.setAdapter(liveNewsAdapter);
    }
}

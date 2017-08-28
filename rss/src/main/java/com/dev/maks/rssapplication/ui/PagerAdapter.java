package com.dev.maks.rssapplication.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Класс, представляющий адаптер для вкладок Live News и Analytics News
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    private static final int LIVE_NEWS_TAB = 0;
    private static final int ANALYTICS_NEWS_TAB = 1;

    private int tabsCount;

    public PagerAdapter(FragmentManager fragmentManager, int tabsCount) {
        super(fragmentManager);
        this.tabsCount = tabsCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case LIVE_NEWS_TAB:
                return new LiveNewsFragment();
            case ANALYTICS_NEWS_TAB:
                return new AnalyticsNewsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabsCount;
    }
}
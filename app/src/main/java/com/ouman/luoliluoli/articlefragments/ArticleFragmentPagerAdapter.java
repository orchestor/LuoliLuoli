package com.ouman.luoliluoli.articlefragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by jfg on 17-1-4.
 */

public class ArticleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "新鲜事", "美日美文", "段子手" , "一句话电影"};
    private Context context;

    public ArticleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return DailyFeedsFragment.newInstance(position);

            case 1:
                return NiceArticleFragment.newInstance(position);
            case 2:
                return DuanziShouFragment.newInstance(position);
            case 3:
                return SingleWordFragment.newInstance(position);
            default:
                return null;
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}

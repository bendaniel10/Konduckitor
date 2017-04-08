package com.bentechapps.konduckitor.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bentech.android.appcommons.AppCommons;
import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.fragments.NonPowerUpShopFragment;
import com.bentechapps.konduckitor.activity.fragments.ShopItemFragment;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bendaniel on 25/02/2017.
 */
public class StoreViewPagerAdapter extends FragmentPagerAdapter {


    private final List<Fragment> fragments;
    private final List<String> titles;

    public StoreViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragments = Arrays.asList(new ShopItemFragment(), new NonPowerUpShopFragment());

        Context context = AppCommons.getApplication();
        this.titles = Arrays.asList(context.getString(R.string.power_ups), context.getString(R.string.items));
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}

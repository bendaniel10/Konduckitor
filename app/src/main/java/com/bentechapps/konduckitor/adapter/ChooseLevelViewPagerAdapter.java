package com.bentechapps.konduckitor.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bentechapps.konduckitor.activity.fragments.ChooseMissionFragment;
import com.bentechapps.konduckitor.model.level.Level;
import com.bentechapps.konduckitor.model.level.LevelFactory;

import java.util.List;

/**
 * Created by bendaniel on 12/11/2016.
 */

public class ChooseLevelViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Level> levelsList;

    public ChooseLevelViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.levelsList = LevelFactory.listLevels();
    }

    @Override
    public Fragment getItem(int position) {
        return ChooseMissionFragment.newInstance(levelsList.get(position));
    }

    @Override
    public int getCount() {
        return levelsList.size();
    }
}

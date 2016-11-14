package com.bentechapps.konduckitor.activity.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bentech.android.appcommons.fragment.AppCommonsFragment;
import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.adapter.ChooseLevelViewPagerAdapter;
import com.bentechapps.konduckitor.model.level.Level;
import com.bentechapps.konduckitor.view.custom.HeaderControls;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnPageChange;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseLevelFragment extends AppCommonsFragment {


    @BindView(R.id.levelsViewPager)
    ViewPager levelsViewPager;
    @BindView(R.id.header_controls)
    HeaderControls headerControls;
    private ChooseLevelViewPagerAdapter chooseLevelViewPagerAdapter;

    public ChooseLevelFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_choose_level, container, false);
        ButterKnife.bind(this, rootView);

        levelsViewPager.setAdapter(chooseLevelViewPagerAdapter = new ChooseLevelViewPagerAdapter(getChildFragmentManager()));
        onViewPagerPageChange(0);

        return rootView;
    }

    @OnPageChange(R.id.levelsViewPager)
    void onViewPagerPageChange(int onPageSelected) {

        Level level = chooseLevelViewPagerAdapter.getLevel(onPageSelected);

        headerControls.setTitle(level.getName());
        headerControls.setSubTitle(level.getDescription());
    }

}

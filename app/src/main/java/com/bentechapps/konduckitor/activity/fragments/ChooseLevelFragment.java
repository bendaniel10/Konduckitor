package com.bentechapps.konduckitor.activity.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bentech.android.appcommons.fragment.AppCommonsFragment;
import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.MainActivity;
import com.bentechapps.konduckitor.adapter.ChooseLevelViewPagerAdapter;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.level.Level;
import com.bentechapps.konduckitor.view.animation.AnimationFactory;
import com.bentechapps.konduckitor.view.custom.HeaderControls;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseLevelFragment extends AppCommonsFragment {


    @BindView(R.id.levelsViewPager)
    ViewPager levelsViewPager;
    @BindView(R.id.header_controls)
    HeaderControls headerControls;

    public ChooseLevelFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_choose_level, container, false);
        ButterKnife.bind(this, rootView);

        levelsViewPager.setAdapter(new ChooseLevelViewPagerAdapter(getChildFragmentManager()));

        return rootView;
    }


}

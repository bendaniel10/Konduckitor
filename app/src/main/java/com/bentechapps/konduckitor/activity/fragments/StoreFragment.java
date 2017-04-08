package com.bentechapps.konduckitor.activity.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bentech.android.appcommons.fragment.AppCommonsFragment;
import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.adapter.StoreViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends AppCommonsFragment {

    @BindView(R.id.storeViewPager)
    ViewPager storeViewPager;
    @BindView(R.id.storeTabLayout)
    TabLayout storeTabLayout;

    public StoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_store, container, false);
        ButterKnife.bind(this, rootView);

        storeTabLayout.setupWithViewPager(storeViewPager);
        storeViewPager.setAdapter(new StoreViewPagerAdapter(getChildFragmentManager()));

        return rootView;
    }


}

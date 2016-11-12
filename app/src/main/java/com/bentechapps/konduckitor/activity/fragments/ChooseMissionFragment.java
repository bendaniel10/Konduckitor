package com.bentechapps.konduckitor.activity.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.bentech.android.appcommons.fragment.AppCommonsFragment;
import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.level.Level;
import com.bentechapps.konduckitor.view.adapter.ChooseMissionAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseMissionFragment extends AppCommonsFragment {


    private static final String LEVEL_OBJECT = "LEVEL_OBJECT";
    @BindView(R.id.gridview)
    GridView gridview;
    private Level level;

    public ChooseMissionFragment() {
        // Required empty public constructor
    }


    public static ChooseMissionFragment newInstance(Level level) {

        Bundle args = new Bundle();
        args.putSerializable(LEVEL_OBJECT, level);

        ChooseMissionFragment fragment = new ChooseMissionFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_choose_mission, container, false);
        ButterKnife.bind(this, rootView);

        Level level = (Level) getArguments().getSerializable(LEVEL_OBJECT);
        gridview.setAdapter(new ChooseMissionAdapter(getActivity(), level));

        return rootView;
    }

}

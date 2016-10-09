package com.bentechapps.konduckitor.activity.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.model.level.Level;
import com.bentechapps.konduckitor.view.adapter.ChooseMissionAdapter;
import com.bentechapps.konduckitor.view.custom.HeaderControls;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseMissionFragment extends Fragment {


    private GridView gridview;
    private Level level;

    public ChooseMissionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_mission, container, false);
    }

    public ChooseMissionFragment setLevel(Level level) {
        this.level = level;
        return this;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((HeaderControls)getView().findViewById(R.id.header_controls)).setFragmentManager(getFragmentManager()).setTitle(level.getName());
        gridview = (GridView) getView().findViewById(R.id.gridview);
        gridview.setAdapter(new ChooseMissionAdapter(getActivity(), level));
    }
}

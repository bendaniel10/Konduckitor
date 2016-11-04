package com.bentechapps.konduckitor.activity.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bentechapps.konduckitor.R;
import com.bentechapps.konduckitor.activity.MainActivity;
import com.bentechapps.konduckitor.data.ApplicationData;
import com.bentechapps.konduckitor.model.level.Level;
import com.bentechapps.konduckitor.view.adapter.ChooseLevelAdapter;
import com.bentechapps.konduckitor.view.animation.AnimationFactory;
import com.bentechapps.konduckitor.view.custom.HeaderControls;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseLevelFragment extends ListFragment {


    public ChooseLevelFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_choose_level, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((HeaderControls)getView().findViewById(R.id.header_controls)).setFragmentManager(getFragmentManager()).setTitle("Choose Level");
        setListAdapter(new ChooseLevelAdapter(getActivity()));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Level level = (Level) getListAdapter().getItem(position);
        if(ApplicationData.getInstance(getActivity()).getCurrentLevel() < level.getLevel()) {
                v.findViewById(R.id.locked_image).startAnimation(AnimationFactory.newWobbleAnimation());
        } else {
            ((MainActivity) getActivity()).switchFragmentsAddToBackStack(R.id.fragment_container, new ChooseMissionFragment().setLevel(level));
        }
    }
}
